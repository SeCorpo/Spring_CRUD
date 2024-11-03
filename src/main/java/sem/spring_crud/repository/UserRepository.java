package sem.spring_crud.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import sem.spring_crud.model.User;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class UserRepository {
    private final List<User> users;
    private final ObjectMapper mapper;
    private final File file;

    public UserRepository() {
        mapper = new ObjectMapper();
        file = new File("users.json");
        users = loadUsers();
    }

    private List<User> loadUsers() {
        try {
            if (file.exists() && file.length() > 0) {
                User[] usersArray = mapper.readValue(file, User[].class);
                return new ArrayList<>(Arrays.asList(usersArray));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to load users from file. Returning an empty list.");
        }
        return new ArrayList<>();
    }

    public boolean save(User user) {
        users.add(user);
        try {
            mapper.writeValue(file, users);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public User findByUsername(String username) {
        return users.stream().filter(u -> u.getUsername().equals(username)).findFirst().orElse(null);
    }
}
