package ru.itis.shop.user.application;

import ru.itis.shop.user.domain.User;
import ru.itis.shop.user.repository.UserRepository;

import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signUp(String email, String password, String profileDescription) {
        User user = new User(email, password, profileDescription);
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

    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
