use sales;
GO
--Procedimiento para detalles de la orden
Create PROCEDURE InsercionSalesOrderDetail 
@SalesOrderID int,
@CarrierTrackingNumber nvarchar(25),
@OrderQty int,
@ProductID int
AS
BEGIN
	DECLARE @Rowguid uniqueidentifier = NEWID();
	DECLARE @ModifiedDate datetime = getdate();
	-- Existencia de la orden
	IF(NOT EXISTS(
	SELECT 1 FROM Sales.SalesOrderHeader 
	WHERE SalesOrderID=@SalesOrderID))BEGIN
	RAISERROR('No existe una orden con id %i', 16, 2, @salesOrderID);
	return -1;
	END
	-- Existencia de producto y su precio
	DECLARE @ListPrice INT;
	IF(EXISTS(
		SELECT 1 FROM 
		OPENQUERY(MYSQLREMOTE,'SELECT * from Production.Product')
		WHERE ProductID=@ProductID)) BEGIN
	-- Existecia de la cantidad suficiente
		IF(EXISTS(
			SELECT  1
			FROM OPENQUERY(MYSQLREMOTE,
			'SELECT A.ProductID, sum(Quantity) AS Cantidad
			FROM Production.Product A 
			LEFT JOIN Production.productInventory B
			ON A.ProductID=B.ProductID
			GROUP BY ProductID')
			WHERE ProductID=@ProductID AND (Cantidad<@OrderQty OR Cantidad IS NULL)))BEGIN
		RAISERROR('No existen suficientes existencias para el producto con ID %d',16,2,@ProductID);
		return -1;
		END
		--Precio recomendado de venta
		SET @ListPrice=(SELECT ListPrice FROM
		OPENQUERY(MYSQLREMOTE,'SELECT ListPrice,ProductID from Production.Product')
		WHERE ProductID=@ProductID);
	END
	ELSE BEGIN
		RAISERROR('No existe el producto con ID %d', 16, 2,@ProductID);
		return -1;
	END
	--EXistencia de ofertas
		DECLARE @SpecialOfferID int;
		DECLARE @DiscountPct smallmoney;
		DECLARE @FechaOrden datetime =(Select OrderDate FROM Sales.SalesOrderHeader
				WHERE SalesOrderID=@SalesOrderID);
	IF(EXISTS(
		SELECT 1 FROM Sales.SpecialOfferProduct
		WHERE ProductID=@ProductID))
		BEGIN
	-- Variables para calculos
		DECLARE @Type nvarchar(50);
		DECLARE @StartDate datetime;
		DECLARE @EndDate datetime;
		DECLARE @MinQty int;
		DECLARE @MaxQty int;
	-- Construyendo el cursor para recorrer la lista
		DECLARE cursorOfertas CURSOR FOR 
		SELECT SpecialOfferID,DiscountPct,Type,StartDate,EndDate,MinQty,MaxQty
		FROM ( -- Ofertas del producto 
		SELECT SO.* FROM Sales.SpecialOffer SO 
		INNER JOIN Sales.SpecialOfferProduct SOP
		ON SO.SpecialOfferID=SOP.SpecialOfferID
		AND ProductID=@ProductID) SQ
		OPEN cursorOfertas;
		-- Obteniendo datos
		FETCH NEXT FROM cursorOfertas 
		INTO @SpecialOfferID,@DiscountPct,@Type,@StartDate,@EndDate,@MinQty,@MaxQty;
		-- Bucle mientras se pueda seguir leyendo la lista
		WHILE @@FETCH_STATUS=0
		BEGIN
			--Comprobamos que tenga fecha valida la oferta
			IF(@SpecialOfferID!=1 AND @FechaOrden BETWEEN @StartDate AND @EndDate) 
			BEGIN
				--Si es del tipo volume discount verificamos la cantidad esté dentro del rango
				IF(@Type='Volume Discount') 
				BEGIN
					IF(@OrderQty BETWEEN @MinQty AND @MaxQty)
						BREAK;
					END 
				-- Si no es del tipo Volume Discount basta con haber tenido fecha valida y tomamos el OfferID
				ELSE
				BREAK;
			END;
			FETCH NEXT FROM cursorOfertas 
			INTO @SpecialOfferID,@DiscountPct,@Type,@StartDate,@EndDate,@MinQty,@MaxQty;
			--Si terminamos de recorrer la lista de ofertas del producto y ninguna es valida tomamos el OfferID=1
			IF(@@FETCH_STATUS!=0)
			BEGIN
				SET @SpecialOfferID=1;
				SET @DiscountPct=0;
			END
		END
		CLOSE cursorOfertas;
		DEALLOCATE cursorOfertas;
	END
	ELSE BEGIN
		-- Si el producto no tiene registro en SpecialOffferProduct hacemos un registro
		-- con SpecialOfferID=1 (Sin descuento). Esto debido a la restricción de las 
		-- llaves foraneas (SpecialOfferID,ProductID) en OrderDetail.
		DECLARE @myid uniqueidentifier = NEWID();
		INSERT INTO Sales.SpecialOfferProduct VALUES(1,@ProductID,@myid,@ModifiedDate);
		SET @SpecialOfferID=1;
		SET @DiscountPct=0;
	END
	-- Calculando Precios
	DECLARE @UnitPrice money=0.6*@ListPrice
	-- Llevando a cabo el registro en SalesOrderDetail:
	DECLARE @SalesOrderDetailID int = (SELECT isnull(MAX(SalesOrderDetailID)+1,1)
								FROM Sales.SalesOrderDetail
								WHERE SalesOrderID=@SalesOrderID);
	SET IDENTITY_INSERT Sales.SalesOrderDetail ON
	INSERT INTO Sales.SalesOrderDetail
	(SalesOrderID,SalesOrderDetailID,CarrierTrackingNumber,OrderQty,
	ProductID,SpecialOfferID,UnitPrice,UnitPriceDiscount,
	Rowguid,ModifiedDate)
	VALUES 
	(@SalesOrderID,@SalesOrderDetailID,@CarrierTrackingNumber,@OrderQty,
	@ProductID,@SpecialOfferID,@UnitPrice,@DiscountPct,
	@Rowguid,@ModifiedDate)
	SET IDENTITY_INSERT Sales.SalesOrderDetail OFF
END
GO

-- Registro de prueba
/*
/* 
Parametros: 
@SalesOrderID,
@CarrierTrackingNumber,
@OrderQty,@ProductID,
*/
EXEC InsercionSalesOrderDetail 75124,NULL,10,1000
GO
*/
-- CONSULTAS DE APOYO
/*
SELECT * FROM Sales.SalesOrderHeader
WHERE SalesOrderID=75124;
GO
SELECT * FROM Sales.SalesOrderDetail
WHERE SalesOrderID=75124;
GO

SELECT * FROM OPENQUERY(MYSQLREMOTE,'SELECT * FROM Production.ProductInventory ');
*/