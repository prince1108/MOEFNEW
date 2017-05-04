package http;

/**
 * Created by ravi on 07-03-2017.
 */

public class NSConfig {
    public String BASE_URL = "http://beta.newsstate.com/jsonfeed";
    public String FEED_SIDE_MENU="/ns-mob-json-menu.php";
    public String FEED_MOBILE_PHOTO="/mob-json-mobile-photo.php";

    private static NSConfig myObj;
    /**
     * Create private constructor
     */
    private NSConfig(){
    }
    /**
     * Create a static method to get instance.
     */
    public static NSConfig getInstance(){
        if(myObj == null){
            myObj = new NSConfig();
        }
        return myObj;
    }
}
