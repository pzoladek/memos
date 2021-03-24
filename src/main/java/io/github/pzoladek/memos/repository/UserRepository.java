package io.github.pzoladek.memos.repository;

import java.util.Optional;

import io.github.pzoladek.memos.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(final String login);
}
