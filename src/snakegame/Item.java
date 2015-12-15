package snakegame;


import java.awt.Graphics;
import java.awt.Image;

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

    Item(int i, int i0) {
    }
    public void draw(Graphics graphics){
        
        graphics.drawImage(getImage(),
        getCellData().getSystemCoordX(getX(), getY()),
        getCellData().getSystemCoordX(getX(), getY()),
        getCellData().getCellWidth(),
                getCellData().getCellHeight(), null);
                            
        
        
    }
    
    public Item(int x, int y, String type, Image image, CellDataProviderIntf cellData){
        
        this.x =x;
        this.y =y;
        this.type =type;
        this.image =image;
        this.cellData = cellData;


        
   
    }
    
     private int x,y;
    private String type;
    private Image image;
    private CellDataProviderIntf cellData;

    
     
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
