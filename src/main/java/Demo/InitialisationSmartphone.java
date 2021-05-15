package Demo;

import Screen.HomeScreen;

public class InitialisationSmartphone {
    private HomeScreen homeScreen ;

    public InitialisationSmartphone() {
        homeScreen = new HomeScreen() ;
    }

    public HomeScreen getHomeScreen() {
        return homeScreen;
    }
}
