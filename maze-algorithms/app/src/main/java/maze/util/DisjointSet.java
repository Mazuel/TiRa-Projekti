package maze.util;

import java.util.HashMap;
import java.util.Map;

public class DisjointSet {
    private Map<Integer, Integer> parent = new HashMap<>();
    private Map<Integer, Integer> rank = new HashMap<>();

    public void createSet(CellList cells) {
        for (int i = 0; i < cells.size(); i++) {
            Cell cell = cells.get(i);
            cell.setId(i);
            int id = cell.getId();
            parent.put(id, id);
            rank.put(id, 0);
        }
    }

    public int find(int cellId) {
        if (parent.get(cellId) == cellId) {
            return cellId;
        }
        return find(parent.get(cellId));
    }

    public void union(int a, int b) {
        int xRoot = find(a);
        int yRoot = find(b);

        if (xRoot == yRoot) {
            return;
        }

        if (rank.get(xRoot) > rank.get(yRoot)) {
            parent.put(yRoot, xRoot);
        } else if (rank.get(xRoot) < rank.get(yRoot)) {
            parent.put(xRoot, yRoot);
        } else {
            parent.put(xRoot, yRoot);
            rank.put(yRoot, rank.get(yRoot) + 1);
        }
    }
}
