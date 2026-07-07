package ru.itis.shop.user.infrastructure.persistence.jdbc;

import ru.itis.shop.infrastructure.persistence.jdbc.RowMapper;
import ru.itis.shop.user.domain.User;
import ru.itis.shop.user.repository.UserRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryJdbcImpl implements UserRepository {

    private final DataSource dataSource;

    private final RowMapper<User> userRowMapper = row -> new User(
            row.getInt("id"),
            row.getString("name"),
            row.getString("email"),
            row.getString("password"),
            row.getString("profileDescription")
    );

    public UserRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
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
    public Optional<User> findById(Integer id) {
        throw new UnsupportedOperationException("Метод findById еще не реализован для Jdbc");
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery("select * from account")) {
                    while (resultSet.next()) {
                        users.add(userRowMapper.mapRow(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return users;
    }

    @Override
    public List<User> findAllByProfileDescription(String profileDescription) {
        List<User> users = new ArrayList<>();
        String sql = "select * from account where profiledescription = '" + profileDescription + "'";
        try (Connection connection = dataSource.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(sql)) {
                    while (resultSet.next()) {
                        users.add(userRowMapper.mapRow(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return users;
    }
}
