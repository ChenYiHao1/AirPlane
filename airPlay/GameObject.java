package airPlay;
/*
* 游戏体父类
*/

import java.awt.*;

public class GameObject {

    Image img;
    double x,y;
    int speed;
    int height,width;

    public void drawSelf(Graphics g){
        g.drawImage(img,(int)x,(int)y,null);
    }

    public GameObject(Image img, double x, double y, int speed, int height, int width) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.height = height;
        this.width = width;
    }

    public GameObject(Image img, double x, double y) {
        this.img = img;
        this.x = x;
        this.y = y;
    }

    public GameObject(){

    }

    //返回物体所在矩形，便于进行碰撞检测
    public Rectangle getRect(){
        return  new Rectangle((int)x,(int)y,width,height);
    }
}
