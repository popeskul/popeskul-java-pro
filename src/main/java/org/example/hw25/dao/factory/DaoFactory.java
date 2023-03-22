package org.example.hw25.dao.factory;

import org.example.hw25.dao.HomeworkDao;
import org.example.hw25.dao.LessonDao;

import java.sql.SQLException;

public interface DaoFactory {
    HomeworkDao createHomeworkDao() throws SQLException;

    LessonDao createLessonDao() throws SQLException;
}
