package ru.codemonkeystudio.olld40.objects;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;

public class Grid {
    public Tile grid[][];

    public Grid(TiledMap map) {
        grid = new Tile[5][5];
        for (Object object : map.getLayers().get("walls").getObjects().getByType(RectangleMapObject.class)) {
            RectangleMapObject mapObject = (RectangleMapObject) object;
            Rectangle rect = mapObject.getRectangle();
            grid
                    [Integer.parseInt(mapObject.getName().substring(0, 0))]
                    [Integer.parseInt(mapObject.getName().substring(1, 1))] = new Tile(rect.getX(), rect.getY(), 0);
        }
    }
}
