import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MainSwitch implements KeyEventDispatcher, ActionListener {

    private final static int DEFAULT_SIZE = 8;
    private JFrame top;
    private JPanel gamePanel;
    private JLabel[][] gameBoardUI;
    private JMenuItem reset, quit;
    private NumberGame theGame;
    /* TODO: declare your game1024.NumberGame variable */

    public GUI()
    {
        top = new JFrame ("Insert a title here");
        /* TODO: instantiate the game1024.NumberGame object */

        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(DEFAULT_SIZE, DEFAULT_SIZE));
        top.add(gamePanel, BorderLayout.CENTER);
        KeyboardFocusManager kManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        kManager.addKeyEventDispatcher(this);

        gameBoardUI = new JLabel[DEFAULT_SIZE][DEFAULT_SIZE];
        Font myTextFont = new Font(Font.SERIF, Font.BOLD, 40);
        for (int k = 0; k < gameBoardUI.length; k++)
            for (int m = 0; m < gameBoardUI[k].length; m++)
            {
                gameBoardUI[k][m] = new JLabel("X", JLabel.CENTER);
                gameBoardUI[k][m].setFont(myTextFont);
                gameBoardUI[k][m].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                gameBoardUI[k][m].setPreferredSize(new Dimension (100, 100));
                gamePanel.add(gameBoardUI[k][m]);
            }

        JMenuBar mb = new JMenuBar();
        top.setJMenuBar(mb);
        JMenu game = new JMenu("Game");
        mb.add(game);
        reset = new JMenuItem ("Reset");
        reset.addActionListener(this);
        quit = new JMenuItem("Quit");
        quit.addActionListener(this);
        game.add(reset);
        game.addSeparator();
        game.add(quit);

        /* TODO: update the UI using the result of getNonEmptyCells() of your game1024.NumberGame object */
        top.pack();
        top.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        top.setVisible(true);
    }

    public static void main (String[] args)
    {
        new GUI();
    }

    private void updateUI()
    {
        /* TODO: complete this method to update the JLabels using the result
            provided by the getNonEmptyCells() method of game1024.NumberGame

            Use JLabel's setText() method to update the text on each JLabel
         */
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        if (e.getID() == KeyEvent.KEY_PRESSED) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:

                    /* TODO: invoked the move() method of your game1024.NumberGame */

                    break;

                /* TODO: complete the remaining three case labels */

            }


            /* TODO: update the game board (invoke the private updateUI() method */

            /* TODO: check if the player has won and display appropriate dialog */

            /* TODO: check if the player can't move further, and ask whether she
                wants to play again?
            */
        }
        return true;
    }

    @Override
    public void actionPerformed (ActionEvent e)
    {
        Object which = e.getSource();
        /* TODO add your code to respond to the menu items */
    }
}
