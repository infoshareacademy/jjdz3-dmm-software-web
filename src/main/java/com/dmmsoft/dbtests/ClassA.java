package com.dmmsoft.dbtests;

import javax.persistence.Embeddable;

/**
 * Created by milo on 21.05.17.
 */

@Embeddable
public class ClassA {

    private String classALevelField;

    public String getClassALevelField() {
        return classALevelField;
    }

    public void setClassALevelField(String testFieldClassALevel) {
        this.classALevelField = testFieldClassALevel;
    }

}
