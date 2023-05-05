package webMVC.CRUD.DAO;

import org.springframework.stereotype.Component;
import webMVC.models.User;

import java.util.List;

@Component
public interface UserDao {
    List<User> getAllUsers();
    User getUserById(int id);
    void addNewUser(User user);
    int findUserByLoginAndPassword(String login, String password);
    List<User> getFriends(User user);
    void addFriend(User user, User Friend);
}
