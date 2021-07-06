package ru.codemonkeystudio.olld40.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.codemonkeystudio.olld40.CMSGame;

public class DesktopLauncher {

    public static void main(String[] args) {

        LwjglApplicationConfiguration configuration = new LwjglApplicationConfiguration();
        configuration.width = 1366;
        configuration.height = 768;

        new LwjglApplication(new CMSGame(), configuration);

    }

}
