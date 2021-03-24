package io.github.pzoladek.memos.controller;

import java.util.List;

import io.github.pzoladek.memos.dto.FolderRequest;
import io.github.pzoladek.memos.dto.MemoRequest;
import io.github.pzoladek.memos.service.FoldersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MemosController {

    private final FoldersService foldersService;

    @GetMapping("/folders")
    public ResponseEntity<?> getFolders() {
        final var folders = foldersService.getFolderNames();
        return ResponseEntity.ok(folders);
    }

    @GetMapping("/folders/{folderName}")
    public ResponseEntity<?> getFolder(@PathVariable("folderName") final String folderName) {
        final var folder = foldersService.getFolder(folderName);
        return ResponseEntity.ok(folder);
    }

    @PostMapping("/folders")
    public ResponseEntity<?> createFolder(@RequestBody final FolderRequest folderRequest) {
        final var folder = foldersService.createFolder(folderRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(folder);
    }

    @PostMapping("/folders/{folderName}")
    public ResponseEntity<?> createMemo(@PathVariable("folderName") final String folderName,
            @RequestBody final MemoRequest memoRequest) {
        final var memo = foldersService.createMemo(folderName, memoRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(memo);
    }

    @DeleteMapping("/folders/{folderName}")
    public void delete(@PathVariable("folderName") final String folderName,
            @RequestParam("memos") final List<Long> memosIds) {
        foldersService.delete(folderName, memosIds);
    }
}
