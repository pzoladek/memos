package io.github.pzoladek.memos.service;

import javax.annotation.PostConstruct;

import io.github.pzoladek.memos.dto.User;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InitialUserImportingService {

    private final UsersService usersService;
    private final String initialUserLogin;
    private final String initialUserPassword;

    public InitialUserImportingService(final UsersService usersService,
            @Value("${app.initial-user.login}") final String initialUserLogin,
            @Value("${app.initial-user.password}") final String initialUserPassword) {
        this.usersService = usersService;
        this.initialUserLogin = initialUserLogin;
        this.initialUserPassword = initialUserPassword;
    }

    @PostConstruct
    void importUser() {
        log.info("Importing initial user...");
        if (!usersService.exists(initialUserLogin)) {
            final var user = User.builder()
                    .login(initialUserLogin)
                    .password(initialUserPassword)
                    .build();
            usersService.create(user);
            log.info("Initial user has been successfully imported.");
        } else {
            log.info("Aborting initial user's import - user already exists.");
        }
    }
}
