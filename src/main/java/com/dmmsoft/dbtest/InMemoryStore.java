package com.dmmsoft.dbtest;

/**
 * Created by milo on 19.05.17.
 */
import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({FIELD, METHOD, PARAMETER, TYPE})

public @interface InMemoryStore {

}
