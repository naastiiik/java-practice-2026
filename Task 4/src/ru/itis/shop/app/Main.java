package ru.itis.shop.app;

import ru.itis.shop.user.api.UserConsoleOperations;
import ru.itis.shop.user.application.UserService;
import ru.itis.shop.user.infrastructure.persistence.UserFileRepository;
import ru.itis.shop.user.infrastructure.persistence.UserMapper;
import ru.itis.shop.user.repository.UserRepository;
import ru.itis.shop.user.repository.UserRepositoryJdbcImpl;

public class Main {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/java_2026";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres";

    public static void main(String[] args) {

        UserRepository userRepository = new UserRepositoryJdbcImpl(DB_URL, DB_USER, DB_PASSWORD);

        UserService userService = new UserService(userRepository);
        UserConsoleOperations operations = new UserConsoleOperations(userService);
        while(true) {
            operations.showMenu();
        }

    }
}