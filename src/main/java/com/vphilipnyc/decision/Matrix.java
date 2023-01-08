package com.vphilipnyc.decision;

import com.vphilipnyc.decision.methods.DecisionMethod;
import com.vphilipnyc.decision.tag.Tag;
import com.vphilipnyc.decision.tag.Taggable;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

/**
 * This is the container object for the decision for display.
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Matrix implements Taggable, Persistable {
    @Id
    private Long id;
    private String name;
    private String description;
    private Long userAuthorId;
    @ManyToMany
    @ToString.Exclude
    private List<Criterion> allCriteria;
    @ManyToMany
    @ToString.Exclude
    private List<Alternative> allAlternatives;
    @ElementCollection
    private Set<Long> allUserParticipantIds;
    @Transient
    private DecisionMethod decisionMethod;
    @ManyToMany
    @ToString.Exclude
    private SortedSet<Tag> tags;

    public void rank() {
        allAlternatives = decisionMethod.run();
    }

    public Optional<Alternative> mostDesired() {
        return getAllAlternatives().stream().max(Comparator.comparing(Alternative::getVector));
    }

    public Optional<Alternative> leastDesired() {
        return getAllAlternatives().stream().min(Comparator.comparing(Alternative::getVector));
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
