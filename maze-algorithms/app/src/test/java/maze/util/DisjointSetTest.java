package maze.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DisjointSetTest {

    @Test
    public void shouldCreateDisjointSet() {
        DisjointSet disjointSet = new DisjointSet();
        CellList cells = new CellList();

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                cells.add(new Cell(i, j));
            }
        }

        disjointSet.createSet(cells);
        disjointSet.union(0, 1);
        disjointSet.union(2, 1);

        assertEquals(1, disjointSet.find(cells.get(2).getId()));

    }
}
