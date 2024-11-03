package sem.spring_crud.service;

import org.springframework.stereotype.Service;
import sem.spring_crud.model.User;
import sem.spring_crud.repository.UserRepository;

import java.security.MessageDigest;

@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean register(String username, String password) {
        if (userRepository.findByUsername(username) == null) {
            String hashedPassword = hashPassword(password);
            return userRepository.save(new User(username, hashedPassword));
        }
        return false;
    }

    public boolean login(String username, String password) {
        User user = userRepository.findByUsername(username);
        return user != null && user.getPassword().equals(hashPassword(password));
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(Integer.toHexString(0xFF & b));
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
}
