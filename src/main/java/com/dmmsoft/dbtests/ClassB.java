package com.dmmsoft.dbtests;

import javax.persistence.Embeddable;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Created by milo on 21.05.17.
 */

@Embeddable
public class ClassB extends ClassA {

private String classBLevelField;

    public String getClassBLevelField() {
        return classBLevelField;
    }

    public void setClassBLevelField(String testFieldClassBLevel) {
        this.classBLevelField = testFieldClassBLevel;
    }
}
