package maze.gui;

import java.awt.Color;
import java.awt.Graphics;

public class Cell {
    private int x, y, distance;

    private boolean[] walls = { true, true, true, true };

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.distance = -1;
    }

    public void draw(Graphics graphics) {
        int x2 = x * Maze.CELL_SIZE;
        int y2 = y * Maze.CELL_SIZE;

        graphics.setColor(Color.DARK_GRAY);
        graphics.fillRect(x2, y2, Maze.CELL_SIZE, Maze.CELL_SIZE);

        graphics.setColor(Color.RED);

        if (walls[0]) {
            graphics.drawLine(x2, y2, x2 + Maze.CELL_SIZE, y2);
        }
        if (walls[1]) {
            graphics.drawLine(x2 + Maze.CELL_SIZE, y2, x2 + Maze.CELL_SIZE, y2 + Maze.CELL_SIZE);
        }
        if (walls[2]) {
            graphics.drawLine(x2 + Maze.CELL_SIZE, y2 + Maze.CELL_SIZE, x2, y2 + Maze.CELL_SIZE);
        }
        if (walls[3]) {
            graphics.drawLine(x2, y2 + Maze.CELL_SIZE, x2, y2);
        }
    }

    public void colorCell(Graphics graphics, Color color) {
        int x2 = x * Maze.CELL_SIZE;
        int y2 = y * Maze.CELL_SIZE;
        graphics.setColor(color);
        graphics.fillRect(x2, y2, Maze.CELL_SIZE, Maze.CELL_SIZE);
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }
}
