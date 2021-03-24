package io.github.pzoladek.memos.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import io.github.pzoladek.memos.dto.FolderRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(schema = "MEMOS", name = "FOLDER")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
public class Folder implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PUBLIC_ACCESS")
    private boolean publicAccess;

    @Column(name = "AUTHOR")
    private String author;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "FOLDER_NAME", referencedColumnName = "NAME")
    private Set<Memo> memos;

    public Set<Memo> getMemos() {
        return Optional.ofNullable(memos).orElseGet(Collections::emptySet);
    }

    public static Folder fromRequest(final FolderRequest folderRequest, final String author) {
        return Folder.builder()
                .author(author)
                .name(folderRequest.getName())
                .description(folderRequest.getDescription())
                .publicAccess(folderRequest.isPublicAccess())
                .build();
    }
}
