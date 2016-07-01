import java.awt.*;
import java.io.*;

public class NumberBox
{
    /* variables for each box */
    int x,y, height, width;
    int num;
    int color = 0;  /* color is just on a 1,2,3,4.. scale --> assigned later */

    /* initialize a new box */
   public NumberBox()
   {
       height = 140;
       width = 140;
       num = 0;
    }
    
    /* initialize a new box at a certain position */
    public NumberBox(int xpos, int ypos)
    {
       height = 140;
       width = 140;
       num = 0;
       x = xpos;
       y = ypos;
    }
    
    /* clear the box's color */
    public void clearColor()
    {
        color = 0;
    }
    
    /* set the box's color to specific color */
    public void setColor(int i)
    {
        color = i;
    }
    
    /* get the number of the box (2,4,8,16..) */
    public int getNum()
    {
        return num;
    }
    
    /* set and return the color of the box -- convert number to color */
    public int getColor()
    {
        if(num == 0)
        {
            color = 0;
        }
        if(num == 2)
        {
            color = 1;
        }
        if(num == 4)
        {
            color = 2;
        }
        if(num == 8)
        {
            color = 3;
        }
        if(num == 16)
        {
            color = 4;
        }
        if(num == 32)
        {
            color = 5;
        }
        if(num == 64)
        {
            color = 6;
        }
       if(num == 128)
        {
            color = 7;
        }
        return color;
    }
    
    /* increment the color */
    public void nextColor()
    {
        color++;
    }
    
    /* manually set the number of the box */
    public void setNum(int n)
    {
       num = n;
    }

    /* return the rectangle of the box */
    public Rectangle getRect()
    {
        Rectangle r = new Rectangle (x, y, width, height);
        return r;
    }
}