package com.dmmsoft.container;

import com.dmmsoft.app.appconfiguration.AppConfigurationProvider;
import com.dmmsoft.app.dataloader.MainContainerLoader;
import com.dmmsoft.app.model.Investment;
import com.dmmsoft.app.model.MainContainer;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.util.List;

/**
 * Created by milo on 06.05.17.
 */

@Singleton
public class DataContainer implements IDataContainerService {

    private List<Investment> investments;
    private com.dmmsoft.app.model.MainContainer mainContainer;


    @PostConstruct
    public void onPostConstruct(){
        AppConfigurationProvider appCon = new AppConfigurationProvider().getConfiguration();
        MainContainerLoader mainContainerLoader = new MainContainerLoader(appCon);
        mainContainerLoader.loadFunds();
        mainContainerLoader.loadCurrencies();

        // jar dependency
        com.dmmsoft.app.model.MainContainer mainContainer = mainContainerLoader.getMainContainer();
        this.investments = mainContainer.getInvestments();
        this.mainContainer=mainContainer;
    }

    public List<Investment> getInvestments(){
        return investments;
    }
    public MainContainer getMainContainer() {
        return mainContainer;
    }
}
