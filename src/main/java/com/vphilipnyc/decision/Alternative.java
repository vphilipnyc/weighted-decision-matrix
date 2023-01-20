package com.vphilipnyc.decision;

import jakarta.persistence.*;
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
@ToString
@Entity
public class Alternative implements Persistable {
    @Id
    @GeneratedValue
    private Long id;

    private Long matrixId;

    @Transient //TODO: could be an issue
    private Item item;

    private Long userId;
    @ElementCollection
    private Map<Criterion, Double> ratingMap;
    private double vector;

}
