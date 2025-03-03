# MURIC-MICROSERVICE
Aloja el código fuente de integración del proyecto MURIC para el consumo del API https://apidev.superfinanciera.gov.co/v2/services/muric
# JAVA - VERSION OPEN JDK 17
# COMPILACIÓN - MVN CLEAN INSTALL
# ENDPOINT DE EJECUTCIÓN
GET Method: localhost:8080/muric/generateAvro?source=/Users/XXXXX/Desktop/Mapa funcional de variables.xlsx&type=FILE
    @RequestParam => source
    @RequestParam => type
Parámetros de petición :
1. type=FILE para ejecución desde archivos excel.
2. type=DATABASE para ejecución desde base de datos.



