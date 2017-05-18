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

package com.nimbits.client.model.instance;

import com.nimbits.client.model.UrlContainer;
import com.nimbits.client.model.email.EmailAddress;
import com.nimbits.client.model.entity.Entity;
import com.nimbits.client.model.server.Protocol;

import java.io.Serializable;

public interface Instance extends Entity, Serializable {

    long getServerId();

    UrlContainer getBaseUrl();

    EmailAddress getAdminEmail();

    String getVersion();

    String getPassword();

    boolean isDefault();

    Protocol getProtocol();

    boolean isSocketsEnabled();


}
