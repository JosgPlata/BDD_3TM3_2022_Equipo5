use AdventureWorks2019
go

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
-- Vista de los clientes con más compras en la region
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
		WHERE CustomerID IS NOT NULL
	GROUP BY CustomerID) T);
GO
--Listar el producto más solicitado en la región
CREATE VIEW MasSolicitadoPR AS
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
ON SOH.TerritoryID=ST.TerritoryID 
GROUP BY ProductID)SQ);
GO

--Listar los 3 productos menos solicitados por region
CREATE VIEW MenosSolicitadoPR_T3 AS
SELECT Top 3 ProductID,SUM(OrderQty) unidadesVendidas
FROM Sales.SalesOrderDetail SOD
INNER JOIN Sales.SalesOrderHeader SOH
ON SOD.SalesOrderID=SOH.SalesOrderID
Inner Join Sales.SalesTerritory ST 
ON SOH.TerritoryID=ST.TerritoryID 
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
ON SOH.TerritoryID=ST.TerritoryID
GROUP BY ProductID)SQ);
GO
















