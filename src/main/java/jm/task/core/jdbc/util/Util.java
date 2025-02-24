package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/q3_db";
    private static final String USERNAME = "bestuser";
    private static final String PASSWORD = "bestuser";

//    public static Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
//    }
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (!connection.isClosed()) {
                System.out.println("Соединение с БД установлено");
            }
        } catch (SQLException s) {
            System.err.println("Ошибка соединения");
        }
        return connection;
    }

    private static SessionFactory sessionFactory = null;
    static  {
        try {
            Properties settings = new Properties();
            settings.setProperty("hibernate.connection.url", "jdbc:mysql://127.0.0.1:3306/q3_db");
            settings.setProperty("hibernate.connection.username", "bestuser");
            settings.setProperty("hibernate.connection.password", "bestuser");
            settings.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
            settings.setProperty("hibernate.hbm2ddl.auto", "create");

            sessionFactory = new org.hibernate.cfg.Configuration()
                    .addProperties(settings)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }

    public static void close() throws HibernateException{
        getSession().close();
    }
}
