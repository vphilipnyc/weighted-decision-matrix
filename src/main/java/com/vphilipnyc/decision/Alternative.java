package com.vphilipnyc.decision;

import lombok.*;

import javax.persistence.*;
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
