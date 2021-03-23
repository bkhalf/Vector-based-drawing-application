package eg.edu.alexu.csd.oop.draw;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Point;
class canvas  implements MouseListener, MouseMotionListener {

    // create a canvas
    Canvas c;
    // constuctor
    canvas() {

        // create a empty canvas
        c = new Canvas() {
            public void paint(Graphics g) {
            }
        };
        // set background
        c.setBackground(Color.white);

        // add mouse listener
        c.addMouseListener(this);
        c.addMouseMotionListener(this);
    }

int x1,y1;
    boolean flag= false;
    // mouse listener  and mouse motion listener mehods
    public void mouseClicked(MouseEvent e)
    {
    Graphics g=c.getGraphics();
//        g.setColor(Color.black);
//
//        // get X and y position
        int x, y;
        x = e.getX();
       y = e.getY();
       flag=true;
x1=x;
y1=y;
            g.drawArc(x1,y1,100,100,0,360);
//        g.drawArc(x1,y1,100,100,0,360);



//        // draw a Oval at the point
//        // where mouse is moved
//        g.fillOval(x,y,5,5);
//        g.fillOval(x,y, 10, 10);
    }

    public void mouseMoved(MouseEvent e)
    {
    }

    public void mouseDragged(MouseEvent e)
    {

            Graphics g = c.getGraphics();

            g.setColor(Color.black);

            // get X and y position
            int x, y;
            x = e.getX();
            y = e.getY();
            // draw a Oval at the point where mouse is moved
//        g.fillOval(x, y, 10, 10);




//        g.fillOval(x,y,100,100);
    }

    public void mouseExited(MouseEvent e)
    {
    }

    public void mouseEntered(MouseEvent e)
    {
    }

    public void mouseReleased(MouseEvent e)
    {

    }

    public void mousePressed(MouseEvent e)
    {


    }
    // main class
}