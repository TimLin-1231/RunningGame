package src.userinterface;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;

import src.objectgame.Clouds;
import src.objectgame.EnemiesManager;
import src.objectgame.GiftManager;
import src.objectgame.Land;
import src.objectgame.MainCharacter;
import src.objectgame.musicStuff;


public class GameScreen extends JPanel implements Runnable, KeyListener{

    public static final int Game_First_State = 0;
    public static final int Game_Play_State = 1;
    public static final int Game_Over_State = 2;
    public static final float GRAVITY = 0.1f;
    public static final float GROUNDY = 335;
    

    private MainCharacter maincharacter;
    private Thread thread;
    private Land land;
    private Clouds clouds;
    private EnemiesManager enemiesManager;
    private GiftManager giftManager;
    private musicStuff musicObject;
    private int score = 0;
    public int coin = 0;
    public int HP = 5;
    URL url;
    
    private int gameState = Game_First_State;

    private BufferedImage replayButtonImage;
    private BufferedImage imageGameOverText;
    private BufferedImage gameScreenImage;
    private AudioClip scoreUpSound;
    private AudioClip CoinSound;

    public GameScreen(){
        thread = new Thread(this);
        maincharacter = new MainCharacter();
        maincharacter.setX(50);
        maincharacter.setY(280);
        land = new Land(this);
        clouds = new Clouds();
        musicObject = new musicStuff();
        enemiesManager = new EnemiesManager(maincharacter, this);
        giftManager = new GiftManager(maincharacter, this);
        replayButtonImage = src.utl.Resource.getResourceImage("data/replay_button.png");
        imageGameOverText = src.utl.Resource.getResourceImage("data/gameover_text.png");
        gameScreenImage = src.utl.Resource.getResourceImage("data/land3.png");
        
        try{
            scoreUpSound = Applet.newAudioClip(new URL("file", "", "data/scoreup.wav"));
            CoinSound = Applet.newAudioClip(new URL("file", "", "data/Mario-coin-sound.wav"));
            String filepath = "data/kirby.wav";
            musicObject.playMusic(filepath);
        }catch(MalformedURLException ex) {
            Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void startGame(){
        thread.start();
    }

    @Override
    public void run(){
        while(true){
            try{
                update();
                repaint();
                if(score <= 100){
                    Thread.sleep(7);//調整速度
                }else if(score > 100 && score <=200){
                    Thread.sleep(5);
                }else if(score > 200 ){
                    Thread.sleep(3);
                }
                
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            
        }
    }

    public void update() {
        switch(gameState) {
            case Game_Play_State:
                maincharacter.update();
                land.update();
                clouds.update();
                enemiesManager.update();
                giftManager.update();
                if( !maincharacter.getAlive()) {
                    maincharacter.dead(true);
                    gameState = Game_Over_State;
                }
                break;

        }
        
    }

    public void plusScore(int score) {
        this.score += score ;
        scoreUpSound.play();
    }

    public void plusCoin(int coin) {
        this.coin += coin ;
        CoinSound.play();
    }

    public void MinusHP(int HP) {
        this.HP -= HP;
    }


    @Override
    public void paint(Graphics g){
        g.setColor(Color.decode("#f7f7f7"));
        g.fillRect(0, 0, getWidth(), getHeight());
        switch(gameState) {
            case Game_First_State:
                g.drawImage(gameScreenImage, 0, 0, null);
                g.setColor(Color.black);
                Font stringFont1 = new Font(null, Font.PLAIN,38);
                g.setFont(stringFont1);
                g.drawString("RunningGame", 270, 180);
                Font stringFont4 = new Font(null, Font.PLAIN,28);
                g.setFont(stringFont4);
                g.drawString("start", 350, 230);
                
                //maincharacter.draw(g);
                break;
            case Game_Play_State:
                land.draw(g);
                clouds.draw(g);
                maincharacter.draw(g);
                giftManager.draw(g);
                enemiesManager.draw(g);
                g.setColor(Color.black);
                Font stringFont2 = new Font(null, Font.PLAIN,18);
                g.setFont(stringFont2);
                g.drawString("金幣:" + String.valueOf(coin), 600, 20);
                g.drawString("分數:" + String.valueOf(score), 700, 20);
                g.drawString("HP:" + String.valueOf(HP), 500, 20);
                break;
            case Game_Over_State:
                land.draw(g);
                clouds.draw(g);
                maincharacter.draw(g);
                enemiesManager.draw(g);
                giftManager.draw(g);
                g.drawImage(imageGameOverText, 300, 167, null);
                g.drawImage(replayButtonImage, 383, 187, null);
                g.setColor(Color.black);
                Font stringFont3 = new Font(null, Font.PLAIN,28);
                g.setFont(stringFont3);
                g.drawString("金幣:" + String.valueOf(coin)+"     分數:" + String.valueOf(score), 293, 237);
                //g.drawString("分數:" + String.valueOf(score), 415, 237);
                musicObject.stop();
                break;
        }
        
    }

    private void resetGame() { 
        this.score = 0;
        this.coin = 0;
        this.HP = 5;
        enemiesManager.reset();
        giftManager.reset();
        maincharacter.setAlive(true);
        maincharacter.reset();
        String filepath = "data/kirby.wav";
        musicObject.playMusic(filepath);

    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            maincharacter.down(true);
        }else if (e.getKeyCode() == KeyEvent.VK_S) {
            maincharacter.down(true);
        }

        if (e.getKeyCode() == KeyEvent.VK_A) {
            maincharacter.blue(true);
        }else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            maincharacter.blue(true);
        }else if(e.getKeyCode() == KeyEvent.VK_D) {
            maincharacter.red(true);
        }else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            maincharacter.red(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                if(gameState == Game_First_State) {
                    gameState = Game_Play_State;
                }else if(gameState == Game_Play_State) {
                    maincharacter.jump();
                }else if(gameState == Game_Over_State) {
                    resetGame();
                    gameState = Game_Play_State;  
                }
                break;
            case KeyEvent.VK_UP:
                if(gameState == Game_First_State) {
                    gameState = Game_Play_State;
                }else if(gameState == Game_Play_State) {
                    maincharacter.jump();
                }else if(gameState == Game_Over_State) {
                    resetGame();
                    gameState = Game_Play_State;  
                }
                break;
            case KeyEvent.VK_W:
                if(gameState == Game_First_State) {
                    gameState = Game_Play_State;
                }else if(gameState == Game_Play_State) {
                    maincharacter.jump();
                }else if(gameState == Game_Over_State) {
                    resetGame();
                    gameState = Game_Play_State;  
                }
                break;

            case KeyEvent.VK_DOWN:
                maincharacter.down(false);
                break;
            case KeyEvent.VK_S:
                maincharacter.down(false);
                break;

            case KeyEvent.VK_A:
                maincharacter.blue(false);
                break;
            case KeyEvent.VK_D:
                maincharacter.red(false);
                break;
        }
    }
}