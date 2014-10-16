package com.example.spawneddeliveryservice.tasks;

public class ApiConstants {
    private static final String baseApiUrl = "http://spawndeliveryservice.apphb.com/api/";

    public static final String LOGIN = baseApiUrl + "account/login";
    public static final String REGISTER = baseApiUrl + "account/register";

    public static final String COORDINATES = baseApiUrl + "users/coordinates";

    public static final String STATS = baseApiUrl + "stats/get";
    public static final String TOWNS = baseApiUrl + "stats/towns";

    public static final String TRANSPORTS_CREATE = baseApiUrl+"transports/create";
    public static final String TRANSPORTS_ACTIVE = baseApiUrl+"transports/active";
    public static final String TRANSPORTS_PENDING = baseApiUrl+"transports/pending";
    public static final String TRANSPORTS_FINISHED = baseApiUrl+"transports/finished";
    public static final String TRANSPORTS_ADD_PACKAGE = baseApiUrl+"transports/addPackage";
    public static final String TRANSPORTS_DETAILS = baseApiUrl+"transports/get";
    public static final String TRANSPORTS_FIND = baseApiUrl+"transports/find";

    public static final String PACKAGES_CREATE = baseApiUrl+"packages/create";
    public static final String PACKAGES_COORDINATES = baseApiUrl+"packages/coordinates";
    public static final String PACKAGES_FINISHED = baseApiUrl+"packages/finished";
    public static final String PACKAGES_DETAILS = baseApiUrl+"packages/get";
    public static final String PACKAGES_FIND = baseApiUrl+"packages/find";
}
