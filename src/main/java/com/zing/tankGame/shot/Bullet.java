package com.zing.tankGame.shot;

import com.sun.media.sound.SoftReverb;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zengling
 * @create 2021-03-29-22:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bullet implements Runnable {
    int x;//子弹的横坐标
    int y;//子弹的纵坐标
    int direct;//子弹射击的方向
    int speed;//子弹的速度
    boolean isLive=true;//子弹是否存在
    public Bullet(int x,int y,int direct,int speed){
        this.direct=direct;
        this.x=x;
        this.y=y;
        this.speed=speed;
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch (direct){
                case 0://上
                    y-=speed;
                    break;
                case 1://右
                    x+=speed;
                    break;
                case 2://下
                    y+=speed;
                    break;
                case 3://左
                    x-=speed;
                    break;
            }
            //当子弹遇到边界线程结束，或者遇到敌人
            if(!(x>=0&&x<=1000&&y>=0&&y<=750&&isLive)){
                isLive=false;
                break;
            }

        }
    }
}
