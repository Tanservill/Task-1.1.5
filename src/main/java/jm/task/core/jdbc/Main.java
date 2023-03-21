package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {


        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Keks","Keksovich", (byte) 23);
        userService.saveUser("Keks2","Keksovich2", (byte) 24);
        userService.saveUser("Keks3","Keksovich3", (byte) 25);
        userService.saveUser("Keks4","Keksovich4", (byte) 26);

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
