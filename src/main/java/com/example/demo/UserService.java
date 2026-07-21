package com.example.demo;

import java.util.List;

public interface UserService {

    User createUser(User user);

    User insertUser(User newUser);

    List<User> getAllUsers();

    User getUserById(Integer id);

    User updateUser(Integer id, User user);

    void deleteUser(Integer id);
}