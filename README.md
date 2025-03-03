# MURIC-MICROSERVICE
Aloja el código fuente de integración del proyecto MURIC para el consumo del API https://apidev.superfinanciera.gov.co/v2/services/muric
# JAVA -VERSION
OPEN JDK 17
# COMPILAtion PROJECT
Execute mvn clean install in the root directory of each module  
firt compliation order:
1. muric-entities
2. muric-infrastructure
3. muric-usecase
4. muric-web
5. MURIC-MICROSERVICES
# ENDPOINT MICROSERVICE 
GET Method: {{host}}/muric/generateAvro?source={pathUrl}&type=FILE
    @RequestParam => source
    @RequestParam => type

GET Method: {{host}}/muric/generateAvro?type=DATABASE
@RequestParam => source is not required
@RequestParam => type

Parámetros de petición :
1. type=FILE para ejecución desde archivos excel.
2. type=DATABASE para ejecución desde base de datos.

# ENDPOINT MICROSERVICE - HEALTH CEHCK
{{host}}/muric/healthCheck





