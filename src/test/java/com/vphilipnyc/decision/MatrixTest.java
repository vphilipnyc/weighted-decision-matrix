package com.vphilipnyc.decision;

import com.vphilipnyc.decision.methods.SimpleWeightedSumDecisionMethod;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MatrixTest {
        /*
                                 Looks                 Acting        Fit for Role     vector
                              Criterion.WEIGHT Criterion.WEIGHT  Criterion.WEIGHT
                                   8                3                 5
            Natalie Portman   8         64      6        18       10        50         132
            Hugh Jackman      5         40      9        27        9        45         112
            ALT 3             10        80      8        24        3        15         119
         */

    Criterion looksCriterion = Criterion.builder()
            .id(1L)
            .weight(8)
            .userAuthorId(1L)
            .name("Looks")
            .build();
    Criterion actingCriterion = Criterion.builder()
            .id(2L)
            .weight(3)
            .userAuthorId(1L)
            .name("Acting")
            .build();

    Criterion fitForRoleCriterion = Criterion.builder()
            .id(3L)
            .weight(5)
            .userAuthorId(1L)
            .name("Fit for Role")
            .build();

    @Test
    void noAlternativeGivenTest() {

        List<Criterion> criteria = new ArrayList<>();
        criteria.add(looksCriterion);
        criteria.add(actingCriterion);
        criteria.add(fitForRoleCriterion);

        List<Alternative> alternatives = new ArrayList<>();
        //don't add any

        SimpleWeightedSumDecisionMethod method = new SimpleWeightedSumDecisionMethod(criteria, alternatives);
        Matrix matrix = Matrix.builder()
                .id(1L)
                .allCriteria(criteria)
                .allAlternatives(alternatives)
                .description("This is a test to make sure no ranking is done when there are no alternatives.")
                .decisionMethod(method)
                .userAuthorId(1L)
                .name("Test matrix")
                .build();

        matrix.rank();
        assertEquals(0, matrix.getAllAlternatives().size());
        assertEquals(3, matrix.getAllCriteria().size());
    }

    @Test
    void noCriteriaGivenTest() {

        List<Criterion> criteria = new ArrayList<>();
        //don't add any

        List<Alternative> alternatives = new ArrayList<>();

        SimpleWeightedSumDecisionMethod method = new SimpleWeightedSumDecisionMethod(criteria, alternatives);
        Matrix matrix = Matrix.builder()
                .id(2L)
                .allCriteria(criteria)
                .allAlternatives(alternatives)
                .description("This is a test to make sure no ranking is done when there are no criteria.")
                .decisionMethod(method)
                .userAuthorId(1L)
                .name("Test matrix")
                .build();

        matrix.rank();
        assertEquals(0, matrix.getAllAlternatives().size());
        assertEquals(0, matrix.getAllCriteria().size());
    }

    @Test
    void calculateOneAlternativeVector() {

        List<Criterion> criteria = new ArrayList<>();
        criteria.add(looksCriterion);
        criteria.add(actingCriterion);
        criteria.add(fitForRoleCriterion);

        List<Alternative> alternatives = new ArrayList<>();

        Map<Criterion, Double> ratingMap = new HashMap<>(criteria.size());
        ratingMap.put(looksCriterion, 8d);
        ratingMap.put(actingCriterion, 6d);
        ratingMap.put(fitForRoleCriterion, 10d);

        Alternative nataliePortman = Alternative.builder()
                .item(Actor.builder().id(1L).name("Natalie Portman").build())
                .matrixId(3L)
                .ratingMap(ratingMap)
                .userId(1L)
                .build();
        alternatives.add(nataliePortman);

        SimpleWeightedSumDecisionMethod method = new SimpleWeightedSumDecisionMethod(criteria, alternatives);
        Matrix matrix = Matrix.builder()
                .id(1L)
                .allCriteria(criteria)
                .allAlternatives(alternatives)
                .description("This is test decision 2.")
                .decisionMethod(method)
                .userAuthorId(1L)
                .name("Test matrix 2")
                .build();

        assertAll(matrix::rank);
        assertEquals(matrix.getAllAlternatives().size(), 1);
        assertEquals(matrix.getAllCriteria().size(), 3);

        double totalAlternativeWeight = matrix.getAllAlternatives().get(0).vector();
        assertEquals(132d, totalAlternativeWeight);
    }

    @Test
    void calculateTwoAlternativeVector() {

        List<Criterion> criteria = new ArrayList<>();
        criteria.add(looksCriterion);
        criteria.add(actingCriterion);
        criteria.add(fitForRoleCriterion);

        List<Alternative> alternatives = new ArrayList<>();

        Map<Criterion, Double> ratingMap = new HashMap<>(criteria.size());
        ratingMap.put(looksCriterion, 8d);
        ratingMap.put(actingCriterion, 6d);
        ratingMap.put(fitForRoleCriterion, 10d);

        Alternative nataliePortman = Alternative.builder()
                .item(Actor.builder().id(1L).name("Natalie Portman").build())
                .matrixId(3L)
                .ratingMap(ratingMap)
                .userId(1L)
                .build();
        alternatives.add(nataliePortman);

        Map<Criterion, Double> ratingMap2 = new HashMap<>(criteria.size());
        ratingMap2.put(looksCriterion, 5d);
        ratingMap2.put(actingCriterion, 9d);
        ratingMap2.put(fitForRoleCriterion, 9d);

        Alternative hughJackman = Alternative.builder()
                .item(Actor.builder().id(2L).name("Aishwarya Rai").build())
                .matrixId(3L)
                .ratingMap(ratingMap2)
                .userId(1L)
                .build();
        alternatives.add(hughJackman);

        System.out.println("alternatives=" + alternatives);
        assertEquals(2, alternatives.size());

        SimpleWeightedSumDecisionMethod method = new SimpleWeightedSumDecisionMethod(criteria, alternatives);
        Matrix matrix = Matrix.builder()
                .id(4L)
                .allCriteria(criteria)
                .allAlternatives(alternatives)
                .description("This is test decision 4.")
                .decisionMethod(method)
                .userAuthorId(1L)
                .name("Test matrix 4")
                .build();

        assertAll(matrix::rank);
        assertEquals(matrix.getAllAlternatives().size(), 2);
        assertEquals(matrix.getAllCriteria().size(), 9);

        assertEquals(132d, matrix.getAllAlternatives().get(0).vector());
        assertEquals(112d, matrix.getAllAlternatives().get(1).vector());

        double totalAlternativeWeight = matrix.getAllAlternatives().get(0).vector();
        assertEquals(132d, totalAlternativeWeight);

        matrix.leastDesired().ifPresent(alternative ->
                assertEquals(hughJackman.item().getName(), alternative.item().getName()));
        matrix.mostDesired().ifPresent(alternative ->
                assertEquals(nataliePortman.item().getName(), alternative.item().getName()));
    }
}
