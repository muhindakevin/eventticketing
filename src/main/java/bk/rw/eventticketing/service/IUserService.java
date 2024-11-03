package bk.rw.eventticketing.service;

import java.util.List;

import bk.rw.eventticketing.model.User;

public interface IUserService {
    User createUser(User user) throws Exception;
    boolean authenticateUser(String email, String password) throws Exception;
    List<User> getUsers();
    User getUser(String email);
    void deleteUser(String email);
}
