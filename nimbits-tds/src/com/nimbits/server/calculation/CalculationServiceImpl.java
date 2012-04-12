/*
 * Copyright (c) 2010 Tonic Solutions LLC.
 *
 * http://www.nimbits.com
 *
 *
 * Licensed under the GNU GENERAL PUBLIC LICENSE, Version 3.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.gnu.org/licenses/gpl.html
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the license is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, eitherexpress or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.nimbits.server.calculation;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.nimbits.client.common.Utils;
import com.nimbits.client.enums.EntityType;
import com.nimbits.client.enums.ProtectionLevel;
import com.nimbits.client.exception.NimbitsException;
import com.nimbits.client.model.calculation.Calculation;
import com.nimbits.client.model.calculation.CalculationModelFactory;
import com.nimbits.client.model.entity.Entity;
import com.nimbits.client.model.entity.EntityModelFactory;
import com.nimbits.client.model.entity.EntityName;
import com.nimbits.client.model.user.User;
import com.nimbits.client.model.value.Value;
import com.nimbits.client.model.value.ValueModelFactory;
import com.nimbits.client.service.calculation.CalculationService;
import com.nimbits.server.entity.EntityServiceFactory;
import com.nimbits.server.logging.LogHelper;
import com.nimbits.server.orm.CalcEntity;
import com.nimbits.server.orm.PointEntity;
import com.nimbits.server.user.UserServiceFactory;
import com.nimbits.server.value.RecordedValueServiceFactory;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * Created by bsautner
 * User: benjamin
 * Date: 2/18/12
 * Time: 12:21 PM
 */
public class CalculationServiceImpl extends RemoteServiceServlet implements CalculationService {
    final Logger log = Logger.getLogger(CalculationServiceImpl.class.getName());
    private User getUser() {
        try {
            return UserServiceFactory.getServerInstance().getHttpRequestUser(
                    this.getThreadLocalRequest());
        } catch (NimbitsException e) {
            return null;
        }
    }



    @Override
    public Calculation getCalculation(Entity entity) throws NimbitsException {
        return (Calculation) EntityServiceFactory.getInstance().getEntityByKey(getUser(), entity.getKey(), CalcEntity.class.getName()).get(0);

    }

    @Override
    public Entity addUpdateCalculation(User u, Entity entity, EntityName name, Calculation calculation) throws NimbitsException {
        // Entity retObj = null;

        if (entity == null) {


            final Entity e = EntityModelFactory.createEntity(name, "", EntityType.calculation, ProtectionLevel.onlyMe,
                    calculation.getTrigger(), u.getKey(), UUID.randomUUID().toString());

            final Calculation c = CalculationModelFactory.createCalculation(e, calculation.getTrigger(), calculation.getEnabled(),
                    calculation.getFormula(), calculation.getTarget(), calculation.getX(),
                    calculation.getY(), calculation.getZ());

            return EntityServiceFactory.getInstance().addUpdateEntity(c);



        }
        else if (entity.getEntityType().equals(EntityType.point) && Utils.isEmptyString(calculation.getKey())) {


            Calculation c = CalculationModelFactory.createCalculation(entity.getKey(), calculation.getEnabled(),
                    calculation.getFormula(), calculation.getTarget(), calculation.getX(),
                    calculation.getY(), calculation.getZ());

            return EntityServiceFactory.getInstance().addUpdateEntity(c);


        }
        else if (entity.getEntityType().equals(EntityType.calculation)) {
            entity.setName(name);
            return EntityServiceFactory.getInstance().addUpdateEntity(calculation);



        }
        else {
            return null;
        }


    }

    @Override
    public void deleteCalculation(final User u, final Entity entity) {
        CalculationServiceFactory.getDaoInstance(u).deleteCalculation(entity);
    }

    @Override
    public Entity addUpdateCalculation(Entity entity, EntityName name, Calculation calculation) throws NimbitsException {
        User u = getUser();
        return addUpdateCalculation(u, entity, name, calculation);
    }

    //Section - Calls from RPC Client


    @Override
    public List<Calculation> getCalculations(Entity entity) throws NimbitsException {

        return CalculationServiceFactory.getDaoInstance(getUser()).getCalculations(entity);
    }

    @Override
    public void processCalculations(final User u, final Entity point, final Value value) throws NimbitsException {

//        final Entity e = EntityServiceFactory.getInstance().getEntityByKey(u, point.getKey(), EntityStore.class.getName());

        final List<Calculation> calculations = getCalculations(point);
        for (final Calculation c : calculations) {
            if (c.getEnabled()) {


                try {

                    final List<Entity> target =   EntityServiceFactory.getInstance().getEntityByKey(u, c.getTarget(), PointEntity.class.getName());
                    if (target.isEmpty()) {
                        log.severe("Point target was null " + c.getTarget());
                        log.severe(c.getFormula());
                        log.severe("trigger: " + c.getTrigger());
                    }
                    else {
                        log.info("Solving calc" + c.getFormula());
                        final Value result = solveEquation(u, c);
                        log.info("result" + result);
                        RecordedValueServiceFactory.getInstance().recordValue(u, target.get(0), result, true);
                    }
                } catch (NimbitsException e1) {
                    LogHelper.logException(this.getClass(), e1);
                    c.setEnabled(false);
                    EntityServiceFactory.getInstance().addUpdateEntity(u, c);
//                    if (u != null) {
//                        FeedServiceFactory.getInstance().postToFeed(u, e1);
//                    }
                }


            }
        }
    }
    @Override
    public  Value solveEquation(final User user, final Calculation calculation) throws NimbitsException {

        final MathEvaluator m = new MathEvaluatorImpl(calculation.getFormula());
        log.info(calculation.getFormula());

        if (!Utils.isEmptyString(calculation.getX()) && calculation.getFormula().contains("x")) {
            //  Point p = PointServiceFactory.getInstance().getPointByKey(calculation.getX());
            final Entity p =  EntityServiceFactory.getInstance().getEntityByKey(user, calculation.getX(), PointEntity.class.getName()).get(0);

            if (p != null) {
                log.info("calc has an x car and i found " + p.getName());
                final Value val = RecordedValueServiceFactory.getInstance().getCurrentValue(p);
                final double d = val == null ? 0.0 : val.getDoubleValue();

                m.addVariable("x", d);
            }
            else {
                log.severe("calc has an x car and x not found");
            }
        }
        if (!Utils.isEmptyString(calculation.getY()) && calculation.getFormula().contains("y")) {
            final Entity p =  EntityServiceFactory.getInstance().getEntityByKey(user, calculation.getY(), PointEntity.class.getName()).get(0);

            // Point p = PointServiceFactory.getInstance().getPointByKey(calculation.getY());
            if (p != null) {
                final Value val = RecordedValueServiceFactory.getInstance().getCurrentValue(p);
                final double d = val == null ? 0.0 : val.getDoubleValue();
                m.addVariable("y", d);
            }

        }
        if (!Utils.isEmptyString(calculation.getZ()) && calculation.getFormula().contains("z")) {
            final Entity p =  EntityServiceFactory.getInstance().getEntityByKey(user, calculation.getZ(), PointEntity.class.getName()).get(0);

            //  Point p = PointServiceFactory.getInstance().getPointByKey(calculation.getZ());
            if (p != null) {
                final Value val = RecordedValueServiceFactory.getInstance().getCurrentValue(p);
                final double d = val == null ? 0.0 : val.getDoubleValue();
                m.addVariable("z", d);
            }
        }

        final Double retVal = m.getValue();


        if (retVal == null) {

            throw new NimbitsException("Formula returned a null value: " + calculation.getFormula());


        }


        return ValueModelFactory.createValueModel(retVal, "CV");
    }
}
