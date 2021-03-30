package com.zing.tankGame.frame;

import com.zing.tankGame.panel.myPanel;
import com.zing.tankGame.recorder.Recorder;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Scanner;

/**
 * @author zengling
 * @create 2021-03-29-19:35
 */
public class tankGame extends JFrame {
    myPanel mp;
    static Scanner scanner=new Scanner(System.in);
    //设置面板
    public tankGame(){
        System.out.println("1:新游戏   2:继续上局");
        Integer str = scanner.nextInt();
        mp=new myPanel(str);
        new Thread(mp).start();
        this.add(mp).setSize(1000,750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(mp);//让画板监听键盘事件
        this.setVisible(true);


        this.addWindowListener(new WindowAdapter()  {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.keepRecord();
            }
        });

    }
    public static void main(String[] args) {

        tankGame game=new tankGame();
    }
}
