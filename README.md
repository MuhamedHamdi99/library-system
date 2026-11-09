# Library System

A learning project that manages books and authors. It was built in two stages: first with plain **JDBC**, then rebuilt with **Hibernate** to show how much boilerplate an ORM removes.

## Tech Stack

- Java 21
- Maven
- SQL Server
- JDBC (`mssql-jdbc`)
- Hibernate ORM 6.6

## What This Project Demonstrates

- CRUD operations on books using **plain JDBC** (`PreparedStatement`, try-with-resources, `JOIN` queries)
- The same CRUD operations using **Hibernate** (`Session`, `Transaction`, `persist`, `get`, `merge`, `remove`, HQL)
- A **one-to-many** relationship: one `Author` has many `Book`s (`@OneToMany` / `@ManyToOne`)
- The **N+1 query problem** and how to fix it with `join fetch`
- Money handled with `BigDecimal` mapped to `DECIMAL(10,2)`
- Database credentials kept out of source control

## Project Structure

```
src/main/java/org/example
├── Entity        # Book, Author (Hibernate entities)
├── DAO           # BookDAO (JDBC version)
├── repository    # BookRepository (Hibernate version)
├── util          # HibernateUtil (SessionFactory)
└── Main          # Demo entry point
src/main/resources
├── db.properties.example
└── hibernate.cfg.xml.example
```

## Database Schema

```sql
CREATE DATABASE LibraryDB;
GO

USE LibraryDB;
GO

CREATE TABLE Author (
    id   INT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(100) NOT NULL
);

CREATE TABLE Books (
    Id        INT IDENTITY(1,1) PRIMARY KEY,
    Title     NVARCHAR(100) NOT NULL,
    Price     DECIMAL(10,2),
    author_id INT NULL,
    CONSTRAINT FK_Books_Author FOREIGN KEY (author_id) REFERENCES Author(id)
);
GO
```

## Setup

1. Install SQL Server and enable SQL Server authentication and TCP/IP on port 1433.
2. Run the schema script above.
3. In `src/main/resources`, copy the example files and fill in your own values:
   - `db.properties.example` -> `db.properties`
   - `hibernate.cfg.xml.example` -> `hibernate.cfg.xml`
4. Build and run:
   ```bash
   mvn clean compile
   ```
   Then run `org.example.Main` from your IDE.

> `db.properties` and `hibernate.cfg.xml` contain credentials and are listed in `.gitignore`. Never commit them.

## Example `db.properties`

```properties
db.url=jdbc:sqlserver://localhost:1433;databaseName=LibraryDB;encrypt=true;trustServerCertificate=true
db.username=YOUR_USERNAME
db.password=your_password
```

## The N+1 Problem (Demo)

Loading authors and then touching each author's books triggers one extra query per author:

```java
List<Author> authors = session.createQuery("from Author", Author.class).list();
for (Author a : authors) {
    System.out.println(a.getName() + " -> " + a.getBooks().size()); // 1 query per author
}
```

Fixed with a fetch join (a single query):

```java
session.createQuery(
    "select distinct a from Author a join fetch a.books", Author.class).list();
```


## What I Learned

- The difference between JDBC and an ORM
- How lazy loading works and why it causes N+1 queries
- Basic Git workflow: commit, push, and keeping secrets out of the repository
