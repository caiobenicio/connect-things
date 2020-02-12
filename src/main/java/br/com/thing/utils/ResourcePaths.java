package br.com.thing.utils;

public final class ResourcePaths {

	///////////////////////////////////////////////////////////////
	// ROOT PATH
	///////////////////////////////////////////////////////////////

	public static final String ALL = "/**";
	
    public static final String ROOT_PATH = "/api";
    
    public static final String PUBLIC_ROOT_PATH = ROOT_PATH + "/public";
    
    public static final String PRIVATE_ROOT_PATH = ROOT_PATH + "/private";
    
    ///////////////////////////////////////////////////////////////
    // PRIVATE PATHS
    ///////////////////////////////////////////////////////////////


    public static final String USER_PATH =  PRIVATE_ROOT_PATH + "/user";
    
    public static final String PERMISSION_PATH = PRIVATE_ROOT_PATH + "/permission";

    public static final String RESIDENCIA_PATH =  PRIVATE_ROOT_PATH + "/residencia";

    public static final String COMODO_PATH =  PRIVATE_ROOT_PATH + "/comodo";

    public static final String SENSOR_PATH =  PRIVATE_ROOT_PATH + "/sensor";

    public static final String DISPOSITIVO_PATH =  PRIVATE_ROOT_PATH + "/dispositivo";


    ///////////////////////////////////////////////////////////////
    // PUBLIC PATHS
    ///////////////////////////////////////////////////////////////

    public static final String SCHEDULE_PATH =  PUBLIC_ROOT_PATH + "/schedule";

    public static final String LOGIN_PATH = PUBLIC_ROOT_PATH + "/login";

    public static final String LOGOUT_PATH = PUBLIC_ROOT_PATH + "/logout";

    private ResourcePaths() {}

}
