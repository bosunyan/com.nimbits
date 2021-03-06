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

package com.nimbits.client.model.hal;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.nimbits.client.model.value.Value;

import java.io.Serializable;

public class ValueContainer implements Serializable {


    private Links links;

    private EmbeddedValues embedded;


    private Value snapshot;


    public ValueContainer() {
    }

    public ValueContainer(Links links, EmbeddedValues embedded, Value snapshot) {
        this.links = links;
        this.embedded = embedded;
        this.snapshot = snapshot;
    }

    @JsonProperty(value = "_links")
    public Links getLinks() {
        return links;
    }

    @JsonProperty(value = "_embedded")
    public EmbeddedValues getEmbedded() {
        return embedded;
    }

    public Value getSnapshot() {
        return snapshot;
    }
}
