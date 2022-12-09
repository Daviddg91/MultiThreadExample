# Proyecto kafka dockerizado con envio de mensajes a logger a traves de un controlador

Api Rest links(este enlace envia 100 mensajes a traves del topico:
-------------------------------------------------------------------
GET: localhost:8080/api/kafka


El fichero de log se genera en el directorio root del proyecto:
-------------------------------------------------------------------
./demo/customLogFile.log

Valores de concurrencia(subprocesos):
--------------------------------------
El aplicativo tiene 3 consumidores los cuales estan plenamente configurados a traves de sus properties para establecer los valores
de concurrencia, actualmente estan seteados a un nivel conservador de forma que no levante un numero excesivo de subprocesos,
pero dependiendo de la carga y del uso del protocolo se podrian escalar facilmente modificando las propperties de las particiones
y la concurrencia.

Test:
--------------------------------------
Me parecia algo poco util y enrevesado generar un test con la libreria embebida, por lo que me decante por desarrollar los test de 
contenedores de forma que se presta uso la misma imagen docker para lanzar los test, con un envio y recepción de mensajes
basicos en diferentes escenarios.