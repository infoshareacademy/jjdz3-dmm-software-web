package com.dmmsoft.container;

import com.dmmsoft.app.appconfiguration.AppConfigurationProvider;
import com.dmmsoft.app.model.loader.MainContainerLoader;
import com.dmmsoft.app.model.MainContainer;
import com.dmmsoft.app.model.Investment;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.util.List;

/**
 * Created by milo on 06.05.17.
 */



@Singleton
public class ModelContainer implements IModelContainerService {

    private List<Investment> investments;
    private MainContainer mainContainer;

    @PostConstruct
    private void onPostConstruct() {
        AppConfigurationProvider appCon = new AppConfigurationProvider().getConfiguration();
        MainContainerLoader mainContainerLoader = new MainContainerLoader(appCon);
        mainContainerLoader.loadFunds();
        mainContainerLoader.loadCurrencies();

        MainContainer mainContainer = mainContainerLoader.getMainContainer();
        this.mainContainer = mainContainer;
        this.investments = mainContainer.getInvestments();

    }

    public void reload(){
      if(!mainContainer.getInvestments().isEmpty()) {
          mainContainer.getInvestments().clear();
      }

        AppConfigurationProvider appCon = new AppConfigurationProvider().getConfiguration();
        MainContainerLoader mainContainerLoader = new MainContainerLoader(appCon);
        mainContainerLoader.loadFunds();
        mainContainerLoader.loadCurrencies();

        com.dmmsoft.app.model.MainContainer mainContainer = mainContainerLoader.getMainContainer();
        this.investments = mainContainer.getInvestments();
        this.mainContainer = mainContainer;
    }

    public List<Investment> getInvestments() {
        return investments;
    }

    public MainContainer getMainContainer() {
        return mainContainer;
    }

}
