package com.dmmsoft.dbtest;

import javax.ejb.Singleton;
import javax.enterprise.inject.Default;
import java.util.List;

/**
 * Created by milo on 19.05.17.
 */

@Singleton
@InMemoryStore
public class TestStorage implements ITestStorageService {

    private List<TestEntity> testEntities;


    public void addTestEntity(TestEntity te){
        testEntities.add(te);
    }

}
