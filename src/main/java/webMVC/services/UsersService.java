package webMVC.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webMVC.models.User;
import webMVC.repositories.UsersRepository;

import java.util.List;

@Component
@Service
@Transactional(readOnly = true, value = "transactionManager")
public class UsersService{
    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    public List<User> getAllUsers(int id) {
        List<User> userList = usersRepository.findAll();
        userList = userList.stream().filter(user -> user.getId() != id).toList();
        return userList;
    }


    public User getUserById(int id) {
        return usersRepository.findById(id).orElse(null);
    }


    @Transactional(value = "transactionManager")
    public void addNewUser(User user) {
        usersRepository.save(user);
    }


    public int findUserByLoginAndPassword(String login, String password) {
        List<User> userList = usersRepository.findByLoginAndPassword(login, password);
        if (userList.isEmpty()) return 0;
        else return userList.get(0).getId();
    }


    @Transactional(value = "transactionManager")
    public void addFriend(int userId, int friendId) {
        User user = usersRepository.findById(userId).orElse(null);
        User friend = usersRepository.findById(friendId).orElse(null);
        if (user != null && friend != null){
            user.getFriends().add(friend);
            friend.getFriends().add(user);
        }
    }


    @Transactional(value = "transactionManager")
    public void updateInformation(int id, User user) {
        user.setId(id);
        User oldVersionUser = usersRepository.findById(id).orElse(null);
        if (oldVersionUser != null){
            user.setFriends(oldVersionUser.getFriends());
            usersRepository.save(user);
        }
    }


    @Transactional(value = "transactionManager")
    public void delete(int id) {
        usersRepository.deleteById(id);
    }

    @Transactional(value = "transactionManager")
    public void deleteFriend(int userId, int friendId) {
        User user = usersRepository.findById(userId).orElse(null);
        User friend = usersRepository.findById(friendId).orElse(null);
        if (user != null && friend != null){
            user.getFriends().remove(friend);
            friend.getFriends().remove(user);
            usersRepository.save(friend);
            usersRepository.save(user);
        }
    }
}
