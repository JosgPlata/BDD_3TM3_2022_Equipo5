/* 
1. La Información de los clientes se debe almacenar por región, 
considerando las regiones de acuerdo con el atributo group de salesterritory. 
*/
Select * from Sales.SalesTerritory;
GO

-- Clientes 1 = Clientes en la región Europe
SELECT CustomerID,PersonID,StoreID,StoreID,C.TerritoryID,[Group]
FROM Sales.Customer C 
Inner Join Sales.SalesTerritory ST 
ON C.TerritoryID=ST.TerritoryID
WHERE [Group]='Europe';
GO
-- Clientes2 = Clientes en la región North America
SELECT CustomerID,PersonID,StoreID,StoreID,C.TerritoryID,[Group]
FROM Sales.Customer C 
Inner Join Sales.SalesTerritory ST 
ON C.TerritoryID=ST.TerritoryID
WHERE [Group]='North America';
GO
-- Clientes 3 = Clientes en la región North America
SELECT CustomerID,PersonID,StoreID,StoreID,C.TerritoryID,[Group]
FROM Sales.Customer C 
Inner Join Sales.SalesTerritory ST 
ON C.TerritoryID=ST.TerritoryID
WHERE [Group]='Pacific';
GO

/*
2. Listar datos del empleado que atendió más ordenes por territorio.
*/

-- Vista con los empleaos con el máximo de ventas por territorio
CREATE VIEW maxOrdenesXEmpleadoXTerritorio AS(
SELECT T1.TerritoryID as TerritoryIDVentas,T1.SalesPersonID,T1.totalOrdenes totalOrdenesTerritorio
FROM 
-- Numero de ventas de empleados por territorio
	(SELECT TerritoryID,SalesPersonID, COUNT(*) totalOrdenes FROM Sales.SalesOrderHeader
	WHERE SalesPersonID IS NOT NULL
	GROUP BY TerritoryID,SalesPersonID) T1
INNER JOIN
-- Maximo de ventas por empleados por territorio
	(SELECT TerritoryID, MAX(totalOrdenes) maxTotalOrdenes
	FROM (-- Numero de ventas de empleados por territorio
		SELECT TerritoryID,SalesPersonID, COUNT(*) totalOrdenes FROM Sales.SalesOrderHeader
		WHERE SalesPersonID IS NOT NULL
		GROUP BY TerritoryID,SalesPersonID) SQ
	Group BY TerritoryID) T2
ON T1.TerritoryID=T2.TerritoryID AND T1.totalOrdenes=T2.maxTotalOrdenes);
GO

--Datos de los empleados en SalesPerson que atendieron más ordenes por territorio
SELECT MOET.*,SP.* 
FROM Sales.SalesPerson SP
INNER JOIN maxOrdenesXEmpleadoXTerritorio MOET
ON SP.BusinessEntityID=MOET.SalesPersonID;
GO

--Datos de los empleados en la tabla Employee
SELECT MOET.*,EMP.* 
FROM OPENQUERY([192.168.100.16\MSSQLS2],'SELECT * FROM Human.HumanResources.Employee') EMP
INNER JOIN maxOrdenesXEmpleadoXTerritorio MOET
ON EMP.BusinessEntityID=MOET.SalesPersonID;
GO

/*
3. Listar los datos del cliente con más ordenes solicitadas en la región "north america" 
*/
/*
SELECT [Group],CustomerID, COUNT(*) totalOrdenes
FROM Sales.SalesOrderHeader SOH
Inner Join Sales.SalesTerritory ST 
ON SOH.TerritoryID=ST.TerritoryID AND [Group]='North America'
GROUP BY [GROUP],CustomerID
HAVING COUNT(*)=(
-- Maximo de compras por clientes en la región "north america" 
SELECT MAX(totalOrdenes) maxTotalOrdenes
	FROM(-- Numero de compras de clientes en la región "north america" 
	SELECT CustomerID, COUNT(*) totalOrdenes
		FROM Sales.SalesOrderHeader SOH
		Inner Join Sales.SalesTerritory ST 
		ON SOH.TerritoryID=ST.TerritoryID AND [Group]='North America' 
		WHERE CustomerID IS NOT NULL
	GROUP BY CustomerID) T);
GO*/

-- Vista de los clientes con más compras en North America
CREATE VIEW ClientesMasComprasNA AS
SELECT CustomerID, COUNT(*) totalOrdenes
FROM Sales.SalesOrderHeader SOH
WHERE TerritoryID BETWEEN 1 AND 6
GROUP BY CustomerID
HAVING COUNT(*)=(
-- Maximo de compras por clientes en la región "north america" 
SELECT MAX(totalOrdenes) maxTotalOrdenes
	FROM(-- Numero de compras de clientes en la región "north america" 
	SELECT CustomerID, COUNT(*) totalOrdenes
		FROM Sales.SalesOrderHeader SOH
		WHERE CustomerID IS NOT NULL AND TerritoryID BETWEEN 1 AND 6
	GROUP BY CustomerID) T);
GO

-- Datos de los clientes con más compras en North America
SELECT C.CustomerID,C.PersonID,Prs.BusinessEntityID,
Prs.PersonType,Prs.FirstName,Prs.LastName,CMCNA.totalOrdenes
FROM OPENQUERY([192.168.100.166\MSSQLS2],
'SELECT BusinessEntityID,PersonType,FirstName,LastName
FROM Person.Person.Person') Prs
INNER JOIN Sales.Customer C
ON C.PersonID=Prs.BusinessEntityID
INNER JOIN ClientesMasComprasNA CMCNA
ON CMCNA.CustomerID=C.CustomerID;
GO

/*
4.Listar el producto más solicitado en la región "europe"
*/

SELECT ProductID,SUM(OrderQty) unidadesVendidas
FROM Sales.SalesOrderDetail SOD
INNER JOIN Sales.SalesOrderHeader SOH
ON SOD.SalesOrderID=SOH.SalesOrderID AND TerritoryID IN (7,8,10)
GROUP BY ProductID
HAVING SUM(OrderQty)=(
-- Mayor numero de unidades vendidas en la region europa
SELECT MAX(unidadesVendidas)
FROM(-- Numero de productos vendidos por ProductID
SELECT ProductID,SUM(OrderQty) unidadesVendidas
FROM Sales.SalesOrderDetail SOD
INNER JOIN Sales.SalesOrderHeader SOH
ON SOD.SalesOrderID=SOH.SalesOrderID
Inner Join Sales.SalesTerritory ST 
ON SOH.TerritoryID=ST.TerritoryID AND [Group]='Europe'
GROUP BY ProductID)SQ);
GO

/*
5. Listar las ofertas que tienen los productos de la categoría "bikes" 
*/

-- Productos con sus ofertas que sean de la categoría bikes
SELECT * FROM Sales.SpecialOfferProduct 
WHERE ProductID IN (
SELECT ProductID FROM OPENQUERY(MYSQLREMOTE, 
'SELECT P.ProductID,PSC.NAME Subcategoria
FROM Production.Product P
INNER JOIN Production.ProductSubcategory PSC
ON P.ProductSubcategoryID=PSC.ProductSubcategoryID
INNER JOIN Production.ProductCategory PC
ON PC.ProductCategoryID=PSC.ProductCategoryID
AND PC.NAME LIKE "Bikes"'))
GO

-- Ofertas que tengan los productos de categoría bikes
SELECT SO.SpecialOfferID FROM Sales.SpecialOfferProduct SOP
INNER JOIN
Sales.SpecialOffer SO ON SO.SpecialOfferID=SOP.SpecialOfferID
WHERE ProductID IN (
SELECT ProductID FROM OPENQUERY(MYSQLREMOTE, 
'SELECT P.ProductID,PSC.NAME Subcategoria
FROM Production.Product P
INNER JOIN Production.ProductSubcategory PSC
ON P.ProductSubcategoryID=PSC.ProductSubcategoryID
INNER JOIN Production.ProductCategory PC
ON PC.ProductCategoryID=PSC.ProductCategoryID
AND PC.NAME LIKE "Bikes"')) 
GROUP BY So.SpecialOfferID
GO

SELECT ProductID FROM OPENQUERY(MYSQLREMOTE, 
'SELECT * FROM Production.Product')
/*
6. Listar los 3 productos menos solicitados en la región "pacific" 
*/

SELECT Top 3 ProductID,SUM(OrderQty) unidadesVendidas
FROM Sales.SalesOrderDetail SOD
INNER JOIN Sales.SalesOrderHeader SOH
ON SOD.SalesOrderID=SOH.SalesOrderID
Inner Join Sales.SalesTerritory ST 
ON SOH.TerritoryID=ST.TerritoryID AND [Group]='Pacific'
GROUP BY ProductID
HAVING SUM(OrderQty)=(
-- Menor numero de unidades vendidas en la region pacific
SELECT MIN(unidadesVendidas)
FROM(-- Numero de productos vendidos por ProductID
SELECT ProductID,SUM(OrderQty) unidadesVendidas
FROM Sales.SalesOrderDetail SOD
INNER JOIN Sales.SalesOrderHeader SOH
ON SOD.SalesOrderID=SOH.SalesOrderID
Inner Join Sales.SalesTerritory ST 
ON SOH.TerritoryID=ST.TerritoryID AND [Group]='Pacific'
GROUP BY ProductID)SQ);
GO

/*
7. Actualizar la subcategoría de los productos con productID del 1 al 
   4 a la subcategoría válida para el tipo de producto.
*/

SELECT * FROM OPENQUERY(MYSQLREMOTE, 
'SELECT * FROM Production.Product 
WHERE ProductID BETWEEN 1 AND 4');
GO
/*
8. Listar los productos que no estén disponibles a la venta 
*/

--Tomando en cuenta la fecha actual

DECLARE @fecha date = getdate();

SELECT * FROM OPENQUERY(MYSQLREMOTE, 
'SELECT * FROM Production.Product')
WHERE NOT(@fecha>=SellStartDate
AND @fecha<SellEndDate)
AND SellEndDate IS NOT NULL;

-- Sin tomar en cuenta la fecha
SELECT * FROM OPENQUERY(MYSQLREMOTE, 
'SELECT * FROM Production.Product')
WHERE SellEndDate IS NOT NULL;

GO
/*
9. Listar los clientes del territorio 1 y 4 que no tengan asociado un valor en personID 
*/

SELECT * FROM Sales.Customer WHERE TerritoryID IN(1,4) AND PersonID IS NULL;
GO
-- Complemento
SELECT* FROM Sales.Customer
WHERE CustomerID not in
(SELECT CustomerID FROM Sales.Customer 
WHERE TerritoryID IN(1,4) AND PersonID IS NULL);
GO

/*
10. Listar los clientes del territorio 1 que tengan ordenes en otro territorio. 
*/

SELECT C.CustomerID,C.TerritoryID TerritorioCliente,
SOH.SalesOrderID,SOH.TerritoryID TerritorioVenta
FROM Sales.SalesOrderHeader SOH
INNER JOIN Sales.Customer C 
ON SOH.CustomerID=C.CustomerID 
AND C.TerritoryID=1 AND SOH.TerritoryID!=C.TerritoryID;
GO