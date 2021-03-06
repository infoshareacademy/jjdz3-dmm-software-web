package com.dmmsoft.analyzer.analysis.investmentindicator;

import com.dmmsoft.app.analyzer.analyses.indicator.IndicatorCriteria;
import com.dmmsoft.app.model.Investment;
import com.dmmsoft.user.User;
import sun.awt.SunHints;

import javax.persistence.*;
import java.util.*;

/**
 * Created by milo on 21.06.17.
 */

@Entity
public class PersistedComparatorIndicatorCriteria {

    @Id
    @GeneratedValue
    private long Id;
    private boolean isFavouriteChecked;
    private String  userCustomName;


    @ManyToOne
    private User user;

    @ElementCollection
    private Set<String> investmentNamesToCompare = new HashSet<>();

    public String getUserCustomName() {
        return userCustomName;
    }

    public void setUserCustomName(String userCustomName) {
        this.userCustomName = userCustomName;
    }

    public boolean isFavouriteChecked() {
        return isFavouriteChecked;
    }

    public void setFavouriteChecked(boolean favouriteChecked) {
        isFavouriteChecked = favouriteChecked;
    }

    public Set<String> getInvestmentNamesToCompare() {
        return investmentNamesToCompare;
    }

    public void setInvestmentNamesToCompare(Set<String> investmentNamesToCompare) {
        this.investmentNamesToCompare = investmentNamesToCompare;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

}
