package com.stubedavd.mapobjects.species;

import com.stubedavd.models.Entity;

public interface Liveable {
    int getHealthPoints();
    int getSpeed();
    Class <? extends Entity> getTargetClass();
}
