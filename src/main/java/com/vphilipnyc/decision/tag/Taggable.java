package com.vphilipnyc.decision.tag;

import java.util.Set;

@FunctionalInterface
public interface Taggable {
    Set<Tag> getTags();

}
