package org.example;

import java.util.Scanner;

/**
 * @author lilong
 * @create 2020-03-05
 */
public class Graphtest {


    static void deepTraOne(GraphMatrix gm, int n) {
        int i;
        gm.isTrave[n] = 1;
        for (i = 0; i < gm.VertexNum; i++) {
            if (gm.EdgeWeight[n][i] != GraphMatrix.MaxNum && gm.isTrave[i] == 0)
                deepTraOne(gm, i);
        }

    }


    static final int MaxValue = 65535;   //最大值（可设为一个最大整数）
    static final int USED = 0;  //已选用顶点
    static final int NoL = -1;  //非邻接顶点
    static Scanner input = new Scanner(System.in);

    //创建邻接矩阵图
    static void CreateGraph(GraphMatrix GM) {
        int i, j, k;
        int weight;     //权
        char EstartV, EendV;        //边的起始顶点
        System.out.printf("输入图中各顶点信息\n");
        for (i = 0; i < GM.VertexNum; i++) {        //输入顶点
            System.out.printf("第%d个顶点：", i + 1);
            GM.Vertex[i] = (input.next().toCharArray())[0];  //保存到各顶点的数组元素中
        }
        System.out.printf("输入构成各边的顶点及权值：\n");
        for (k = 0; k < GM.EdgeNum; k++) {            //输入边的信息
            System.out.printf("第%d条边：", k + 1);
            EstartV = input.next().charAt(0);
            EendV = input.next().charAt(0);
            weight = input.nextInt();
            for (i = 0; EstartV != GM.Vertex[i]; i++) ;      //在已有顶点中查找始点
            for (j = 0; EendV != GM.Vertex[j]; j++) ;        //在已有的顶点中查找终点
            GM.EdgeWeight[i][j] = weight;          //对应位置保存权值，表示有一条边
            if (GM.GType == 0) {        //若是无向图
                GM.EdgeWeight[j][i] = weight;
            }
        }
    }

    //清空矩阵
    static void ClearGraph(GraphMatrix GM) {
        int i, j;
        for (i = 0; i < GM.VertexNum; i++) {
            for (j = 0; j < GM.VertexNum; j++) {
                GM.EdgeWeight[i][j] = MaxValue;   //设置矩阵中各元素的值为MaxValue
            }
        }
    }

    //输出邻接矩阵
    static void OutGraph(GraphMatrix GM) {
        int i, j;
        for (j = 0; j < GM.VertexNum; j++) {
            System.out.printf("\t%c", GM.Vertex[j]);    //在第一行输出顶点信息
        }
        System.out.println();
        for (i = 0; i < GM.VertexNum; i++) {
            System.out.printf("%c", GM.Vertex[i]);
            for (j = 0; j < GM.VertexNum; j++) {
                if (GM.EdgeWeight[i][j] == MaxValue) {    //若权值为最大值
                    System.out.printf("\tZ");    //以Z表示无穷大
                } else {
                    System.out.printf("\t%d", GM.EdgeWeight[i][j]);      //输出边的权值
                }
            }
            System.out.println();
        }
    }

    //最小生成树算法
    static void PrimGraph(GraphMatrix GM) {
        int i, j, k, min, sum;
        int[] weight = new int[GraphMatrix.MaxNum];    //权值
        char[] vtempx = new char[GraphMatrix.MaxNum];        //临时顶点信息
        sum = 0;
        for (i = 1; i < GM.VertexNum; i++) {    //保存邻接矩阵中的一行数据
            weight[i] = GM.EdgeWeight[0][i];
            if (weight[i] == MaxValue) {
                vtempx[i] = (char) NoL;
            } else {
                vtempx[i] = GM.Vertex[0];        //邻接顶点
            }
        }
        vtempx[0] = USED;    //选用
        weight[0] = MaxValue;
        for (i = 1; i < GM.VertexNum; i++) {
            min = weight[0];        //最小权值
            k = i;
            for (j = 1; j < GM.VertexNum; j++) {
                if (weight[j] < min && vtempx[j] > 0) {        //找到具有更小权值的未使用边
                    min = weight[j];        //保存权值
                    k = j;                //保存邻接点序号
                }
            }
            sum += min;        //权值累加
            System.out.printf("(%c,%c),", vtempx[k], GM.Vertex[k]);   //输出生成树一条边
            vtempx[k] = USED;        //选用
            weight[k] = MaxValue;
            for (j = 0; j < GM.VertexNum; j++) {    //重新选择最小边
                if (GM.EdgeWeight[k][j] < weight[j] && vtempx[j] != 0) {
                    weight[j] = GM.EdgeWeight[k][j];        //权值
                    vtempx[j] = GM.Vertex[k];
                }
            }
        }
        System.out.printf("\n最小生成树的总权值为：%d\n", sum);
    }

    public static void main(String[] args) {
        GraphMatrix GM = new GraphMatrix();        //定义保存邻接表结构的图
        char again;
        String go;
        System.out.println("寻找最小生成树！");
        do {
            System.out.print("请先输入生成图的类型：");
            GM.GType = input.nextInt();    //图的种类
            System.out.print("输入图的顶点数量：");
            GM.VertexNum = input.nextInt();        //输入图的顶点数
            System.out.print("输入图的边数量：");
            GM.EdgeNum = input.nextInt();         //输入图边数
            ClearGraph(GM);        //清空图
            CreateGraph(GM);        //生成邻接表结构的图

            System.out.print("最小生成树的边为：");
            PrimGraph(GM);

            System.out.println("\n继续玩吗（y/n）?");
            go = input.next();
        } while (go.equalsIgnoreCase("y"));
        System.out.println("游戏结束！");
    }

}

//定义邻接矩阵图结构
class GraphMatrix {
    static final int MaxNum = 20;  //图的最大顶点数
    char[] Vertex = new char[MaxNum];  //保存顶点信息（序号或字母）
    int GType;      //图的类型（0：无向图，1：有向图）
    int VertexNum;  //顶点的数量
    int EdgeNum;     //边的数量
    int[][] EdgeWeight = new int[MaxNum][MaxNum];  //保存边的权
    int[] isTrave = new int[MaxNum];   //遍历标志
}

