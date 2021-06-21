package src.objectgame;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.awt.Rectangle;
import src.utl.Resource;

public class Cactus extends Enemy{

    public Color randomColor;
    public BufferedImage image;
    public int posX, posY;
    private Rectangle rect;
    private MainCharacter mainCharacter;
    private EnemiesManager enemiesManager;
    private boolean isScoreGot = false;
    private boolean isHpMinus = false;
    //private boolean isSameColor = true;
    private int colorNumber;
    
    public Cactus( MainCharacter mainCharacter){
        this.mainCharacter = mainCharacter;
        image = Resource.getResourceImage("data/cactus1.png");
        posX = 300;  //cactus位置
        posY = 275;
        rect = new Rectangle();
        Color[] colors = new Color[] {Color.RED, Color.BLUE};
        randomColor = colors[(int)(Math.random()*2)];

       
    }

    public void update(){
        posX -= 2;
        rect.x = posX;
        rect.y = posY;
        rect.width = image.getWidth();
        rect.height = image.getHeight();
        
    }

    @Override
    public Rectangle getBound() {
        return rect;
    }

    @Override
    public void draw(Graphics g){
        g.setColor(randomColor);
         if(randomColor.equals(Color.BLUE)) {  //將顏色轉成int
            colorNumber = 1; //blue
            //System.out.println("colorNumber:"+colorNumber);
        }else if(randomColor.equals(Color.RED)){  
            colorNumber = 2; //red
            //System.out.println("colorNumber:"+colorNumber);
        }
        g.drawImage(image, posX, posY, null);
        g.drawRect(posX, posY, image.getWidth() , image.getHeight() );
        //System.out.println("posX:"+posX);
    }



    public void setX(int x) {
        posX = x;
    }

    public void setY(int y) {
        posY = y;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    @Override
    public boolean isOutOfScreen() {
        return(posX + image.getWidth() < 0);
    }

    @Override
    public boolean isCloseEnough() {
        return(posX - mainCharacter.x < 60);
    }

    @Override
    public boolean isOver() {
        return (mainCharacter.getX() > posX);
    }

    @Override
    public boolean isSameColor() {
     return mainCharacter.colorSwitch == colorNumber || mainCharacter.colorSwitch == 0;
    }


    @Override
    public boolean isScoreGot() {
        return isScoreGot;
    }

    @Override
    public void SetIsScoreGot(boolean isScoreGot) {
        this.isScoreGot = isScoreGot;
    }

    @Override
    public boolean isHpMinus() {
        return isHpMinus;
    }

    @Override
    public void SetIsHpMinus(boolean isHpMinus) {
        this.isHpMinus = isHpMinus;
    }



}
