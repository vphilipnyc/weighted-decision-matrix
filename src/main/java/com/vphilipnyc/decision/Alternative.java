package com.vphilipnyc.decision;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * An Alternative is a possible ranked outcome for a decision.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public record Alternative(Long matrixId,
                          Item item,
                          Long userId,
                          Map<Criterion, Double> ratingMap,
                          double vector) {
}
