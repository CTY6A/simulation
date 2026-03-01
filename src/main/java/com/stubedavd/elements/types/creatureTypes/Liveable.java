package com.stubedavd.elements.types.creatureTypes;

import com.stubedavd.elements.Entity;

public interface Liveable {
    int getHealthPoints();
    int getSpeed();
    Class <? extends Entity> getTargetClass();
}
