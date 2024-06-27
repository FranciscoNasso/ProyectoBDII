# APIProyectoBDII

Este proyecto incluye una Web API con Spring, un frontend con Angular, y una base de datos MySQL en Docker.

## Prerrequisitos

Antes de comenzar, asegúrate de tener instalados los siguientes programas en tu sistema:
- [Java JDK 11+](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven](https://maven.apache.org/install.html)
- [Node.js y npm](https://nodejs.org/en/download/)
- [Angular CLI](https://angular.io/cli) (`npm install -g @angular/cli`)
- [Docker](https://www.docker.com/products/docker-desktop/)

## Instrucciones

### 1. Levantar la base de datos MySQL en Docker

Primero, necesitas levantar la base de datos MySQL utilizando Docker. Asegúrate de estar en el directorio raíz del proyecto y luego ejecuta el siguiente comando:

```sh
docker-compose up -d
```

Este comando levantará el contenedor de Docker utilizando el archivo `docker-compose.yml` que se encuentra en el proyecto.

### 2. Backend: Web API con Spring

A continuación, necesitas compilar y levantar el backend del proyecto, que es una Web API construida con Spring Boot.

1. Navega al directorio del proyecto `APIProyectoBDII`:

```sh
cd APIProyectoBDII
```

2. Compila y empaqueta el proyecto utilizando Maven:

```sh
mvn clean install
```

3. Levanta la aplicación Spring Boot:

```sh
mvn spring-boot:run
```

La aplicación debería estar corriendo en `http://localhost:8080`.

### 3. Frontend: Aplicación Angular

Ahora, necesitas levantar el frontend del proyecto, que es una aplicación construida con Angular.

1. Navega al directorio del proyecto `PencaFront`:

```sh
cd ../PencaFront
```

2. Instala las dependencias necesarias:

```sh
npm install
```

3. Levanta la aplicación Angular:

```sh
ng serve --open
```

La aplicación debería estar corriendo en `http://localhost:4200`, se abrirá una pestaña en el navegador predeterminado.


## Notas adicionales

- El archivo `./datosDePrueba.sql` contiene un script que carga la base de datos con algunos datos de ejemplo. Este archivo solo se debe correr una vez en una aplicación que permita manejar bases de datos como DataGrip.
- El archivo `./limpiarBD.sql` vacia la base de datos.