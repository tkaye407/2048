import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Game implements KeyListener
{
    /* boolean variables for key pressing */
    boolean left = false, right = false, up = false, down = false;

    /* create a two boards for convenience */
    int[][] board = new int[4][4];
    NumberBox[][] visualBoard = new NumberBox[4][4];

    /* score is the current score and it is a public int */
    public int score = 0;

    /* make a GameCan object to draw the board */
    GameCan GameCanvas = new GameCan();

    /* create the frame, panels, score labels, restar button..etc */
    JFrame frame = new JFrame("2014 Game - TKStyle");
    JPanel topPanel = new JPanel();
    JPanel midPanel = new JPanel();
    JPanel bottomPanel = new JPanel();
    JPanel thePanel = new JPanel();
    JLabel Score = new JLabel("Total Score: " +  score);
    JLabel Intro = new JLabel("Welcome to TK's 2048");
    JButton restartButton = new JButton("Play Again?");

    /* is the program due to add a new tile */
    boolean shouldAddTile;

    /* array of colors -- same as in canvas */
    Color[] colors = {Color.RED,  Color.ORANGE, Color.YELLOW, Color.GREEN,Color.BLUE, Color.CYAN, Color.MAGENTA};

    public static void main(String[] args) 
    {
        Game game = new Game();
    }

    /* create a new game object */
    public Game()
    {
        /* set the frame characteristics */
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700,750);

        /* set the panel layouts */
        topPanel.setLayout( new FlowLayout() );
        midPanel.setLayout( new GridLayout(1,1) );
        bottomPanel.setLayout( new GridLayout(1,1) );
        thePanel.setLayout( new BorderLayout() );

        /* add the GameCanvas to the main panel */
        midPanel.add(GameCanvas);

        /* add the score and the retart button to the bottom panel */
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.add(Score);
        bottomPanel.add(restartButton);

        /* add the intro label to the top panel */
        topPanel.add(Intro);

        /* properly set panels */
        thePanel.add(topPanel, BorderLayout.NORTH);
        thePanel.add(midPanel, BorderLayout.CENTER);
        thePanel.add(bottomPanel, BorderLayout.SOUTH);
        frame.add(thePanel);
        frame.setFocusable(true);
        frame.requestFocusInWindow();
        //setting focus to main panel
        //creating 2 panels to place in frame
        frame.setVisible(true);
        frame.addKeyListener(this);
        //restartButton.addActionListener(this);

        // GameCanvas.visualBoard = this.visualBoard;

        /* initialize eveything to 0 */
        for( int i = 0; i<4; i++)
        {
            for (int j = 0; j<4; j++)
            {
                board[i][j] = 0;
            }
        }

        /* set the initial one to 4 and print the board */
        board[1][3] = 4;
        printBoard();

        /* handle the restart button */
        restartButton.addActionListener( new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    GameCanvas.lost = false;
                    for( int i = 0; i<4; i++)
                    {
                        for (int j = 0; j<4; j++)
                        {
                            board[i][j] = 0;

                        }
                    }
                    board[1][3] = 4;
                }
            }
        );

        //board[2][0] = 4;
    }

    /* get the score of the game */
    public void getScore()
    {
        System.out.println("Score: " + score); 
    }

    /* set the left key to true/false */
    public void setLeft(boolean b)
    {
        left = b;
    }

    /* is the gae lost */
    public boolean lost()
    {
        boolean lost = true;
        for( int i = 0; i<4; i++)
        {
            for (int j = 1; j<3; j++)
            {
                if(board[i][j] == board[i][j-1] || board[i][j]==board[i][j+1])
                {
                    lost = false;    
                }
            }
        }
        for( int i = 1; i<3; i++)
        {
            for (int j = 0; j<4; j++)
            {
                if(board[i][j] == board[i+1][j] || board[i][j]==board[i-1][j])
                {
                    lost = false;    
                }
            }
        }
        return lost;
    }

    /* add a new tile to the game board */
    public void addNewTile()
    {
        int num = 0;
        for( int i = 0; i<4; i++)
        {
            for (int j = 0; j<4; j++)
            {
                if( board[i][j] == 0)
                {
                    num++;  
                }
            }
        }
        if(num == 0)
        {
            System.out.println("Lost");
            if(lost())
            {
                System.out.println("Lost");

                GameCanvas.score = score;
                GameCanvas.lost = true;
            }
        }
        int numdone = getRand(num+1);
        for( int i = 0; i<4; i++)
        {
            for (int j = 0; j<4; j++)
            {
                if( board[i][j] == 0)
                {
                    numdone--; 
                    if(numdone == 0)
                    {
                        int temp = ((1+ getRand(2))*2);
                        board[i][j] = temp;
                        score += temp;
                        return;
                    }
                }
            }
        }

    }

    /* helper method to return a random number from 0 --> range  */
    public int getRand(int range)
    {
        return ((int)((Math.random()*range)));
    }

    /* if the right key is pressed rotate twice --> slide left --> rotate twice */
    public void moveRight()
    {
        shouldAddTile = false;
        rotate();
        rotate();
        slideArray();
        rotate();
        rotate();
        printBoard();
        if(shouldAddTile == true)
        {
            addNewTile();
            printBoard();
        }
    }

    /* if the left key is pressed, just use slide method */
    public void moveLeft()
    {
        shouldAddTile = false;
        slideArray();
        printBoard();
        if(shouldAddTile == true)
        {
            addNewTile();
            printBoard();
        }
    }

    /* slide array method --> slide all  keys to the left + checks for collisions */
    public void slideArray()
    {
        slideArrayRid();
        slideArraySmash();
        slideArrayRid();
        Score.setText("Score: " + score);
        int num = 0;
        for( int i = 0; i<4; i++)
        {
            for (int j = 0; j<4; j++)
            {
                if( board[i][j] == 0)
                {
                    num++;  
                }
            }
        }
        if(num == 0)
        {
            //System.out.println("Lost");
            if(lost())
            {
                //System.out.println("Lost");

                GameCanvas.score = score;
                GameCanvas.lost = true;
            }
        }
    }

    /* slide tiles first where there are emptry spaces next to */
    /* it in the direction of the slide */
    public void slideArrayRid()
    {
        boolean changed = true;
        while(changed == true)
        {
            changed = false;
            for(int row = 0; row<=3; row++)
            {
                for(int col = 1; col<=3; col++)
                {
                    int num1 = board[row][col];
                    int num2 = board[row][col-1];
                    if(num2==0 && num1!=0)
                    {
                        changed = true;
                        shouldAddTile = true;
                        board[row][col-1] = num1;
                        board[row][col] = 0;

                    }
                }
            }

        }

    }

    /* handle the case that two tiles in the slide direction are the same */
    public void slideArraySmash()
    {
        for(int row = 0; row<=3; row++)
        {
            for(int col = 1; col<=3; col++)
            {
                int num1 = board[row][col];
                int num2 = board[row][col-1];
                if(num1 == num2)
                {
                    board[row][col-1] = 2*num1;
                    score += 2*num1;
                    board[row][col] = 0;
                    shouldAddTile = true;

                } 
            }
        }
    }

    /* if up button is pressed -> rotate --> slide left --> rotate 3 times */
    public void moveUp()
    {

        shouldAddTile = false;
        rotate();
        slideArray();
        rotate();
        rotate();
        rotate();
        printBoard();
        if(shouldAddTile == true)
        {
            addNewTile();
            printBoard();
        }
    }

    /* if down button pressed --> rotate 3 times --> slide left --> rotate once */
    public void moveDown()
    {
        shouldAddTile = false;
        rotate();
        rotate();
        rotate();
        slideArray();
        rotate();
        printBoard();
        if(shouldAddTile == true)
        {
            addNewTile();
            printBoard();
        }
    }

    /* how to print the board */
    public void printBoard()
    {
        for( int i = 0; i<4; i++)
        {
            for (int j = 0; j<4; j++)
            {
                GameCanvas.visualBoard[i][j].setNum(board[i][j]);
                GameCanvas.repaint();
            }
        }  
    }

    public void rotate()
    {
        int[][]tempBoard = new int[4][4];
        int m = board.length;

        for( int row = 0; row< m; row++)
        {
            for(int col = 0; col< m; col++)
            {
                tempBoard[row][col] = board[col][m-row-1];
            }   
        }
        for( int row = 0; row< m; row++)
        {
            for(int col = 0; col< m; col++)
            {
                board[row][col] = tempBoard[row][col];
            }}
    }

    public void keyReleased(KeyEvent k) 

    {
        if(k.getKeyCode() == KeyEvent.VK_RIGHT)
            moveRight();
        if(k.getKeyCode() == KeyEvent.VK_LEFT)
            moveLeft();
        if(k.getKeyCode() == KeyEvent.VK_DOWN)
            moveDown();
        if(k.getKeyCode() == KeyEvent.VK_UP)
            moveUp();
    }

    public void keyTyped(KeyEvent k) 
    {}

    public void keyPressed(KeyEvent k) 
    {
        //when keys are pressed --> make booleans true in order to move

        //if(k.getKeyCode() == KeyEvent.VK_SPACE)
        //  canvas.add10Stars();
    }
}