package src.objectgame;

import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import src.userinterface.GameScreen;
import src.utl.Resource;
import java.awt.image.BufferedImage;


public class GiftManager {

    private List<Gift> gift;

    private BufferedImage imageCoin;
    private MainCharacter mainCharacter;
    private GameScreen gameScreen;
    private Coin coin;

    public GiftManager(MainCharacter mainCharacter, GameScreen gameScreen) {

        this.gameScreen = gameScreen;
        this.mainCharacter = mainCharacter;
        gift = new ArrayList<Gift>();
        imageCoin = Resource.getResourceImage("data/coin.png");
        gift.add(getCoin());
    }

    public void update() {
        
        for(Gift gi : gift) {
            gi.update();

            if(gi.getBound().intersects(mainCharacter.getBound()) && !gi.isCoinGot()){
                gameScreen.plusCoin(1);
                gi.SetIsCoinGot(true);

            }

            if(gameScreen.coin == 5   && !gi.isHpPlus() && gameScreen.HP < 5){
                gameScreen.HP ++;
                gi.SetIsHpPlus(true);
            }else if(gameScreen.coin == 10   && !gi.isHpPlus() && gameScreen.HP < 5){
                gameScreen.HP ++;
                gi.SetIsHpPlus(true);
            }


        }
        Gift firstCoin = gift.get(0);
        if(firstCoin.isOutOfScreen()) {
            gift.remove(firstCoin);
            gift.add(getCoin());
        }

    }

    public void draw(Graphics g) {
        for(Gift gi : gift) {
            gi.draw(g);
        }

    }

    public void reset() {
        gift.clear();
        gift.add(getCoin());
    }




    private Coin getCoin() {
        Coin coin;
        coin = new Coin(mainCharacter);
        coin.setX(600);
        coin.setImage(imageCoin);
        return coin;
    }

}
