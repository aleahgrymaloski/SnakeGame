package snakegame;

import images.ResourceTools;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Aleah
 */
public class Item {

    public void draw(Graphics graphics) {
        graphics.drawImage(getImage(),
                getCellData().getSystemCoordX(getX(), getY()),
                getCellData().getSystemCoordY(getX(), getY()),
                getCellData().getCellWidth(),
                getCellData().getCellHeight(), null);
    }

    public Item(int x, int y, String type, Image image, CellDataProviderIntf cellData) {

        this.x = x;
        this.y = y;
        this.type = type;
        this.image = image;
        this.cellData = cellData;

        if (type.equals(ITEM_TYPE_BROCCOLI)) {
            image = ResourceTools.loadImageFromResource("resource/broccoli_pixel.png");
        }

    }

    public static final String ITEM_TYPE_BROCCOLI = "BROCCOLI";
    public static final String ITEM_TYPE_CANDY = "CANDY";
    public static final String ITEM_TYPE_CUPCAKE = "CUPCAKE";
    public static final String ITEM_TYPE_ICECREAM = "ICECREAM";

    private int x, y;
    private String type;
    private Image image;
    private CellDataProviderIntf cellData;
    private MoveValidatorIntf moveValidator;
    private boolean alive;

    public Point getLocation(){
        return new Point(x, y);
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public CellDataProviderIntf getCellData() {
        return cellData;
    }

    public void setCellData(CellDataProviderIntf cellData) {
        this.cellData = cellData;
    }
}
