/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import images.Animator;
import images.ImageManager;  
import images.ResourceTools;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Aleah
 */
public class NyanAnimator {

    private Animator animator;
    
    
    public static final String BKGND_MAIN_01 = "BKNGD_MAIN_01";
    public static final String BKGND_MAIN_02 = "BKNGD_MAIN_02";
    public static final String BKGND_MAIN_03 = "BKNGD_MAIN_03";
    public static final String BKGND_MAIN_04 = "BKNGD_MAIN_04";


    public static final String BKGND_GAME_OVER_01 = "BKGND_GAME_OVER_01";
    public static final String BKGND_GAME_OVER_02 = "BKGND_GAME_OVER_02";
    public static final String BKGND_GAME_OVER_03 = "BKGND_GAME_OVER_03";
    public static final String BKGND_GAME_OVER_04 = "BKGND_GAME_OVER_04";
    public static final String BKGND_GAME_OVER_05 = "BKGND_GAME_OVER_05";
    public static final String BKGND_GAME_OVER_06 = "BKGND_GAME_OVER_06";
    public static final String BKGND_GAME_OVER_07 = "BKGND_GAME_OVER_07";
    public static final String BKGND_GAME_OVER_08 = "BKGND_GAME_OVER_08";
    
    public static ArrayList<String> BKGND_MAIN;
    public static ArrayList<String> BKGND_GAME_OVER;
    
    {
        BKGND_MAIN = new ArrayList<>();
        BKGND_MAIN.add(BKGND_MAIN_01);
        BKGND_MAIN.add(BKGND_MAIN_02);
        BKGND_MAIN.add(BKGND_MAIN_03);
        BKGND_MAIN.add(BKGND_MAIN_04);
        
        BKGND_GAME_OVER = new ArrayList<>();
        BKGND_GAME_OVER.add(BKGND_GAME_OVER_01);
        BKGND_GAME_OVER.add(BKGND_GAME_OVER_02);
        BKGND_GAME_OVER.add(BKGND_GAME_OVER_03);
        BKGND_GAME_OVER.add(BKGND_GAME_OVER_04);
        BKGND_GAME_OVER.add(BKGND_GAME_OVER_05);
        BKGND_GAME_OVER.add(BKGND_GAME_OVER_06);
        BKGND_GAME_OVER.add(BKGND_GAME_OVER_07);
        BKGND_GAME_OVER.add(BKGND_GAME_OVER_08);
        
        HashMap<String, Image> imageHM = new HashMap<>();
        imageHM.put(BKGND_MAIN_01, ResourceTools.loadImageFromResource("resource/background1.tiff"));
        imageHM.put(BKGND_MAIN_01, ResourceTools.loadImageFromResource("resource/background2.tiff"));
        imageHM.put(BKGND_MAIN_01, ResourceTools.loadImageFromResource("resource/background3.tiff"));
        imageHM.put(BKGND_MAIN_01, ResourceTools.loadImageFromResource("resource/background4.tiff"));


        imageHM.put(BKGND_GAME_OVER_01, ResourceTools.loadImageFromResource("resource/G.png"));
        imageHM.put(BKGND_GAME_OVER_02, ResourceTools.loadImageFromResource("resource/GA.png"));
        imageHM.put(BKGND_GAME_OVER_03, ResourceTools.loadImageFromResource("resource/GAM.png"));
        imageHM.put(BKGND_GAME_OVER_04, ResourceTools.loadImageFromResource("resource/GAME.png"));
        imageHM.put(BKGND_GAME_OVER_05, ResourceTools.loadImageFromResource("resource/GAME O.png"));
        imageHM.put(BKGND_GAME_OVER_06, ResourceTools.loadImageFromResource("resource/GAME OV.png"));
        imageHM.put(BKGND_GAME_OVER_07, ResourceTools.loadImageFromResource("resource/GAME OVE.png"));
        imageHM.put(BKGND_GAME_OVER_08, ResourceTools.loadImageFromResource("resource/GAME OVER.png"));
        
        ImageManager imageManager = new ImageManager(imageHM);
        this.animator = new Animator(imageManager, BKGND_MAIN, 150);
    }

    
    public void setAnimationImageNames(ArrayList<String> imageNames){
        animator.setImageNames(imageNames);
    }
    
    public Image getCurrentImage(){
        return animator.getCurrentImage();
    }
}
