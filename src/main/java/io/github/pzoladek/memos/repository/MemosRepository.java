package io.github.pzoladek.memos.repository;

import java.util.List;

import io.github.pzoladek.memos.model.Memo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemosRepository extends JpaRepository<Memo, Long> {

    List<Memo> findByFolderNameAndAuthor(final String folderName, final String author);
}