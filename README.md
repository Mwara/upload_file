# API in Spring Boot to load records to the database from a CDR file

## Requirements

- Java 17
- Maven 3.9
- Postgres data base
- Spring Boot

## prerequisite
- update the value of the variables in the application.properties file
- build the metadata for this property
```bash 
spring.datasource.path
```
# API to get call data record
## Installation

### Create and activate virtual environment
```bash
python3 -m venv env
source env/bin/activate
```

### Install requirements
```bash
pip install -r requirements.txt
```

### Add DB connection data to .env file
```bash
echo -e "# DB connection\nDB_HOST=\"\"\nDB_PASSWORD=\"\"\nDB_NAME=\"\"\nDB_USER=\"\"\nDB_PORT=5432" > .env
```

## Running
```bash
uvicorn app.main:app --port 8000
```

## Test
http://127.0.0.1:8000/docs