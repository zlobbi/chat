package km.hw72.chat.service;

import km.hw72.chat.DTO.UserDTO;
import km.hw72.chat.model.User;
import km.hw72.chat.repository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {
    private final UserRepository repository;

    public boolean isUserExitsts(String session) {
        return repository.existsBySession(session);
    }

    public void saveUser(User u) {
        repository.save(u);
    }

    public User getUserBySession(String session) {
        return repository.findBySession(session);
    }
}
