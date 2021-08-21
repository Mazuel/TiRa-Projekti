package maze.util;

public class Cell {
    private int x, y;

    private boolean visited = false;
    private boolean[] walls = { true, true, true, true };
    private boolean cursor = false;
    private boolean recursiveVisit = false;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
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

    public CellList getUnvisitedValidMoveNeighbours(CellList cells) {
        CellList neighbours = getUnvisitedNeighbours(cells);
        CellList validNeighbours = new CellList();

        for (int i = 0; i < neighbours.size(); i++) {
            Cell neighbour = neighbours.get(i);
            if (isPath(neighbour)) {
                validNeighbours.add(neighbour);
            }
        }
        return validNeighbours;
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

    public boolean isPath(Cell neighbour) {
        int x = this.x - neighbour.getX();
        int y = this.y - neighbour.getY();

        if (x == 1) {
            // Vasen seinä
            return !(walls[3] && neighbour.walls[1]);
        } else if (x == -1) {
            // Oikea seinä
            return !(walls[1] && neighbour.walls[3]);
        }

        if (y == 1) {
            // Ylä seinä
            return !(walls[0] && neighbour.walls[2]);
        } else {
            // Ala seinä
            return !(walls[2] && neighbour.walls[0]);
        }
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
    public void setRecursiveVisit(boolean deadEnd) {
        this.recursiveVisit = deadEnd;
    }

}
