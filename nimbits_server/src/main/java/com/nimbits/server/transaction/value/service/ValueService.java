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

package com.nimbits.server.transaction.value.service;

import com.google.common.base.Optional;
import com.google.common.collect.Range;
import com.nimbits.client.enums.AlertType;
import com.nimbits.client.model.entity.Entity;
import com.nimbits.client.model.point.Point;
import com.nimbits.client.model.user.User;
import com.nimbits.client.model.value.Value;

import java.util.List;
import java.util.Map;


public interface ValueService  {


    List<Value> getSeries(Entity entity, Optional<Range<Long>> timespan, final Optional<Range<Integer>> range, Optional<String> mask);

    Map<String, Value> getCurrentValues(Map<String, Point> entities);

    void recordValues(User user, Point point, List<Value> values);

    Value getCurrentValue(Entity p);

    String getChartTable(User user, Entity entity, List<Entity> children, Optional<Range<Long>> timespan, Optional<Integer> count, Optional<String> mask);

    AlertType getAlertType(final Point point, final Value value);

    void deleteAllData(Point point);
}
