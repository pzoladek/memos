package io.github.pzoladek.memos.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import io.github.pzoladek.memos.configuration.UserAuthenticationService.MemosUserDetails;
import io.github.pzoladek.memos.dto.Folder;
import io.github.pzoladek.memos.dto.FolderRequest;
import io.github.pzoladek.memos.dto.Memo;
import io.github.pzoladek.memos.dto.MemoRequest;
import io.github.pzoladek.memos.exception.FolderException;
import io.github.pzoladek.memos.repository.FoldersRepository;
import io.github.pzoladek.memos.repository.MemosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FoldersService {

    private final FoldersRepository foldersRepository;
    private final MemosRepository memosRepository;

    public Set<String> getFolderNames() {
        final var author = getAuthor();
        return foldersRepository.findByAuthor(author).stream()
                .map(io.github.pzoladek.memos.model.Folder::getName)
                .collect(Collectors.toSet());
    }

    public Folder createFolder(final FolderRequest folderRequest) {
        final var author = getAuthor();
        if (foldersRepository.existsByNameAndAuthor(folderRequest.getName(), author)) {
            throw FolderException.alreadyExists(folderRequest.getName(), author);
        }
        final var model = io.github.pzoladek.memos.model.Folder.fromRequest(folderRequest, author);
        final var savedModel = foldersRepository.save(model);
        return Folder.fromModel(savedModel);
    }

    public Folder getFolder(final String folderName) {
        final var author = getAuthor();
        final var model = foldersRepository.findByAuthorAndName(author, folderName)
                .orElseThrow(() -> FolderException.notFound(folderName, author));
        return Folder.fromModel(model);
    }

    public Memo createMemo(final String folderName, final MemoRequest memoRequest) {
        final var author = getAuthor();
        if (!foldersRepository.existsByNameAndAuthor(folderName, author)) {
            throw FolderException.notFound(folderName, author);
        }
        final var model = io.github.pzoladek.memos.model.Memo.fromRequest(memoRequest, folderName, author);
        final var savedModel = memosRepository.save(model);
        return Memo.fromModel(savedModel);
    }

    public void delete(final String folderName, final List<Long> memosIds) {
        final var author = getAuthor();
        final var folder = foldersRepository.findByAuthorAndName(author, folderName)
                .orElseThrow(() -> FolderException.notFound(folderName, author));
        final var ids = Optional.ofNullable(memosIds)
                .orElseGet(Collections::emptyList);
        if (ids.isEmpty()) {
            foldersRepository.delete(folder);
        } else {
            ids.forEach(memosRepository::deleteById);
        }
    }

    private String getAuthor() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(auth -> (MemosUserDetails) auth.getPrincipal())
                .map(MemosUserDetails::getUsername)
                .orElse("not authenticated");
    }
}
