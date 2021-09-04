package maze.algorithms.generators;

import java.util.Random;

import maze.gui.Maze;
import maze.gui.MazeGridPanel;
import maze.util.Cell;
import maze.util.CellList;
import maze.util.GeneratorAlgorithm;

/**
 * 'Metsästä ja tapa' -algoritmi </br>
 * 
 * 
 * 1. Valitaan jokin aloitus solu </br>
 * 
 * 2. Asetetaan aloitussolu vierailluksi, ja suoritetaan satunnainen kävely,
 * kunnes ajaudutaan umpikujaan </br>
 * 
 * 3. Siirrytään 'metsästys' -moodiin, jossa käydään labyrintin soluja läpi,
 * kunnes löytyy sellainen solu, jossa ei ole vierailtu ja sillä on ainakin yksi
 * naapuri, jossa on vierailtu. Jos tälläinen solu löytyi, poistetaan seinä
 * tämän ja satunnaisesti valitun vieraillun naapurin väliltä ja asetetaan tämä
 * solu uudeksi 'aloitussoluksi' </br>
 * 
 * 4. Suoritetaan askelia 2 ja 3, kunnes jokaisessa solussa on vierailtu </br>
 * </br>
 * Tämö ei toimi täysin niin kuin sen pitäisi, koska olen toteuttanut algorimien
 * suorittamisen vähän väärällä tavalla. Suorittamisen pitäisi oikeassa
 * versiossa loppua, kun 'metsästys'-moodi ei löydä enää yhtäkään solua. Nyt se
 * lopetataan kun kaikissa soluissa on vierailtu.
 * 
 */
public class HuntAndKillGenerator implements GeneratorAlgorithm {

    private CellList grid;
    private Cell currentCell;
    private Random random;

    public HuntAndKillGenerator(MazeGridPanel gridPanel) {
        this.random = new Random();
        this.grid = gridPanel.getGrid();
        currentCell = grid.get(random.nextInt(grid.size()));
    }

    @Override
    public void generate() {
        currentCell.setVisited(true);
        CellList unvisitedNeighbours = currentCell.getUnvisitedNeighbours(grid);

        if (unvisitedNeighbours.size() > 0) {
            Cell nextCell = unvisitedNeighbours.get(random.nextInt(unvisitedNeighbours.size()));
            currentCell.removeWall(nextCell);
            currentCell = nextCell;
        } else {
            for (int i = 0; i < grid.size(); i++) {
                Cell cell = grid.get(i);
                if (cell.getUnvisitedNeighbours(grid).size() > 0 && cell.isVisited()) {
                    currentCell = cell;
                    break;
                }

            }
        }

        if (grid.isAllVisited()) {
            Maze.generated = true;
        }

    }
}
