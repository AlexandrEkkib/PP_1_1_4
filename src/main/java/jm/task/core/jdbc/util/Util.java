package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/q3_db";
    private static final String USERNAME = "bestuser";
    private static final String PASSWORD = "bestuser";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (!connection.isClosed()) {
                System.out.println("Соединение с БД установлено");
            }
        } catch (SQLException s) {
            System.err.println("Ошибка соединения");
            s.printStackTrace(); // Добавлено для вывода информации об ошибке
        }
        return connection;
    }

    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Properties settings = new Properties();
            settings.setProperty("hibernate.connection.url", URL);
            settings.setProperty("hibernate.connection.username", USERNAME);
            settings.setProperty("hibernate.connection.password", PASSWORD);
            settings.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

            return new Configuration()
                    .addProperties(settings)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Initial SessionFactory creation failed." + e); // Более информативное исключение
        }
    }

    public static SessionFactory getSessionFactory() throws HibernateException {
        if (sessionFactory == null) {
            throw new HibernateException("SessionFactory is not initialized.");
        }
        return sessionFactory;
    }

    public static void close() throws HibernateException {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}

