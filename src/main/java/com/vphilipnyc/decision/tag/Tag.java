package com.vphilipnyc.decision.tag;

import com.vphilipnyc.decision.Persistable;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.SortedSet;

/**
 * A tag for anything that can be categorized.
 * A tag can have only one parent, but any number of children and any number of aliases (synonyms).
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tag implements Persistable {
    @Id
    String id;

    @Builder.Default
    String name = "Name";

    @Builder.Default
    String description = "No description yet.";

    @Builder.Default
    Integer count = 0;

    @Builder.Default
    String fontColor = "#fff";

    @Builder.Default
    String backgroundColor = "#000";

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    Tag parent;

    @OneToMany
    SortedSet<Tag> children;

    /**
     * when the tag is clicked, user is sent to...
     */
    String targetLink;

    /**
     * further documentation on this tag
     */
    @ElementCollection
    List<String> externalLinks;

    @ManyToMany
    SortedSet<Tag> synonyms;

}
