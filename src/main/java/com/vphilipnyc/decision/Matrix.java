package com.vphilipnyc.decision;

import com.vphilipnyc.decision.methods.DecisionMethod;
import com.vphilipnyc.decision.tag.Tag;
import com.vphilipnyc.decision.tag.Taggable;
import lombok.Builder;
import lombok.Data;

import java.util.*;

/**
 * This is the container object for the decision for display.
 */
@Data
@Builder
public class Matrix implements Taggable {
    private Long id;
    private String name;
    private String description;
    private Long userAuthorId;
    private List<Criterion> allCriteria;
    private List<Alternative> allAlternatives;
    private Set<Long> allUserParticipantIds;
    private DecisionMethod decisionMethod;
    private SortedSet<Tag> tags;

    public void rank() {
        this.setAllAlternatives(decisionMethod.run()); //setter used sparingly
    }

    public Optional<Alternative> mostDesired() {
        return getAllAlternatives().stream().max(Comparator.comparing(Alternative::vector));
    }

    public Optional<Alternative> leastDesired() {
        return getAllAlternatives().stream().min(Comparator.comparing(Alternative::vector));
    }
}
