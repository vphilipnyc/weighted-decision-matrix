package com.vphilipnyc.decision.methods;

import com.vphilipnyc.decision.Alternative;
import com.vphilipnyc.decision.Criterion;

import java.util.List;

public class NormalizedWeightedProductDecisionMethod implements DecisionMethod {
    private final List<Criterion> allCriteria;
    private final List<Alternative> allAlternatives;

    public NormalizedWeightedProductDecisionMethod(List<Criterion> allCriteria, List<Alternative> allAlternatives) {
        this.allCriteria = allCriteria;
        this.allAlternatives = allAlternatives;
    }

    @Override
    public List<Alternative> run() {
        //TODO
        throw new RuntimeException();
    }

    @Override
    public String getName() {
        return "Normalized Weighted-Product Decision Method (WPD)";
    }

    @Override
    public String getDescription() {
        return "Returns the product of an alternative's ratings to their factor weights as the vector.  The weights are normalized based on weights of the other alternatives.  A cost is treated as an inverse of a benefit.";
    }
}
