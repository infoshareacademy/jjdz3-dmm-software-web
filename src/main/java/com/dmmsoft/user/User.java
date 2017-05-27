package com.dmmsoft.user;

import com.dmmsoft.analyzer.analysis.LocalInvestmentRevenueCriteria;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by milo on 23.05.17.
 */

@Entity
public class User {


    @Id
    @GeneratedValue
    private long id;
    private String login;
    private Boolean isAdmin;


//@OneToMany(mappedBy = "user")
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE })
    @JoinColumn(name = "UserId")
    private List<LocalInvestmentRevenueCriteria> favourites = new ArrayList<>();



    public User() {
    }

    public User(long id) {
        this.id=id;
    }



    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
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

    public List<LocalInvestmentRevenueCriteria> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<LocalInvestmentRevenueCriteria> favourites) {
        this.favourites = favourites;
    }






}
