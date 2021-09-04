package maze.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Aputietorakenne, jonka avulla jaetaan solut erillisiin osajoukkoihin, eli
 * kaikkien joukkojen leikkaus on tyhjä joukko
 */
public class DisjointSet {
    private Map<Integer, Integer> parent = new HashMap<>();
    private Map<Integer, Integer> rank = new HashMap<>();

    /**
     * Alustaa erilliset joukot labyrintin soluille
     * 
     * @param cells labyrintin solut
     */
    public void createSet(CellList cells) {
        for (int i = 0; i < cells.size(); i++) {
            Cell cell = cells.get(i);
            cell.setId(i);
            int id = cell.getId();
            parent.put(id, id);
            rank.put(id, 0);
        }
    }

    /**
     * Etsii rekursiivisesti joukon 'edustajan'
     * 
     * @param cellId
     * @return int
     */
    public int find(int cellId) {
        if (parent.get(cellId) == cellId) {
            return cellId;
        }
        return find(parent.get(cellId));
    }

    /**
     * Joukkojen a ja b yhdistys arvon (rank) perusteella, asettaa aina isomman puun
     * pienemmän puun vanhemmaksi
     * 
     * 
     * @param a
     * @param b
     */
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
