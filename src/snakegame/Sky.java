/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import environment.Environment;
import grid.Grid;
import images.ResourceTools;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author Aleah
 */
class Sky extends Environment implements MoveValidatorIntf {

    private Grid grid;
    private NeonCat cat;

    public Sky() {
        grid = new Grid(25, 20, 20, 20, new Point(10, 50), Color.pink);
        cat = new NeonCat(Direction.LEFT, grid, this);

//         this.setBackground("sky background.png");
//        Image NyanCat = ResourceTools.loadImageFromResource("tumblr_mjphnqLpNyls5jjtzol_400.gif");
//        cat = ResourceTools.loadImageFromResource("sky background.png");
    }

    @Override
    public void initializeEnvironment() {
    }

    int moveDelay = 0;
    int moveDelayLimit = 4;

    int counter;

    @Override
    public void timerTaskHandler() {
//        System.out.println("Hey dude..." + ++counter);
        if (cat != null) {
            if (moveDelay >= moveDelayLimit) {
                cat.move();
                moveDelay = 0;
            } else {
                moveDelay++;

            }
        }
    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
//        System.out.println("Key Event" + e.getKeyChar());
//        System.out.println("Key Event" + e.getKeyCode());

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            cat.setDirection(Direction.LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            cat.setDirection(Direction.RIGHT);
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            cat.setDirection(Direction.UP);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
//            System.out.println("Key Event" + e.getKeyChar());
//            System.out.println("Key Event" + e.getKeyCode());
//            System.out.println("DOWN!!!");
            cat.setDirection(Direction.DOWN);
        }
    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {
//        if (e.getKeyCode()== KeyEvent.VK_W){
//            System.out.println("Go Left!");
//        } else if (e.getKeyCode()== KeyEvent.VK_A) {
//            System.out.println("Go Right!");
//        } else if (e.getKeyCode()== KeyEvent.VK_S) {
//            System.out.println("Go UP!");
//    } else if (e.getKeyCode()== KeyEvent.VK_D) {
//            System.out.println("Go Down!");
//        }
    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {
        System.out.println("mouse clicked at " + e.getPoint());
        System.out.println("mouse clicked at " + grid.getCellLocationFromSystemCoordinate(e.getPoint()));

    }

    @Override
    public void paintEnvironment(Graphics graphics) {
        if (grid != null) {
            grid.paintComponent(graphics);
        }
        if (cat != null) {
            cat.draw(graphics);

        }

    }

    public void sky(Graphics graphics) {
//        Image NyanCat;
//         if (cat != null) {
//            graphics.drawImage(cat, 400, 200, 125, 125, this);
//        }

    }

    //<editor-fold defaultstate="collapsed" desc="MoveValidator">
    @Override
    public Point validateMove(Point proposedLocation) {
        if (proposedLocation.x < 0) {
            
            if (proposedLocation.y > grid.getRows() /2) {
                proposedLocation.x++;
                proposedLocation.y--;

                
            }

            System.out.println("OUT OF BOUNDS!!!");

        }
        return proposedLocation;
    }

//</editor-fold>
}
