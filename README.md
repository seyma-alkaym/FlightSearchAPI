# FlightSearchAPI

FlightSearchAPI is a Java-based RESTful API designed for searching and managing flight information, airports, and providing authentication services.

## API Endpoints

### Authentication

- `POST /api/v1/auth/authenticate`: Authenticate user
- `POST /api/v1/auth/register`: Register a new user

### Flights

- `GET /api/v1/flights/`: Get all flights
- `POST /api/v1/flights/create-flight`: Create a new flight
- `DELETE /api/v1/flights/delete-flight/{id}`: Delete a flight by ID
- `GET /api/v1/flights/get-flight/{id}`: Get details of a flight by ID
- `PUT /api/v1/flights/update-flight/{id}`: Update details of a flight by ID

### Airports

- `GET /api/v1/airports/`: Get all airports
- `POST /api/v1/airports/create-airport`: Create a new airport
- `DELETE /api/v1/airports/delete-airport/{id}`: Delete an airport by ID
- `GET /api/v1/airports/get-airport/{id}`: Get details of an airport by ID
- `PUT /api/v1/airports/update-airport/{id}`: Update details of an airport by ID

### Search

- `GET /api/v1/search/`: Search for flights based on departure and arrival locations, departure date, and return date.

## Data Modeling

### Flights Table

- ID
- Departure Airport
- Arrival Airport
- Departure Date/Time
- Return Date/Time
- Price

### Airports Table

- ID
- City

## CRUD Operations

CRUD operations are available for both flights and airports.

### Scheduled Background Jobs

A scheduled job runs daily to fetch flight information from a mock third-party API and stores it in the database.

## Authentication

Authentication is implemented to secure the API endpoints.

## Technologies Used

- Java
- Spring Boot
- JWT
- Swagger for API documentation

## Setup

1. Clone the repository
2. Configure the database connection in `application.yml`
3. Run the application
4. Access the Swagger documentation at `http://localhost:8080/swagger-ui/index.html`

## Mock API

A mock API is used for testing and generating artificial data.

## Documentation

API documentation is available using Swagger. Access it at `http://localhost:8080/swagger-ui/index.html`.

## Contributors

- [Seyma](https://github.com/seyma-alkaym)


This project was created by [Seyma Alkaym](https://github.com/seyma-alkaym/) as a fun and educational exercise. Feel free to customize and improve it as you see fit.
