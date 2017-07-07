package com.dmmsoft.container;

import com.dmmsoft.WebAppDeployListener;
import com.dmmsoft.app.appconfiguration.AppConfigurationProvider;
import com.dmmsoft.app.file.RemoteDownloader;
import com.dmmsoft.app.model.loader.MainContainerLoader;
import com.dmmsoft.app.model.MainContainer;
import com.dmmsoft.app.model.Investment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.io.IOException;
import java.util.List;

/**
 * Created by milo on 06.05.17.
 */



@Singleton
public class ModelContainer implements IModelContainerService {

    private List<Investment> investments;
    private MainContainer mainContainer;
    private RemoteDownloader remoteDownloader = new RemoteDownloader();
    private static final Logger LOGGER = LoggerFactory.getLogger(WebAppDeployListener.class);
    @PostConstruct
    private void onPostConstruct() {

        this.updateModelFileResources();

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

    private void updateModelFileResources() {
        try {
            LOGGER.info("Data model CSV Zip files download initialized...");
            remoteDownloader.getModelFilesFromRemoteLocation();
        } catch (IOException e) {
            LOGGER.error("Failed to download CSV Zip files from remote location. Model cannot be actualized! {}", e.getMessage());
        }

    }

    public List<Investment> getInvestments() {
        return investments;
    }

    public MainContainer getMainContainer() {
        return mainContainer;
    }

}
