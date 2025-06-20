# 📦 Proyecto Spring Boot con Java 21

Este proyecto es una API REST desarrollada con Spring Boot y Java 21. Incluye funcionalidades como lectura de archivos CSV y conexión con Firebase para operaciones CRUD.

---

## 🚀 Requisitos previos

Asegúrate de tener instalado:

- [Java 21 (JDK)](https://jdk.java.net/21/)
- [Maven 3.8+](https://maven.apache.org/download.cgi)
- (Opcional) [Docker](https://www.docker.com/) si deseas contenerizar la app

---

## ⚙️ Configuración del entorno

1. Descargar todas las dependencias necesarias del proyecto:

```bash
mvn clean install -DskipTests
```

2. Ejecutar el proyecto

```bash
mvn spring-boot:run
```

## Docker
```bash
docker build -t u4 .
```

```bash
docker run -p 8100:8100 -e FIREBASE_CREDENTIALS={credentials in base64} u4
```
