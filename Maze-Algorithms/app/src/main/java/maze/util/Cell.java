package maze.util;

import java.awt.Color;
import java.awt.Graphics;

import maze.gui.Maze;

public class Cell {
    private int x, y;

    private boolean visited = false;
    private boolean[] walls = { true, true, true, true };
    private final Color wallColor = Color.BLACK;
    private boolean cursor = false;
    private boolean recursiveVisit = false;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @ExcludeFromJacocoGeneratedReport
    public void draw(Graphics graphics) {
        int xLocation = x * Maze.CELL_SIZE;
        int yLocation = y * Maze.CELL_SIZE;

        graphics.setColor(Color.DARK_GRAY);
        graphics.fillRect(xLocation, yLocation, Maze.CELL_SIZE, Maze.CELL_SIZE);

        if (cursor) {
            graphics.setColor(Color.RED);
            graphics.fillRect(xLocation, yLocation, Maze.CELL_SIZE, Maze.CELL_SIZE);
        } else if (visited) {
            graphics.setColor(Color.YELLOW);
            graphics.fillRect(xLocation, yLocation, Maze.CELL_SIZE, Maze.CELL_SIZE);
        }

        if (recursiveVisit) {
            graphics.setColor(Color.PINK);
            graphics.fillRect(xLocation, yLocation, Maze.CELL_SIZE, Maze.CELL_SIZE);
        }
        drawWalls(graphics, xLocation, yLocation);

    }

    @ExcludeFromJacocoGeneratedReport
    private void drawWalls(Graphics graphics, int xLocation, int yLocation) {

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

    public CellList getUnvisitedNeighbours(CellList cells) {
        CellList neighbours = getNeighbours(cells);
        CellList unvisitedNeighbours = new CellList();

        for (int i = 0; i < neighbours.size(); i++) {
            Cell neighbour = neighbours.get(i);
            if (neighbour.notVisited()) {
                unvisitedNeighbours.add(neighbour);
            }
        }

        return unvisitedNeighbours;
    }

    public CellList getVisitedNeighbours(CellList cells) {
        CellList neighbours = getNeighbours(cells);
        CellList visitedNeighbours = new CellList();

        for (int i = 0; i < neighbours.size(); i++) {
            Cell neighbour = neighbours.get(i);
            if (neighbour.isVisited()) {
                visitedNeighbours.add(neighbour);
            }
        }
        return visitedNeighbours;
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

    @ExcludeFromJacocoGeneratedReport
    public void colorCell(Graphics graphics, Color color) {
        int locationX = x * Maze.CELL_SIZE;
        int locationY = y * Maze.CELL_SIZE;
        graphics.setColor(color);
        graphics.fillRect(locationX, locationY, Maze.CELL_SIZE, Maze.CELL_SIZE);
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
    @ExcludeFromJacocoGeneratedReport
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @ExcludeFromJacocoGeneratedReport
    public boolean[] getWalls() {
        return walls;
    }

    @ExcludeFromJacocoGeneratedReport
    public void setWalls(boolean[] walls) {
        this.walls = walls;
    }

    @ExcludeFromJacocoGeneratedReport
    public boolean notVisited() {
        return !visited;
    }

    @ExcludeFromJacocoGeneratedReport
    public boolean isVisited() {
        return visited;
    }

    @ExcludeFromJacocoGeneratedReport
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    @ExcludeFromJacocoGeneratedReport
    public int getX() {
        return x;
    }

    @ExcludeFromJacocoGeneratedReport
    public void setX(int x) {
        this.x = x;
    }

    @ExcludeFromJacocoGeneratedReport
    public int getY() {
        return y;
    }

    @ExcludeFromJacocoGeneratedReport
    public void setY(int y) {
        this.y = y;
    }

    @ExcludeFromJacocoGeneratedReport
    public boolean isCursor() {
        return cursor;
    }

    @ExcludeFromJacocoGeneratedReport
    public void setCursor(boolean cursor) {
        this.cursor = cursor;
    }

    @ExcludeFromJacocoGeneratedReport
    public boolean isRecursiveVisit() {
        return recursiveVisit;
    }

    @ExcludeFromJacocoGeneratedReport
    public void setRecursiveVisit(boolean recursiveVisit) {
        this.recursiveVisit = recursiveVisit;
    }

}
