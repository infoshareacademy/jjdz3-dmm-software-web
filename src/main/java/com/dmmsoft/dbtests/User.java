package com.dmmsoft.dbtests;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by milo on 23.05.17.
 */

@Entity
public class User {


    @Id @GeneratedValue
    private long id;
    private String login;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<LocalInvestmentRevenueCriteria> favourites = new ArrayList<>();


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
