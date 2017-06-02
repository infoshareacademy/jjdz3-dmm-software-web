package com.dmmsoft.analyzer.analysis;

import com.dmmsoft.app.analyzer.analyses.revenue.InvestmentRevenueCriteria;
import com.dmmsoft.user.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by milo on 23.05.17.
 */

@Entity
public class PersistedInvestmentRevenueCriteria extends InvestmentRevenueCriteria {

    @Id
    @GeneratedValue
    private long id;


    @ManyToOne
    private User user;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PersistedInvestmentRevenueCriteria() {
    }

    public PersistedInvestmentRevenueCriteria(BigDecimal investedCapital, LocalDate buyDate, LocalDate sellDate, String investmentName, Boolean isFavourite) {
        super(investedCapital, buyDate, sellDate, investmentName, isFavourite);
    }

    public PersistedInvestmentRevenueCriteria(InvestmentRevenueCriteria criteria) {
        super.setInvestedCapital(criteria.getInvestedCapital());
        super.setBuyDate(criteria.getBuyDate());
        super.setSellDate(criteria.getSellDate());
        super.setInvestmentName(criteria.getInvestmentName());
        super.setFavourite(criteria.getFavourite());
    }


}
