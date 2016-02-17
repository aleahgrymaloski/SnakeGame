/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import audio.AudioPlayer;
import audio.Playlist;
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
import java.awt.Font;

/**
 *
 * @author Aleah
 */
class Sky extends Environment implements MoveValidatorIntf, CellDataProviderIntf {

    private Grid grid;
    public NyanCat cat;
    Image background;
    private ArrayList<Item> items;
    private int score;
    Image toaster;
    private String trackNameGameTimer;
    
    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
        
        if (score <= 0) {
            AudioPlayer.play("/resource/cat_scream.wav");
        }
    }

    public void addScore(int score) {
        setScore(this.score + score);
        if (score >= 0)
        AudioPlayer.play("/resource/cat_meow.wav");
    }

    

    public Sky() {

        grid = new Grid(17, 16, 52, 36, new Point(10, 10), new Color(2, 49, 178, 1));
        cat = new NyanCat(Direction.LEFT, grid, this);

        BufferedImage temp = (BufferedImage) ResourceTools.loadImageFromResource("resource/nyan_cat_background.jpg");

        this.setBackground(temp.getScaledInstance(1000, 700, Image.SCALE_SMOOTH));
        
        

        
        items = new ArrayList<>();

        items.add(new Item(getRandom(grid.getColumns()), getRandom(grid.getColumns()), Item.ITEM_TYPE_BROCCOLI,
                ResourceTools.loadImageFromResource("resource/broccoli_pixel.png"), this));
        items.add(new Item(getRandom(grid.getColumns()), getRandom(grid.getColumns()), Item.ITEM_TYPE_CANDY,
                ResourceTools.loadImageFromResource("resource/pink_candy.gif"), this));
        items.add(new Item(getRandom(grid.getColumns()), getRandom(grid.getColumns()), Item.ITEM_TYPE_CANDY,
                ResourceTools.loadImageFromResource("resource/pink_candy.gif"), this));
        items.add(new Item(getRandom(grid.getColumns()), getRandom(grid.getColumns()), Item.ITEM_TYPE_BROCCOLI,
                ResourceTools.loadImageFromResource("resource/broccoli_pixel.png"), this));

//        items.add(new Item(getRandom(grid.getColumns()), getRandom(grid.getColumns()), Item.ITEM_TYPE_CANDY,
//                ResourceTools.loadImageFromResource("resource/pink_candy.gif"), this));
        items.add(new Item(getRandom(grid.getColumns()), getRandom(grid.getColumns()), Item.ITEM_TYPE_CANDY,
                ResourceTools.loadImageFromResource("resource/pink_candy.gif"), this));
        items.add(new Item(getRandom(grid.getColumns()), getRandom(grid.getColumns()), Item.ITEM_TYPE_CANDY,
                ResourceTools.loadImageFromResource("resource/pink_candy.gif"), this));
        items.add(new Item(getRandom(grid.getColumns()), getRandom(grid.getColumns()), Item.ITEM_TYPE_BROCCOLI,
                ResourceTools.loadImageFromResource("resource/broccoli_pixel.png"), this));

        items.add(new Item(getRandom(grid.getColumns()), getRandom(grid.getColumns()), Item.ITEM_TYPE_BROCCOLI,
                ResourceTools.loadImageFromResource("resource/broccoli_pixel.png"), this));

        items.add(new Item(getRandom(grid.getColumns()), getRandom(grid.getColumns()), Item.ITEM_TYPE_ICECREAM,
                ResourceTools.loadImageFromResource("resource/ice_cream.gif"), this));
        items.add(new Item(getRandom(grid.getColumns()), getRandom(grid.getColumns()), Item.ITEM_TYPE_ICECREAM,
                ResourceTools.loadImageFromResource("resource/ice_cream.gif"), this));
        items.add(new Item(getRandom(grid.getColumns()), getRandom(grid.getColumns()), Item.ITEM_TYPE_ICECREAM,
                ResourceTools.loadImageFromResource("resource/ice_cream.gif"), this));
        items.add(new Item(getRandom(grid.getColumns()), getRandom(grid.getColumns()), Item.ITEM_TYPE_TOASTER,
                ResourceTools.loadImageFromResource("resource/super_toaster.png"), this));
        items.add(new Item(getRandom(grid.getColumns()), getRandom(grid.getColumns()), Item.ITEM_TYPE_TOASTER,
                ResourceTools.loadImageFromResource("resource/super_toaster.png"), this));
        items.add(new Item(getRandom(grid.getColumns()), getRandom(grid.getColumns()), Item.ITEM_TYPE_SUPERCANDY,
                ResourceTools.loadImageFromResource("resource/super_candy.png"), this));

        setUpSound();

        soundManager.play(NYAN_SONG, -1);
        
    }

    //accept an int, returns a random number betwee zero and int
    private int getRandom(int maximum) {
        return (int) (Math.random() * maximum);
    }

    SoundManager soundManager;
    public static final String NYAN_SONG = "nyan";

    private void setUpSound() {
        // set up a list of tracks in a playlist
        ArrayList<Track> tracks = new ArrayList<>();
        tracks.add(new Track(NYAN_SONG, Source.RESOURCE, "/resource/nyan_song.wav"));

        Playlist playlist = new Playlist(tracks);
        // pass the playlist to a sound manager
        soundManager = new SoundManager(playlist);
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
        if ((cat != null) && (score >= 0)) {
            if (moveDelay >= moveDelayLimit) {
                cat.move();
                
                moveDelay = 0;
                checkIntersections();
            } else {
                moveDelay++;
            }
        }
    }

    private void checkIntersections() {
        if (items != null) {
            for (Item item : items) {
                if (item.getLocation().equals(cat.getHead())) {
                    //move the item, no matter what type it is
                    item.setX(getRandom(grid.getColumns()));
                    item.setY(getRandom(grid.getRows()));

                    //do differents stuff for different items
                    if (item.getType().equals(Item.ITEM_TYPE_CANDY)) {
                        
                        addScore(10);

                    } else if (item.getType().equals(Item.ITEM_TYPE_ICECREAM)) {
                       AudioPlayer.play("/resource/cat_meow.wav");

                        addScore(50);
                    
                        
                    } else if (item.getType().equals(Item.ITEM_TYPE_BROCCOLI)) {
                        AudioPlayer.play("/resource/cat_scream.wav");

                        addScore(-100);
                    }
                 
                    if (item.getType().equals(Item.ITEM_TYPE_TOASTER)){
                        
                        AudioPlayer.play("/resource/cat_scream.wav");
                        
                        addScore(-1000);
                    }
                    
                      if (item.getType().equals(Item.ITEM_TYPE_SUPERCANDY)){
                        
                        AudioPlayer.play("/resource/cat_scream.wav");
                        
                        addScore(-1000);
                    }
                }
            }
        }
    }

    @Override
    public void keyPressedHandler(KeyEvent e) {

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
        } else if (e.getKeyCode() == KeyEvent.VK_R) {
            setScore(0);
        }
    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {
    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {

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
                
              if (toaster != null){
                  graphics.drawImage(toaster, 10, 10, 100, 100, this);
             
              }
              
            }

        }
        graphics.setColor(Color.white);
        graphics.setFont(new Font("Typewriter", Font.BOLD, 35));
        graphics.drawString("Score: " + score, 10, 30);
        
        if (score < 0) {
            graphics.drawString("Game Over", 300, 300);  
            graphics.drawString("Press R to restart!", 300, 350);  
        }
        
    }

    public void sky(Graphics graphics) {

    }

//<editor-fold defaultstate="collapsed" desc="MoveValidator">
    @Override
    public Point validateMove(Point proposedLocation) {

        //check if off grid
        if (proposedLocation.x < 0) {
            proposedLocation.x = grid.getColumns() - 1;
        } else if (proposedLocation.x > grid.getColumns() - 1) {
            proposedLocation.x = 0;
        }
        if (proposedLocation.y < 0) {
            proposedLocation.y = grid.getRows() - 1;
        } else if (proposedLocation.y > grid.getColumns() - 1) {
            proposedLocation.y = 0;
        }

        return proposedLocation;
    }

//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="CellDataProviderIntf">
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
    public int getSystemCoordY(int x, int y) {
        return grid.getCellSystemCoordinate(x, y).y;
    }
//</editor-fold>

}
