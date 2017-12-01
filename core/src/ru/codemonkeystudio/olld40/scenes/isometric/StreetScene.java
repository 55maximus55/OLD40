package ru.codemonkeystudio.olld40.scenes.isometric;

import ru.codemonkeystudio.olld40.screens.GameScreen;

public class StreetScene extends IsometricScene {
    public StreetScene(GameScreen screen) {
        super(screen);
        name = "Street";
        loadMap();
    }

    @Override
    public void render() {
        mapRenderer.render();

        screen.spriteBatch.begin();
        player.draw(screen.spriteBatch);
        screen.spriteBatch.end();

//        debugRenderer.render(world, camera.combined);
    }
}
