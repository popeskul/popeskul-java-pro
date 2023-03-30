package org.example.hw25.dao.impl;

import org.example.hw25.dao.DataBaseConnection;
import org.example.hw25.dao.HomeworkDao;
import org.example.hw25.model.Homework;
import org.example.hw25.query.SqlHomework;

import java.sql.*;
import java.util.ArrayList;

public class HomeworkDaoImpl implements HomeworkDao {
    private final DataBaseConnection dataBaseConnection;

    public HomeworkDaoImpl(DataBaseConnection dataBaseConnection) {
        this.dataBaseConnection = dataBaseConnection;
    }

    public void addHomework(Homework homework) throws SQLException {
        try (Connection connection = dataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlHomework.INSERT_HOMEWORK)) {
            statement.setString(1, homework.getName());
            statement.setString(2, homework.getDescription());
            statement.executeUpdate();
        }
    }

    public void deleteHomework(int id) throws SQLException {
        try (Connection connection = dataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlHomework.DELETE_HOMEWORK)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public ArrayList<Homework> getAllHomework() throws SQLException {
        ArrayList<Homework> homeworkList = new ArrayList<>();
        try (Connection connection = dataBaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SqlHomework.SELECT_ALL_HOMEWORK)) {
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String description = resultSet.getString(3);
                Homework homework = new Homework(id, name, description);
                homeworkList.add(homework);
            }
        }
        return homeworkList;
    }

    public Homework getHomeworkById(int id) throws SQLException {
        try (Connection connection = dataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlHomework.SELECT_HOMEWORK_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString(2);
                    String description = resultSet.getString(3);
                    return new Homework(id, name, description);
                } else {
                    return null;
                }
            }
        }
    }
}
