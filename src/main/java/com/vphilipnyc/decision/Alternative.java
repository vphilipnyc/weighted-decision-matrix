package com.vphilipnyc.decision;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.*;

import java.util.Map;

/**
 * An Alternative is a possible ranked outcome for a decision.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Alternative {
    @Id
    private Long matrixId;

    @Transient
    private Item item;

    private Long userId;
    @ElementCollection
    private Map<Criterion, Double> ratingMap;
    private double vector;
}
