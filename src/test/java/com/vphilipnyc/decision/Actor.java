package com.vphilipnyc.decision;

import lombok.Builder;
import lombok.Data;

/**
 * Sample implementation of an Item
 */
@Data
@Builder
public class Actor implements Item, Persistable {

    public Long id;

    public String name;
}
