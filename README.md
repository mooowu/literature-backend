# 📖 Literature Backend

웹소설 플랫폼을 위한 백엔드 서버입니다. 사용자는 이 플랫폼을 통해 자신만의 소설을 창작하고, 챕터를 추가하며, 독자들과 소통할 수 있습니다.

## ✨ 주요 기능

-   **소설 관리**: 소설 생성, 정보 수정, 상세 조회, 삭제 기능을 제공합니다.
-   **챕터 관리**: 소설에 챕터를 추가하고 내용을 관리합니다.
-   **등장인물 설정**: 작품의 등장인물을 생성하고 관리할 수 있습니다.
-   **태그 시스템**: 태그를 이용해 소설을 분류하고 검색할 수 있습니다.
-   **API 제공**: RESTful API와 GraphQL API를 모두 지원하여 유연한 데이터 통신이 가능합니다.

## 🛠️ 기술 스택

### Backend

-   **Language**: Kotlin, Java
-   **Framework**: Spring Boot 3, Spring Web, Spring Data JPA, GraphQL DGS
-   **Database**: PostgreSQL

### Build & Deployment

-   **Build Tool**: Gradle
-   **CI/CD**: Jenkins
-   **Containerization**: Docker (Jib)
-   **Orchestration**: ArgoCD

## 🏗️ 프로젝트 구조

이 프로젝트는 DDD(도메인 주도 설계)를 기반으로 한 계층형 아키텍처를 따릅니다.

-   `core`: 도메인 엔티티와 리포지토리 인터페이스 등 핵심 비즈니스 로직을 포함합니다.
-   `application`: 유스케이스와 애플리케이션 서비스를 구현합니다.
-   `api`: RESTful API 컨트롤러와 관련 DTO를 정의합니다.
-   `graphql`: GraphQL API 데이터 페처(Data Fetcher) 및 스키마 관련 코드를 포함합니다.
-   `lib`: 여러 모듈에서 공통으로 사용되는 유틸리티 클래스를 관리합니다.

## 🚀 시작하기

### 사전 요구 사항

-   JDK 21
-   Docker

### 1. 데이터베이스 실행

프로젝트에서 사용할 PostgreSQL 데이터베이스를 Docker를 이용해 실행합니다.

```bash
docker run -d \
  --name postgres-gowdb \
  -e POSTGRES_DB=gowdb \
  -e POSTGRES_USER=gowuser \
  -e POSTGRES_PASSWORD=gowpassword \
  -p 5432:5432 \
  postgres:latest
```

### 2. 애플리케이션 설정

`application.yml` 또는 `application.properties` 파일에 아래와 같이 데이터베이스 연결 정보를 설정합니다.

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/gowdb
    username: gowuser
    password: gowpassword
```

### 3. 애플리케이션 실행

아래 명령어를 통해 애플리케이션을 실행합니다.

```bash
./gradlew :api:bootRun
```

## 🚢 CI/CD

![CI/CD](./rsrc/cicd.png)
