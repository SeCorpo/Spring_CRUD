package sem.spring_crud.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String username;
    private String password;

    // No-args constructor needed by Jackson for deserialization
    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
