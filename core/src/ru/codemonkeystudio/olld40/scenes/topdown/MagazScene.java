package ru.codemonkeystudio.olld40.scenes.topdown;

import ru.codemonkeystudio.olld40.screens.GameScreen;

public class MagazScene extends TopDownScene {
    public MagazScene(GameScreen screen) {
        super(screen);
        name = "Magaz";
        playerRadius = 8f;
        loadMap();
    }

    @Override
    public void render() {
        mapRenderer.render();
        debugRenderer.render(world, camera.combined);
    }
}
