# User API

This project is a user management API that allows users to authenticate, register, activate/deactivate users, and retrieve a list of all users. It is built using Java Spring Boot and MySQL database.

## Test the API
- [API DOC](http://localhost:8082/v3/api-docs)
- [Swagger UI](http://localhost:8082/swagger-ui/index.html)

[<img src="https://run.pstmn.io/button.svg" alt="Run In Postman" style="width: 128px; height: 32px;">](https://god.gw.postman.com/run-collection/28660393-866aa553-45ca-4192-be1a-cd6e15052f18?action=collection%2Ffork&source=rip_markdown&collection-url=entityId%3D28660393-866aa553-45ca-4192-be1a-cd6e15052f18%26entityType%3Dcollection%26workspaceId%3Dcf048289-7983-422a-953e-c3f1ddb75b7a)
## Requirements

- Java Development Kit (JDK) 17 or above
- MySQL Database (You can either use a local MySQL instance or connect to a remote one)

## How to Run

1. Clone the project repository from Git (if it's not already cloned).
2. Import the project into your favorite Java IDE (e.g., IntelliJ, Eclipse, etc.).
3. Build the project to resolve dependencies.

## Features

| No. | Feature                  | Description                           | Endpoint                                |
|----:|--------------------------|---------------------------------------|-----------------------------------------|
|  1. | Authenticate             | Authenticate user credentials         | `POST /users/auth/authenticate`        |
|  2. | Register                 | Register a new user                   | `POST /users/auth/register`            |
|  3. | Activate User            | Activate a user account               | `PUT /users/activation/{userId}`       |
|  4. | Deactivate User          | Deactivate a user account             | `PUT /users/deactivation/{userId}`     |
|  5. | Get All Users            | Retrieve a list of all users          | `GET /users`                           |

## ERD

```mermaid
erDiagram
     USERS {
    long id PK
    string email "Unique"
    string password 
    boolean enabled "Active or Not"
    }