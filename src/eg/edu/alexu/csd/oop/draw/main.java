//package eg.edu.alexu.csd.oop.draw;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//
//
//public class main {
//    public static void main(String args[]) {
//        JFrame frame=new JFrame();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
//        frame.setSize(2500,2000);
//        frame.setTitle("Paint");
//        JButton l =new JButton();
//        l.setSize(100,100);
//        frame.add(l);
//        Canvas canvse =new Canvas();
//        canvse.setPreferredSize(new Dimension(2000,1500));
//        canvse.setMaximumSize(new Dimension(2000,1500));
//        canvse.setMinimumSize(new Dimension(2000,1500));
//        canvse.addMouseListener(new MouseListener() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                Graphics g = canvse.getGraphics();
//
//                g.setColor(Color.red);
//
//                // get X and y position
//                int x, y;
//                x = e.getX();
//                y = e.getY();
//
//                // draw a Oval at the point
//                // where mouse is moved
//                g.drawArc(x,y,100,100,0,360);
//            }
//
//            @Override
//            public void mousePressed(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseEntered(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//
//            }
//        });
//        frame.add(canvse);
//    }
//}
//
