#/bin/bash



#DETENER TODOS LOS CONTAINERS
sudo docker stop $( sudo docker ps -qa)
#LIMPIAR DOCKER DE CONTAINERS BASURA QUE NO SE ESTAN EJECUTANDO 
sudo docker system prune


#Descargando imagen de sql server 
sudo docker pull mcr.microsoft.com/mssql/server:2022-latest
#Deploy containers de sqlserver 
sleep 2
sudo docker run -e "ACCEPT_EULA=Y" -e "SA_PASSWORD=P@SSWORD1" -p 1435:1433 --name NA_SERVER --hostname NA_SERVER  \
-d mcr.microsoft.com/mssql/server:2022-latest #FULL SERVER 
sleep 1
sudo docker exec -it NA_SERVER mkdir /var/opt/mssql/backup
sudo docker cp ./Fragmentos/NorthAmerica.bak NA_SERVER:/var/opt/mssql/backup
sleep 1
sudo docker exec -it NA_SERVER /opt/mssql-tools/bin/sqlcmd -S localhost \
   -U SA -P 'P@SSWORD1' \
   -Q 'RESTORE FILELISTONLY FROM DISK = "/var/opt/mssql/backup/NorthAmerica.bak"' \
   | tr -s ' ' | cut -d ' ' -f 1-2

sudo docker exec -it NA_SERVER /opt/mssql-tools/bin/sqlcmd \
-S localhost -U SA -P 'P@SSWORD1' \
-Q 'RESTORE DATABASE AdventureWorks2019 FROM DISK = "/var/opt/mssql/backup/NorthAmerica.bak" WITH MOVE "AdventureWorks2017" TO "/var/opt/mssql/data/NorthAmerica.mdf", MOVE "AdventureWorks2017_log" TO "/var/opt/mssql/data/AdventureWorks2017_log.ldf"'

echo "\n\n [###] SERVER DE NORTEAMÉRICA ARRIBA\n\n"

sudo docker run -e "ACCEPT_EULA=Y" -e "SA_PASSWORD=P@SSWORD2" -p 1436:1433 --name PA_SERVER --hostname PA_SERVER  \
-d mcr.microsoft.com/mssql/server:2022-latest
sleep 2
sudo docker exec -it PA_SERVER mkdir /var/opt/mssql/backup
sudo docker cp ./Fragmentos/Pacific.bak PA_SERVER:/var/opt/mssql/backup
sleep 1
sudo docker exec -it PA_SERVER /opt/mssql-tools/bin/sqlcmd -S localhost \
   -U SA -P 'P@SSWORD2' \
   -Q 'RESTORE FILELISTONLY FROM DISK = "/var/opt/mssql/backup/Pacific.bak"' \
   | tr -s ' ' | cut -d ' ' -f 1-2
sleep 1
sudo docker exec -it PA_SERVER /opt/mssql-tools/bin/sqlcmd \
-S localhost -U SA -P 'P@SSWORD2' \
-Q 'RESTORE DATABASE AdventureWorks2019 FROM DISK = "/var/opt/mssql/backup/Pacific.bak" WITH MOVE "AdventureWorks2017" TO "/var/opt/mssql/data/Pacific.mdf", MOVE "AdventureWorks2017_log" TO "/var/opt/mssql/data/AdventureWorks2017_log.ldf"'

echo "\n\n [###] SERVER PACIFICO ARRIBA\n\n"

sudo docker run -e "ACCEPT_EULA=Y" -e "SA_PASSWORD=P@SSWORD3" -p 1437:1433 --name EU_SERVER --hostname EU_SERVER \
-d mcr.microsoft.com/mssql/server:2022-latest #FULL SERVER 
sleep 1
sudo docker exec -it EU_SERVER mkdir /var/opt/mssql/backup
sudo docker cp ./Fragmentos/Europe.bak EU_SERVER:/var/opt/mssql/backup
sleep 1
sudo docker exec -it EU_SERVER /opt/mssql-tools/bin/sqlcmd -S localhost \
   -U SA -P 'P@SSWORD3' \
   -Q 'RESTORE FILELISTONLY FROM DISK = "/var/opt/mssql/backup/Europe.bak"' \
   | tr -s ' ' | cut -d ' ' -f 1-2
sleep 1
sudo docker exec -it EU_SERVER /opt/mssql-tools/bin/sqlcmd \
-S localhost -U SA -P 'P@SSWORD3' \
-Q 'RESTORE DATABASE AdventureWorks2019 FROM DISK = "/var/opt/mssql/backup/Europe.bak" WITH MOVE "AdventureWorks2017" TO "/var/opt/mssql/data/Europe.mdf", MOVE "AdventureWorks2017_log" TO "/var/opt/mssql/data/AdventureWorks2017_log.ldf"'

echo "\n\n [###] SERVER EUROPA ARRIBA\n\n"



sudo docker run -e "ACCEPT_EULA=Y" -e "SA_PASSWORD=P@SSWORD4" -p 1438:1433 --name MA_SERVER --hostname MA_SERVER \
-d mcr.microsoft.com/mssql/server:2022-latest #FULL SERVER 
sleep 1
sudo docker exec -it MA_SERVER mkdir /var/opt/mssql/backup
#sudo docker cp ./Fragmentos/AdventureWorks2019.bak MA_SERVER:/var/opt/mssql/backup
#sudo docker exec -it MA_SERVER /opt/mssql-tools/bin/sqlcmd -S localhost \
#   -U SA -P 'P@SSWORD4' \
#   -Q 'RESTORE FILELISTONLY FROM DISK = "/var/opt/mssql/backup/AdventureWorks2019.bak"' \
#   | tr -s ' ' | cut -d ' ' -f 1-2
#
#sudo docker exec -it MA_SERVER /opt/mssql-tools/bin/sqlcmd \
#-S localhost -U SA -P 'P@SSWORD4' \
#-Q 'RESTORE DATABASE AdventureWorks2019 FROM DISK = "/var/opt/mssql/backup/AdventureWorks2019.bak" WITH MOVE "AdventureWorks2017" TO "/var/opt/mssql/data/Europe.mdf", MOVE "AdventureWorks2017_log" TO "/var/opt/mssql/data/AdventureWorks2017_log.ldf"'
#

echo "\n\n [###] MAIN SERVER ARRIBA\n\n"

sleep 1
echo "\n\n [###] MAIN SERVER... Creando Linked Servers\n\n"

sudo docker exec -it MA_SERVER /opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P 'P@SSWORD4'  -Q "
EXEC sp_addlinkedserver
@server=N'NorteAmerica', 
@provider=N'MSOLEDBSQL',
@srvproduct=N'',
@datasrc=N'192.168.0.11,1435';

exec sp_addlinkedsrvlogin
@rmtsrvname =N'NorteAmerica',
@useself = 'False',
@rmtuser = N'SA', 
@rmtpassword=N'P@SSWORD1'; 

EXEC sp_addlinkedserver
@server=N'PACIFICO', 
@provider=N'MSOLEDBSQL',
@srvproduct=N'',
@datasrc=N'192.168.0.11,1436';


exec sp_addlinkedsrvlogin
@rmtsrvname =N'PACIFICO',
@useself = 'False',
@rmtuser = N'SA', 
@rmtpassword=N'P@SSWORD2'; 


EXEC sp_addlinkedserver
@server=N'EUROPA', 
@provider=N'MSOLEDBSQL',
@srvproduct=N'',
@datasrc=N'192.168.0.11,1437';


exec sp_addlinkedsrvlogin
@rmtsrvname =N'EUROPA',
@useself = 'False',
@rmtuser = N'SA', 
@rmtpassword=N'P@SSWORD3'; 

EXEC sp_addlinkedserver
@server=N'F_LOCAL', 
@provider=N'MSOLEDBSQL',
@srvproduct=N'',
@datasrc=N'192.168.0.11,1433';

exec sp_addlinkedsrvlogin
@rmtsrvname =N'F_LOCAL',
@useself = 'False',
@rmtuser = N'SA', 
@rmtpassword=N'N0m3l0n0m3l0'; 


GO
"
echo "\n\n [###] MAIN SERVER... Linked Servers creados :D \n\n"
for i in 1 2 3
do
	eval $(echo "sqlcmd -S localhost,$((1434+$i)) -U SA -P 'P@SSWORD$i' -i utils.sql")
done


sqlcmd -S localhost,1438 -U SA -P 'P@SSWORD4' -Q "CREATE DATABASE AdventureWorks2019"
sqlcmd -S localhost,1438 -U SA -P 'P@SSWORD4' -i mainutils.sql










