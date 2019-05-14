package constants;

import static utils.Util.convertStringToBase64;

public class Endpoints {


    public static final String BASE_URL = "http://test-api.d6.dev.devcaz.com";
    public static final String REGISTRATION = "/v2/players";
    public static final String CLIENT_CREDENTIALS_GRANT = "/v2/oauth2/token";
    public static final String AUTHORIZATION_BASIC = "Basic " + convertStringToBase64("front_2d6b0a8391742f5d789d7d915755e09e:");


}
