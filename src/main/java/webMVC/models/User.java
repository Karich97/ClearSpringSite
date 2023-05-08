package webMVC.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @NotEmpty(message = "name should not be empty")
    @Size(min = 2, max = 20, message = "name name should be between 2 and 20 characters")
    String name;
    @Min(value = 1, message = "Age should be grater than 1")
    int age;
    @Size(min = 3, message = "password should not be less than 3 characters")
    String password;
    @Size(min = 2, message = "login should not be less than 3 characters")
    String login;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private Set<User> friends = new HashSet<>();

    public User() {
    }

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.login = name;
        this.password = "111";
    }

    public User(String name, String login, int age, String password) {
        this.id = 0;
        this.name = name;
        this.age = age;
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

    public Set<User> getFriends() {
        System.out.println("return friends");
        System.out.println(friends.isEmpty());
        if (friends.isEmpty()) System.out.println(0);
        return friends;
    }
}
