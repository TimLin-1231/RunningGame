package src.objectgame;

import java.awt.Rectangle;
import java.awt.Graphics;

public abstract class Enemy {
    public abstract Rectangle getBound();
    public abstract void draw(Graphics g);
    public abstract void update();
    public abstract boolean isOutOfScreen();
    public abstract boolean isCloseEnough();
    public abstract boolean isOver();
    public abstract boolean isSameColor();
    public abstract boolean isScoreGot();
    public abstract void SetIsScoreGot(boolean isScoreGot);
    public abstract boolean isHpMinus();
    public abstract void SetIsHpMinus(boolean isHpMinus);
}
