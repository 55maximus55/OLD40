package ru.codemonkeystudio.olld40.scenes.topdown;

import ru.codemonkeystudio.olld40.screens.GameScreen;
import ru.codemonkeystudio.olld40.tools.ContactListener;

public class HomeScene extends TopDownScene {
    public HomeScene(GameScreen screen) {
        super(screen);
        name = "Home";
        playerRadius = 16f;
        loadMap();
    }

    @Override
    public void render() {
        mapRenderer.render();

        screen.spriteBatch.begin();
        player.draw(screen.spriteBatch);
        screen.spriteBatch.end();

        debugRenderer.render(world, camera.combined);
    }

    @Override
    public void dispose(boolean location) {
        super.dispose(location);
        if (ContactListener.location.equals("location 1 street")) {
            screen.save.putString("location", "Street");
            screen.save.putFloat("Scene-" + "Street" + "-playerX", 632);
            screen.save.putFloat("Scene-" + "Street" + "-playerY", -416);
            screen.save.flush();
        }
    }
}
