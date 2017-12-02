package ru.codemonkeystudio.olld40.objects;

public class Grid {
    public Tile grid[][];

    public Grid() {
        grid = new Tile[5][5];
        for (int i = 0; i < grid[0].length; i++) {
            for (int j = 0; j < grid.length; j++) {
                grid[j][i] = new Tile(0f, 0f, 0);
            }
        }
    }
}
