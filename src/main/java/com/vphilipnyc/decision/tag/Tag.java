package com.vphilipnyc.decision.tag;

import com.vphilipnyc.decision.Persistable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * A tag for anything that can be categorized.
 * A tag can have only one parent, but any number of children and any number of aliases (synonyms).
 */
@Entity
@Getter
@Setter
@SuperBuilder
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

    @Override
    public int compareTo(Tag otherTag) {
        return this.name.compareTo(otherTag.name);
    }
}
