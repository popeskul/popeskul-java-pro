package org.example.hw25;

import org.example.hw25.dao.DataBaseConnection;
import org.example.hw25.dao.HomeworkDao;
import org.example.hw25.dao.LessonDao;
import org.example.hw25.dao.factory.DaoFactory;
import org.example.hw25.dao.factory.DaoFactoryImpl;
import org.example.hw25.dao.impl.DataBaseConnectionImpl;
import org.example.hw25.model.Homework;
import org.example.hw25.model.Lesson;
import org.example.hw25.query.SqlHomework;
import org.example.hw25.query.SqlLesson;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/ithillel";
        String user = "postgres";
        String password = "postgres";

        try (DataBaseConnection dataBaseConnection = new DataBaseConnectionImpl(url, user, password)) {
            initDB(dataBaseConnection);

            DaoFactory daoFactory = new DaoFactoryImpl(dataBaseConnection);

            HomeworkDao homeworkDao = daoFactory.createHomeworkDao();
            LessonDao lessonDao = daoFactory.createLessonDao();

            Homework homework = new Homework(1, "Homework 1", "Description 1");
            homeworkDao.addHomework(homework);

            Lesson lesson = new Lesson(1, "Lesson 1", homework);
            lessonDao.addLesson(lesson);

            printLessons(lessonDao, homeworkDao);

            Lesson lessonById = lessonDao.getLessonById(1);
            if (lessonById != null) {
                homework = homeworkDao.getHomeworkById(lessonById.getHomework().getId());
                lessonById.setHomework(homework);
                System.out.println(lessonById);
            }

            lessonDao.deleteLesson(1);

            Lesson deletedLesson = lessonDao.getLessonById(1);
            if (deletedLesson == null) {
                System.out.println("Lesson with id 1 was deleted");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initDB(DataBaseConnection dataBaseConnectionImpl) {
        try (Connection connection = dataBaseConnectionImpl.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(SqlLesson.DROP_TABLE_LESSON);
            statement.execute(SqlHomework.DROP_TABLE_HOMEWORK);

            statement.executeUpdate(SqlHomework.CREATE_TABLE_HOMEWORK);
            statement.executeUpdate(SqlLesson.CREATE_TABLE_LESSON);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printLessons(LessonDao lessonDao, HomeworkDao homeworkDao) throws SQLException {
        ArrayList<Lesson> lessons = lessonDao.getAllLessons();
        for (Lesson l : lessons) {
            Homework h = homeworkDao.getHomeworkById(l.getHomework().getId());
            l.setHomework(h);
            System.out.println(l);
        }
    }
}
