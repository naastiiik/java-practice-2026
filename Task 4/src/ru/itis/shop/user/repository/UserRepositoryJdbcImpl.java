package ru.itis.shop.user.repository;

import ru.itis.shop.user.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryJdbcImpl implements UserRepository {
    private final String dbUrl;
    private final String dbUser;
    private final String dbPassword;

    public UserRepositoryJdbcImpl(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    @Override
    public void save(User user) {
        throw new UnsupportedOperationException("Метод save еще не реализован для Jdbc");
    }

    @Override
    public void update(User user) {
        throw new UnsupportedOperationException("Метод update еще не реализован для Jdbc");
    }

    @Override
    public Optional<User> findByEmail(String email) {
        throw new UnsupportedOperationException("Метод findByEmail еще не реализован для Jdbc");
    }

    @Override
    public Optional<User> findById(String id) {
        throw new UnsupportedOperationException("Метод findById еще не реализован для Jdbc");
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        String sql = "select * from users";

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String profileDescription = resultSet.getString("profile_description");

                users.add(new User(id, name, email, password, profileDescription));
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка при получении всех пользователей", e);
        }

        return users;
    }
}
