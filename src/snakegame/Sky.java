/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import audio.AudioPlayer;
import environment.Environment;
import grid.Grid;
import images.ResourceTools;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import audio.SoundManager;
import audio.Source;
import audio.Track;

/**
 *
 * @author Aleah
 */
class Sky extends Environment implements MoveValidatorIntf, CellDataProviderIntf {

    private Grid grid;
    public NyanCat cat;
    Image background;
    private ArrayList<Item> items;
    private SoundManager soundManager;

    private String trackNameGameTimer;

    public Sky() {

        grid = new Grid(25, 17, 52, 36, new Point(10, 10), new Color (2, 49, 178));
        cat = new NyanCat(Direction.LEFT, grid, this);

        BufferedImage temp = (BufferedImage) ResourceTools.loadImageFromResource("resource/nyan_cat_background.jpg");

        this.setBackground(temp.getScaledInstance(2000, 1200, Image.SCALE_SMOOTH));

        items = new ArrayList<>();
        items.add(new Item(10, 12, "POWER_UP",
                ResourceTools.loadImageFromResource("resource/candycat_new.png"), this));
        items.add(new Item(5, 8, "POWER_UP",
                ResourceTools.loadImageFromResource("resource/ice_cream.gif"), this));
        items.add(new Item(2, 2, "POWER_UP",
                ResourceTools.loadImageFromResource("resource/broccoli_pixel.png"), this));
                
        items.add(new Item(9, 9, "POWER_UP",
                ResourceTools.loadImageFromResource("resource/pink_candy.gif"), this));
        items.add(new Item(1, 6, "POWER_UP",
                ResourceTools.loadImageFromResource("resource/peppermint.png"), this));
        items.add(new Item(6, 7, "POWER_UP",
                ResourceTools.loadImageFromResource("resource/pink_candy.gif"), this));
        items.add(new Item(12, 8, "POWER_UP",
                ResourceTools.loadImageFromResource("resource/pink_candy.gif"), this));
        items.add(new Item(4, 9, "POWER_UP",
                ResourceTools.loadImageFromResource("resource/broccoli_pixel.png"), this));
               
        items.add(new Item(2, 10, "POWER_UP",
                ResourceTools.loadImageFromResource("resource/broccoli_pixel.png"), this));
               
        items.add(new Item(6, 8, "POWER_UP",
                ResourceTools.loadImageFromResource("resource/ice_cream.gif"), this));
        

    }

    @Override
    public void initializeEnvironment() {

        ArrayList<Track> tracks = new ArrayList<>();

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
            cat.setDirection(Direction.DOWN);
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {

            AudioPlayer.play("/resource/cat_meow.wav");
        } else if (e.getKeyCode() == KeyEvent.VK_SHIFT) {

            AudioPlayer.play("/resource/cat_scream.wav");

//        } else if (e.getKeyCode() == KeyEvent.VK_1) {
//           this.limit = LIMIT_SLOW;
//        
//        } else if (e.getKeyCode() == KeyEvent.VK_2) {
//            this.limit = LIMIT_MEDIUM;
//       
//         } else if (e.getKeyCode() == KeyEvent.VK_3) {
//            this.limit = LIMIT_FAST;
//       
//         } else if (e.getKeyCode() == KeyEvent.VK_4) {
//            this.limit = LIMIT_CRAZY;
//        }
//    
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

            if (items != null) {
                for (int i = 0; i < items.size(); i++) {
                    items.get(i).draw(graphics);
                }
            }

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

            if (proposedLocation.x < 0) {
                proposedLocation.x++;
                proposedLocation.y--;
                cat.setDirection(Direction.RIGHT);
            }

            if (proposedLocation.x >= grid.getColumns()) {
                proposedLocation.x++;
                proposedLocation.y--;
                cat.setDirection(Direction.LEFT);

            }
            if (proposedLocation.y < 0) 
           
            if (proposedLocation.y < 0) {
                proposedLocation.y++;
                proposedLocation.x--;
                cat.setDirection(Direction.RIGHT);
            }

            if (proposedLocation.y >= grid.getColumns()) {
                proposedLocation.y++;
                proposedLocation.x--;
                cat.setDirection(Direction.LEFT);

            }

            AudioPlayer.play("/resource/cat_scream.wav");
            

        }
        return proposedLocation;
    }

//</editor-fold>
    @Override
    public int getCellWidth() {
        return grid.getCellWidth();
    }

    @Override
    public int getCellHeight() {
        return grid.getCellHeight();
    }

    @Override
    public int getSystemCoordX(int x, int y) {
        return grid.getCellSystemCoordinate(x, y).x;

    }

    @Override
    public int getSystemCoorY(int x, int y) {
        return grid.getCellSystemCoordinate(x, y).y;
    }
}
