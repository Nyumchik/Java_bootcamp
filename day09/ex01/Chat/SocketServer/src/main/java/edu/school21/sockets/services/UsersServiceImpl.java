package edu.school21.sockets.services;

import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.MessagesRepository;
import edu.school21.sockets.models.Message;
import edu.school21.sockets.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class UsersServiceImpl implements UsersService {
    private PasswordEncoder passwordEncoder;
    private UsersRepository usersRepository;
    private MessagesRepository messagesRepository;

    @Autowired
    public UsersServiceImpl(PasswordEncoder passwordEncoder, UsersRepository usersRepository, MessagesRepository messagesRepository) {
        this.passwordEncoder = passwordEncoder;
        this.usersRepository = usersRepository;
        this.messagesRepository = messagesRepository;
    }

    @Override
    public void signUp(User user) {
        if (usersRepository.findByUsername(user.getName()).isPresent()) {
            throw new RuntimeException(user.getName() + " already exist");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersRepository.save(user);
    }

    @Override
    public boolean signIn(User user) {
        Optional<User> test = usersRepository.findByUsername(user.getName());

        if (test.isPresent() && passwordEncoder.matches(user.getPassword(), test.get().getPassword())) {
            return true;
        }
        return false;
    }

    @Override
    public void saveMessage(String username, String message) {
        messagesRepository.save(new Message(username, message));
    }
}
