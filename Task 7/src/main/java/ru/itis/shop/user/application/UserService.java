package ru.itis.shop.user.application;

import ru.itis.shop.user.api.dto.UserDto;
import ru.itis.shop.user.domain.User;
import ru.itis.shop.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getEmail(), user.getProfileDescription());
    }

    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(RuntimeException::new);

        return toDto(user);
    }

    public void signUp(String name, String email, String password, String profileDescription) {
        User user = new User(name, email, password, profileDescription);
        userRepository.save(user);
    }

    public boolean signIn(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.getPassword().equals(password);
        } else {
            return false;
        }
        // return userOptional.map(user -> user.getPassword().equals(password)).orElse(false);
    }

    public boolean updateProfile(String email, String profileDescription) {
        Optional<User> userUpdate = userRepository.findByEmail(email);

        if (userUpdate.isPresent()) {
            User user = userUpdate.get();

            user.setProfileDescription(profileDescription);

            userRepository.update(user);
            return true;
        }
        return false;
    }

    public Optional<UserDto> findById(Integer id) {
        return userRepository.findById(id).map(this::toDto);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<UserDto> findDtoByEmail(String email) {
        return userRepository.findByEmail(email).map(this::toDto);
    }

    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<UserDto> findAllByProfileDescription(String profileDescription) {
        return userRepository.findAllByProfileDescription(profileDescription).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
