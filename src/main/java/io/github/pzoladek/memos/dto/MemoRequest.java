package io.github.pzoladek.memos.dto;

import java.util.Set;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class MemoRequest {

    private final String frontText;
    private final String backText;
    private final Set<String> tags;

    public MemoRequest(final String frontText,
            final String backText,
            final Set<String> tags) {
        this.frontText = frontText;
        this.backText = backText;
        this.tags = tags;
    }

    public static MemoRequest fromModel(final io.github.pzoladek.memos.model.Memo model) {
        return MemoRequest.builder()
                .frontText(model.getFrontText())
                .backText(model.getBackText())
                .tags(model.getTags())
                .build();
    }
}
