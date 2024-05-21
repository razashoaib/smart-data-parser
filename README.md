# Smart Data Parser

This project processes a multiline string of data and generates various reports based on the input. It follows SOLID principles and utilizes a design pattern for flexibility and extensibility.

## Report Features

- Retrieves the number of unique customerId for each contractId
- Retrieves the number of unique customerId for each geozone
- Retrieves the average buildduration for each geozone
- Retrieves the list of unique customerId for each geozone

## Requirements

- Java 16 or higher
- Apache Maven v3.6.3

## How to Build and Run

### Using Command Line

1. **Clone the Repository:**

   ```bash
   git clone <repo-link>
   cd project-directory
   mvn clean install
   mvn exec:java -Dexec.mainClass=main
   mvn test
### Project Built Using

- git
- JDK v16.0.1
- IntelliJ Idea (Community edition)
- JUnit v5.11 for Unit Testing
- Apache Maven v3.6.3

### Acknowledgements

- [W3Schools](https://www.w3schools.com/)
- [Stack Overflow](https://stackoverflow.com/)

