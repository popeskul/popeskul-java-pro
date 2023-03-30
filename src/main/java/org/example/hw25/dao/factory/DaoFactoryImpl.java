package org.example.hw25.dao.factory;

import org.example.hw25.dao.DataBaseConnection;
import org.example.hw25.dao.HomeworkDao;
import org.example.hw25.dao.LessonDao;
import org.example.hw25.dao.impl.HomeworkDaoImpl;
import org.example.hw25.dao.impl.LessonDaoImpl;

import java.sql.SQLException;

public class DaoFactoryImpl implements DaoFactory {
    private final DataBaseConnection dataBaseConnection;

    public DaoFactoryImpl(DataBaseConnection dataBaseConnection) {
        this.dataBaseConnection = dataBaseConnection;
    }

    @Override
    public HomeworkDao createHomeworkDao() throws SQLException {
        return new HomeworkDaoImpl(dataBaseConnection);
    }

    @Override
    public LessonDao createLessonDao() throws SQLException {
        return new LessonDaoImpl(dataBaseConnection);
    }
}

