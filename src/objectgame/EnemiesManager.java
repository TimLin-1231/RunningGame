package src.objectgame;

import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import src.userinterface.GameScreen;
import src.utl.Resource;
import java.awt.image.BufferedImage;


public class EnemiesManager {
    private List<Enemy> enemies;
    private Random random;

    private BufferedImage imageCactus1,imageCactus2;
    private MainCharacter mainCharacter;
    private GameScreen gameScreen;
    private Cactus cactus;


    public EnemiesManager(MainCharacter mainCharacter, GameScreen gameScreen) {

        this.gameScreen = gameScreen;
        this.mainCharacter = mainCharacter;
        enemies = new ArrayList<Enemy>();
        imageCactus1 = Resource.getResourceImage("data/cactus1.png");
        imageCactus2 = Resource.getResourceImage("data/cactus2.png");
        random = new Random();
        
        enemies.add(getRandomCactus()); 
    }

    public void update() {
        for(Enemy e : enemies) {
            e.update();
            if(e.isOver() && !e.isScoreGot() && e.isSameColor() && !e.isHpMinus()) {  //已經過了 且 還沒得到分數 且isSameColor
                gameScreen.plusScore(20);
                e.SetIsScoreGot(true);
            }
            if(e.getBound().intersects(mainCharacter.getBound()) && !e.isHpMinus()){  //角色碰到障礙物
                gameScreen.MinusHP(1);
                System.out.println(gameScreen.HP);
                mainCharacter.hurt(true);
                e.SetIsHpMinus(true);
                if(gameScreen.HP <= 0){
                    mainCharacter.setAlive(false);
                }
                
            }


            if(!e.isSameColor()  && !e.isHpMinus() && mainCharacter.y + 50 < gameScreen.GROUNDY - 90 && mainCharacter.state == mainCharacter.JUMPING && e.isCloseEnough()){ //要在同時 jump && !e.isSameColor() 
                gameScreen.MinusHP(1);
                mainCharacter.hurt(true);
                e.SetIsHpMinus(true);
                if(gameScreen.HP <= 0){
                    mainCharacter.setAlive(false);
                }
            }
        }
        Enemy firstEnemy = enemies.get(0);
        if(firstEnemy.isOutOfScreen()) {
            enemies.remove(firstEnemy);
            enemies.add(getRandomCactus());
        }


    }

    public void draw(Graphics g) {
        for(Enemy e : enemies) {
            e.draw(g);
        }
        
    }

    public void reset() {
        enemies.clear();
        enemies.add(getRandomCactus());

    }



    private Cactus getRandomCactus() {
        Cactus cactus;
        cactus = new Cactus(mainCharacter);
        cactus.setX(800);
        if(random.nextBoolean()) {
            cactus.setImage(imageCactus1);
        }else {
            cactus.setImage(imageCactus2);
        }
        return cactus;
    }

}
