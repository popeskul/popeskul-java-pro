package org.example.hw25.query;

public class SqlHomework {
    public static final String INSERT_HOMEWORK = "INSERT INTO Homework (name, description) VALUES (?, ?)";

    public static final String DELETE_HOMEWORK = "DELETE FROM Homework WHERE id=?";

    public static final String SELECT_ALL_HOMEWORK = "SELECT * FROM Homework";

    public static final String SELECT_HOMEWORK_BY_ID = "SELECT * FROM Homework WHERE id=?";

    public static final String DROP_TABLE_HOMEWORK = "DROP TABLE IF EXISTS Homework";

    public static final String CREATE_TABLE_HOMEWORK = """
            CREATE TABLE Homework (
                id SERIAL PRIMARY KEY,
                name TEXT NOT NULL,
                description TEXT
            );""";
}
