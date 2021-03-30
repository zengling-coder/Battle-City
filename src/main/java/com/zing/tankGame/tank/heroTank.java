package com.zing.tankGame.tank;

import com.zing.tankGame.shot.Bullet;
import lombok.Data;

import java.util.Vector;

/**
 * @author zengling
 * @create 2021-03-29-19:30
 */

@Data
public class heroTank extends Tank {//自己的坦克
    public Bullet bullet=null;
    public Vector<Bullet> bullets=new Vector<>();
    public heroTank(int x,int y,int direct,int speed){
        super(x,y,direct,speed);
    }
    public void shotEnemyTank(){//自己的坦克射击

        if(bullets.size()>5){
            return;
        }
        int direct = this.getDirect();
        int x = this.getX();
        int y = this.getY();
        int speed = getSpeed()*2;
        switch (direct){
            case 0:{//向上
                bullet=new Bullet(x+20,y,direct,speed);
                break;
            }
            case 1:{//向右
                bullet=new Bullet(x+60,y+20,direct,speed);
                break;
            }
            case 2:{//向下
                bullet=new Bullet(x+20,y+60,direct,speed);
                break;
            }
            case 3:{//向左
                bullet=new Bullet(x,y+20,direct,speed);
                break;
            }
        }
        bullets.add(bullet);
        new Thread(bullet).start();
    }
}
