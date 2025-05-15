# Personal Book Collection Manager
*  This project is pretty similar to [my old JS repo](https://github.com/pygosclune/js-library-page) 
## Prerequisites

*   JDK 21 or higher.
*   Apache Maven 3.6.x or higher.
*   An IDE (e.g., IntelliJ IDEA, Eclipse, VS Code with Java extensions) is recommended but not required.

## Setup and Running

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/pygosclune/JavaBookCollection.git
    cd JavaBookCollection
    ```

2.  **Build and run the application using Maven:**
    ```bash
    mvn spring-boot:run
    ```
    Alternatively, you can build a JAR file:
    ```bash
    mvn clean package
    java -jar target/book-collection-0.0.1-SNAPSHOT.jar
    ```

3.  **Access the application:**
    Open your web browser and navigate to:
    `http://localhost:8080/`

4.  **Access the H2 Database Console (optional):**
    If you want to inspect the in-memory database:
    *   Navigate to: `http://localhost:8080/h2-console`
    *   **JDBC URL:** `jdbc:h2:mem:bookdb`
    *   **User Name:** `sa`
    *   **Password:** (leave blank)
    *   Click "Connect". You can then run SQL queries (e.g., `SELECT * FROM BOOKS;`).

## Database
*   By default, the application uses an **H2 in-memory database**. This means data is lost when the application stops.

## Contributing

Feel free to fork, modify, and enhance it!
