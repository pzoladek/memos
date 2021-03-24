package io.github.pzoladek.memos.dto;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    @NotNull
    private final String login;

    @NotNull
    private final String password;

    public User(final String login, final String password) {
        this.login = login;
        this.password = password;
    }
}
