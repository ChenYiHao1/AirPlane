package airPlay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

/*
 *游戏主窗口
 */

public class Demo03 extends Frame {

    Image un = DemoT.getImage("image\\un.jpg");
    Image planeImg = DemoT.getImage("image\\plane.png");

    Plane plane = new Plane(planeImg, 300, 300);
    Shell[] shells = new Shell[30];

    Explode bao;

    Date startTime = new Date();
    Date endTime;
    int period;//游戏持续时间


    public void paint(Graphics g) {//自动调用，g相当画笔
        Color c = g.getColor();
        super.paint(g);
        g.drawImage(un, 0, 0, null);

        plane.drawSelf(g);

        //画出所有炮弹
        for (int i = 0; i < shells.length; i++) {
            shells[i].draw(g);
//飞机和炮弹的碰撞检测
            boolean peng = shells[i].getRect().intersects(plane.getRect());
            if (peng) {
                plane.live = false;
                if (bao == null) {
                    bao = new Explode(plane.x, plane.y);
                    endTime = new Date();
                    period = (int) (endTime.getTime() - startTime.getTime()) / 1000;
                }

                bao.draw(g);
            }
            if (!plane.live) {
                g.setColor(Color.BLUE);
                Font f =new Font("宋体",Font.BOLD,50);
                g.setFont(f);
                g.drawString("时间:" + period + "秒", (int) plane.x, (int) plane.y);
            }

        }

        g.setColor(c);
    }

    class PaintThread extends Thread {
        public void run() {
            while (true) {
                repaint();

                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //键盘控制
    class KeyMonitor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            plane.addDirection(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            plane.minusDirection(e);
        }
    }


    //初始化窗口
    public void launchFrame() {
        this.setTitle("飞机大战");
        this.setVisible(true);
        this.setSize(Constant.GAME_Width, Constant.GAME_Height);
        this.setLocation(300, 300);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        new PaintThread().start();//启动线程
        addKeyListener(new KeyMonitor());//添加键盘监听


        //初始化炮弹

        for (int i = 0; i < shells.length; i++) {
            shells[i] = new Shell();
        }
    }

    public static void main(String[] args) {
        Demo03 demo03 = new Demo03();
        demo03.launchFrame();
    }

    //添加双缓冲
    private Image offScreenImage = null;

    public void update(Graphics g) {
        if (offScreenImage == null)
            offScreenImage = this.createImage(Constant.GAME_Width, Constant.GAME_Height);//这是游戏窗口的宽度和高度

        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);
    }

}
