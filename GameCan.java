import java.awt.*;
import java.util.ArrayList;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.Font;
import java.awt.Color;

public class GameCan extends Canvas
{
  /* this is the board */
  NumberBox[][] visualBoard = new NumberBox[4][4];

  /* fonts */
  private static Font monoFont = new Font("Monospaced", Font.BOLD
    | Font.ITALIC, 36);
  private static Font lostFont = new Font("Monospaced", Font.BOLD
    | Font.ITALIC, 55);

  /* array of colors */
  Color[] colors = 
  {
    Color.BLACK, Color.RED,  Color.ORANGE, Color.YELLOW, Color.GREEN,Color.BLUE, Color.CYAN, Color.MAGENTA
  };

  /* is the game over */
  boolean lost = false;
  int score = 0;

  /* initialize the game canvas */
  public GameCan()
  {
    /* set the size of the canvas and create new boxes */
    setSize(700,700);
    visualBoard[0][0] = new NumberBox(50, 50);
    visualBoard[0][1] = new NumberBox(200, 50);
    visualBoard[0][2] = new NumberBox(350, 50);
    visualBoard[0][3] = new NumberBox(500, 50);
    visualBoard[1][0] = new NumberBox(50, 200);
    visualBoard[1][1] = new NumberBox(200, 200);
    visualBoard[1][2] = new NumberBox(350, 200);
    visualBoard[1][3] = new NumberBox(500, 200);
    visualBoard[2][0] = new NumberBox(50, 350);
    visualBoard[2][1] = new NumberBox(200, 350);
    visualBoard[2][2] = new NumberBox(350, 350);
    visualBoard[2][3] = new NumberBox(500, 350);
    visualBoard[3][0] = new NumberBox(50, 500);
    visualBoard[3][1] = new NumberBox(200, 500);
    visualBoard[3][2] = new NumberBox(350, 500);
    visualBoard[3][3] = new NumberBox(500, 500);
  }

  /* set the num of a certain box in the 2d array */
  public void changeNum(int row, int col, int num)
  {
    visualBoard[row][col].setNum(num);
  }
    
  /* paint the canvas */
  public void paint(Graphics g)
  {       
    /* draw the background */
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, 700, 750);

    /* what to draw if the game is lost */
    if(lost == true)
    {
      g.setFont(lostFont);
      g.setColor(Color.WHITE);
      g.drawString("Game Over", 230, 300);
      g.setFont(monoFont);
      g.drawString("Final Score:", 255, 370);
      g.drawString("" + score, 275, 420);
      return;
    }

    /*  otherwise this is what the game draws --> draw each Box */
    for (int row = 0; row <= 3; row++)
    {
      for (int col = 0; col <= 3; col++)
      {
        NumberBox temp = new NumberBox();
        temp = visualBoard[row][col];
        g.setColor(colors[temp.getColor()]);
        Rectangle r = temp.getRect();

        g.fillRect(r.x, r.y, r.width, r.height);
              
        g.setFont(monoFont);
        g.setColor(Color.BLACK);
        g.drawString(""+temp.getNum(), r.x + 40, r.y+87);
              
      }
            
    }  
  }
}

