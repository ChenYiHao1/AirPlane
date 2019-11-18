package airPlay;

import java.awt.Graphics;
import java.awt.Image;

public class Explode {

    double x,y;
    static Image[] imgs = new Image[16];
    static {

            DemoT.getImage("image\\boom (1).png");


    }

    int count;

    public void draw(Graphics g){
        if(count<=15){
            g.drawImage(imgs[count], (int)x, (int)y, null);
            count++;
        }
    }

    public Explode(double x,double y){
        this.x = x;
        this.y = y;
    }
}
