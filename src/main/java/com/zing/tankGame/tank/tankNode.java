package com.zing.tankGame.tank;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zengling
 * @create 2021-03-30-19:53
 */
@Data
public class tankNode {
    private  int x;
    private int y;
    private int direct;

    public tankNode(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

}
