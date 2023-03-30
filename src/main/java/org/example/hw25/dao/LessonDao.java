package org.example.hw25.dao;

import org.example.hw25.model.Lesson;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LessonDao {
    void addLesson(Lesson lesson) throws SQLException;

    void deleteLesson(int id) throws SQLException;

    ArrayList<Lesson> getAllLessons() throws SQLException;

    Lesson getLessonById(int id) throws SQLException;
}
