# Crypto Daily Server

## Tech Stack

[![Kotlin](https://img.shields.io/badge/kotlin-1.9.22-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![Ktor](https://img.shields.io/badge/ktor-2.3.8-blue.svg?logo=ktor)](http://ktor.io)
[![MongoDB](https://img.shields.io/badge/mongodb-4.11.0-blue.svg?logo=mongodb)](http://mongodb.io)

## Start web service

Run docker-compose command, the MongoDB will create first, then the web service.

```bash
docker-compose up
```

Open the `http://localhost:8080` to see the web content.

## Test

```bash
./gradlew test
```

## Deployment

The `Dockerfile` and `docker-compose.yml` files are available for Docker depolyment.

Unfortunately, some vendors are not support the docker-compose such as [Render.com](https://render.com).

To deloy service on Render we can use the [Blueprint solution](https://docs.render.com/blueprint-spec) , the deploy file is `render.yaml`.


