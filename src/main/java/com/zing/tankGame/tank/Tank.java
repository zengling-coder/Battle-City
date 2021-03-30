package com.zing.tankGame.tank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zengling
 * @create 2021-03-29-19:29
 */
@Data
@AllArgsConstructor()
@NoArgsConstructor
public class Tank implements tankMoveMethod {//抽象tank
    protected int x;//坦克横坐标
    protected int y;//纵坐标
    protected int direct;//坦克方向
    protected int speed;//坦克的移动速度
    protected boolean isLive;//坦克是否存活

    public Tank(int x, int y, int direct, int speed) {
        this.x = x;
        this.y = y;
        this.direct = direct;
        this.speed = speed;
        this.isLive=true;
    }

    @Override
    public void moveUp() {
        y-=speed;
    }

    @Override
    public void moveDown() {
         y+=speed;
    }

    @Override
    public void moveLeft() {
         x-=speed;
    }

    @Override
    public void moveRight() {
         x+=speed;
    }
}
