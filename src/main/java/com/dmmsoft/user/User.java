package com.dmmsoft.user;

import com.dmmsoft.analyzer.analysis.investmentindicator.PersistedIndicatorCriteria;
import com.dmmsoft.analyzer.analysis.investmentrevenue.PersistedInvestmentRevenueCriteria;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by milo on 23.05.17.
 */

@Entity
public class User {


    @Id
    @GeneratedValue
    private long id;
    private String login;
    private String pass;
    private boolean isAdmin;
    private LocalDateTime creationDateTime;
    private LocalDateTime lastLoginDateTime;
    private LocalDateTime lastUpdateDateTime;

    @PrePersist
    private void onCreate(){
        creationDateTime=LocalDateTime.now();
    }

    @PreUpdate
    private void onUpdate(){
        lastUpdateDateTime=LocalDateTime.now();
    }

    @OneToMany(fetch=FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id")
    private List<PersistedInvestmentRevenueCriteria> favourites = new ArrayList<>();

    @OneToMany(fetch=FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id")
    private Set<PersistedIndicatorCriteria> favouriteInvestmentIndicators = new HashSet<>();


    public Set<PersistedIndicatorCriteria> getFavouriteInvestmentIndicators() {
        return favouriteInvestmentIndicators;
    }

    public void setFavouriteInvestmentIndicators(Set<PersistedIndicatorCriteria> favouriteInvestmentIndicators) {
        this.favouriteInvestmentIndicators = favouriteInvestmentIndicators;
    }

    public User() {
    }

    public User(long id) {
        this.id=id;
    }


    public String getPass() {
        return pass;
    }

    public void setPass(String name) {
        this.pass = name;
    }

    public boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public LocalDateTime getLastUpdateDateTime() {
        return lastUpdateDateTime;
    }

    public LocalDateTime getLastLoginDateTime() {
        return lastLoginDateTime;
    }

    public void setLastLoginDateTime(LocalDateTime lastLoginDateTime) {
        this.lastLoginDateTime = lastLoginDateTime;
    }

    public List<PersistedInvestmentRevenueCriteria> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<PersistedInvestmentRevenueCriteria> favourites) {
        this.favourites = favourites;
    }






}
