package Demo;

import Screen.HomeScreen;
import TopBar.TopBarHomeScreen;

public class InitialisationSmartphone {
    // création des variables impossibles à créer dans la classe Smartphone
    private HomeScreen homeScreen ;
    private TopBarHomeScreen topBarHomeScreen;

    /**
     * Constructeur qui sera appelé une fois uniquement à l'initialisation du smartphone.
     * Est utile car si l'on crée un HomeScreen dans la classe Smartphone et que l'on crée un Smartphone dans la classe HomeScreen, cela crée des classes en boucle
     * Afin d'éviter ceci, on fait appel à la classe HomeScreen dans une classe tierce (celle-ci) de manière à ne pas devoir l'appeler dans la classe Smartphone
     * Fait de même avec la topBar (car même soucis)
     */

    public InitialisationSmartphone() {
        homeScreen = new HomeScreen() ;
        topBarHomeScreen = new TopBarHomeScreen() ;
    }

    /**
     * Méthode permettant à la classe Smartphone de Get le HomeScreen lors de l'initialisation
     * @return
     * retourne le panel de HomeScreen
     */

    public HomeScreen getHomeScreen() {
        return homeScreen;
    }

    /**
     * Méthode permettant à la classe Smartphone de Get la topBar lors de l'initialisation
     * @return
     * retourne le panel de la topBar
     */

    public TopBarHomeScreen getTopBarHomeScreen(){
        return topBarHomeScreen;
    }
}
