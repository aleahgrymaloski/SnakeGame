 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import grid.Grid;
import images.ResourceTools;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 *
 * @author Aleah
 */
public class NyanCat {

    public NyanCat(Direction direction, Grid grid, MoveValidatorIntf validator) {
        this.direction = direction;
        this.grid = grid;
        this.validator = validator;

        this.nyanCatRight = ResourceTools.loadImageFromResource("resource/nyan.cat.png");
        this.nyanCatLeft = ResourceTools.loadImageFromResource("resource/new_invert.png");
        this.rainbow = ResourceTools.loadImageFromResource("resource/rainbow.png");
        this.rainbowUp = ResourceTools.loadImageFromResource("resource/rainbow_up.png");
        this.rainbowDown = ResourceTools.loadImageFromResource("resource/rainbow_up.png");

        //create the snake body
        body = new ArrayList<>();
        body.add(new Point(5, 5));
        body.add(new Point(5, 4));
        body.add(new Point(5, 3));
        body.add(new Point(6, 3));
        body.add(new Point(7, 3));
        body.add(new Point(8, 3));
    }

    private Direction direction = Direction.LEFT;
    private ArrayList<Point> body;
    private Grid grid;
    private Color bodyColor = Color.MAGENTA;
    private final MoveValidatorIntf validator;
    private Image nyanCatRight, nyanCatLeft, rainbow, rainbowUp, rainbowDown;
    private int growthCounter;

    public void draw(Graphics graphics) {
        for (int i = 0; i < getBody().size(); i++) {
//            System.out.println("body location = " + body.get(i).toString());

            if (i == HEAD_POSITION) {
                graphics.drawImage(getNyanCat(), getGrid().getCellSystemCoordinate(getBody().get(i)).x,
                        getGrid().getCellSystemCoordinate(getBody().get(i)).y,
                        getGrid().getCellWidth(),
                        getGrid().getCellHeight(), null);

            } else {
                graphics.drawImage(rainbow, getGrid().getCellSystemCoordinate(getBody().get(i)).x,
                        getGrid().getCellSystemCoordinate(getBody().get(i)).y,
                        getGrid().getCellWidth(),
                        getGrid().getCellHeight(), null);

            }

//        
        }
    }

    private Image getNyanCat() {
        if (direction == Direction.RIGHT) {
            return nyanCatRight;
       
        } else {
           
            return nyanCatLeft;
            
        }

    }

    private Image getRainbowUp() {
        if (direction == Direction.UP) {
            return rainbowUp;
        
        } else {

            return rainbow;

        }
    }

    private Image getRainbowDown() {
        if (direction == Direction.DOWN) {
            return rainbowDown;

        } else {

            return rainbow;
        }
    }


    public void move() {
        //make a copy of the current head location
        Point newHead = new Point(getHead());

        if (getDirection() == Direction.LEFT) {
            newHead.x--;
        } else if (getDirection() == Direction.RIGHT) {
            newHead.x++;
        } else if (getDirection() == Direction.UP) {
            newHead.y--;
        } else if (getDirection() == Direction.DOWN) {
            newHead.y++;
        }

        //add new head
        body.add(HEAD_POSITION, validator.validateMove(newHead));

        //delete tail
        getBody().remove(getBody().size() - 1);
    }

    public Point getHead() {
        return getBody().get(HEAD_POSITION);

    }

    public void grow(int length) {
        setGrowthCounter(getGrowthCounter() + length);
    }
    private static final int HEAD_POSITION = 0;

    /**
     * @return the direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * @return the body
     */
    public ArrayList<Point> getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(ArrayList<Point> body) {
        this.body = body;
    }

    /**
     * @return the grid
     */
    public Grid getGrid() {
        return grid;
    }

    /**
     * @param grid the grid to set
     */
    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    /**
     * @return the bodyColor
     */
    public Color getBodyColor() {
        return bodyColor;
    }

    /**
     * @param bodyColor the bodyColor to set
     */
    public void setBodyColor(Color bodyColor) {
        this.bodyColor = bodyColor;
    }

    /**
     * @return the growthCounter
     */
    public int getGrowthCounter() {
        return growthCounter;
    }

    /**
     * @param growthCounter the growthCounter to set
     */
    public void setGrowthCounter(int growthCounter) {
        this.growthCounter = growthCounter;
    }

    public void addGrowthCounter(int growthCounter) {
        this.growthCounter += growthCounter;
    }
}
