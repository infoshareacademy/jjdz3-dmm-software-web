package com.dmmsoft.dbtests;

import com.dmmsoft.app.analyzer.analyses.revenue.InvestmentRevenueCriteria;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by milo on 23.05.17.
 */

@Entity
public class LocalInvestmentRevenueCriteria extends InvestmentRevenueCriteria {

    @ManyToOne
    User user;

    public LocalInvestmentRevenueCriteria() {
    }

    public LocalInvestmentRevenueCriteria(BigDecimal investedCapital, LocalDate buyDate, LocalDate sellDate, String investmentName, Boolean isFavourite) {
        super(investedCapital, buyDate, sellDate, investmentName, isFavourite);
    }
}
