package com.vphilipnyc.decision.tag;

import java.util.SortedSet;

public interface Taggable {
    SortedSet<Tag> getTags(); //we want to preserve the order of the tags for readability

}
