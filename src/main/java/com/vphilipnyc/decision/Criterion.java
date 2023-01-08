package com.vphilipnyc.decision;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;
import java.util.Objects;

/**
 * A criterion is a pertinent factor used to help evaluate a decision.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Criterion implements Comparable<Criterion> {
    @Id
    private Long id;
    private String name;
    private int weight; // on a scale of 1 to 10, how important is this factor
    boolean isCost; //whether a lower weight is beneficial for the decision.  Assume it is a benefit by default (false)
    private Long userAuthorId;
    @ManyToMany
    private List<Criterion> determinants; //what criteria is used to make this criterion, if any?

    @Override
    public int compareTo(Criterion otherCriterion) {
        return Long.compare(id, otherCriterion.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Criterion that = (Criterion) o;
        return Objects.equals(this.id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
