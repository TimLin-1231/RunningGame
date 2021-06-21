package src.objectgame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import src.utl.Resource;
import java.util.List;
import java.util.ArrayList;

public class Clouds {

    private BufferedImage cloudImage;
    private List<Cloud> clouds;

    public Clouds() {
        cloudImage = Resource.getResourceImage("data/cloud.png");
        clouds = new ArrayList<Cloud>();

        Cloud cloud1 = new Cloud();
        cloud1.posX = 300;
        cloud1.posY = 150;
        clouds.add(cloud1);

        cloud1 = new Cloud();
        cloud1.posX = 400;
        cloud1.posY = 130;
        clouds.add(cloud1);

        cloud1 = new Cloud();
        cloud1.posX = 500;
        cloud1.posY = 180;
        clouds.add(cloud1);

        cloud1 = new Cloud();
        cloud1.posX = 650;
        cloud1.posY = 150;
        clouds.add(cloud1);

        cloud1 = new Cloud();
        cloud1.posX = 800;
        cloud1.posY = 160;
        clouds.add(cloud1);
    }

    public void update() {
        for(Cloud cloud : clouds) {
            cloud.posX--;
        }
        Cloud firstCloud = clouds.get(0);
        if(firstCloud.posX + cloudImage.getWidth() < 0) {
            firstCloud.posX = 800;
            clouds.remove(firstCloud);
            clouds.add(firstCloud);
        }
    }

    public void draw(Graphics g) {
        for(Cloud cloud : clouds) {
            g.drawImage(cloudImage, (int)cloud.posX, (int)cloud.posY, null);
        }
        
    }

    private class Cloud {
        float posX;
        float posY;
    }
}
