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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import maze.util.CellList;

public class Maze {

    public static final int WIDTH = 800;
    public static final int HEIGHT = WIDTH;
    public static final int CELL_SIZE = WIDTH / 32;
    public static final int START_CELL = 0;

    public static void main(String[] args) {
        new Maze();
    }

    public Maze() {

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
        JFrame mainFrame = new JFrame("Java Maze Generator");

        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.Y_AXIS));
        mainFrame.setContentPane(mainContainer);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mazeBorder = createMazeBorder(WIDTH, HEIGHT);
        MazeGridPanel mazeGridPanel = createMazeGridPanel(WIDTH, HEIGHT);

        mazeBorder.add(mazeGridPanel);

        mainContainer.add(mazeBorder);
        mainContainer.add(createCards(mazeGridPanel));

        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        return mainFrame;
    }

    private void showGui() {
        createGUI().setVisible(true);
    }

    private JPanel createMazeBorder(int rows, int cols) {
        JPanel mazeBorder = new JPanel();
        mazeBorder.setBounds(0, 0, WIDTH + CELL_SIZE, HEIGHT + CELL_SIZE);
        mazeBorder.setBorder(BorderFactory.createEmptyBorder(CELL_SIZE, CELL_SIZE, CELL_SIZE, CELL_SIZE));
        return mazeBorder;
    }

    private MazeGridPanel createMazeGridPanel(int rows, int cols) {
        MazeGridPanel mazeGrid = new MazeGridPanel(rows, cols);
        mazeGrid.setBackground(Color.GRAY);

        return mazeGrid;
    }

    private JPanel createCards(MazeGridPanel mazeGridPanel) {
        JButton runButton = new JButton("Suorita");
        CardLayout cardLayout = new CardLayout(30, 30);
        JPanel cards = new JPanel(cardLayout);

        cards.setOpaque(false);
        cards.add(runButton, "Suorita");

        return cards;
    }
}
