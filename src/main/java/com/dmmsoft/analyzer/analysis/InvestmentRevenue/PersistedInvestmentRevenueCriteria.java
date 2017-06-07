package com.dmmsoft.analyzer.analysis.InvestmentRevenue;

import com.dmmsoft.app.analyzer.analyses.revenue.InvestmentRevenueCriteria;
import com.dmmsoft.user.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
public class PersistedInvestmentRevenueCriteria extends InvestmentRevenueCriteria {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private User user;
    private String userCustomName;

    @Override
    public long getId() {
        return id;
    }
    @Override
    public void setId(long id) {
        this.id = id;
    }


    public String getUserCustomName() {
        return userCustomName;
    }

    public void setUserCustomName(String userCustomName) {
        this.userCustomName = userCustomName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PersistedInvestmentRevenueCriteria get(InvestmentRevenueCriteria criteria, String userCustomName) {
        setInvestedCapital(criteria.getInvestedCapital());
        setBuyDate(criteria.getBuyDate());
        setSellDate(criteria.getSellDate());
        setInvestmentName(criteria.getInvestmentName());
        setFavourite(criteria.getFavourite());
        setUserCustomName(userCustomName);
        return this;
    }

    public InvestmentRevenueCriteria getEqualEquivalent(PersistedInvestmentRevenueCriteria revenueCriteria){

        InvestmentRevenueCriteria criteria = new InvestmentRevenueCriteria();
        criteria.setInvestedCapital(revenueCriteria.getInvestedCapital());
        criteria.setBuyDate(revenueCriteria.getBuyDate());
        criteria.setSellDate(revenueCriteria.getSellDate());
        criteria.setInvestmentName(revenueCriteria.getInvestmentName());
        criteria.setFavourite(revenueCriteria.getFavourite());
        return criteria;
    }


}
