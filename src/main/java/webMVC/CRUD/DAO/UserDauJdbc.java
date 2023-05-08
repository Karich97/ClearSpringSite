package webMVC.CRUD.DAO;

import org.springframework.stereotype.Component;
import webMVC.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDauJdbc implements UserDao {
    private static final String URL = "jdbc:postgresql://localhost:5432/SpringMVC";
    private static final String user = "karich";
    private static final String password = "123";
    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, user, password);
            Statement statement = connection.createStatement();
            final String sql = """
                    CREATE TABLE IF NOT EXISTS Users (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(20) NOT NULL ,
                        login VARCHAR(20) NOT NULL ,
                        age SERIAL NOT NULL,
                        password VARCHAR(20) NOT NULL
                    );
                    """;
            statement.executeUpdate(sql);
            statement.close();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC DriverLoading Error");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println("JDBC DriverManager Error");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers(int id) {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "select * from users";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setAge(resultSet.getInt("age"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));

                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Get all users error");
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public void addNewUser(User user) {
        try {
            Statement statement = connection.createStatement();

            String sql = "insert into users values(" + 1 + ", '" + user.getName() + "'" +
                         ", '" + user.getLogin() + "'" +
                         ", " + user.getAge() +
                         ", '" + user.getPassword() + "')";
            statement.executeUpdate(sql);
            statement.close();
            System.out.println("was added user " + user.getName() + " with password  " + user.getPassword());
        } catch (SQLException e) {
            System.out.println("Adding JDBC error");
            throw new RuntimeException(e);
        }
    }

    @Override
    public int findUserByLoginAndPassword(String login, String password) {
        return 0;
    }


    @Override
    public void addFriend(int user, int Friend) {

    }

    @Override
    public void updateInformation(int id, User user) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void deleteFriend(int user, int friend) {

    }
}
