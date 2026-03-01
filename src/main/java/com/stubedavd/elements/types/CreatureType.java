package com.stubedavd.elements.types;

import com.stubedavd.elements.Entity;

public interface CreatureType {
    int getHealthPoints();
    int getSpeed();
    Class <? extends Entity> getTargetClass();
}
