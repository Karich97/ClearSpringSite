package webMVC.CRUD.DAO;

import org.springframework.stereotype.Component;
import webMVC.models.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoList implements UserDao{
    private static int PEOPLE_COUNT;
    private final List<User> userList;
    {
        userList = new ArrayList<>();
        User stas = new User(++PEOPLE_COUNT, "Stas", 26);
        User pavel = new User(++PEOPLE_COUNT, "Pavel", 30);
        User inga = new User(++PEOPLE_COUNT, "Inga", 33);
        User alex = new User(++PEOPLE_COUNT, "Alex", 27);
        User stacy = new User(++PEOPLE_COUNT, "Stacy", 18);
        User nick = new User(++PEOPLE_COUNT, "Nick", 44);
        userList.add(stas);
        userList.add(pavel);
        userList.add(inga);
        userList.add(alex);
        userList.add(stacy);
        userList.add(nick);
        addFriend(stas, stacy);
        addFriend(stas, pavel);
        addFriend(stas, alex);
        addFriend(stacy, inga);
    }

    @Override
    public List<User> getAllUsers() {
        return userList;
    }

    @Override
    public User getUserById(int id) {
        return userList.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }

    @Override
    public void addNewUser(User user) {
        user.setId(++PEOPLE_COUNT);
        userList.add(user);
    }

    @Override
    public int findUserByLoginAndPassword(String login, String password) {
        User validUser = userList.stream().filter(user -> user.getLogin().equals(login)).filter(user -> user.getPassword().equals(password)).findAny().orElse(null);
        if (validUser == null) return 0;
        return validUser.getId();
    }

    @Override
    public List<User> getFriends(User user) {
        return user.getFriends();
    }

    @Override
    public void addFriend(User user, User friend) {
        user.getFriends().add(friend);
        friend.getFriends().add(user);
    }

}
