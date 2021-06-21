package src.objectgame;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.awt.Rectangle;
import src.utl.Resource;

public class Coin extends Gift{

    public Color randomColor;
    public BufferedImage image;
    private int posX, posY;
    private Rectangle rect;
    private MainCharacter mainCharacter;
    //private EnemiesManager enemiesManager;
    private boolean isCoinGot = false;
    private boolean isHpPlus = false;

    public Coin(MainCharacter mainCharacter){
        this.mainCharacter = mainCharacter;
        image = Resource.getResourceImage("data/coin.png");
        posX = 200;  //Cion位置
        posY = 155;
        rect = new Rectangle();
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
        g.drawImage(image, posX, posY, null);
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
    public boolean isCoinGot() {
        return isCoinGot;
    }

    @Override
    public void SetIsCoinGot(boolean isCoinGot) {
        this.isCoinGot = isCoinGot;

    }

    @Override
    public boolean isHpPlus() {
        return isHpPlus;
    }

    @Override
    public void SetIsHpPlus(boolean isHpPlus) {
        this.isHpPlus = isHpPlus;

    }

}
