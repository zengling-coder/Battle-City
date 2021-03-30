package com.zing.tankGame.recorder;

import com.zing.tankGame.tank.Tank;
import com.zing.tankGame.tank.enemyTank;
import com.zing.tankGame.tank.tankNode;
import lombok.Data;

import java.io.*;
import java.util.Arrays;
import java.util.Vector;

/**
 * @author zengling
 * @create 2021-03-30-17:58
 */

public class Recorder {
    //记录击毁坦克数
    public static int allEnemyTankNum=0;
    //定义IO对象
    private static BufferedReader rd=null;
    private static BufferedWriter rw=null;
    private static String fileName="E:\\myRecord.txt";
    private static Vector<enemyTank> tanks=new Vector<>();

    private static Vector<tankNode> nodes=new Vector<>();

    public static void setNodes(Vector<tankNode> nodes) {
        Recorder.nodes = nodes;
    }

    public static void setTanks(Vector<enemyTank> tanks) {
        Recorder.tanks = tanks;
    }

    public static int getAllEnemyTankNum() {
        return allEnemyTankNum;
    }

    public static void setAllEnemyTankNum(int allEnemyTankNum) {
        Recorder.allEnemyTankNum = allEnemyTankNum;
    }

    //当我方坦克击毁敌方坦克allEnemyTankNum++
    public static void addEnemyTankNum(){
        Recorder.allEnemyTankNum++;
    }
    //当游戏退出时,保存
    public static void keepRecord() {
        try {
            rw=new BufferedWriter( new FileWriter(fileName));
            rw.write(allEnemyTankNum+"\r\n");
            for (int i = 0; i <tanks.size() ; i++) {
                enemyTank tank = tanks.get(i);
                if(tank.isLive()){
                    String recode=tank.getX()+" "+tank.getX()+" "+tank.getDirect();
                    rw.write(recode+"\r\n");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                rw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //读取存储
    public static Vector<tankNode> getNodesAndEnemyNum(){
        try {
            rd=new BufferedReader(new FileReader(fileName));
            allEnemyTankNum= Integer.parseInt(rd.readLine());
            String line="";
            while((line=rd.readLine())!=null){
                String[] strings = line.split(" ");
                tankNode node = new tankNode(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), Integer.parseInt(strings[2]));
                nodes.add(node);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                rd.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return nodes;
    }
}
