package com.dmmsoft.dbtests;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by milo on 22.05.17.
 */

@Entity
public class EmbedingEntity {

    @Id
    @GeneratedValue
    private long id;
    private String entityLevelField;

    @Embedded
    private ClassA propertyA;

    @Embedded
    private ClassB propertyB;


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getEntityLevelField() {
        return entityLevelField;
    }
    public void setEntityLevelField(String testFieldEmbedingEntityLevel) {
        this.entityLevelField = testFieldEmbedingEntityLevel;
    }

    public ClassA getPropertyA() {
        return propertyA;
    }

    public void setPropertyA(ClassA classAProperty) {
        this.propertyA = classAProperty;
    }

    public ClassB getPropertyB() {
        return propertyB;
    }

    public void setPropertyB(ClassB classBProperty) {
        this.propertyB = classBProperty;
    }


}
