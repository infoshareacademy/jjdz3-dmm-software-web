package com.dmmsoft.analyzer.analysis.comparison;

import com.dmmsoft.analyzer.analysis.investmentindicator.PersistedIndicatorCriteria;

import com.dmmsoft.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;


/**
 * Created by milo on 15.04.17.
 */

@Entity
public class AnalysisComparisonContainer {

    @Id
    @GeneratedValue
    private long Id;

    private boolean isFavouriteChecked;
    private String  userCustomName;
   // private List<AnalysisComparisonData> comparisonData;

    @ManyToOne
    private User user;

/*  @ElementCollection
    private Set<String> investmentNamesToCompare = new HashSet<>();*/

    @OneToMany (fetch=FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<PersistedIndicatorCriteria> criteriaSet = new ArrayList<>();

    public List<PersistedIndicatorCriteria> getCriteriaSet() {
        return criteriaSet;
    }

    public void setCriteriaSet(List<PersistedIndicatorCriteria> criteriaSet) {
        this.criteriaSet = criteriaSet;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public boolean isFavouriteChecked() {
        return isFavouriteChecked;
    }

    public void setFavouriteChecked(boolean favouriteChecked) {
        isFavouriteChecked = favouriteChecked;
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



}
