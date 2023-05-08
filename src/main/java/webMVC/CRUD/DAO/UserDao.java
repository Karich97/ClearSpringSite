package webMVC.CRUD.DAO;

import webMVC.models.User;

import java.util.List;


public interface UserDao {
    List<User> getAllUsers(int id);

    User getUserById(int id);

    void addNewUser(User user);

    int findUserByLoginAndPassword(String login, String password);

    void addFriend(int userId, int friendId);

    void updateInformation(int id, User user);

    void delete(int id);

    void deleteFriend(int userId, int friendId);
}
