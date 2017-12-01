package ru.codemonkeystudio.olld40.scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.Disposable;
import ru.codemonkeystudio.olld40.screens.GameScreen;

public abstract class Scene implements Disposable {
    public GameScreen screen;

    public String name;
    public TiledMap map;

    public OrthographicCamera camera;

    public Scene(GameScreen screen) {
        this.screen = screen;

        camera = new OrthographicCamera();
        camera.setToOrtho(false);
    }

    public void loadMap() {
        map = new TmxMapLoader().load("scenes/" + name + ".tmx");
    }

    @Override
    public void dispose() {
        map.dispose();
    }

    public void dispose(boolean location) {
        dispose();
    }

    public abstract void update();
    public abstract void render();
}
