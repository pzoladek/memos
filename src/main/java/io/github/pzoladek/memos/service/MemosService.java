package io.github.pzoladek.memos.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import io.github.pzoladek.memos.configuration.UserAuthenticationService.MemosUserDetails;
import io.github.pzoladek.memos.dto.MemoRequest;
import io.github.pzoladek.memos.exception.FolderException;
import io.github.pzoladek.memos.repository.FoldersRepository;
import io.github.pzoladek.memos.repository.MemosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemosService {

    private final MemosRepository memosRepository;
    private final FoldersRepository foldersRepository;

    public MemoRequest create(final String folderName, final MemoRequest memoRequest) {
        final var author = getAuthor();
        if (!foldersRepository.existsByNameAndAuthor(folderName, author)) {
            throw FolderException.notFound(folderName, author);
        }
        final var model = io.github.pzoladek.memos.model.Memo.fromRequest(memoRequest, folderName, author);
        final var savedModel = memosRepository.save(model);
        return MemoRequest.fromModel(savedModel);
    }

    public List<MemoRequest> getMemos(final String folderName) {
        final var models = memosRepository.findByFolderNameAndAuthor(folderName, getAuthor());
        if (models.isEmpty()) {
            throw FolderException.notFound(folderName, getAuthor());
        }
        final var memos = models.stream()
                .map(MemoRequest::fromModel)
                .collect(Collectors.toList());
        Collections.shuffle(memos);
        return memos;
    }

    private String getAuthor() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(auth -> (MemosUserDetails) auth.getPrincipal())
                .map(MemosUserDetails::getUsername)
                .orElse("not authenticated");
    }
}
