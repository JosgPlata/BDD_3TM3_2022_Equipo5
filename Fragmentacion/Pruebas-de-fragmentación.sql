USE NorthAmerica;
GO

-- Fragmentación en SalesOrderHeader
-- Ordenes en north America 16,110
SELECT * 
FROM Sales.SalesOrderHeader SOH
INNER JOIN Sales.SalesTerritory ST
ON SOH.TerritoryID=ST.TerritoryID 
AND [GROUP]='North America';
GO
/*
DELETE Sales.SalesOrderHeader WHERE
SalesOrderID IN(
SELECT * 
FROM Sales.SalesOrderHeader SOH
INNER JOIN Sales.SalesTerritory ST
ON SOH.TerritoryID=ST.TerritoryID 
AND [GROUP]!='North America');
GO
*/
USE Europe;
GO
-- Fragmentación en SalesOrderHeader
-- Ordenes en Europa 8,514
SELECT * 
FROM Sales.SalesOrderHeader SOH
INNER JOIN Sales.SalesTerritory ST
ON SOH.TerritoryID=ST.TerritoryID 
AND [GROUP]='Europe';
GO
/*
DELETE Sales.SalesOrderHeader WHERE
SalesOrderID IN(
SELECT * 
FROM Sales.SalesOrderHeader SOH
INNER JOIN Sales.SalesTerritory ST
ON SOH.TerritoryID=ST.TerritoryID 
AND [GROUP]!='Europe');
GO
*/

USE Pacific;
GO
-- Fragmentación en SalesOrderHeader
-- Ordenes en Pacific 6,843
SELECT * 
FROM Sales.SalesOrderHeader SOH
INNER JOIN Sales.SalesTerritory ST
ON SOH.TerritoryID=ST.TerritoryID 
AND [GROUP]='Pacific';
GO

/*
DELETE Sales.SalesOrderHeader WHERE
SalesOrderID IN(
SELECT * 
FROM Sales.SalesOrderHeader SOH
INNER JOIN Sales.SalesTerritory ST
ON SOH.TerritoryID=ST.TerritoryID 
AND [GROUP]!='Pacific');
GO
*/
--Confirmamos que no falten datos
(SELECT * FROM 
AdventureWorks2019.Sales.SalesOrderHeader)
EXCEPT(
(SELECT * FROM
NorthAmerica.Sales.SalesOrderHeader NA)
UNION ALL
(SELECT * FROM
Europe.Sales.SalesOrderHeader EU)
UNION ALL
(SELECT * FROM
Pacific.Sales.SalesOrderHeader PA));
GO 