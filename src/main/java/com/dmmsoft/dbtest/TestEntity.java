package com.dmmsoft.dbtest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigInteger;

/**
 * Created by milo on 19.05.17.
 */

@Entity
public class TestEntity {


    @Id
    @GeneratedValue
    private long Id;
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }





}
