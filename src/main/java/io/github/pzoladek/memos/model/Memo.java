package io.github.pzoladek.memos.model;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Set;

import io.github.pzoladek.memos.dto.MemoRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(schema = "MEMOS", name = "MEMO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
public class Memo {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FOLDER_NAME")
    private String folderName;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "FRONT_TEXT")
    private String frontText;

    @Column(name = "BACK_TEXT")
    private String backText;

    @Column(name = "TAGS")
    @Convert(converter = StringSetConverter.class)
    private Set<String> tags;

    public static Memo fromRequest(final MemoRequest memoRequest, final String folderName, final String author) {
        return Memo.builder()
                .frontText(memoRequest.getFrontText())
                .backText(memoRequest.getBackText())
                .tags(memoRequest.getTags())
                .folderName(folderName)
                .author(author)
                .build();
    }
}
