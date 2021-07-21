/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package maze.gui;

import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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

        mainContainer.add(mazeBorder);

        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        return mainFrame;
    }

    private void showGui() {
        createGUI().setVisible(true);
    }

    private JPanel createMazeBorder(int rows, int cols) {
        MazeGridPanel mazeGrid = new MazeGridPanel(rows, cols);
        mazeGrid.setBackground(Color.GRAY);

        JPanel mazeBorder = new JPanel();
        mazeBorder.setBounds(0, 0, WIDTH + CELL_SIZE, HEIGHT + CELL_SIZE);
        mazeBorder.setBorder(BorderFactory.createEmptyBorder(CELL_SIZE, CELL_SIZE, CELL_SIZE, CELL_SIZE));

        mazeBorder.add(mazeGrid);
        return mazeBorder;
    }
}
