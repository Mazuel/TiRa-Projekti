package maze.gui;

import java.awt.Color;
import java.awt.Graphics;

public class Cell {
    private int x, y;

    private boolean visited = false;

    private boolean[] walls = { true, true, true, true };

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics graphics) {
        int cellWidth = x * Maze.CELL_SIZE;
        int cellHeight = y * Maze.CELL_SIZE;

        graphics.setColor(Color.DARK_GRAY);
        graphics.fillRect(cellWidth, cellHeight, Maze.CELL_SIZE, Maze.CELL_SIZE);

        if (visited) {
            graphics.setColor(Color.YELLOW);
            graphics.fillRect(cellWidth, cellHeight, Maze.CELL_SIZE, Maze.CELL_SIZE);
        }

        graphics.setColor(Color.RED);

        if (walls[0]) {
            graphics.drawLine(cellWidth, cellHeight, cellWidth + Maze.CELL_SIZE, cellHeight);
        }
        if (walls[1]) {
            graphics.drawLine(cellWidth + Maze.CELL_SIZE, cellHeight, cellWidth + Maze.CELL_SIZE,
                    cellHeight + Maze.CELL_SIZE);
        }
        if (walls[2]) {
            graphics.drawLine(cellWidth + Maze.CELL_SIZE, cellHeight + Maze.CELL_SIZE, cellWidth,
                    cellHeight + Maze.CELL_SIZE);
        }
        if (walls[3]) {
            graphics.drawLine(cellWidth, cellHeight + Maze.CELL_SIZE, cellWidth, cellHeight);
        }
    }

    public void removeWalls(Cell next) {
        int x = this.x - next.x;

        if (x == 1) {
            walls[3] = false;
            next.walls[1] = false;
        } else if (x == -1) {
            walls[1] = false;
            next.walls[3] = false;
        }
    }

    public void colorCell(Graphics graphics, Color color) {
        int cellWidth = x * Maze.CELL_SIZE;
        int cellHeight = y * Maze.CELL_SIZE;
        graphics.setColor(color);
        graphics.fillRect(cellWidth, cellHeight, Maze.CELL_SIZE, Maze.CELL_SIZE);
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        Cell cell = (Cell) o;

        return Integer.compare(x, cell.x) == 0 && Integer.compare(y, cell.y) == 0;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }

}
