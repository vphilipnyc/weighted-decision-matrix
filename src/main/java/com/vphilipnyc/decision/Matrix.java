package com.vphilipnyc.decision;

import com.vphilipnyc.decision.methods.DecisionMethod;
import com.vphilipnyc.decision.tag.Tag;
import com.vphilipnyc.decision.tag.Taggable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 * This is the container object for the decision for display.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
        allAlternatives = decisionMethod.run();
    }

    public Optional<Alternative> mostDesired() {
        return getAllAlternatives().stream().max(Comparator.comparing(Alternative::vector));
    }

    public Optional<Alternative> leastDesired() {
        return getAllAlternatives().stream().min(Comparator.comparing(Alternative::vector));
    }

    //helper methods for a front-end

    public void addAlternative(Alternative alternative) {
        allAlternatives.add(alternative);
    }

    public void removeAlternative(Alternative alternative) {
        allAlternatives.remove(alternative);
    }

    public void clearAlternatives() {
        allAlternatives.clear();
    }

    public void addCriterion(Criterion criterion) {
        allCriteria.add(criterion);
    }

    public void removeCriterion(Criterion criterion) {
        allCriteria.remove(criterion);
    }

}
