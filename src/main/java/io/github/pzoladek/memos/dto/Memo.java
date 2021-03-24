package io.github.pzoladek.memos.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class Memo extends MemoRequest {

    private final Long id;

    public static Memo fromModel(final io.github.pzoladek.memos.model.Memo model) {
        return Memo.builder()
                .frontText(model.getFrontText())
                .backText(model.getBackText())
                .tags(model.getTags())
                .id(model.getId())
                .build();
    }
}
