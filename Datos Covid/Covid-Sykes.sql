--1. Listar los casos potivos por entidad de residencia
--1
select * into Consulta1 from datoscovid

alter table Consulta1 alter column ENTIDAD_RES nvarchar(15) not null
create clustered index CIX_ENTIDAD_RES on Consulta1 (ENTIDAD_RES)

select ENTIDAD_RES, count(*) total_confirmado
from Consulta1
where CLASIFICACION_FINAL between 1 and 3
group by ENTIDAD_RES

--2
select ENTIDAD_RES, count(*) total_confirmado
from dbo.datoscovid
where CLASIFICACION_FINAL between 1 and 3
group by ENTIDAD_RES
order by ENTIDAD_RES

--2. Listar los casos sospechosos por entidad
--1
select ENTIDAD_UM, ENTIDAD_RES, count(*) total_sospechosos
from dbo.datoscovid
where CLASIFICACION_FINAL = 6
group by ENTIDAD_UM, ENTIDAD_RES
order by ENTIDAD_UM 

--2
alter table Consulta1 alter column ENTIDAD_UM nvarchar(15) not null
drop index  CIX_ENTIDAD_RES on Consulta1
create clustered index CIX_ENTIDAD_RES_ENTIDAD_UM on Consulta1 (ENTIDAD_RES,ENTIDAD_UM)

select ENTIDAD_UM, ENTIDAD_RES, count(*) total_sospechosos
from Consulta1
where CLASIFICACION_FINAL = 6
group by ENTIDAD_UM, ENTIDAD_RES
order by ENTIDAD_UM


--3. Listar el top 5 de municipios por entidad con el mayor número de casos reportados, 
--indicando casos sospechosos y casos confirmados.
--1
select top 5 ENTIDAD_RES, MUNICIPIO_RES, count(*) as reportados, count(case CLASIFICACION_FINAL when 1 then CLASIFICACION_FINAL 
                                                                  when 2 then CLASIFICACION_FINAL
																  when 3 then CLASIFICACION_FINAL
                                          end) as confirmado,
       count(case CLASIFICACION_FINAL when 6 then CLASIFICACION_FINAL end) as sospechoso
from dbo.datoscovid
group by ENTIDAD_RES, MUNICIPIO_RES
order by reportados desc
--2

drop index  CIX_ENTIDAD_RES_ENTIDAD_UM on Consulta1
create clustered index CIX_ENTIDAD_RES_MUNICIPIO_RES on Consulta1 (ENTIDAD_RES,MUNICIPIO_RES)
select top 5 ENTIDAD_RES, MUNICIPIO_RES, count(*) as reportados, count(case CLASIFICACION_FINAL when 1 then CLASIFICACION_FINAL 
                                                                  when 2 then CLASIFICACION_FINAL
																  when 3 then CLASIFICACION_FINAL
                                          end) as confirmado,
       count(case CLASIFICACION_FINAL when 6 then CLASIFICACION_FINAL end) as sospechoso
from Consulta1
group by ENTIDAD_RES,MUNICIPIO_RES
order by reportados desc
--4. Determinar el municipio con el mayor número de defunciones en casos confirmados.
--1
create view Defuciones as
select * from datoscovid
where FECHA_DEF != '9999-99-99'

select top 1 MUNICIPIO_RES,count(case CLASIFICACION_FINAL when 1 then CLASIFICACION_FINAL
							when 2 then CLASIFICACION_FINAL
							when 3 then CLASIFICACION_FINAL end) as DefuncionesConfirmados
from Defuciones
group by MUNICIPIO_RES
order by DefuncionesConfirmados desc
--2
select * into DefuncionesTabla from Defuciones
create clustered index CIX_Defunciones on DefuncionesTabla (MUNICIPIO_RES)

select top 1 MUNICIPIO_RES,count(case CLASIFICACION_FINAL when 1 then CLASIFICACION_FINAL
							when 2 then CLASIFICACION_FINAL
							when 3 then CLASIFICACION_FINAL end) as DefuncionesConfirmados
from DefuncionesTabla
group by MUNICIPIO_RES
order by DefuncionesConfirmados desc
--5. Determinar por entidad, si de casos sospechosos hay defunciones reportadas asociadas a neumonia.
--1
select * into DefuncionesNeumonia
from Defuciones
where NEUMONIA = 1

select * into DefuncionesNeumonia2
from Defuciones
where NEUMONIA = 1

select ENTIDAD_UM,ENTIDAD_RES,count(case CLASIFICACION_FINAL when 6 then CLASIFICACION_FINAL end) 
									as DefuncionesNeumoniaSospechosos  
									from DefuncionesNeumonia2
group by ENTIDAD_UM,ENTIDAD_RES
order by ENTIDAD_UM,ENTIDAD_RES 
--2
create clustered index CIX_ENTIDAD_UM_ENTIDAD_RES on DefuncionesNeumonia (ENTIDAD_UM,ENTIDAD_RES)

select ENTIDAD_UM,ENTIDAD_RES,count(case CLASIFICACION_FINAL when 6 then CLASIFICACION_FINAL end) 
									as DefuncionesNeumoniaSospechosos  
									from DefuncionesNeumonia
group by ENTIDAD_UM,ENTIDAD_RES
order by ENTIDAD_UM,ENTIDAD_RES 
--6.Listar por entidad el total de casos sospechosos, casos confirmados, total de defunciones en los meses 
--de marzo a agosto 2020 y de diciembre 2020 a mayo 2021.
--1
create view Sospechosos as
select ENTIDAD_UM,ENTIDAD_RES,FECHA_INGRESO,
count(case CLASIFICACION_FINAL when 6 then CLASIFICACION_FINAL end) as Sospechoso
from datoscovid 
where (FECHA_INGRESO between '2020-03-01' and '2020-08-31') or 
(FECHA_INGRESO between '2020-12-01' and '2021-05-31' )
group by ENTIDAD_UM,ENTIDAD_RES,FECHA_INGRESO

create view Confirmados as
select ENTIDAD_UM,ENTIDAD_RES,FECHA_INGRESO,
count(case CLASIFICACION_FINAL when 1 then CLASIFICACION_FINAL
							when 2 then CLASIFICACION_FINAL
							when 3 then CLASIFICACION_FINAL end) as Confirmados
from datoscovid 
where (FECHA_INGRESO between '2020-03-01' and '2020-08-31') or 
(FECHA_INGRESO between '2020-12-01' and '2021-05-31' )
group by ENTIDAD_UM,ENTIDAD_RES,FECHA_INGRESO


create view Defunciones2 as
select ENTIDAD_UM,ENTIDAD_RES,FECHA_INGRESO, count(*) as Defuncioness from Defuciones
where (FECHA_INGRESO between '2020-03-01' and '2020-08-31') or 
(FECHA_INGRESO between '2020-12-01' and '2021-05-31' )
group by ENTIDAD_UM,ENTIDAD_RES,FECHA_INGRESO

select c.ENTIDAD_UM,c.ENTIDAD_RES,c.FECHA_INGRESO,s.Sospechoso,c.Confirmados,d2.Defuncioness 
from Confirmados c 
inner join Defunciones2 d2
on c.FECHA_INGRESO = d2.FECHA_INGRESO and c.ENTIDAD_UM = d2.ENTIDAD_UM and c.ENTIDAD_RES = d2.ENTIDAD_RES 
inner join Sospechosos s
on s.FECHA_INGRESO = d2.FECHA_INGRESO and s.ENTIDAD_UM = d2.ENTIDAD_UM and s.ENTIDAD_RES = d2.ENTIDAD_RES
order by c.ENTIDAD_UM, c.ENTIDAD_RES,c.FECHA_INGRESO
--2

select c.ENTIDAD_UM,c.ENTIDAD_RES,c.FECHA_INGRESO,s.Sospechoso,c.Confirmados,d2.Defuncioness 
from ConfirmadosTabla c 
inner join Defunciones2Tabla d2
on c.FECHA_INGRESO = d2.FECHA_INGRESO and c.ENTIDAD_UM = d2.ENTIDAD_UM and c.ENTIDAD_RES = d2.ENTIDAD_RES 
inner join SospechososTabla s
on s.FECHA_INGRESO = d2.FECHA_INGRESO and s.ENTIDAD_UM = d2.ENTIDAD_UM and s.ENTIDAD_RES = d2.ENTIDAD_RES
order by c.ENTIDAD_UM, c.ENTIDAD_RES,c.FECHA_INGRESO
--7.Listar los 5 municipios con el mayor número de casos confirmados en niños menos de 13 años con alguna 
--comorbilidad reportada y cuantos de esos casos fallecieron.
--1
create view Enfermedades as
select MUNICIPIO_RES,EDAD,CLASIFICACION_FINAL,FECHA_DEF, case when NEUMONIA != 1 THEN 0  else 1
END AS Neumonia,case when DIABETES != 1 THEN 0  else 1
END AS Diabetes,case when EPOC != 1 THEN 0  else 1
END AS Epoc,case when ASMA != 1 THEN 0  else 1
END AS Asma,case when INMUSUPR != 1 THEN 0  else 1
END AS Inmusupr,case when HIPERTENSION != 1 THEN 0  else 1
END AS Hipertension,case when OTRA_COM != 1 THEN 0  else 1
END AS OtrasEnfermedades, case when CARDIOVASCULAR != 1 THEN 0  else 1
END AS Cardiovascular, case when OBESIDAD != 1 THEN 0  else 1
END AS Obesidad, case when RENAL_CRONICA != 1 THEN 0  else 1
END AS RenalCronica, case when TABAQUISMO != 1 THEN 0  else 1
END AS Tabaquismo, case when OTRO_CASO != 1 THEN 0  else 1
END AS OtroCaso from Consula7
where EDAD <13

create view Comorbilidad2 as
select MUNICIPIO_RES,EDAD,count( case CLASIFICACION_FINAL when 1 then CLASIFICACION_FINAL
							when 2 then CLASIFICACION_FINAL
							when 3 then CLASIFICACION_FINAL end)as Confirmados,
count(FECHA_DEF)as Defunciones,
(Neumonia+Diabetes+Epoc+Asma+Inmusupr+Hipertension+OtrasEnfermedades+Cardiovascular+Obesidad
+RenalCronica+Tabaquismo+OtroCaso)as Comorbilidad from Enfermedades
where FECHA_DEF != '9999-99-99'
group by MUNICIPIO_RES,EDAD,Neumonia,Diabetes,Epoc,Asma,Inmusupr,Hipertension,
OtrasEnfermedades,Cardiovascular,Obesidad,RenalCronica,Tabaquismo,OtroCaso

create view Comorbilidad3 as
select * from Comorbilidad2
where Comorbilidad >=2

select top 5 MUNICIPIO_RES,EDAD,Confirmados,Defunciones,count(Comorbilidad) as ComorbilidadTotal 
from Comorbilidad3 where EDAD != 0
group by MUNICIPIO_RES,EDAD,Confirmados,Defunciones
order by Confirmados desc
--2
select * into CormobilidadTabla from Comorbilidad3

create clustered index CIX_MUNICIPIO_RES on CormobilidadTabla (MUNICIPIO_RES)

select top 5 MUNICIPIO_RES,EDAD,Confirmados,Defunciones,count(Comorbilidad) as ComorbilidadTotal 
from CormobilidadTabla where EDAD != 0
group by MUNICIPIO_RES,EDAD,Confirmados,Defunciones
order by Confirmados desc

--8. Determinar si en el año 2020 hay una mayor cantidad de defunciones menores de edad que 
--en el año 2021 y 2022.
--1
--Select para visualización humana xD 
select  count(case when FECHA_DEF LIKE '2020%' then FECHA_DEF end) as defME2020,
		count(case when FECHA_DEF LIKE '2021%' then 1 end) as defME2021,
		count(case when FECHA_DEF LIKE '2022%' then 1 end ) as defME2022
		from dbo.datoscovid where EDAD <18
--Select con comprobación
select sq1.*,
		(case when (defME2020>defME2021 and defME2020>defME2022) 
			then 'En 2020 hubo MÁS defunciones menores de edad respecto a 2021 y 2022' 
			else 'En 2020 hubo MENOS defunciones menores de edad respecto a 2021 y 2022' end) as BOOL2020GT20212022 
			from (
				select  
					count(case when FECHA_DEF LIKE '2020%' then FECHA_DEF end) as defME2020,
					count(case when FECHA_DEF LIKE '2021%' then 1 end) as defME2021,
					count(case when FECHA_DEF LIKE '2022%' then 1 end ) as defME2022
				from dbo.datoscovid where EDAD <18
)as sq1
--2
select * into Consulta8 from datoscovid

create clustered index CIX_EDAD on Consulta8 (EDAD)

select sq1.*,
		(case when (defME2020>defME2021 and defME2020>defME2022) 
			then 'En 2020 hubo MÁS defunciones menores de edad respecto a 2021 y 2022' 
			else 'En 2020 hubo MENOS defunciones menores de edad respecto a 2021 y 2022' end) as BOOL2020GT20212022 
			from (
				select  
					count(case when FECHA_DEF LIKE '2020%' then FECHA_DEF end) as defME2020,
					count(case when FECHA_DEF LIKE '2021%' then 1 end) as defME2021,
					count(case when FECHA_DEF LIKE '2022%' then 1 end ) as defME2022
				from Consulta8 where EDAD <18
)as sq1

--9. Determinar si en el año 2021 hay un pocentaje mayor al 60 de casos reportados que son 
--confirmados por estudios de laboratorio en comparación al año 2020.
--1
select (case when (select count(*)*1.0/(select count(*) 
from dbo.datoscovid where FECHA_INGRESO  LIKE '2020%' and CLASIFICACION_FINAL = 3) 
from dbo.datoscovid where FECHA_INGRESO  LIKE '2021%' and CLASIFICACION_FINAL = 3)>1.6 
then 'Creció mas de 60% respecto 2020' else 'Creció menos de 60% respecto 2020' end) as Resultado

select 
  (
    case when (select count(*)* 1.0 /(select count(*) 
          from dbo.datoscovid 
          where FECHA_INGRESO LIKE '2020%' 
          and CLASIFICACION_FINAL = 3) 
	from dbo.datoscovid 
     where FECHA_INGRESO LIKE '2021%' 
     and CLASIFICACION_FINAL = 3)> 1.6 
	 then 'Creció mas de 60% respecto 2020' else 'Creció menos de 60% respecto 2020' end) as R
--2

drop index  CIX_EDAD on Consulta8

create clustered index CIX_CLASIFICACION_FINAL on Consulta8 (CLASIFICACION_FINAL)

select 
  (
    case when (select count(*)* 1.0 /(select count(*) 
          from Consulta8 
          where FECHA_INGRESO LIKE '2020%' 
          and CLASIFICACION_FINAL = 3) 
	from Consulta8 
     where FECHA_INGRESO LIKE '2021%' 
     and CLASIFICACION_FINAL = 3)> 1.6 
	 then 'Creció mas de 60% respecto 2020' else 'Creció menos de 60% respecto 2020' end) as R
--10. Determinar en que rango de edad: menor de edad, 19 a 40, 40 a 60 o mayor de 60 
--hay mas casos reportados que se hayan recuperado.
--1
select  (case 
	when ((RecME>Rec1940) and (RecME>Rec4060) and(RecME>Rec60M)) then 'Los que mas se recuperaron fueron los Menores de edad'
	when ((Rec1940>RecME) and (Rec1940>Rec4060) and(Rec1940>Rec60M)) then 'Los que mas se recuperaron fueron personas entre 19 y 40 años'
	when ((Rec4060>RecME) and (Rec4060>Rec1940) and(Rec4060>Rec60M)) then 'Los que mas se recuperaron fueron personas entre 19 y 40 años'
	ELSE 'Los que mas se recuperaron fueron personas Mayores de 60 años' end) as Mejorcategoria from (
		select  count(case when EDAD <18 then FECHA_DEF end) as RecME,
	    count(case when EDAD BETWEEN 19 and 40 then 1 end) as Rec1940,
		count(case when EDAD BETWEEN 40 and 60  then 1 end ) as Rec4060,
		count(case when EDAD >60 then 1 end ) as Rec60M
		from datoscovid where  FECHA_DEF = '9999-99-99') as sq1
--2
drop index  CIX_CLASIFICACION_FINAL on Consulta8

create clustered index CIX_EDAD on Consulta8 (EDAD)

select  (case 
	when ((RecME>Rec1940) and (RecME>Rec4060) and(RecME>Rec60M)) then 'Los que mas se recuperaron fueron los Menores de edad'
	when ((Rec1940>RecME) and (Rec1940>Rec4060) and(Rec1940>Rec60M)) then 'Los que mas se recuperaron fueron personas entre 19 y 40 años'
	when ((Rec4060>RecME) and (Rec4060>Rec1940) and(Rec4060>Rec60M)) then 'Los que mas se recuperaron fueron personas entre 19 y 40 años'
	ELSE 'Los que mas se recuperaron fueron personas Mayores de 60 años' end) as Mejorcategoria from (
		select  count(case when EDAD <18 then FECHA_DEF end) as RecME,
	    count(case when EDAD BETWEEN 19 and 40 then 1 end) as Rec1940,
		count(case when EDAD BETWEEN 40 and 60  then 1 end ) as Rec4060,
		count(case when EDAD >60 then 1 end ) as Rec60M
		from Consulta8 where  FECHA_DEF = '9999-99-99') as sq1