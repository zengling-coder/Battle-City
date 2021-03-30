package com.zing.tankGame.tank;

import com.zing.tankGame.shot.Bullet;
import lombok.Data;

import java.util.Vector;

/**
 * @author zengling
 * @create 2021-03-29-21:09
 */
@Data
public class enemyTank extends Tank implements Runnable {
    Vector<Bullet> bullets=new Vector<>();//敌人坦克多次射击
    //其他坦克
    Vector<enemyTank> enemyTanks=new Vector<>();
    //初始化其他坦克
    public void setEnemyTanks(Vector<enemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

  public enemyTank(int x,int y,int direct,int speed){
      super(x,y,direct,speed);
  }
    //判断坦克是否发生碰撞
    public boolean isTouch(){
        switch (direct){
            //当前坦克向上
            case 0:{
                //当前敌人坦克和其他敌人坦克进行比较
                for (int i = 0; i <enemyTanks.size() ; i++) {
                    enemyTank enemyTank = enemyTanks.get(i);
                    if(enemyTank!=this){
                        //其他敌人坦克是向上或者向下
                        if(enemyTank.getDirect()==0||enemyTank.getDirect()==2){
                            //当前坦克左上角坐标
                            if(this.getX()>=enemyTank.getX()
                                    &&this.getX()<=enemyTank.getX()+40
                                    && this.getY()>=enemyTank.getY()
                                    &&this.getY()<=enemyTank.getY()+60) {
                                return true;
                            }
                            //当前坦克右上角坐标
                            if(this.getX()+40>=enemyTank.getX()
                                    &&this.getX()+40<=enemyTank.getX()+40
                                    && this.getY()>=enemyTank.getY()
                                    &&this.getY()<=enemyTank.getY()+60){
                                return true;
                            }
                        }
                        //其他敌人坦克是向右或者向左
                        if(enemyTank.getDirect()==1||enemyTank.getDirect()==3){
                            //当前坦克左上角坐标
                            if(this.getX()>=enemyTank.getX()
                                    &&this.getX()<=enemyTank.getX()+60
                                    && this.getY()>=enemyTank.getY()
                                    &&this.getY()<=enemyTank.getY()+40)
                                return true;
                            //当前坦克右上角坐标
                            if(this.getX()+40>=enemyTank.getX()
                                    &&this.getX()+40<=enemyTank.getX()+60
                                    && this.getY()>=enemyTank.getY()
                                    &&this.getY()<=enemyTank.getY()+40)
                                return true;
                        }
                    }
                }
                break;
            }
            //当前坦克向右
            case 1:{
                for (int i = 0; i <enemyTanks.size() ; i++) {
                    enemyTank enemyTank = enemyTanks.get(i);
                    if(enemyTank!=this){
                        //其他敌人坦克是向上或者向下
                        if(enemyTank.getDirect()==0||enemyTank.getDirect()==2){
                            //当前坦克右上角坐标
                            if(this.getX()+60>=enemyTank.getX()
                                    &&this.getX()+60<=enemyTank.getX()+40
                                    && this.getY()>=enemyTank.getY()
                                    &&this.getY()<=enemyTank.getY()+60)
                                return true;

                            //当前坦克右下角坐标
                            if(this.getX()+60>=enemyTank.getX()
                                    &&this.getX()+60<=enemyTank.getX()+40
                                    && this.getY()+40>=enemyTank.getY()
                                    &&this.getY()+40<=enemyTank.getY()+60)
                                return true;
                        }
                        //其他敌人坦克是向右或者向左
                        if(enemyTank.getDirect()==1||enemyTank.getDirect()==3){
                            //当前坦克左上角坐标
                            if(this.getX()+60>=enemyTank.getX()
                                    &&this.getX()+60<=enemyTank.getX()+60
                                    && this.getY()>=enemyTank.getY()
                                    &&this.getY()<=enemyTank.getY()+40)
                                return true;
                            //当前坦克右上角坐标
                            if(this.getX()+60>=enemyTank.getX()
                                    &&this.getX()+60<=enemyTank.getX()+60
                                    && this.getY()+40>=enemyTank.getY()
                                    &&this.getY()+40<=enemyTank.getY()+40)
                                return true;
                        }
                    }
                }
                break;
            }
            //当前坦克向下
            case 2:{
                for (int i = 0; i <enemyTanks.size() ; i++) {
                    enemyTank enemyTank = enemyTanks.get(i);
                    if(enemyTank!=this){
                        //其他敌人坦克是向上或者向下
                        if(enemyTank.getDirect()==0||enemyTank.getDirect()==2){
                            //当前坦克左下角
                            if(this.getX()>=enemyTank.getX()
                                    &&this.getX()<=enemyTank.getX()+40
                                    && this.getY()+60>=enemyTank.getY()
                                    &&this.getY()+60<=enemyTank.getY()+60){
                                return true;
                            }

                            //当前坦克右下角坐标
                            if(this.getX()+40>=enemyTank.getX()
                                    &&this.getX()+40<=enemyTank.getX()+40
                                    && this.getY()+60>=enemyTank.getY()
                                    &&this.getY()+60<=enemyTank.getY()+60)
                                return true;
                        }
                        //其他敌人坦克是向右或者向左
                        if(enemyTank.getDirect()==1||enemyTank.getDirect()==3){
                            //当前坦克左下角
                            if(this.getX()>=enemyTank.getX()
                                    &&this.getX()<=enemyTank.getX()+60
                                    && this.getY()+60>=enemyTank.getY()
                                    &&this.getY()+60<=enemyTank.getY()+40)
                                return true;
                            //当前坦克右下角坐标
                            if(this.getX()+40>=enemyTank.getX()
                                    &&this.getX()+40<=enemyTank.getX()+60
                                    && this.getY()+60>=enemyTank.getY()
                                    &&this.getY()+60<=enemyTank.getY()+40)
                                return true;
                        }
                    }
                }
                break;
            }
            //当前坦克向左
            case 3:{
                for (int i = 0; i <enemyTanks.size() ; i++) {
                    enemyTank enemyTank = enemyTanks.get(i);
                    if(enemyTank!=this){
                        //其他敌人坦克是向上或者向下
                        if(enemyTank.getDirect()==0||enemyTank.getDirect()==2){
                            //当前坦克左上角
                            if(this.getX()>=enemyTank.getX()
                                    &&this.getX()<=enemyTank.getX()+40
                                    && this.getY()>=enemyTank.getX()
                                    &&this.getY()<=enemyTank.getY()+60)
                                return true;

                            //当前坦克左下角
                            if(this.getX()>=enemyTank.getX()
                                    &&this.getX()<=enemyTank.getX()+40
                                    && this.getY()+40>=enemyTank.getX()
                                    &&this.getY()+40<=enemyTank.getY()+60)
                                return true;
                        }
                        //其他敌人坦克是向右或者向左
                        if(enemyTank.getDirect()==1||enemyTank.getDirect()==3){
                            //当前坦克左上角
                            if(this.getX()>=enemyTank.getX()
                                    &&this.getX()<=enemyTank.getX()+60
                                    && this.getY()>=enemyTank.getX()
                                    &&this.getY()<=enemyTank.getY()+40)
                                return true;
                            //当前坦克左下角
                            if(this.getX()>=enemyTank.getX()
                                    &&this.getX()<=enemyTank.getX()+60
                                    && this.getY()+40>=enemyTank.getX()
                                    &&this.getY()+40<=enemyTank.getY()+40)
                                return true;
                        }
                    }
                }
            }
            break;
        }
        return false;
    }

    @Override
    public void run() {
        while(true){

            //如果没有子弹了
            if(this.isLive&&bullets.size()<2){
                Bullet bullet=null;
                 switch (direct){
                     case 0:
                          bullet = new Bullet(x+20,y,direct,speed);
                         break;
                     case 1:
                          bullet = new Bullet(x+60,y+20,direct,speed);
                         break;
                     case 2:
                         bullet = new Bullet(x+20,y+60,direct,speed);
                         break;
                     case 3:
                         bullet = new Bullet(x,y+20,direct,speed);
                         break;
                 }
                 bullets.add(bullet);
                 new Thread(bullet).start();
            }

            //根据坦克的方向来继续移动
            switch (direct){
                case 0:
                    for (int i = 0; i <20 ; i++) {
                       if(y>0&&!isTouch()){
                           moveUp();
                       }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i <20 ; i++) {
                        if(x+60<1000&&!isTouch()){
                            moveRight();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i <20 ; i++) {
                        if(y+60<750&&!isTouch()){
                            moveDown();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i <20 ; i++) {
                        if(x>0&&!isTouch()){
                            moveLeft();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
            //然后随机的改变坦克方向
            this.setDirect((int) (Math.random()*4));
            if(!this.isLive){
                break;//坦克死了,线程结束
            }
        }
    }

}
