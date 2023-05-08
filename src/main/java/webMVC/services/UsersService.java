package webMVC.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webMVC.CRUD.DAO.UserDao;
import webMVC.models.User;
import webMVC.repositories.UsersRepository;

import java.util.List;

@Component
@Service
@Transactional(readOnly = true, value = "transactionManager")
public class UsersService implements UserDao{
    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<User> getAllUsers(int id) {
        List<User> userList = usersRepository.findAll();
        userList = userList.stream().filter(user -> user.getId() != id).toList();
        return userList;
    }

    @Override
    public User getUserById(int id) {
        return usersRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(value = "transactionManager")
    public void addNewUser(User user) {
        usersRepository.save(user);
    }

    @Override
    public int findUserByLoginAndPassword(String login, String password) {
        List<User> userList = usersRepository.findByLoginAndPassword(login, password);
        if (userList.isEmpty()) return 0;
        else return userList.get(0).getId();
    }

    @Override
    @Transactional(value = "transactionManager")
    public void addFriend(int userId, int friendId) {
        User user = usersRepository.findById(userId).orElse(null);
        User friend = usersRepository.findById(friendId).orElse(null);
        if (user != null && friend != null){
            user.getFriends().add(friend);
            friend.getFriends().add(user);
        }
    }

    @Override
    @Transactional(value = "transactionManager")
    public void updateInformation(int id, User user) {
        user.setId(id);
        User oldVersionUser = usersRepository.findById(id).orElse(null);
        if (oldVersionUser != null){
            user.setFriends(oldVersionUser.getFriends());
            usersRepository.save(user);
        }
    }

    @Override
    @Transactional(value = "transactionManager")
    public void delete(int id) {
        usersRepository.deleteById(id);
    }

    @Override
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
