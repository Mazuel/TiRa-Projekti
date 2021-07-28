/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package maze.gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Maze {

    public static final int WIDTH = 800;
    public static final int HEIGHT = WIDTH; // Sama kuin leveys, jotta voidaan pitää näkymä symmetrisenä. Helpottaa
                                            // labyrintin käsittelyä
    public static final int CELL_SIZE = WIDTH / 32;
    public static final int START_CELL = 0;
    public static boolean algorithmInAction = false;

    private int mazeColumns, mazeRows;

    public static void main(String[] args) {
        new Maze();
    }

    public Maze() {
        // Jaetaan solujen leveys solun koolla, jotta voidaan luoda oikea määrä soluja
        // käsiteltävään listaan ja pidetään rivien ja kolumnien määrä samana, jotta
        // symmetrisyys säilyy
        mazeColumns = Math.floorDiv(WIDTH, CELL_SIZE);
        mazeRows = mazeColumns;

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                        | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
                showGui();
            }
        });
    }

    private JFrame createGUI() {
        JFrame mainFrame = new JFrame("Labyrintti algoritmeja");

        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.Y_AXIS));
        mainFrame.setContentPane(mainContainer);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mazeBorder = createMazeBorder(mazeRows, mazeColumns);
        MazeGridPanel mazeGridPanel = createMazeGridPanel(mazeRows, mazeColumns);
        JComboBox<AlgorithmComboItem> algorithmOptions = createAlgorithmOptions();

        mazeBorder.add(mazeGridPanel); 

        mainContainer.add(mazeBorder);
        mainContainer.add(algorithmOptions);
        mainContainer.add(createButtonLayout(mazeGridPanel, algorithmOptions));


        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        return mainFrame;
    }

    private void showGui() {
        createGUI().setVisible(true);
    }

    private JPanel createMazeBorder(int mazeRows, int mazeColumns) {
        JPanel mazeBorder = new JPanel();
        mazeBorder.setBounds(0, 0, WIDTH + CELL_SIZE, HEIGHT + CELL_SIZE);
        mazeBorder.setBorder(BorderFactory.createEmptyBorder(CELL_SIZE, CELL_SIZE, CELL_SIZE, CELL_SIZE));
        return mazeBorder;
    }

    private MazeGridPanel createMazeGridPanel(int mazeRows, int mazeColumns) {
        MazeGridPanel mazeGrid = new MazeGridPanel(mazeRows, mazeColumns);
        mazeGrid.setBackground(Color.GRAY);
        return mazeGrid;
    }

    private JComboBox<AlgorithmComboItem> createAlgorithmOptions() {
        JComboBox<AlgorithmComboItem> algorithmOptions = new JComboBox<AlgorithmComboItem>();
        algorithmOptions.setBounds(0, 0, CELL_SIZE, CELL_SIZE);
        algorithmOptions.setBorder(BorderFactory.createEmptyBorder(0, CELL_SIZE, 0, CELL_SIZE));

        algorithmOptions.addItem(new AlgorithmComboItem(AlgorithmOption.BINARY_TREE, "Binääri puu"));
        algorithmOptions.addItem(new AlgorithmComboItem(AlgorithmOption.ALDOUS_BRODER, "Aldous-Broder"));
        return algorithmOptions;
    }

    private JPanel createButtonLayout(MazeGridPanel mazeGridPanel, JComboBox<AlgorithmComboItem> algorithmOptions) {
        JButton runButton = new JButton("Suorita");
        CardLayout buttonLayout = new CardLayout(30, 30);
        JPanel buttons = new JPanel(buttonLayout);

        buttons.setOpaque(false);
        buttons.add(runButton, "Suorita");

        runButton.addActionListener(event -> {
            if (!algorithmInAction) {
                algorithmInAction = true;
                AlgorithmComboItem item = (AlgorithmComboItem) algorithmOptions.getSelectedItem();
                mazeGridPanel.generate(item.getAlgorithm());
            } else {
                System.out.println("Algoritmia suoritetaan jo!");
            }
        });

        return buttons;
    }
}
