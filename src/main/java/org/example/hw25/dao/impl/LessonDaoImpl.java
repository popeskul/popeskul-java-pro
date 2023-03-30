package org.example.hw25.dao.impl;

import org.example.hw25.dao.DataBaseConnection;
import org.example.hw25.dao.LessonDao;
import org.example.hw25.model.Homework;
import org.example.hw25.model.Lesson;
import org.example.hw25.query.SqlLesson;

import java.sql.*;
import java.util.ArrayList;

public class LessonDaoImpl implements LessonDao {
    private final DataBaseConnection dataBaseConnection;

    public LessonDaoImpl(DataBaseConnection dataBaseConnection) {
        this.dataBaseConnection = dataBaseConnection;
    }

    public void addLesson(Lesson lesson) throws SQLException {
        try (Connection connection = dataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlLesson.INSERT_LESSON)) {
            statement.setString(1, lesson.getName());
            statement.setInt(2, lesson.getHomework().getId());
            statement.executeUpdate();
        }
    }

    public void deleteLesson(int id) throws SQLException {
        try (Connection connection = dataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlLesson.DELETE_LESSON)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public ArrayList<Lesson> getAllLessons() throws SQLException {
        ArrayList<Lesson> lessons = new ArrayList<>();
        try (Connection connection = dataBaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SqlLesson.SELECT_ALL_LESSONS)) {
            while (resultSet.next()) {
                int lessonId = resultSet.getInt(1);
                String lessonName = resultSet.getString(2);
                int homeworkId = resultSet.getInt(3);
                String homeworkName = resultSet.getString(4);
                String homeworkDescription = resultSet.getString(5);
                Homework homework = new Homework(homeworkId, homeworkName, homeworkDescription);
                Lesson lesson = new Lesson(lessonId, lessonName, homework);
                lessons.add(lesson);
            }
        }
        return lessons;
    }

    public Lesson getLessonById(int id) throws SQLException {
        try (Connection connection = dataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlLesson.SELECT_LESSON_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int lessonId = resultSet.getInt(1);
                    String lessonName = resultSet.getString(2);
                    int homeworkId = resultSet.getInt(3);
                    String homeworkName = resultSet.getString(4);
                    String homeworkDescription = resultSet.getString(5);
                    Homework homework = new Homework(homeworkId, homeworkName, homeworkDescription);
                    return new Lesson(lessonId, lessonName, homework);
                } else {
                    return null;
                }
            }
        }
    }
}
