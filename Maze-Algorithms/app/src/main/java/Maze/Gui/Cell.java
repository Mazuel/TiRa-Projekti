package Maze.Gui;

import java.awt.Color;
import java.awt.Graphics;

public class Cell {
    private int x, y, distance;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.distance = -1;
    }

    public void draw(Graphics graphics) {
        int x2 = x * Maze.BORDER_SIZE;
        int y2 = x * Maze.BORDER_SIZE;

        graphics.setColor(Color.DARK_GRAY);
        graphics.fillRect(x2, y2, Maze.BORDER_SIZE, Maze.BORDER_SIZE);
    }
}
