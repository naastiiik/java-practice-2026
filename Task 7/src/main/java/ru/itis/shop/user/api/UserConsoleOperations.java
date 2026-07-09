package ru.itis.shop.user.api;

import ru.itis.shop.user.api.dto.UserDto;
import ru.itis.shop.user.application.UserService;
import ru.itis.shop.user.domain.User;
import ru.itis.shop.user.repository.UserRepository;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserConsoleOperations {

    private final UserService userService;
    private final Scanner scanner;

    public UserConsoleOperations(UserService userService) {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        printUserMenu();

        String command = scanner.nextLine();
        switch (command) {
            case "1": {
                signUp();
            } break;
            case "2": {
                signIn();
            } break;
            case "3": {
                findById();
            } break;
            case "4": {
                updateProfile();
            } break;
            case "5": {
                printAllUsers();
            } break;
            case "6": {
                findAllByProfileDescription();
            } break;
            case "7": {
                findUserByEmail();
            } break;
            case "0": {
                System.exit(0);
            }
        }
    }

    private static void printUserMenu() {
        System.out.println("1. Регистрация пользователя");
        System.out.println("2. Вход в систему");
        System.out.println("3. Найти пользователя по id");
        System.out.println("4. Обновить описание профиля по email");
        System.out.println("5. Получить информацию обо всех пользователях");
        System.out.println("6. Показать информацию о пользователях с заданным profileDescription");
        System.out.println("7. Показать информацию о пользователе по email");
        System.out.println("0. Выход");
    }

    private void signUp() {
        System.out.println("Сейчас будем регистрировать пользователя");
        System.out.println("Введите name:");
        String name = scanner.nextLine();
        System.out.println("Введите email:");
        String email = scanner.nextLine();
        System.out.println("Введите password:");
        String password = scanner.nextLine();
        System.out.println("Введите описание профиля:");
        String profileDescription = scanner.nextLine();
        userService.signUp(name, email, password, profileDescription);
        System.out.println("Пользователь успешно зарегистрирован");
    }

    private void signIn() {
        System.out.println("Вы можете войти в приложение");
        System.out.println("Введите email:");
        String email = scanner.nextLine();
        System.out.println("Введите password:");
        String password = scanner.nextLine();

        if (userService.signIn(email, password)) {
            System.out.println("Вы вошли в приложение");
        } else {
            System.out.println("Email или пароль не верны");
        }
    }

    private void findById() {
        System.out.println("Введите id пользователя для поиска:");
        Integer searchId = Integer.parseInt(scanner.nextLine());

        Optional<UserDto> foundUserOpt = userService.findById(searchId);
        if (foundUserOpt.isPresent()) {
            UserDto userDto = foundUserOpt.get();
            System.out.println("Пользователь найден");
            System.out.println("id: " + userDto.getId() + " | email: " + userDto.getEmail());
        } else {
            System.out.println("Пользователь с таким id не найден");
        }
    }

    private void updateProfile() {
        System.out.println("Обновление описания профиля");
        System.out.println("Введите email:");
        String email = scanner.nextLine();

        Optional<UserDto> userOptional = userService.findDtoByEmail(email);
        if (userOptional.isPresent()) {
            UserDto user = userOptional.get();
            System.out.println("Пользователь найден");
            System.out.println("Текущее описание профиля: " + user.getProfileDescription());
            System.out.println("Введите новое описание профиля");
            String updateDescription = scanner.nextLine();

            boolean isSuccess = userService.updateProfile(email, updateDescription);
            if (isSuccess) {
                System.out.println("Описание пользователя успешно обновлено");
            }
        } else {
            System.out.println("Пользователь с таким email не найден");
        }
    }

    private void printAllUsers() {
        System.out.println("Список всех пользователей");
        List<UserDto> users = userService.findAll();
        if (users.isEmpty()) {
            System.out.println("Пользователей пока нет.");
        } else {
            for (UserDto user: users) {
                System.out.println("id: " + user.getId() + " | Email: " + user.getEmail() + " | Profile description: " + user.getProfileDescription());
            }
        }
    }

    private void findAllByProfileDescription() {
        System.out.println("Поиск пользователей по описанию профиля");
        System.out.println("Введите profileDescription:");
        String description = scanner.nextLine();

        List<UserDto> users = userService.findAllByProfileDescription(description);
        if (users.isEmpty()) {
            System.out.println("Пользователей с таким описанием профиля не найдено");
        } else {
            System.out.println("Найденные пользователи");
            for (UserDto user : users) {
                System.out.println("id: " + user.getId() +
                        " | Email: " + user.getEmail());
            }
        }
    }

    private void findUserByEmail() {
        System.out.println("Поиск по email");
        System.out.println("Введите email:");
        String email = scanner.nextLine();

        try {
            UserDto user = userService.getUserByEmail(email);
            System.out.println("Пользователь найден:");
            System.out.println("id: " + user.getId() + " | email: " + user.getEmail() + " | profile description: " + user.getProfileDescription());
        } catch (Exception e) {
            System.out.println("Пользователь с таким email не найден");
        }

    }
}
