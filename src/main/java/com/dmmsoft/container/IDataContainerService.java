package com.dmmsoft.container;

import com.dmmsoft.app.model.Investment;
import com.dmmsoft.app.model.MainContainer;

import java.util.List;

/**
 * Created by milo on 06.05.17.
 */
public interface IDataContainerService {
    public List<Investment> getInvestments();

    public MainContainer getMainContainer();


}
