# wander-hub

`wander-hub` is the backend project of wander system.

Tech stack:

- Java 8
- Spring Boot 2.7
- Maven
- MySQL 5.7
- Redis (token storage, with in-memory fallback)
- Elasticsearch (note/ai log indexing, non-blocking fallback)

## module layout (feature-based)

- `wander-hub-common`: shared model and response objects
- `wander-hub-infra`: infrastructure configuration (ES/RestTemplate/properties)
- `wander-hub-auth`: login authentication domain
- `wander-hub-note`: notes domain
- `wander-hub-ai`: ai chat domain
- `wander-hub-announce`: announcement domain
- `wander-hub-change`: change log domain
- `wander-hub-app`: application bootstrap and global web config

## 1. Run locally

### 1.1 Prepare database

```sql
CREATE DATABASE IF NOT EXISTS wander DEFAULT CHARACTER SET utf8mb4;
```

Default config:

- host: `127.0.0.1:3306`
- db: `wander`
- user: `root`
- password: `root`

You can override with env vars:

- `MYSQL_URL`
- `MYSQL_USERNAME`
- `MYSQL_PASSWORD`
- `REDIS_HOST`
- `REDIS_PORT`
- `ES_HOST`
- `ES_PORT`

### 1.2 Start service

```bash
mvn -pl wander-hub-app -am spring-boot:run
```

Backend URL: `http://127.0.0.1:8080/wander-hub`

Health check:

- `GET /api/health`

## 2. Build

```bash
mvn -pl wander-hub-app -am -DskipTests package
```

## 3. Database init

On startup, Spring SQL init executes:

- `src/main/resources/schema.sql`
- `src/main/resources/data.sql`

Default seeded user:

- username: `admin`
- password: `123456`

## 4. API docs

All responses use:

```json
{
  "success": true,
  "message": "OK",
  "data": {}
}
```

### 4.1 Login

- Method: `POST`
- Path: `/api/auth/login`
- Body:

```json
{
  "username": "admin",
  "password": "123456"
}
```

### 4.2 Create note

- Method: `POST`
- Path: `/api/notes`
- Header: `Authorization: Bearer <token>`
- Body:

```json
{
  "title": "today",
  "content": "something important"
}
```

### 4.3 List notes

- Method: `GET`
- Path: `/api/notes`
- Header: `Authorization: Bearer <token>`

### 4.4 AI chat

- Method: `POST`
- Path: `/api/ai/chat`
- Header: `Authorization: Bearer <token>`
- Body:

```json
{
  "prompt": "请介绍一下wander系统"
}
```

If `ai.base-url` is empty, service returns local mock answer.

### 4.5 User and permission

- `GET /api/auth/me`
- `GET /api/auth/users` (ADMIN/`USER_MANAGE`)
- `POST /api/auth/users` (ADMIN/`USER_MANAGE`)

### 4.6 Announcement

- `GET /api/announcements/play` (public play list)
- `GET /api/announcements` (ADMIN/`ANNOUNCEMENT_MANAGE`)
- `POST /api/announcements` (ADMIN/`ANNOUNCEMENT_MANAGE`)

### 4.7 Change log

- `GET /api/changelog`
- `POST /api/changelog` (ADMIN/`CHANGELOG_MANAGE`)

## 5. Frontend integration

Frontend repo `wander-admin` should proxy:

- `/api` -> `http://127.0.0.1:8080/wander-hub`
