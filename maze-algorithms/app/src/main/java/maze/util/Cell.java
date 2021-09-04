package maze.util;

public class Cell {
    private int x, y, id;

    private boolean visited = false;
    private boolean[] walls = { true, true, true, true };
    private boolean cursor = false;
    private boolean recursiveVisit = false;
    private boolean bfsShortestPath = false;
    private Cell parent;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Palauttaa solun, jos se kuuluu labyrinttiin
     * 
     * @param cells lista kaikista soluista
     * @param cell  solu, jonka olemassaolo halutaan selvittää
     * @return Cell
     */
    private Cell getCellIfExists(CellList cells, Cell cell) {
        return cells.contains(cell) ? cells.get(cells.indexOf(cell)) : null;
    }

    /**
     * Poistaa seinän solun ja naapurin väliltä
     * 
     * @param neighbour
     */
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

    /**
     * Palauttaa solun kaikki naapurit x- ja y-akselilla
     * 
     * @param cells
     * @return CellList
     */
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

    /**
     * Palauttaa listan naapurisoluja, joissa ei ole vierailtu
     * 
     * @param cells
     * @return CellList
     */
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

    /**
     * Palauttaa listan naapurisoluja, joissa ei ole vierailtu ja joiden välissä ei
     * ole seinää
     * 
     * @param cells
     * @return CellList
     */
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

    /**
     * Palauttaa listan naapurisoluja, joissa on vierailtu
     * 
     * @param cells
     * @return CellList
     */
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

    /**
     * Palauttaa totuusarvon onko solun ja naapurisolun välillä reittiä, eli ei
     * seinää
     * 
     * @param neighbour naapuri solu
     * @return boolean
     */
    public boolean isPath(Cell neighbour) {
        int x = this.x - neighbour.getX();
        int y = this.y - neighbour.getY();

        if (x == 1) {
            // Vasen seinä
            return !(walls[3] || neighbour.walls[1]);
        } else if (x == -1) {
            // Oikea seinä
            return !(walls[1] || neighbour.walls[3]);
        }

        if (y == 1) {
            // Ylä seinä
            return !(walls[0] || neighbour.walls[2]);
        } else if (y == -1) {
            // Ala seinä
            return !(walls[2] || neighbour.walls[0]);
        } else {
            return false;
        }
    }

    /**
     * @param cells     lista labyrintin kaikista soluista
     * @param direction suunta josta naapuri halutaan hakea
     * @return Cell
     */
    public Cell getNeighbour(CellList cells, Direction direction) {
        switch (direction) {
            case TOP:
                return getCellIfExists(cells, new Cell(x, y - 1));
            case RIGHT:
                return getCellIfExists(cells, new Cell(x + 1, y));
            case LEFT:
                return getCellIfExists(cells, new Cell(x - 1, y));
            case BOTTOM:
                return getCellIfExists(cells, new Cell(x, y + 1));
            default:
                return null;
        }
    }

    public void resetCell() {
        walls = new boolean[] { true, true, true, true };
        cursor = false;
        visited = false;
        recursiveVisit = false;
        bfsShortestPath = false;
    }

    /**
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        Cell cell = (Cell) o;

        return Integer.compare(x, cell.x) == 0 && Integer.compare(y, cell.y) == 0;
    }

    /**
     * @return String
     */
    @Override
    @ExcludeFromJacocoGeneratedReport
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    /**
     * @return boolean[]
     */
    @ExcludeFromJacocoGeneratedReport
    public boolean[] getWalls() {
        return walls;
    }

    /**
     * @param walls
     */
    @ExcludeFromJacocoGeneratedReport
    public void setWalls(boolean[] walls) {
        this.walls = walls;
    }

    /**
     * @return boolean
     */
    @ExcludeFromJacocoGeneratedReport
    public boolean notVisited() {
        return !visited;
    }

    /**
     * @return boolean
     */
    @ExcludeFromJacocoGeneratedReport
    public boolean isVisited() {
        return visited;
    }

    /**
     * @param visited
     */
    @ExcludeFromJacocoGeneratedReport
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * @return int
     */
    @ExcludeFromJacocoGeneratedReport
    public int getX() {
        return x;
    }

    /**
     * @param x
     */
    @ExcludeFromJacocoGeneratedReport
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return int
     */
    @ExcludeFromJacocoGeneratedReport
    public int getY() {
        return y;
    }

    /**
     * @param y
     */
    @ExcludeFromJacocoGeneratedReport
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return boolean
     */
    @ExcludeFromJacocoGeneratedReport
    public boolean isCursor() {
        return cursor;
    }

    /**
     * @param cursor
     */
    @ExcludeFromJacocoGeneratedReport
    public void setCursor(boolean cursor) {
        this.cursor = cursor;
    }

    /**
     * @return boolean
     */
    @ExcludeFromJacocoGeneratedReport
    public boolean isRecursiveVisit() {
        return recursiveVisit;
    }

    /**
     * @param recursiveVisit
     */
    @ExcludeFromJacocoGeneratedReport
    public void setRecursiveVisit(boolean recursiveVisit) {
        this.recursiveVisit = recursiveVisit;
    }

    /**
     * @return Cell
     */
    @ExcludeFromJacocoGeneratedReport
    public Cell getParent() {
        return parent;
    }

    /**
     * @param parent
     */
    @ExcludeFromJacocoGeneratedReport
    public void setParent(Cell parent) {
        this.parent = parent;
    }

    /**
     * @return boolean
     */
    @ExcludeFromJacocoGeneratedReport
    public boolean isBfsShortestPath() {
        return bfsShortestPath;
    }

    /**
     * @param bfsShortestPath
     */
    @ExcludeFromJacocoGeneratedReport
    public void setBfsShortestPath(boolean bfsShortestPath) {
        this.bfsShortestPath = bfsShortestPath;
    }

    /**
     * @return int
     */
    @ExcludeFromJacocoGeneratedReport
    public int getId() {
        return id;
    }

    /**
     * @param id
     */
    @ExcludeFromJacocoGeneratedReport
    public void setId(int id) {
        this.id = id;
    }

}
