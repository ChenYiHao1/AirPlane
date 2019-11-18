package airPlay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/*
 *游戏主窗口
 */

public class Demo02 extends JFrame {

    public void paint(Graphics g){//自动调用，g相当画笔
        super.paint(g);

    }
    //初始化窗口
    public void launchFrame(){
        this.setTitle("飞机大战");
        this.setVisible(true);
        this.setSize(500,500);
        this.setLocation(300,300);

        this.addWindowListener(new WindowAdapter() {
            public  void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        Demo02 demo02 = new Demo02();
        demo02.launchFrame();
    }

}
