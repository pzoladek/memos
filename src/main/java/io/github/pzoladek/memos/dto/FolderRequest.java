package io.github.pzoladek.memos.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class FolderRequest {

    private final String name;
    private final String description;
    private final boolean publicAccess;

    public FolderRequest(final String name, final String description, final boolean publicAccess) {
        this.name = name;
        this.description = description;
        this.publicAccess = publicAccess;
    }
}
