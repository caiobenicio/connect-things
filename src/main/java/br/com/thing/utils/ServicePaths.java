package br.com.thing.utils;

public final class ServicePaths {

	// ROOT PATH
	public static final String ALL = "/**";
	public static final String ROOT_PATH = "/api";

	public static final String PUBLIC_ROOT_PATH = ROOT_PATH + "/public";
	public static final String PRIVATE_ROOT_PATH = ROOT_PATH + "/private";

	// PUBLIC PATHS
	public static final String LOGIN_PATH = PUBLIC_ROOT_PATH + "/login";
	public static final String SIGNUP_PATH = PUBLIC_ROOT_PATH + "/signup";
	public static final String LOGOUT_PATH = PUBLIC_ROOT_PATH + "/logout";

	// PRIVATE PATHS
	public static final String USER_PATH = PRIVATE_ROOT_PATH + "/user";
	public static final String PERMISSION_PATH = PRIVATE_ROOT_PATH + "/permission";
	public static final String HOME_PATH = PRIVATE_ROOT_PATH + "/home";
	public static final String COMODO_PATH = PRIVATE_ROOT_PATH + "/room";
	public static final String SENSOR_PATH = PRIVATE_ROOT_PATH + "/sensor";
	public static final String DISPOSITIVO_PATH = PRIVATE_ROOT_PATH + "/device";
	public static final String SCHEDULE_PATH = PRIVATE_ROOT_PATH + "/schedule";
	public static final String GATEWAY_PATH = PRIVATE_ROOT_PATH + "/gateway";
	public static final String PROFILE_PATH = PRIVATE_ROOT_PATH + "/profile";
	public static final String TAB_PATH = PRIVATE_ROOT_PATH + "/tab";

	private ServicePaths() {}

}
