package Demo;

import Screen.HomeScreen;
import TopBar.TopBarHomeScreen;

public class InitialisationSmartphone {
    private HomeScreen homeScreen ;
    private TopBarHomeScreen topBarHomeScreen;

    public InitialisationSmartphone() {
        homeScreen = new HomeScreen() ;
        topBarHomeScreen = new TopBarHomeScreen() ;
    }

    public HomeScreen getHomeScreen() {
        return homeScreen;
    }

    public TopBarHomeScreen getTopBarHomeScreen(){
        return topBarHomeScreen;
    }
}
