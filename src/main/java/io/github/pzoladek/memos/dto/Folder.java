package io.github.pzoladek.memos.dto;

import java.util.Set;
import java.util.stream.Collectors;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Folder extends FolderRequest {

    private final Set<MemoRequest> memoRequests;

    public static Folder fromModel(final io.github.pzoladek.memos.model.Folder model) {
        return Folder.builder()
                .name(model.getName())
                .description(model.getDescription())
                .memoRequests(fromModel(model.getMemos()))
                .publicAccess(model.isPublicAccess())
                .build();
    }

    private static Set<MemoRequest> fromModel(final Set<io.github.pzoladek.memos.model.Memo> models) {
        return models.stream()
                .map(Memo::fromModel)
                .collect(Collectors.toSet());
    }
}
