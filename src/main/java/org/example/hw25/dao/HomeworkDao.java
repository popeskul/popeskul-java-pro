package org.example.hw25.dao;

import org.example.hw25.model.Homework;

import java.sql.SQLException;
import java.util.ArrayList;

public interface HomeworkDao {
    void addHomework(Homework homework) throws SQLException;

    void deleteHomework(int id) throws SQLException;

    ArrayList<Homework> getAllHomework() throws SQLException;

    Homework getHomeworkById(int id) throws SQLException;
}
