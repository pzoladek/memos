package io.github.pzoladek.memos.repository;

import java.util.List;
import java.util.Optional;

import io.github.pzoladek.memos.model.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoldersRepository extends JpaRepository<Folder, Long> {

    boolean existsByNameAndAuthor(final String name, final String author);

    List<Folder> findByAuthor(final String author);

    Optional<Folder> findByAuthorAndName(final String author, final String name);
}
