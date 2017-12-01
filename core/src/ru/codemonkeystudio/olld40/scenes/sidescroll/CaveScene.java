package ru.codemonkeystudio.olld40.scenes.sidescroll;

import ru.codemonkeystudio.olld40.screens.GameScreen;

public class CaveScene extends SideScrollScene {
    public CaveScene(GameScreen screen) {
        super(screen);
        name = "Cave";
        loadMap();
    }

    @Override
    public void render() {
        mapRenderer.render();
        debugRenderer.render(world, camera.combined);
    }
}
