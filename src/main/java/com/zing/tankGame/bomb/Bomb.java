package com.zing.tankGame.bomb;

/**
 * @author zengling
 * @create 2021-03-30-11:55
 */
public class Bomb {
    public int x,y;//炸弹的坐标
    public int life=9;//炸弹的生命周期
    boolean isLive=true;//是否存活

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }
    //减少生命值
    public void lifeDown(){
        if(life>0) life--;
        else  isLive=false;
    }
}
