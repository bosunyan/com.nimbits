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

package com.nimbits.server.orm;

import com.nimbits.client.enums.AlertType;
import com.nimbits.client.enums.EntityType;
import com.nimbits.client.model.common.impl.CommonFactory;
import com.nimbits.client.model.entity.Entity;
import com.nimbits.client.model.entity.EntityName;
import com.nimbits.client.model.hal.Links;
import com.nimbits.client.model.user.User;
import org.apache.commons.lang3.StringUtils;

import javax.jdo.annotations.*;
import java.util.List;

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class EntityStore implements Entity {

    @PrimaryKey
    @Persistent
    protected String id;

    @Persistent @Index
    private String name;

    @Persistent
    private String description;

    @Persistent
    private Integer entityType;

    @Persistent
    private String parent;

    @Persistent
    private String owner;

    @NotPersistent
    private int alertType;

    @NotPersistent
    private boolean readOnly;

    @SuppressWarnings("unused")
    protected EntityStore() {

    }


    public EntityStore(final Entity entity) {

        final EntityName saferName = CommonFactory.createName(entity.getName().getValue(), entity.getEntityType());


        setValues(entity, saferName);

    }


    private void setValues(Entity entity, EntityName saferName) {

        this.name = saferName.getValue();
        this.description = entity.getDescription();
        this.entityType = entity.getEntityType().getCode();
        this.parent = entity.getParent();
        this.owner = entity.getOwner();

    }


    @Override
    public EntityName getName() {
        try {
            return name != null ? CommonFactory.createName(name, EntityType.get(this.entityType)) : null;
        } catch (Exception ex) {

            return null;
        }

    }


    @Override
    public void setName(final EntityName name) {
        final EntityName saferName = CommonFactory.createName(name.getValue(), EntityType.get(this.entityType));
        this.name = saferName.getValue();
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(final String description) {
        this.description = description;
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.get(entityType);
    }

    @Override
    public void setEntityType(final EntityType entityType) {
        this.entityType = entityType.getCode();
    }

    @Override
    public String getId() {

        return this.id == null ? null : this.id;
    }


    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getParent() {
        return parent;
    }

    @Override
    public void setParent(final String parent) {
        this.parent = parent;
    }

    @Override
    public String getOwner() {
        return owner;
    }

    @Override
    public void setOwner(final String owner) {
        this.owner = owner;
    }

    @Override
    public AlertType getAlertType() {
        return AlertType.get(this.alertType);
    }

    @Override
    public void setAlertType(final AlertType alertType) {
        this.alertType = alertType.getCode();
    }

    @Override
    public boolean isReadOnly() {
        return this.readOnly;
    }

    @Override
    public void setReadOnly(final boolean readOnly) {
        this.readOnly = readOnly;
    }


    @Override
    public void setChildren(final List<Entity> children) {

    }


    @Override
    public void update(final Entity update) {
        this.description = update.getDescription();
        this.name = update.getName().getValue();

        this.parent = update.getParent();
        this.id = update.getId();

    }

    @Override
    public List<Entity> getChildren() {
        return null;
    }

    @Override
    public boolean isOwner(final User user) {
        return false;
    }

    @Override
    public void validate(User user) {

        if (StringUtils.isEmpty(this.owner) || StringUtils.isEmpty(this.name) || StringUtils.isEmpty(this.parent)) {
            throw new IllegalArgumentException("Entity was missing required data, validation failed: "
                    + "owner=" + owner + "name=" + this.name + "parent:" + this.parent);
        }

        if (StringUtils.isEmpty(this.owner)) {
            throw new IllegalArgumentException("Owner must not be null");
        }



    }


    @Override
    public int compareTo(final Entity entity) {
        return 0;
    }

    @SuppressWarnings("NonFinalFieldReferenceInEquals")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntityStore)) return false;

        EntityStore that = (EntityStore) o;

        if (alertType != that.alertType) return false;
        if (readOnly != that.readOnly) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (entityType != null ? !entityType.equals(that.entityType) : that.entityType != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (owner != null ? !owner.equals(that.owner) : that.owner != null) return false;
        if (parent != null ? !parent.equals(that.parent) : that.parent != null) return false;

        return true;
    }

    @SuppressWarnings("NonFinalFieldReferencedInHashCode")
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);

        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (entityType != null ? entityType.hashCode() : 0);

        result = 31 * result + (parent != null ? parent.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + alertType;
        result = 31 * result + (readOnly ? 1 : 0);
        return result;
    }


    @Override
    public void setEmbedded(com.nimbits.client.model.hal.Embedded embedded) {

    }

    @Override
    public void setLinks(Links links) {

    }

}
