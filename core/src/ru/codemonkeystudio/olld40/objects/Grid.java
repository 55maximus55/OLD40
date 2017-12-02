package ru.codemonkeystudio.olld40.objects;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Random;

public class Grid {
    public Tile grid[][];

    public Grid(TiledMap map) {
        grid = new Tile[5][5];
        for (Object object : map.getLayers().get("grid").getObjects().getByType(RectangleMapObject.class)) {
            RectangleMapObject mapObject = (RectangleMapObject) object;
            Rectangle rect = mapObject.getRectangle();
            grid
                    [Integer.parseInt(mapObject.getName().substring(0, 1))]
                    [Integer.parseInt(mapObject.getName().substring(1, 2))] = new Tile(rect.getX() + rect.getWidth() / 2, rect.getY() + rect.getHeight() / 2, -1);
        }
        grid[2][4].app = 0;
    }

    public Vector2 goTo() {
        ArrayList<Vector2> a = new ArrayList<Vector2>();
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                if (grid[x][y].app == -1) {
                    a.add(new Vector2(grid[x][y].x, grid[x][y].y));
                }
            }
        }
        return a.get(new Random().nextInt(a.size()));
    }
}
