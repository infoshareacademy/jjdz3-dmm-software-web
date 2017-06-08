package com.dmmsoft;

/**
 * Created by milo on 08.06.17.
 */
public class ConstantsProvider {

    private ConstantsProvider(){}

    public static final String CRITERIA_MODERATION_MESSAGE = "Note! Your input data does not correspond to current investment history of quotations. \n" +
            "    For analysis system used nearest possible quoutations acording to dates from submitted form.\n" +
            "    User criteria moderated by system are listed in User input moderation report.";
    public static final String NO_DATA_FOR_CRITERIA_MESSAGE = "Error! No data for current criteria!";

    public static final String AUTH_USER = "authenticatedUser";

    public static final String CONTENT_WRAPPER_COLLECTION = "contentWrappers";
    public static final String USER_FAVOURITE_CUSTOM_NAME = "userCustomName";
    public static final String CRITERIA_ID = "criteriaId";
    public static final String DELETE_ACTION = "deleteAction";
    public static final String UPDATE_ACTION = "updateAction";

    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String INVESTMENT_NAME = "investmenName";
    public static final String CAPITAL = "capital";
    public static final String BUY_DATE = "buyDate";
    public static final String SELL_DATE = "sellDate";
    public static final String IS_FAVOURITE = "isFavourite";
    public static final String CONTENT_WRAPPER = "contentWrapper";
    public static final String APP_MESSAGE = "message";

}
