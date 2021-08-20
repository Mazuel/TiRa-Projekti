
package maze.gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
    private JFrame mainFrame;

    public static boolean generated = false;

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
                createGUI();
            }
        });
    }

    private void createGUI() {
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
        mainContainer.add(createButtonLayout(mazeGridPanel, algorithmOptions));

        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        this.mainFrame = mainFrame;
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
        algorithmOptions.addItem(new AlgorithmComboItem(AlgorithmOption.PRIMS, "Primin algoritmi"));
        algorithmOptions.addItem(new AlgorithmComboItem(AlgorithmOption.RECURSIVE_BACKTRACKER,
                "Rekursiivinen algoritmi (Recursive Backtracker)"));
        return algorithmOptions;
    }

    private JPanel createButtonLayout(MazeGridPanel mazeGridPanel, JComboBox<AlgorithmComboItem> algorithmOptions) {
        JButton runButton = new JButton("Suorita");
        JButton resetButton = new JButton("Resetoi näkymä");
        CardLayout cardLayout = new CardLayout(15, 15);
        JPanel buttonCards = new JPanel(cardLayout);

        JPanel runButtonCard = new JPanel();
        JPanel resetButtonCard = new JPanel();
        runButtonCard.setLayout(new GridBagLayout());
        resetButtonCard.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 12;
        constraints.ipadx = 500;

        constraints.gridx = 0;
        constraints.gridy = 0;

        runButtonCard.add(algorithmOptions, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;

        runButtonCard.add(runButton, constraints);
        resetButtonCard.add(resetButton, constraints);

        buttonCards.setBorder(BorderFactory.createEmptyBorder(0, CELL_SIZE, CELL_SIZE, CELL_SIZE));

        buttonCards.setOpaque(false);
        buttonCards.add(runButtonCard, "Suorita");
        buttonCards.add(resetButtonCard, "Resetoi näkymä");

        runButton.addActionListener(event -> {
            generated = false;
            AlgorithmComboItem item = (AlgorithmComboItem) algorithmOptions.getSelectedItem();
            mazeGridPanel.generate(item.getAlgorithm());
            cardLayout.next(buttonCards);
        });

        resetButton.addActionListener(event -> {
            if (generated) {
                createGUI();
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Odota, että labyrintti on generoitu loppuun");
            }
        });

        return buttonCards;
    }
}
