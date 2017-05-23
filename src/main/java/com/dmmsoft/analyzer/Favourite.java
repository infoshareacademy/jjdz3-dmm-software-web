package com.dmmsoft.analyzer;

import com.dmmsoft.app.analyzer.analyses.revenue.InvestmentRevenueCriteria;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by milo on 19.05.17.
 */

@Entity
public class Favourite {


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
