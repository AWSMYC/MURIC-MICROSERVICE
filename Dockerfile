# Etapa 1: Compilar el código fuente


# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo pom.xml
COPY pom.xml .

# Copia el código fuente
COPY . .

# Descarga las dependencias del proyecto
RUN mvn dependency:go-offline

# Compila la aplicación
RUN mvn clean install

# Etapa 2: Crear un contenedor ligero solo con el JAR compilado

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el JAR compilado de la etapa anterior

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "muric-web-1.0.jar"]