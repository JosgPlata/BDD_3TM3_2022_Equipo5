USE Sales;
GO
-- Procedimiento almacenado para la orden en SalesOrderHeader
CREATE PROCEDURE NuevaVenta
@OrderDate DATE=NULL,
@DueDate DATE=NULL,
@ShipDate DATE=NULL,
@Status TINYINT=1,
@OnlineOrderFlag bit=1,
@PurchaseOrderNumber nvarchar(25)=NULL,
@AccountNumber nvarchar(15)=NULL,
@CustomerID int, --Valor obligatorio
@SalesPersonID int=NULL,
@TerritoryID int=NULL,
@BillToAddressID int, --Valor obligatorio
@ShipToAddressID int, --Valor obligatorio
@ShipMethodID int, --Valor obligatorio
@CreditCardID int=NULL,
@CreditCardApprovalCode varchar(15)=NULL,
@CurrencyRateID int=NULL,
@TaxAmt money=0.00,
@Freight money=0.00, 
@Comment nvarchar(128)=NULL,
@OrderID int output --ID de la orden para los registros de Detail.
AS BEGIN
--Obtenemos la fecha de la venta
IF(@OrderDate IS NULL)
SET @OrderDate = GETDATE();
-- Si no se especifica la fecha de entrega tomamos el día de la venta
IF(@DueDate IS NULL)
SET @DueDate = @OrderDate;

DECLARE @TOrderID table(OrderID int);

--Inserción de la orden en OrderHeader
Insert Sales.SalesOrderHeader(
[RevisionNumber],[OrderDate],[DueDate]
,[ShipDate],[Status],[OnlineOrderFlag]
,[PurchaseOrderNumber],[AccountNumber]
,[CustomerID],[SalesPersonID],[TerritoryID]
,[BillToAddressID],[ShipToAddressID]
,[ShipMethodID],[CreditCardID]
,[CreditCardApprovalCode],[CurrencyRateID]
,[SubTotal],[TaxAmt],[Freight]
,[Comment],[rowguid],[ModifiedDate])
-- Obtenemos el id de la orden en una tabla
OUTPUT inserted.SalesOrderID 
INTO @TOrderID
VALUES(
DEFAULT,@OrderDate,@DueDate,
@ShipDate,@Status,@OnlineOrderFlag,
@PurchaseOrderNumber,@AccountNumber,
@CustomerID,@SalesPersonID,@TerritoryID,
@BillToAddressID,@ShipToAddressID,
@ShipMethodID,@CreditCardID,
@CreditCardApprovalCode,@CurrencyRateID,
DEFAULT,@TaxAmt,@Freight,
@Comment,DEFAULT,DEFAULT);
-- Retornamos el ID de la orden para posteriormente agregar los detalles de la misma
Select @OrderID=orderID FROM @TOrderID;
return @OrderID;
END
GO

--Registro de prueba
/*
DECLARE @OrdenID int;

EXEC NuevaVenta 
@OrderDate = DEFAULT,
@DueDate = DEFAULT,
@ShipDate = DEFAULT,
@Status = DEFAULT,
@OnlineOrderFlag = DEFAULT,
@PurchaseOrderNumber = DEFAULT,
@AccountNumber = DEFAULT,
@CustomerID = 1,--Obligatorio
@SalesPersonID = DEFAULT,
@TerritoryID = DEFAULT,
@BillToAddressID = 1,--Obligatorio
@ShipToAddressID = 1,--Obligatorio
@ShipMethodID = 1,--Obligatorio
@CreditCardID = DEFAULT,
@CreditCardApprovalCode = DEFAULT,
@CurrencyRateID = DEFAULT,
@TaxAmt = DEFAULT,
@Freight = DEFAULT,
@Comment = DEFAULT,
@OrderID = @OrdenID OUTPUT;

SELECT @OrdenID;
GO
*/

-- Verifico si se hizo el registro y lo borro
/*

SELECT * FROM Sales.SalesOrderHeader ORDER BY SalesOrderID desc;
DELETE FROM Sales.SalesOrderHeader WHERE SalesOrderID>=75124

DBCC CHECKIDENT ('Sales.SalesOrderHeader', RESEED, 75123);

*/
