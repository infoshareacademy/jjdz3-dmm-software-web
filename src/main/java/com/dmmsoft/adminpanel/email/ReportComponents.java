package com.dmmsoft.adminpanel.email;

import com.dmmsoft.analyzer.IFavouriteService;

/**
 * Created by milo on 08.07.17.
 */
public class ReportComponents {

    private IFavouriteService favouriteService;

    public ReportComponents(IFavouriteService favouriteService) {
        this.favouriteService = favouriteService;
    }

    public IFavouriteService getFavouriteService() {
        return favouriteService;
    }
}
