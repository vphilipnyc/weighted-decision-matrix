package com.vphilipnyc.decision.methods;

import com.vphilipnyc.decision.Alternative;
import com.vphilipnyc.decision.Criterion;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleWeightedSumDecisionMethod implements DecisionMethod {

    private final List<Criterion> allCriteria;
    private final List<Alternative> allAlternatives;

    public SimpleWeightedSumDecisionMethod(List<Criterion> allCriteria, List<Alternative> allAlternatives) {
        this.allCriteria = allCriteria;
        this.allAlternatives = allAlternatives;
    }

    @Override
    public List<Alternative> run() {

        if (allAlternatives.isEmpty() || allCriteria.isEmpty()) {
            //log.info("Nothing to run");
            System.out.println("Nothing to run");
            return new ArrayList<>(0);
        }
        //for now assume all benefits (cost == false)
        return allAlternatives.stream()
                .map(alternative -> new Alternative(alternative.getMatrixId(),
                        alternative.getItem(),
                        alternative.getUserId(),
                        alternative.getRatingMap(),
                        allCriteria.stream()
                                .mapToDouble(criterion -> alternative.getRatingMap().get(criterion) * criterion.getWeight())
                                .sum()))
                .sorted(Comparator.comparing(Alternative::getVector).reversed())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public String getName() {
        return "Simple Weighted-Sum Decision Method (WSD)";
    }

    @Override
    public String getDescription() {
        return "Returns the sum product of an alternative's ratings to their factor weights as the vector.";
    }

    @Override
    public String toString() {
        return getName();
    }
}
