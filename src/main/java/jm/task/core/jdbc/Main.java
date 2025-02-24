package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        // 1.Создание таблицы
        userService.createUsersTable();

//         2.Добавление 4 пользователей
        userService.saveUser("Alice", "Smith", (byte) 30);
        userService.saveUser("Bob", "Brown", (byte) 25);
        userService.saveUser("Charlie", "Davis", (byte) 28);
        userService.saveUser("Diana", "Wilson", (byte) 22);

        // 3.Получение всех пользователей
        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }

//         4.Очистка таблицы
        userService.cleanUsersTable();

        //5. Удаление таблицы
//        userService.dropUsersTable();

  }
}
