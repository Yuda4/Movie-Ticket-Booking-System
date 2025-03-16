# Movie-Ticket-Booking-System
This assignment aims to develop a RESTful API for a movie ticket booking system using PostgreSQL and Spring Boot. 
The system will manage movies, showtimes, users, and ticket bookings.

## Prerequisites

- Java 17
- PostgreSQL
- Maven

## Project Structure

~~~
movieticketbooking
│── src
│   ├── main
│   │   ├── java
│   │   │   ├──/org/example/movieticketbooking
│   │   │   │   ├── controller
│   │   │   │   │   ├── MovieController.java
│   │   │   │   │   ├── ShowtimeController.java
│   │   │   │   │   ├── TicketController.java
│   │   │   │   │   ├── UserController.java
│   │   │   │   ├── service
│   │   │   │   │   ├── MovieService.java
│   │   │   │   │   ├── ShowtimeService.java
│   │   │   │   │   ├── TicketService.java
│   │   │   │   │   ├── UserService.java
│   │   │   │   ├── repository
│   │   │   │   │   ├── MovieRepository.java
│   │   │   │   │   ├── ShowtimeRepository.java
│   │   │   │   │   ├── TicketRepository.java
│   │   │   │   │   ├── UserRepository.java
│   │   │   │   ├── model
│   │   │   │   │   ├── Movie.java
│   │   │   │   │   ├── Showtime.java
│   │   │   │   │   ├── Ticket.java
│   │   │   │   │   ├── User.java
│   │   │   │   ├── MovieticketbookingApplication.java
│   │   ├── resources
│   │   │   ├── application.properties
│   ├── test
│   │   ├── java/org/example/movieticketbooking/service
│   │   │   ├── ShowtimeServiceTest.java
│   │   │   ├── TicketServiceTest.java
│── pom.xml

~~~


## Configure the Postgres Database Server
Installing Postgres Database using Homebrew
`brew install postgresql`
This will fetch the latest version of Postgres from the repository and get it installed on your machine.

To start the service to be available for use, use the command as follows to start running the service:
`brew services start postgresql`

Once the PostgreSQL server is up and running, the next step is  configuring it. We are going to create a root user that will have administrator privileges to the database server.

`psql postgres`

Now you're logged into the postgres service and ready to execute PGSQL commands:

`CREATE ROLE newuser WITH LOGIN PASSWORD ‘password’;`

`ALTER ROLE newuser CREATEDB;`

`CREATE DATABASE movieticketbooking;`

`\c movieticketbooking`

Some command:

`\l` - List all databases.

`\dt` - List database tables.

`\c <database-name>` - connect to a Database.

`\d <table-name>` - Describe a table.

## Setup Instructions
`git clone https://github.com/Yuda4/Movie-Ticket-Booking-System.git`

`cd movieticketbooking`

`mvn clean install`

`mvn spring-boot:run`

After project is running, you can enter to Swagger-UI from here and play with the API:

http://localhost:8080/swagger-ui/index.html#/

## How to Ingest This Data?
1. Open Postman or use curl.
2. Make a POST request to:
   
  http://localhost:8080/movies for movies.
  
  http://localhost:8080/showtimes for showtimes.
  
  http://localhost:8080/tickets/book for ticket bookings.
  
  http://localhost:8080/users for users.

4. Use the JSON as the request body, examples below.
5. Verify by making GET requests to:

  http://localhost:8080/api/movies

  http://localhost:8080/api/showtimes
  
  http://localhost:8080/tickets
  
  http://localhost:8080/users/{id}
  
8. In order to delete a ticket, make a DELETE request to:
9. 
  http://localhost:8080/tickets/cancel/{ticketId}

## Some sample data:
### Showtime:

`{
    "movie": {
        "id": 1
    },
    "theater": "IMAX Theater 1",
    "startTime": "2025-03-12T14:00:00",
    "endTime": "2025-03-12T16:30:00"
}`

`{
    "movie": {
        "id": 2
    },
    "theater": "IMAX Theater 1",
    "startTime": "2025-03-12T17:00:00",
    "endTime": "2025-03-12T19:30:00"
}`

`{
    "movie": {
        "id": 3
    },
    "theater": "Cinema Hall 3",
    "startTime": "2025-03-13T18:00:00",
    "endTime": "2025-03-13T21:00:00"
}`

`{
    "movie": {
        "id": 4
    },
    "theater": "Classic Cinema 5",
    "startTime": "2025-03-14T20:00:00",
    "endTime": "2025-03-14T23:15:00"
}`

`{
    "movie": {
        "id": 1
    },
    "theater": "IMAX Theater 1",
    "startTime": "2025-03-18T14:00:00",
    "endTime": "2025-03-18T16:30:00"
}`

### MOVIES:
`{
    "title": "Inception",
    "genre": "Sci-Fi",
    "duration": 148,
    "rating": 8.8,
    "releaseYear": 2010
}`

`{
    "title": "The Dark Knight",
    "genre": "Action",
    "duration": 152,
    "rating": 9.0,
    "releaseYear": 2008
}`

`{
    "title": "Interstellar",
    "genre": "Sci-Fi",
    "duration": 169,
    "rating": 8.6,
    "releaseYear": 2014
}`

`{
    "title": "Titanic",
    "genre": "Romance",
    "duration": 195,
    "rating": 7.9,
    "releaseYear": 1997
}`

### TICKETS:
`{
    "showtime": {
        "id": 1,
        "movie": {
            "id": 1
        }
    },
    "user": {
        "id": 1
    },
    "seatNumber": 12,
    "price": 15.50
}`

`{
    "showtime": {
        "id": 2,
        "movie": {
            "id": 2
        }
    },
    "user": {
        "id": 5
    },
    "seatNumber": 8,
    "price": 17.00
}`

### USERS:
`{
"email": "yehduan@gmail.com",
"name": "Yehuda N"
}`

`{
"email": "tomb@gmail.com",
"name": "Tom B"
}`

`{
"email": "jerryc@gmail.com",
"name": "Jerry C"
}`

### Examples of data in Postgres:

![showtimes](https://github.com/user-attachments/assets/175c071a-71f4-4514-8bf2-f989894c5c2e)

![movies](https://github.com/user-attachments/assets/6a21b56d-ff2f-45ce-8870-b9fc74f81bb8)

![users](https://github.com/user-attachments/assets/1d04422f-6095-4e48-b153-75007a994dac)

![tickets](https://github.com/user-attachments/assets/3c06c69a-b4ba-4f91-b321-1f3a2657586d)


