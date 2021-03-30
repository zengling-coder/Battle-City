package com.zing.tankGame.panel;

import com.zing.tankGame.bomb.Bomb;
import com.zing.tankGame.recorder.Recorder;
import com.zing.tankGame.shot.Bullet;
import com.zing.tankGame.tank.Tank;
import com.zing.tankGame.tank.enemyTank;
import com.zing.tankGame.tank.heroTank;
import com.zing.tankGame.tank.tankNode;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * @author zengling
 * @create 2021-03-29-19:32
 */
//JPanel窗口,KeyListener监听键盘
public class myPanel extends JPanel implements KeyListener,Runnable {
    //定义自己的坦克
    heroTank tank;
    //敌人的坦克
    Vector<enemyTank> enemyTanks;
    //存放炸弹
    Vector<Bomb> bombs;
    int enemyTankNum=5;
    //存储信息
    Vector<tankNode> nodes;

    //定义三张照片,用于显示爆炸效果
    Image image1=null;
    Image image2=null;
    Image image3=null;
    public myPanel(Integer key){
        nodes = Recorder.getNodesAndEnemyNum();
        bombs=new Vector<>();
        //初始化自己的坦克
        tank=new heroTank(500, 500,0,2);
        enemyTanks=new Vector<>();
        //初始化敌人的坦克
        switch (key){
            case 1:{
                Recorder.allEnemyTankNum=0;
                for (int i = 0; i <enemyTankNum ; i++) {

                    enemyTank enemyTank = new enemyTank(100 * (i + 1), 0, 2, 2);
                    new Thread(enemyTank).start();
                    //子弹的初始坐标和速度和方向
                    int x = enemyTank.getX()+20;
                    int y = enemyTank.getY()+60;
                    int direct = enemyTank.getDirect();
                    int speed = enemyTank.getSpeed()*2;
                    //给敌人的坦克加子弹
                    Bullet bullet=new Bullet(x,y,direct,speed);
                    enemyTank.getBullets().add(bullet);
                    enemyTanks.add(enemyTank);
                    //启动子弹线程
                    new Thread(bullet).start();
                }
                break;
            }
            case 2:{
                for (int i = 0; i <nodes.size() ; i++) {
                    tankNode node = nodes.get(i);
                    enemyTank enemyTank = new enemyTank(node.getX(), node.getY(), node.getDirect(), 2);
                    new Thread(enemyTank).start();
                    //子弹的初始坐标和速度和方向
                    int x = enemyTank.getX()+20;
                    int y = enemyTank.getY()+60;
                    int direct = enemyTank.getDirect();
                    int speed = enemyTank.getSpeed()*2;
                    //给敌人的坦克加子弹
                    Bullet bullet=new Bullet(x,y,direct,speed);
                    enemyTank.getBullets().add(bullet);
                    enemyTanks.add(enemyTank);
                    //启动子弹线程
                    new Thread(bullet).start();
                }
                break;
            }
        }
        for (enemyTank enemyTank : enemyTanks) {
            enemyTank.setEnemyTanks(enemyTanks);
        }
        //初始化图片
        image1= Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.png"));
        image2= Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.png"));
        image3= Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.png"));
        Recorder.setTanks(enemyTanks);
    }
    //编写方法,显示我方击毁敌方坦克的信息
    public void showInfo(Graphics g){
        g.setColor(Color.BLACK);
        Font font=new Font("宋体", Font.BOLD, 25);
        g.setFont(font);

        g.drawString("累计击毁坦克数:", 1024, 30);
        this.drawTank(1020,60,g,0,0);
        g.setColor(Color.BLACK);
        String str=Recorder.getAllEnemyTankNum()==0?"":Recorder.getAllEnemyTankNum()+"";
        g.drawString(str, 1080, 100);
    }
    @Override
    public  void paint(Graphics g) {
        g.fillRect(0, 0, 1000, 750);//填充矩形,默认黑色
        showInfo(g);
        //画自己的坦克
        if(tank.isLive()){
            drawTank(tank.getX(), tank.getY(), g, tank.getDirect(), 0);
            //画出自己的坦克的子弹
            for (int i = 0; i <tank.bullets.size() ; i++) {
                Bullet bullet = tank.bullets.get(i);
                if(bullet!=null&&bullet.isLive()){
                    int x = bullet.getX();
                    int y = bullet.getY();
                    g.fill3DRect(x, y, 2, 2, false);
                }else{
                    tank.bullets.remove(bullet);
                }
            }

        }


        //如果bombs集合中有对象,就画处
        for (int i=0;i<bombs.size();i++){
            Bomb bomb = bombs.get(i);
            if(bomb.life>6){
                g.drawImage(image1, bomb.x, bomb.y, 60, 60,this);
            }else if(bomb.life>3){
                g.drawImage(image2, bomb.x, bomb.y, 60, 60,this);
            }else{
                g.drawImage(image3, bomb.x, bomb.y, 60, 60,this);
            }
            bomb.lifeDown();
            if(bomb.life==0){
                bombs.remove(bomb);
            }
        }


        //画出敌人的坦克
        for (int i = 0; i <enemyTanks.size() ; i++) {
            enemyTank enemyTank = enemyTanks.get(i);

            if(enemyTank.isLive()){//如果存活
                int x = enemyTank.getX();
                int y = enemyTank.getY();
                int direct = enemyTank.getDirect();
                drawTank(x,y,g,direct,1);
                //画出敌人坦克的子弹
                for (int j=0;j<enemyTank.getBullets().size();j++){
                    //取出子弹
                    Bullet bullet = enemyTank.getBullets().get(j);
                    if(bullet.isLive()){
                        g.fill3DRect(bullet.getX(), bullet.getY(), 2, 2, false);
                    }else{
                        enemyTank.getBullets().remove(bullet);
                    }
                }
            }else{
                enemyTanks.remove(enemyTank);
            }
        }
    }

    /**
     *
     * @param x 坦克的左上角x的坐标
     * @param y 坦克的右上角y的坐标
     * @param g 画笔
     * @param direct 坦克方向
     * @param type 坦克类型(自己和敌人的坦克)
     */
    public void drawTank(int x,int y,Graphics g,int direct,int type){
        switch (type){
            case 0:{
                //我们的坦克
                g.setColor(Color.cyan);
                break;
            }
            case 1:{//敌人的坦克
                g.setColor(Color.yellow);
                break;
            }
        }
        switch (direct){//坦克方向
            case 0:{//表示向上
                g.fill3DRect(x,y,10,60,false);//左边的轮子
                g.fill3DRect(x+30,y,10,60,false);//右边的轮子
                g.fill3DRect(x+10,y+10,20,40,false);//坦克盖子
                g.fillOval(x+10, y+20, 20, 20);//中间的⚪
                g.drawLine(x+20, y+30, x+20, y);//炮筒
                break;
            }
            case 1:{//表示向右
                g.fill3DRect(x,y,60,10,false);//上边的轮子
                g.fill3DRect(x,y+30,60,10,false);//下边的轮子
                g.fill3DRect(x+10,y+10,40,20,false);//坦克盖子
                g.fillOval(x+20, y+10, 20, 20);//中间的⚪
                g.drawLine(x+30, y+20, x+60, y+20);//炮筒
                break;
            }
            case 2:{//向下
                g.fill3DRect(x,y,10,60,false);//左边的轮子
                g.fill3DRect(x+30,y,10,60,false);//右边的轮子
                g.fill3DRect(x+10,y+10,20,40,false);//坦克盖子
                g.fillOval(x+10, y+20, 20, 20);//中间的⚪
                g.drawLine(x+20, y+30, x+20, y+60);//炮筒
                break;
            }
            case 3:{//向左
                g.fill3DRect(x,y,60,10,false);//上边的轮子
                g.fill3DRect(x,y+30,60,10,false);//下边的轮子
                g.fill3DRect(x+10,y+10,40,20,false);//坦克盖子
                g.fillOval(x+20, y+10, 20, 20);//中间的⚪
                g.drawLine(x+30, y+20, x, y+20);//炮筒
                break;
            }
        }
    }

    //判断敌方坦克是否击中我方坦克
    public boolean hitHeroTank(){
        for (enemyTank enemyTank : enemyTanks) {
            for (Bullet bullet : enemyTank.getBullets()) {
                if(tank.isLive()){
                    isShot(bullet,tank);
                }else {
                    return true;
                }
            }
        }
        return false;
    }
    //编写方法,敌方坦克被击中
    public void hitEnemyTank(){
        for (Bullet bullet : tank.bullets) {
            //判断我方坦克是否击中敌方坦克
            if(bullet!=null&&bullet.isLive()){
                for (enemyTank enemy : enemyTanks) {
                    isShot(bullet,enemy);
                }
            }
        }
    }
    public void isShot(Bullet bullet, Tank tank){
        //子弹的坐标

        switch (tank.getDirect()){
            //坦克上下的方向
            case 0:
            case 2:{
                if(bullet.getX()>tank.getX()&&bullet.getX()<tank.getX()+40&&bullet.getY()>tank.getY()&&bullet.getY()<tank.getY()+60){
                    bullet.setLive(false);
                    tank.setLive(false);
//                    enemyTanks.remove(tank);
                    if(tank instanceof enemyTank) {
                        Recorder.addEnemyTankNum();
                    }
                    //创建Bomb对象,加入bombs中
                    Bomb bomb=new Bomb(tank.getX(),tank.getY());
                    bombs.add(bomb);

                    break;
                }
            }
            //坦克左右方向
            case 1:
            case 3:{
                if(bullet.getX()>tank.getX()&&bullet.getX()<tank.getX()+60&&bullet.getY()>tank.getY()&&bullet.getY()<tank.getY()+40){
                    bullet.setLive(false);
                    tank.setLive(false);
//                    enemyTanks.remove(tank);
                    if(tank instanceof enemyTank){
                        Recorder.addEnemyTankNum();
                    }
                    Bomb bomb=new Bomb(tank.getX(),tank.getY());
                    bombs.add(bomb);

                    break;
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_UP){
            tank.setDirect(0);
            if(tank.getY()>0){
                tank.moveUp();
            }
        }else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            tank.setDirect(1);
            if(tank.getX()+60<1000){
                tank.moveRight();
            }
        }else if(e.getKeyCode()==KeyEvent.VK_DOWN){
            tank.setDirect(2);
            if(tank.getY()+60<750){
                tank.moveDown();
            }
        }else if(e.getKeyCode()==KeyEvent.VK_LEFT){
            tank.setDirect(3);
            if(tank.getX()>0){
                tank.moveLeft();
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_X){
             tank.shotEnemyTank();
        }
        //面板重绘
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while(true){

            //我方坦克击中敌方坦克
            hitEnemyTank();
            //敌方坦克击中我方坦克
            hitHeroTank();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //重绘
            repaint();
        }
    }
}
