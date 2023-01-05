package com.vphilipnyc.decision.methods;

import com.vphilipnyc.decision.Alternative;

import java.util.List;

public interface DecisionMethod {

    /**
     * @return alternatives sorted by their vectors in descending order
     */
    List<Alternative> run();

    /**
     * @return a user-friendly name (e.g., for a front-end)
     */
    @SuppressWarnings("SameReturnValue")
    String getName();

    /**
     * @return a subtitle blurb about how the decision method works
     */
    @SuppressWarnings("SameReturnValue")
    String getDescription();
}
