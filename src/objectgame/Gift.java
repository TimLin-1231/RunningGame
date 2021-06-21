package src.objectgame;

import java.awt.Rectangle;
import java.awt.Graphics;

public abstract class Gift {
    public abstract Rectangle getBound();
    public abstract void draw(Graphics g);
    public abstract void update();
    public abstract boolean isOutOfScreen();
    public abstract boolean isCoinGot();
    public abstract void SetIsCoinGot(boolean isCoinGot);
    public abstract boolean isHpPlus();
    public abstract void SetIsHpPlus(boolean isHpPlus);
}
