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

package com.nimbits.server.transaction.sync;

import com.google.common.base.Optional;
import com.nimbits.client.enums.EntityType;
import com.nimbits.client.io.Nimbits;
import com.nimbits.client.model.entity.Entity;
import com.nimbits.client.model.point.Point;
import com.nimbits.client.model.sync.Sync;
import com.nimbits.client.model.user.User;
import com.nimbits.client.model.value.Value;
import com.nimbits.server.transaction.entity.dao.EntityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SyncService {

    private final EntityDao entityDao;


    @Autowired
    public SyncService(final EntityDao entityDao) {

        this.entityDao = entityDao;

    }

    public void process(final User user, final Point point, final Value value) {
        final Optional<Entity> optional = entityDao.getEntityByTrigger(user, point, EntityType.sync);
        if (optional.isPresent()) {

            Sync sync = (Sync) optional.get();
            String u = sync.getTargetInstance();

            Nimbits nimbits = new Nimbits.Builder()
                    .instance(u)
                    .email(user.getEmail().getValue())
                    .token(sync.getAccessKey()).create();

            // Point target = (Point) entityDao.getEntity(user,((Sync) syncEntity).getTargetPoint(), EntityType.point );
            String targetName = sync.getTarget().split("/")[1];

            nimbits.recordValue(targetName, value);

        }
    }


}
