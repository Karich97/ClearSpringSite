package webMVC.models;

import java.util.ArrayList;
import java.util.List;

public class User {
    int id;
    String name;
    int age;
    String password;
    String login;
    List<User> friends;

    public User() {
    }
    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.friends = new ArrayList<>();

        this.login = name;
        this.password = "111";
    }
    public User(String name, String login, int age, String password) {
        this.id = 0;
        this.name = name;
        this.age = age;
        this.friends = new ArrayList<>();

        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }
}
