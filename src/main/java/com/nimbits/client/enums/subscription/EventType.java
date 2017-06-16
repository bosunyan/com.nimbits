/*
 * Copyright 2016 Benjamin Sautner
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.nimbits.client.enums.subscription;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;


public enum EventType {

    none(-1, "None"),
    anyEvent(1, "Any"),
    high(2, "High"),
    low(3, "Low"),
    idle(4, "Idle"),
    newValue(5, "New"),
    delta(7, "Delta"),
    increase(8, "Increase"),
    decrease(9, "Decrease"),
    equals(10, "Equals");


    private static final Map<Integer, EventType> lookup = new HashMap<Integer, EventType>(7);

    static {
        for (EventType s : EnumSet.allOf(EventType.class))
            lookup.put(s.code, s);
    }

    private final int code;
    private final String text;

    private EventType(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public int getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    public static EventType get(int code) {
        return lookup.get(code);
    }
}