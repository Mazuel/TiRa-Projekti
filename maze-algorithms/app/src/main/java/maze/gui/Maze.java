
package maze.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import maze.algorithms.solvers.BfsSolver;
import maze.util.GeneratorAlgorithm;

public class Maze {

    public static final int WIDTH = 800;
    public static final int HEIGHT = WIDTH; // Sama kuin leveys, jotta voidaan pitää näkymä symmetrisenä.
    private static int CELL_DIVIDER = 16; // 8, 16, 48, 80
    public static int CELL_SIZE = 50;
    public static final int START_CELL = 0;
    private int algorithmSpeed = 1; // viive algoritmien 'askelien' välissä millisekunteina

    private int mazeColumns, mazeRows;
    private JFrame mainFrame;

    public static boolean generated = false;
    public static boolean solved = false;
    private boolean solvingInAction = false;
    private boolean generatorInAction = false;

    public static void main(String[] args) {
        try {
            String argument = args[0];
            if (argument.equals("small")) {
                CELL_DIVIDER = 16;
            } else if (argument.equals("medium")) {
                CELL_DIVIDER = 48;
            } else if (argument.equals("large")) {
                CELL_DIVIDER = 80;
            }
        } catch (ArrayIndexOutOfBoundsException eBoundsException) {
            System.out.println("Labyrintin kokoa ei annettu, käytetään oletuksena pientä");
        }
        CELL_SIZE = WIDTH / CELL_DIVIDER;
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
        mainFrame.add(mainContainer, BorderLayout.CENTER);
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
        int borderSize = 20;
        mazeBorder.setBounds(0, 0, WIDTH + borderSize, HEIGHT + borderSize);
        mazeBorder.setBorder(BorderFactory.createEmptyBorder(borderSize, borderSize, borderSize, borderSize));
        mazeBorder.setBackground(Color.GRAY);
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
        algorithmOptions
                .addItem(new AlgorithmComboItem(AlgorithmOption.RECURSIVE_BACKTRACKER, "Recursive Backtracker"));
        return algorithmOptions;
    }

    private JPanel createButtonLayout(MazeGridPanel mazeGridPanel, JComboBox<AlgorithmComboItem> algorithmOptions) {
        JButton runButton = new JButton("Suorita");
        JButton resetButton = new JButton("Resetoi näkymä");
        JButton solveMaze = new JButton("Ratkaise labyrintti");

        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 20, 5);
        Hashtable<Integer, JLabel> sliderLabels = new Hashtable<>();
        sliderLabels.put(0, new JLabel("Nopea"));
        sliderLabels.put(20, new JLabel("Hidas"));
        slider.setLabelTable(sliderLabels);
        slider.setPaintLabels(true);

        CardLayout cardLayout = new CardLayout(15, 15);
        JPanel buttonCards = new JPanel(cardLayout);

        JPanel card1 = new JPanel();
        JPanel card2 = new JPanel();
        card1.setLayout(new GridBagLayout());
        card2.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 12;
        constraints.ipadx = 500;

        constraints.gridx = 0;
        constraints.gridy = 0;

        card1.add(algorithmOptions, constraints);
        card2.add(solveMaze, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;

        card1.add(runButton, constraints);
        card2.add(resetButton, constraints);

        constraints.gridx = 2;
        constraints.gridy = 2;

        card1.add(slider, constraints);

        buttonCards.setBorder(BorderFactory.createEmptyBorder(0, CELL_SIZE, CELL_SIZE, CELL_SIZE));

        buttonCards.setOpaque(false);
        buttonCards.add(card1, "1");
        buttonCards.add(card2, "2");

        runButton.addActionListener(event -> {
            generated = false;
            AlgorithmComboItem item = (AlgorithmComboItem) algorithmOptions.getSelectedItem();
            GeneratorAlgorithm algorithm = mazeGridPanel.createAlgorithm(item.getAlgorithm());
            createAndStartTimer(algorithm, mazeGridPanel);
            cardLayout.next(buttonCards);
        });

        resetButton.addActionListener(event -> {
            if (generated && !solvingInAction) {
                resetGUI(mazeGridPanel);
                cardLayout.next(buttonCards);
                solved = false;
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Odota, että algoritmi on suorittanut loppuun");
            }
        });

        solveMaze.addActionListener(event -> {
            if (!solvingInAction && !generatorInAction && !solved) {
                System.out.println("hello");
                BfsSolver bfsSolver = new BfsSolver(mazeGridPanel);
                solvingInAction = true;
                final Timer timer = new Timer(algorithmSpeed, null);
                timer.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!solved) {
                            bfsSolver.solve();
                        } else {
                            solvingInAction = false;
                            timer.stop();
                        }
                        mazeGridPanel.repaint();
                    }
                });
                timer.start();
            } else if (solved) {
                JOptionPane.showMessageDialog(mainFrame,
                        "Labyrintti on jo ratkaistu, resetoi näkymä suorittaaksesi uudelleen.");
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Algoritmi on jo käynnissä!");
            }
        });

        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                algorithmSpeed = slider.getValue();
                System.out.println(slider.getValue());
            }
        });

        return buttonCards;
    }

    private void resetGUI(MazeGridPanel gridPanel) {
        gridPanel.resetGrid();
        gridPanel.repaint();
    }

    public void createAndStartTimer(GeneratorAlgorithm algorithm, MazeGridPanel mazeGridPanel) {
        Timer timer = new Timer(algorithmSpeed, null);
        generatorInAction = true;
        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!generated) {
                    algorithm.generate();
                } else {
                    mazeGridPanel.getGrid().setAllUnvisited();
                    generatorInAction = false;
                    timer.stop();
                }
                mazeGridPanel.repaint();
            }
        });
        timer.start();
    }
}
