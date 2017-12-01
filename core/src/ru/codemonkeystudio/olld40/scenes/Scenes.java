package ru.codemonkeystudio.olld40.scenes;

import ru.codemonkeystudio.olld40.scenes.isometric.StreetScene;
import ru.codemonkeystudio.olld40.scenes.sidescroll.CaveScene;
import ru.codemonkeystudio.olld40.scenes.topdown.HomeScene;
import ru.codemonkeystudio.olld40.scenes.topdown.MagazScene;
import ru.codemonkeystudio.olld40.screens.GameScreen;

public class Scenes {
    public static final String START_SCENE = "Home";

    public static final String[] SCENES = {"Home", "Street", "Cave", "Magaz"};

    public static Scene getScene(String name, GameScreen screen) {
        if (name.equals("Home"))
            return new HomeScene(screen);
        if (name.equals("Street"))
            return new StreetScene(screen);
        if (name.equals("Cave"))
            return new CaveScene(screen);
        if (name.equals("Magaz"))
            return new MagazScene(screen);
        return getScene(START_SCENE, screen);
    }
}
