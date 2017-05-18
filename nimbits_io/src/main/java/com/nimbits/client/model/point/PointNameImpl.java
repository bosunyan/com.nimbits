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

package com.nimbits.client.model.point;

import com.nimbits.client.model.common.impl.CommonIdentifierImpl;

import java.io.Serializable;

/**
 * Created by bsautner
 * User: benjamin
 * Date: 7/15/11
 * Time: 6:21 PM
 */
public class PointNameImpl extends CommonIdentifierImpl implements PointName, Serializable {
    private static final long serialVersionUID = 1L;

    public PointNameImpl(final String value) {
        super(value);
    }


    public PointNameImpl() {
        super();
    }

}
