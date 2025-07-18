# G-Scores-Backend

## Technology Stack
- Java 17
- Spring Boot
- Spring Data JPA (Hibernate)
- Maven
- Docker
- MySQL

## Installation

### Prerequisites

- Java 17+
- Maven 3.8+
- Docker (optional, for containerized deployment)
- MySQL database (or compatible, if not using Docker)

### Clone the Repository

```bash
git clone https://github.com/tanle9t2/G-Scores-Backend.git
cd G-Scores-Backend
```

### Setup Environment Variables

Before running the backend, set up a `.env` file in the project root directory with your database configuration and any needed environment variables. Example:

```env
DB_USERNAME=username
DB_PASSWORD=password
DB_URL=your jdbc url connection
FRONTEND_URL=your frontend url For CORS
```
You can add any other environment-specific properties as needed.
### Running MySQL with Docker (Optional)
#### 1. Start a MySQL Container

You need a MySQL database running before starting the backend app. You can use Docker to start a MySQL container. For example:

```bash
docker run --name mysql-gscores -e MYSQL_ROOT_PASSWORD=your_mysql_password \
  -e MYSQL_DATABASE=gscores_db \
  -e MYSQL_USER=your_mysql_username \
  -e MYSQL_PASSWORD=your_mysql_password \
  -p 3306:3306 -d mysql:8
```

- Update `your_mysql_username` and `your_mysql_password` as needed.
- Wait a few seconds for MySQL to initialize.

#### With Maven

```bash
mvn clean package -DskipTests
```

#### With Docker

```bash
docker build -t gscores-backend .
```

### Run the Application

#### With Java

```bash
java -jar target/gscores-backend-*.jar
```

#### With Docker

```bash
docker run --env-file .env -p 8080:8080 gscores-backend
```

The backend will seed the database with the provided CSV file on startup.

## API Endpoints

- `GET /api/v1//student/{id}`: Retrieve scores by student ID.
- `GET /api/v1/subjects`: List all subjects.
- `GET /api/v1/statistics?subjectId={id}`: Get statistics for a subject.
- `GET /api/v1//student/top`: Get top 10 students for group A (Math, Physics, Chemistry).

## Structure

- Main code is under `src/main/java/com/tanle/gscores/`
- Seeder logic: `seeder/GScoreSeeder.java`
- Repository interfaces: `repository/`
- Business logic/services: `service/impl/`
- Entities: `entity/`
### Link: https://g-scores-backend-production.up.railway.app
**Author:** [tanle9t2](https://github.com/tanle9t2)
