package com.vphilipnyc.decision.tag;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vphilipnyc.decision.Persistable;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

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
@ToString
public class Tag implements Persistable, Comparable<Tag> {
    @Id
    @GeneratedValue
    Long id;

    @Builder.Default
    String name = "Name";

    @Builder.Default
    String description = "No description yet.";

    @Builder.Default
    Integer count = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    @ToString.Exclude
    Tag parent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ToString.Exclude
    Set<Tag> children;

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
    @ToString.Exclude
    Set<Tag> aliases;

    @Override
    public int compareTo(Tag otherTag) {
        return this.name.compareTo(otherTag.name);
    }
}
