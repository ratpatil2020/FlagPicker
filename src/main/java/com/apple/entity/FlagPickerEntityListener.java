package com.apple.entity;

import javax.persistence.PostPersist;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;

public class FlagPickerEntityListener {

	//@PostPersist
    public void handleAfterCreate(String type) {
        final Counter counter = Metrics.counter("entity.count", "type", "order");       
      
        counter.increment();
    }

}
