package io.github.pzoladek.memos.service;

import io.github.pzoladek.memos.dto.User;
import io.github.pzoladek.memos.exception.UserException;
import io.github.pzoladek.memos.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public void create(final User user) {
        if (exists(user.getLogin())) {
            throw UserException.alreadyExists(user.getLogin());
        }
        final var userModel = io.github.pzoladek.memos.model.User.builder()
                .login(user.getLogin())
                .password(passwordEncoder.encode(user.getPassword()))
                .build();
        userRepository.save(userModel);
    }

    boolean exists(final String login) {
        return userRepository.findByLogin(login).isPresent();
    }

}
