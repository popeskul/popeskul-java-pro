package org.example.hw25.query;

public final class SqlLesson {
    public static final String DROP_TABLE_LESSON = "DROP TABLE IF EXISTS Lesson";
    public static final String CREATE_TABLE_LESSON = """
            CREATE TABLE Lesson (
                id SERIAL PRIMARY KEY,
                name TEXT NOT NULL,
                updatedAt TIMESTAMP,
                homework_id INTEGER REFERENCES Homework(id)
            );""";

    public static final String INSERT_LESSON = "INSERT INTO Lesson (name, homework_id) VALUES (?, ?)";

    public static final String DELETE_LESSON = "DELETE FROM Lesson WHERE id=?";

    public static final String SELECT_ALL_LESSONS = "SELECT Lesson.id, Lesson.name, Homework.id, Homework.name, Homework.description "
            + "FROM Lesson LEFT JOIN Homework ON Lesson.homework_id = Homework.id";

    public static final String SELECT_LESSON_BY_ID = "SELECT Lesson.id, Lesson.name, Homework.id, Homework.name, Homework.description "
            + "FROM Lesson LEFT JOIN Homework ON Lesson.homework_id = Homework.id WHERE Lesson.id=?";
}
