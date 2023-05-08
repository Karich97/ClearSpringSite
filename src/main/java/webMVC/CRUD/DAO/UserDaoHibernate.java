package webMVC.CRUD.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import webMVC.models.User;

import java.util.List;

@Component
public class UserDaoHibernate implements UserDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select us from User us", User.class).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }

    @Override
    @Transactional
    public void addNewUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public int findUserByLoginAndPassword(String login, String password) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("from User where login = :login and password = :password", User.class);
        if (query == null) return 0;
        query.setParameter("login", login);
        query.setParameter("password", password);
        return query.uniqueResult().getId();
    }

    @Override
    @Transactional
    public void addFriend(int userId, int friendId) {
        Session session = sessionFactory.getCurrentSession();
        System.out.println(userId + " " + friendId);
        User user = session.get(User.class, userId);
        User friend = session.get(User.class, friendId);
        user.getFriends().add(friend);
        System.out.println("added?");

        // session.save(user);
    }

    @Override
    @Transactional
    public void updateInformation(int id, User user) {
        Session session = sessionFactory.getCurrentSession();
        User person = session.get(User.class, id);
        person.setName(user.getName());
        person.setAge(user.getAge());
        person.setLogin(user.getLogin());
        person.setPassword(user.getPassword());
    }

    @Override
    @Transactional
    public void delete(int id) {
        System.out.println(id + " deleted");
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(User.class, id));
    }

    @Override
    @Transactional
    public void deleteFriend(int userId, int friendId) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, userId);
        user.getFriends().remove(session.get(User.class, friendId));
        session.update(user);
    }
}
