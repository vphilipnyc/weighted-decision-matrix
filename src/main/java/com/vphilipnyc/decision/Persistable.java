package com.vphilipnyc.decision;

/**
 * Contract to fulfill an ID requirement for an item.
 */
@FunctionalInterface
public interface Persistable {
    Object getId(); //Some DBs need this to be a String, but some lean toward Longs
}
