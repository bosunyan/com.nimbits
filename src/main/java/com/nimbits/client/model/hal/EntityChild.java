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
import com.fasterxml.jackson.databind.annotation.JsonNaming;


import javax.annotation.Generated;
import java.io.Serializable;

@Generated("org.jsonschema2pojo")
public class EntityChild implements Serializable {


    private Links links;


    private String name;

    public EntityChild() {
    }

    public EntityChild(Links links, String name) {
        this.links = links;
        this.name = name;
    }

    @JsonProperty("_links")
    public Links getLinks() {
        return links;
    }

    public String getName() {
        return name;
    }
}
