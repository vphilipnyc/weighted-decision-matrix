package com.vphilipnyc.decision;

import lombok.Builder;

import java.util.Map;

/**
 * An Alternative is a possible ranked outcome for a decision.
 */
@Builder
public record Alternative(Long matrixId,
                          Item item,
                          Long userId,
                          Map<Criterion, Double> ratingMap,
                          double vector) {
}
