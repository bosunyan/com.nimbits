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

package com.nimbits.client.model.calculation;

import com.google.gson.annotations.Expose;
import com.nimbits.client.enums.EntityType;
import com.nimbits.client.model.common.CommonIdentifier;
import com.nimbits.client.model.common.impl.CommonFactory;
import com.nimbits.client.model.entity.Entity;
import com.nimbits.client.model.entity.EntityName;
import com.nimbits.client.model.trigger.TriggerModel;

import java.io.Serializable;


public class CalculationModel extends TriggerModel implements Serializable, Calculation {
    private static final long serialVersionUID = 1L;


    @Expose
    private String formula;

    @Expose
    private String x;

    @Expose
    private String y;

    @Expose
    private String z;


    @SuppressWarnings("unused")
    private CalculationModel() {
        super();
    }

    protected CalculationModel(String id, CommonIdentifier name, String description, EntityType entityType, String parent, String owner, String target, String trigger, boolean enabled, String formula, String x, String y, String z) {
        super(id, name, description, entityType, parent, owner, target, trigger, enabled);
        this.formula = formula;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void setFormula(String formula) {
        this.formula = formula;
    }

    @Override
    public void setX(String x) {
        this.x = x;
    }

    @Override
    public void setY(String y) {
        this.y = y;
    }

    @Override
    public void setZ(String z) {
        this.z = z;
    }


    @Override
    public String getFormula() {
        return formula;
    }

    @Override
    public String getX() {
        return x;
    }

    @Override
    public String getY() {
        return y;
    }

    @Override
    public String getZ() {
        return z;

    }

    @Override
    public void update(Entity update) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CalculationModel that = (CalculationModel) o;

        if (!formula.equals(that.formula)) return false;
        if (x != null ? !x.equals(that.x) : that.x != null) return false;
        if (y != null ? !y.equals(that.y) : that.y != null) return false;
        if (z != null ? !z.equals(that.z) : that.z != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + formula.hashCode();
        result = 31 * result + (x != null ? x.hashCode() : 0);
        result = 31 * result + (y != null ? y.hashCode() : 0);
        result = 31 * result + (z != null ? z.hashCode() : 0);
        return result;
    }


    public static class Builder extends TriggerBuilder {

        private final EntityType type = EntityType.calculation;
        private String formula;


        private String x;


        private String y;


        private String z;

        public Builder formula(String formula) {
            this.formula = formula;
            return this;
        }

        public Builder x(String x) {
            this.x = x;
            return this;
        }

        public Builder y(String y) {
            this.y = y;
            return this;
        }

        public Builder z(String z) {
            this.z = z;
            return this;
        }

        public Builder x(Entity x) {
            this.x = x.getId();
            return this;
        }

        public Builder y(Entity y) {
            this.y = y.getId();
            return this;
        }

        public Builder z(Entity z) {
            this.z = z.getId();
            return this;
        }

        @Override
        public Builder target(String v) {
            this.target = v;
            return this;
        }

        @Override
        public Builder trigger(String v) {
            this.trigger = v;
            return this;
        }

        @Override
        public Builder target(Entity v) {
            this.target = v.getId();
            return this;
        }

        @Override
        public Builder trigger(Entity v) {
            this.trigger = v.getId();
            return this;
        }

        @Override
        public Builder enabled(boolean v) {
            this.enabled = v;
            return this;
        }

        public Builder name(String name) {
            this.name = CommonFactory.createName(name, type);
            return this;
        }

        public Calculation create() {

            this.enabled = true;


            return new CalculationModel(id, name, description, type, parent, owner, target,
                    trigger, enabled, formula, x, y, z);
        }

        @Override
        public Builder parent(String parent) {

            this.parent = parent;
            return this;
        }


        public Builder init(Calculation c) {
            super.init(c);
            this.formula = c.getFormula();
            this.x = c.getX();
            this.y = c.getY();
            this.z = c.getZ();
            return this;
        }

        @Override
        public Builder name(EntityName name) {
            this.name = name;
            return this;
        }

        @Override
        public Builder description(String description) {
            this.description = description;
            return this;
        }

        @Override
        public Builder alertType(int alertType) {
            this.alertType = alertType;
            return this;
        }

        @Override
        public Builder owner(String owner) {
            this.owner = owner;
            return this;
        }

        @Override
        public Builder readOnly(boolean readOnly) {
            this.readOnly = readOnly;
            return this;
        }

        @Override
        public Builder id(String id) {
            this.id = id;
            return this;
        }

        @Override
        public Builder action(String action) {
            this.action = action;
            return this;
        }
    }
}
