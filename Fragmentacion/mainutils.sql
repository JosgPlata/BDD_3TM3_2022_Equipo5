Use AdventureWorks2019
go
/*Procedimiento almacenado con la solución 1*/

CREATE PROCEDURE Q1
(@Reg int)
AS
BEGIN;
	SELECT CustomerID,PersonID,StoreID,StoreID,C.TerritoryID,[Group]
	FROM F_LOCAL.AdventureWorks2019.Sales.Customer C 
	Inner Join F_LOCAL.AdventureWorks2019.Sales.SalesTerritory ST 
	ON C.TerritoryID=ST.TerritoryID
	WHERE [Group]=case @Reg
	WHEN 1 then 'North America'
	When 2 then 'Pacific'
	When 3 then 'Europe'
end;
END
GO
/*Procedimiento almacenado con la solución 2 alternativa 1*/
CREATE PROCEDURE Q2_1
AS
BEGIN;
	(SELECT MOET.*,SP.* 
		FROM NorteAmerica.AdventureWorks2019.Sales.SalesPerson SP
		INNER JOIN NorteAmerica.AdventureWorks2019.dbo.maxOrdenesXEmpleadoXTerritorio MOET
		ON SP.BusinessEntityID=MOET.SalesPersonID)union
	(SELECT MOET.*,SP.* 
		FROM EUROPA.AdventureWorks2019.Sales.SalesPerson SP
		INNER JOIN EUROPA.AdventureWorks2019.dbo.maxOrdenesXEmpleadoXTerritorio MOET
		ON SP.BusinessEntityID=MOET.SalesPersonID)union
	(SELECT MOET.*,SP.* 
		FROM PACIFICO.AdventureWorks2019.Sales.SalesPerson SP
		INNER JOIN PACIFICO.AdventureWorks2019.dbo.maxOrdenesXEmpleadoXTerritorio MOET
		ON SP.BusinessEntityID=MOET.SalesPersonID)
END;
GO
/*Procedimiento almacenado con la solución 2 alternativa 2*/
CREATE PROCEDURE Q2_2
AS
BEGIN
	(SELECT MOET.*,EMP.* 
		FROM OpenQuery(F_LOCAL,'select * from AdventureWorks2019.HumanResources.Employee') EMP
		INNER JOIN NorteAmerica.AdventureWorks2019.dbo.maxOrdenesXEmpleadoXTerritorio MOET
		ON EMP.BusinessEntityID=MOET.SalesPersonID)
	UNION 
	SELECT MOET.*,EMP.* 
	FROM OpenQuery(F_LOCAL,'select * from AdventureWorks2019.HumanResources.Employee') EMP
	INNER JOIN EUROPA.AdventureWorks2019.dbo.maxOrdenesXEmpleadoXTerritorio MOET
	ON EMP.BusinessEntityID=MOET.SalesPersonID
	UNION 
	SELECT MOET.*,EMP.* 
	FROM OpenQuery(F_LOCAL,'select * from AdventureWorks2019.HumanResources.Employee') EMP
	INNER JOIN PACIFICO.AdventureWorks2019.dbo.maxOrdenesXEmpleadoXTerritorio MOET
	ON EMP.BusinessEntityID=MOET.SalesPersonID
END
GO
/*Procedimiento almacenado con la solución 3*/
CREATE PROCEDURE Q3
AS
BEGIN;
	SELECT C.CustomerID,C.PersonID,Prs.BusinessEntityID,
	Prs.PersonType,Prs.FirstName,Prs.LastName,CMCNA.totalOrdenes
	FROM OPENQUERY(F_LOCAL,
		'SELECT BusinessEntityID,PersonType,FirstName,LastName
		FROM AdventureWorks2019.Person.Person') Prs
	INNER JOIN F_LOCAL.AdventureWorks2019.Sales.Customer C
	ON C.PersonID=Prs.BusinessEntityID
	INNER JOIN NorteAmerica.AdventureWorks2019.dbo.ClientesMasComprasNA CMCNA
	ON CMCNA.CustomerID=C.CustomerID;
END
GO
/*Procedimiento almacenado con la solución 4*/
CREATE PROCEDURE Q4
AS
BEGIN;
	SELECT * from Europa.AdventureWorks2019.dbo.MasSolicitadoPR;
END
GO

/*Procedimiento almacenado con la solución 5 opcion 1*/
-- Productos con sus ofertas que sean de la categoría bikes
CREATE PROCEDURE Q5_1
AS
BEGIN;
	SELECT * FROM F_Local.AdventureWorks2019.Sales.SpecialOfferProduct 
	WHERE ProductID IN (
		SELECT ProductID FROM (
			SELECT P.ProductID,PSC.NAME Subcategoria
			FROM F_Local.AdventureWorks2019.Production.Product P
			INNER JOIN F_Local.AdventureWorks2019.Production.ProductSubcategory PSC
			ON P.ProductSubcategoryID=PSC.ProductSubcategoryID
			INNER JOIN F_Local.AdventureWorks2019.Production.ProductCategory PC
			ON PC.ProductCategoryID=PSC.ProductCategoryID
			AND PC.NAME LIKE 'Bikes') Q1
	)
END
GO
/*Procedimiento almacenado con la solución 5 opcion 1*/
-- Ofertas que tengan los productos de categoría bikes
CREATE PROCEDURE Q5_2
AS
BEGIN;
	SELECT SO.SpecialOfferID FROM F_Local.AdventureWorks2019.Sales.SpecialOfferProduct SOP
	INNER JOIN
	F_Local.AdventureWorks2019.Sales.SpecialOffer SO ON SO.SpecialOfferID=SOP.SpecialOfferID
	WHERE ProductID IN (
		SELECT ProductID FROM (SELECT P.ProductID,PSC.NAME Subcategoria
			FROM F_Local.AdventureWorks2019.Production.Product P
			INNER JOIN F_Local.AdventureWorks2019.Production.ProductSubcategory PSC
			ON P.ProductSubcategoryID=PSC.ProductSubcategoryID
			INNER JOIN F_Local.AdventureWorks2019.Production.ProductCategory PC
			ON PC.ProductCategoryID=PSC.ProductCategoryID
			AND PC.NAME LIKE 'Bikes')q1) 
	GROUP BY So.SpecialOfferID
END
GO

/*Procedimiento almacenado con la solución 6*/
CREATE PROCEDURE Q6
AS
BEGIN;
	SELECT * from Pacifico.AdventureWorks2019.dbo.MenosSolicitadoPR_T3;
END
GO
/*Procedimiento almacenado con la solución 7*/
CREATE PROCEDURE Q7
(@Nsub int)
AS
BEGIN;
	UPDATE F_Local.AdventureWorks2019.Production.Product set ProductSubcategoryID=@Nsub WHERE ProductID BETWEEN 1 AND 4
END;
GO

/*Procedimiento almacenado con la solución 8*/
CREATE PROCEDURE Q8
AS
BEGIN;
	DECLARE @fecha date = getdate();
	SELECT * FROM F_local.AdventureWorks2019.Production.Product
	WHERE NOT(@fecha>=SellStartDate
		AND @fecha<SellEndDate)
	AND SellEndDate IS NOT NULL;
END
GO
/*Procedimiento almacenado con la solución 9*/
CREATE PROCEDURE Q9
AS
BEGIN;
SELECT * FROM F_local.AdventureWorks2019.Sales.Customer WHERE TerritoryID IN(1,4) AND PersonID IS NULL;
END
GO
/*Procedimiento almacenado con la solución 10*/
CREATE PROCEDURE Q10
AS
BEGIN;
(SELECT C.CustomerID,C.TerritoryID TerritorioCliente,
SOH.SalesOrderID,SOH.TerritoryID TerritorioVenta
FROM NorteAmerica.AdventureWorks2019.Sales.SalesOrderHeader SOH
INNER JOIN F_local.AdventureWorks2019.Sales.Customer C 
ON SOH.CustomerID=C.CustomerID 
AND C.TerritoryID=1 AND SOH.TerritoryID!=C.TerritoryID)
UNION 
(SELECT C.CustomerID,C.TerritoryID TerritorioCliente,
SOH.SalesOrderID,SOH.TerritoryID TerritorioVenta
FROM Europa.AdventureWorks2019.Sales.SalesOrderHeader SOH
INNER JOIN F_local.AdventureWorks2019.Sales.Customer C 
ON SOH.CustomerID=C.CustomerID 
AND C.TerritoryID=1 AND SOH.TerritoryID!=C.TerritoryID)
UNION
(SELECT C.CustomerID,C.TerritoryID TerritorioCliente,
SOH.SalesOrderID,SOH.TerritoryID TerritorioVenta
FROM Pacifico.AdventureWorks2019.Sales.SalesOrderHeader SOH
INNER JOIN F_local.AdventureWorks2019.Sales.Customer C 
ON SOH.CustomerID=C.CustomerID 
AND C.TerritoryID=1 AND SOH.TerritoryID!=C.TerritoryID)
END
