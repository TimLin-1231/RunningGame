package src.objectgame;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import src.utl.Animation;
import src.utl.Resource;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import static src.userinterface.GameScreen.GRAVITY;
import static src.userinterface.GameScreen.GROUNDY;
import java.net.MalformedURLException;
import java.net.URL;
import javax.sound.sampled.Clip;

public class MainCharacter{
    public float x = 0;
    public float y = 0;
    private float speedY = 0;
    private Animation characterRun;
    public Rectangle rect;
    private boolean isAlive = true;
    private static final int NORMAL_RUN = 0;
    public static final int JUMPING = 1;
    private static final int DOWN_RUN = 2;
    private static final int DEATH = 3;
    private static final int HURT = 4;
    private int score = 0;
    private int coin = 0;
    private Clip clip;
    public int state = NORMAL_RUN;
    private BufferedImage jumping;
    private Animation downRunAnimation;
    private BufferedImage deathImage;
    private BufferedImage hurtImage;

    private AudioClip jumpSound;
    private AudioClip deadSound;
    private AudioClip hurtSound;

    private static final int Normal_Color = 0;
    private static final int Blue = 1;
    private static final int Red = 2;

    public int colorSwitch = Normal_Color;

    

    public MainCharacter(){
        characterRun = new Animation(100); //調整角色圖片變換速度
        characterRun.addFrame(Resource.getResourceImage("data/main-character1.png"));
        characterRun.addFrame(Resource.getResourceImage("data/main-character2.png"));
        characterRun.addFrame(Resource.getResourceImage("data/main-character7.png"));
        characterRun.addFrame(Resource.getResourceImage("data/main-character8.png"));
        characterRun.addFrame(Resource.getResourceImage("data/main-character9.png"));
        rect = new Rectangle();
        jumping = Resource.getResourceImage("data/main-character3.png");
        downRunAnimation = new Animation(100);
		downRunAnimation.addFrame(Resource.getResourceImage("data/main-character5.png"));
		downRunAnimation.addFrame(Resource.getResourceImage("data/main-character6.png"));
		deathImage = Resource.getResourceImage("data/main-character4.png");
        hurtImage = Resource.getResourceImage("data/main-character10.png");
        try{
            jumpSound =  Applet.newAudioClip(new URL("file","","data/jump.wav"));
			deadSound =  Applet.newAudioClip(new URL("file","","data/dead.wav"));
            hurtSound =  Applet.newAudioClip(new URL("file","","data/hurtSound.wav"));
        }catch(MalformedURLException ex) {
            ex.printStackTrace();
        }
    }

    public void update() {
        characterRun.update();
        downRunAnimation.update();
        if(y >= GROUNDY - characterRun.getFrame().getHeight()){
            speedY = 0;
            y = GROUNDY - characterRun.getFrame().getHeight();
            if(state != DOWN_RUN) {
                state = NORMAL_RUN;
            }
            if(state != HURT) {
                state = NORMAL_RUN;
            }
        }else{
            speedY+=GRAVITY;
            y+=speedY;
        }
        rect.x = (int) x;
        rect.y = (int) y;
        rect.width = characterRun.getFrame().getWidth();
        rect.height = characterRun.getFrame().getHeight();
    }
    
    public Rectangle getBound() {
        return rect;
    }


    public void draw(Graphics g) {
        switch(state) {
            case NORMAL_RUN:
                g.drawImage(characterRun.getFrame(), (int)x, (int)y, null);
                break;
            case JUMPING:
                g.drawImage(jumping, (int)x, (int)y, null);
                //System.out.println("y:"+y);
                break;
            case DOWN_RUN:
                g.drawImage(downRunAnimation.getFrame(), (int)x, (int)y, null);
                break;
            case DEATH:
                g.drawImage(deathImage, (int)x, (int)y, null);
                break;
            case HURT:
                g.drawImage(hurtImage, (int)x, (int)y, null);
                break;

        }

        switch(colorSwitch) {
            case Normal_Color:
                g.setColor(Color.black);
                g.drawRect((int)x, (int)y, 50, 52);
                break;
            case Blue:
                g.setColor(Color.blue);
                g.drawRect((int)x, (int)y, 50, 52);
                break;
            case Red:
                g.setColor(Color.red);
                g.drawRect((int)x, (int)y, 50, 52);
                break;
        }
    
    }


    public void jump() {
			if(jumpSound != null) {
				jumpSound.play();
			}
            speedY = -5;
            y += speedY;
            state = JUMPING;
    }

    public void down(boolean isDown) {
        if(state == JUMPING) {
            return;
        }
        if(isDown) {
            state = DOWN_RUN;
        }else {
            state = NORMAL_RUN;
        }
    }

    public void dead(boolean isDeath) {
		if(isDeath) {
			state = DEATH;
            deadSound.play();
		} else {
			state = NORMAL_RUN;
		}
	}

    public void hurt(boolean isHurt){
        if(isHurt){
            hurtSound.play();
            state = HURT;
            System.out.println("state:" + state);
        }else{
            state = NORMAL_RUN;
        }
    }

    public void blue(boolean isBlue) {
        if(colorSwitch == Blue) {
            return;
        }
        if(isBlue) {
            colorSwitch = Blue;
            //System.out.println("colorSwitch:"+colorSwitch);
        }else {
            colorSwitch = Normal_Color;
            //System.out.println("colorSwitch:"+colorSwitch);
        }
    }

    public void red(boolean isRed) {
        if(colorSwitch == Red) {
            return;
        }
        if(isRed) {
            colorSwitch = Red;
            //System.out.println("colorSwitch:"+colorSwitch);
        }else {
            colorSwitch = Normal_Color;
            //System.out.println("colorSwitch:"+colorSwitch);
        }
    }


    
    public void reset() {
        y = GROUNDY;
        colorSwitch = Normal_Color;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getSpeed() {
        return speedY;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean getAlive() {
        return isAlive;
    }
}