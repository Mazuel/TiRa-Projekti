package maze.util;

import java.awt.Color;
import java.awt.Graphics;

import maze.gui.Maze;

public class Cell {
    private int x, y;

    private boolean visited = false;
    private boolean[] walls = { true, true, true, true };
    private final Color wallColor = Color.BLACK;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics graphics) {
        int xLocation = x * Maze.CELL_SIZE;
        int yLocation = y * Maze.CELL_SIZE;

        graphics.setColor(Color.DARK_GRAY);
        graphics.fillRect(xLocation, yLocation, Maze.CELL_SIZE, Maze.CELL_SIZE);

        if (visited) {
            graphics.setColor(Color.YELLOW);
            graphics.fillRect(xLocation, yLocation, Maze.CELL_SIZE, Maze.CELL_SIZE);
        }

        // Seinien väri
        graphics.setColor(wallColor);

        // Ylä
        if (walls[0]) {
            graphics.drawLine(xLocation, yLocation, xLocation + Maze.CELL_SIZE, yLocation);
        }

        // Oikea
        if (walls[1]) {
            graphics.drawLine(xLocation + Maze.CELL_SIZE, yLocation, xLocation + Maze.CELL_SIZE,
                    yLocation + Maze.CELL_SIZE);
        }

        // Ala
        if (walls[2]) {
            graphics.drawLine(xLocation + Maze.CELL_SIZE, yLocation + Maze.CELL_SIZE, xLocation,
                    yLocation + Maze.CELL_SIZE);
        }
        // Vasen
        if (walls[3]) {
            graphics.drawLine(xLocation, yLocation + Maze.CELL_SIZE, xLocation, yLocation);
        }
    }

    private Cell getNeighbourIfExists(CellList cells, Cell neighbour) {
        return cells.contains(neighbour) ? cells.get(cells.indexOf(neighbour)) : null;
    }

    public void removeWall(Cell neighbour) {
        int x = this.x - neighbour.getX();
        int y = this.y - neighbour.getY();

        if (x == 1) {
            // Vasen seinä
            walls[3] = false;
            neighbour.walls[1] = false;
        } else if (x == -1) {
            // Oikea seinä
            walls[1] = false;
            neighbour.walls[3] = false;
        }

        if (y == 1) {
            // Ylä seinä
            walls[0] = false;
            neighbour.walls[2] = false;
        } else if (y == -1) {
            // Ala seinä
            walls[2] = false;
            neighbour.walls[0] = false;
        }
    }

    // Vaaka- ja pystyakselin naapurit
    public CellList getNeighbours(CellList cells) {
        CellList neighbours = new CellList();

        Cell topNeighbour = getNeighbour(cells, Direction.TOP);
        Cell leftNeighbour = getNeighbour(cells, Direction.LEFT);
        Cell rightNeighbour = getNeighbour(cells, Direction.RIGHT);
        Cell bottomNeighbour = getNeighbour(cells, Direction.BOTTOM);

        if (topNeighbour != null) {
            neighbours.add(topNeighbour);
        }
        if (leftNeighbour != null) {
            neighbours.add(leftNeighbour);
        }
        if (rightNeighbour != null) {
            neighbours.add(rightNeighbour);
        }
        if (bottomNeighbour != null) {
            neighbours.add(bottomNeighbour);
        }

        return neighbours;
    }

    public Cell getNeighbour(CellList cells, Direction direction) {
        switch (direction) {
            case TOP:
                return getNeighbourIfExists(cells, new Cell(x, y - 1));
            case RIGHT:
                return getNeighbourIfExists(cells, new Cell(x + 1, y));
            case LEFT:
                return getNeighbourIfExists(cells, new Cell(x - 1, y));
            case BOTTOM:
                return getNeighbourIfExists(cells, new Cell(x, y + 1));
            default:
                return null;
        }
    }

    public void colorCell(Graphics graphics, Color color) {
        int cellWidth = x * Maze.CELL_SIZE;
        int cellHeight = y * Maze.CELL_SIZE;
        graphics.setColor(color);
        graphics.fillRect(cellWidth, cellHeight, Maze.CELL_SIZE, Maze.CELL_SIZE);
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
        return "(" + x + ", " + y + ")";
    }

    public boolean[] getWalls() {
        return walls;
    }

    public void setWalls(boolean[] walls) {
        this.walls = walls;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
