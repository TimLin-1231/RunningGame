package src.userinterface;

import java.awt.Graphics;

import javax.imageio.ImageIO;
import javax.swing.JFrame;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;

public class GameWindow extends JFrame{

    private GameScreen gameScreen;

    public GameWindow(){
        super("Java game");
        setSize(800,400);  //視窗大小
        setLocation(400,200); //視窗初始位置
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameScreen = new GameScreen();
        add(gameScreen);
        addKeyListener(gameScreen);
    }

    public void startGame(){
        gameScreen.startGame();
    }

    public static void main(String args[]){
        GameWindow gw = new GameWindow();
        gw.setVisible(true);
        gw.startGame();
    }

}