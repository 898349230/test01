package testpro.test02;

import java.util.Scanner;

/**
 * 分治算法，Strassen 计算矩阵(这个有问题)
 */
public class Strassen {

    private static int A[][];

    private static int B[][];

    private static int C[][];

    private final static int NUMBER = 4;

    public Strassen(){
        A = new int[NUMBER][NUMBER];
        B = new int[NUMBER][NUMBER];
        C = new int[NUMBER][NUMBER];
    }

    public static void main(String[] args){
        Strassen demo=new Strassen();
        System.out.println("输入矩阵A");
//        demo.input(A);
        A = new int[][] {
                {1, 2, 3, 4, 5, 6, 7, 8},
                {1, 2, 3, 4, 5, 6, 7, 8},
                {1, 2, 3, 4, 5, 6, 7, 8},
                {1, 2, 3, 4, 5, 6, 7, 8},
                {1, 2, 3, 4, 5, 6, 7, 8},
                {1, 2, 3, 4, 5, 6, 7, 8},
                {1, 2, 3, 4, 5, 6, 7, 8},
                {1, 2, 3, 4, 5, 6, 7, 8},
        };
        System.out.println("输入矩阵B");
//        demo.input(B);
        B = new int[][] {
                {5, 6, 7, 8, 1, 2, 3, 4},
                {5, 6, 7, 8, 1, 2, 3, 4},
                {5, 6, 7, 8, 1, 2, 3, 4},
                {5, 6, 7, 8, 1, 2, 3, 4},
                {5, 6, 7, 8, 1, 2, 3, 4},
                {5, 6, 7, 8, 1, 2, 3, 4},
                {5, 6, 7, 8, 1, 2, 3, 4},
                {5, 6, 7, 8, 1, 2, 3, 4},
        };
        demo.Strassen(A, B, C);
        demo.output(C);
    }

    public void input(int a[][]){
        Scanner scanner = new Scanner(System.in);
        for(int i = 0; i < a.length; i++) {
            for(int j = 0; j < a[i].length; j++) {
                a[i][j] = scanner.nextInt();
            }
        }
    }

    public void output(int[][] resault){
        for(int b[] : resault) {
            for(int temp : b) {
                System.out.print(temp + "  ");

            }
            System.out.println();
        }
    }

    public void Mul(int[][] first, int[][] second, int[][] resault){
        for(int i = 0; i < 2; ++i) {
            for(int j = 0; j < 2; ++j) {
                resault[i][j] = 0;
                for(int k = 0; k < 2; ++k) {
                    resault[i][j] += first[i][k] * second[k][j];
                }
            }
        }
    }

    public void Add(int[][] first, int[][] second, int[][] resault){
        for(int i = 0; i < first.length; i++) {
            for(int j = 0; j < first[i].length; j++){
                resault[i][j] = first[i][j] + second[i][j];
            }
        }
    }

    public void sub(int[][] first, int[][] second, int[][] resault){
        for(int i = 0; i < first.length; i++) {
            for(int j = 0; j < first[i].length; j++){
                resault[i][j] = first[i][j] - second[i][j];
            }
        }
    }

    public void Strassen(int[][] A, int[][] B, int[][] C){
        //定义一些中间变量
        int [][] M1=new int [NUMBER][NUMBER];
        int [][] M2=new int [NUMBER][NUMBER];
        int [][] M3=new int [NUMBER][NUMBER];
        int [][] M4=new int [NUMBER][NUMBER];
        int [][] M5=new int [NUMBER][NUMBER];
        int [][] M6=new int [NUMBER][NUMBER];
        int [][] M7=new int [NUMBER][NUMBER];
        int [][] C11=new int [NUMBER][NUMBER];
        int [][] C12=new int [NUMBER][NUMBER];
        int [][] C21=new int [NUMBER][NUMBER];
        int [][] C22=new int [NUMBER][NUMBER];
        int [][] A11=new int [NUMBER][NUMBER];
        int [][] A12=new int [NUMBER][NUMBER];
        int [][] A21=new int [NUMBER][NUMBER];
        int [][] A22=new int [NUMBER][NUMBER];
        int [][] B11=new int [NUMBER][NUMBER];
        int [][] B12=new int [NUMBER][NUMBER];
        int [][] B21=new int [NUMBER][NUMBER];
        int [][] B22=new int [NUMBER][NUMBER];
        int [][] temp=new int [NUMBER][NUMBER];
        int [][] temp1=new int [NUMBER][NUMBER];
        if(A.length==2){
            Mul(A, B, C);
        }else{
            //首先将矩阵A，B 分为4块
            for(int i = 0; i < A.length/2; i++) {
                for(int j = 0; j < A.length/2; j++) {
                    A11[i][j]=A[i][j];
                    A12[i][j]=A[i][j+A.length/2];
                    A21[i][j]=A[i+A.length/2][j];
                    A22[i][j]=A[i+A.length/2][j+A.length/2];
                    B11[i][j]=B[i][j];
                    B12[i][j]=B[i][j+A.length/2];
                    B21[i][j]=B[i+A.length/2][j];
                    B22[i][j]=B[i+A.length/2][j+A.length/2];
            }
        }
            //计算M1  M1=A11(B12-B22)
            sub(B12, B22, temp);
            Mul(A11, temp, M1);
            //计算M2  M2=(A11+A12)B22
            Add(A11, A12, temp);
            Mul(temp, B22, M2);
            //计算M3  M3=(A21+A22)B11
            Add(A21, A22, temp);
            Mul(temp, B11, M3);
            //M4  M4=A22(B21-B11)
            sub(B21, B11, temp);
            Mul(A22, temp, M4);
            //M5  M5=(A11+A22)(B11+B22)
            Add(A11, A22, temp1);
            Add(B11, B22, temp);
            Mul(temp1, temp, M5);
            //M6  M6=(A12-A22)(B21+B22)
            sub(A12, A22, temp1);
            Add(B21, B22, temp);
            Mul(temp1, temp, M6);
            //M7  M7=(A11-A21)(B11+B12)
            sub(A11, A21, temp1);
            Add(B11, B12, temp);
            Mul(temp1, temp, M7);
            //计算C11  C11=M5+M4-M2+M6
            Add(M5, M4, temp1);
            sub(temp1, M2, temp);
            Add(temp, M6, C11);
            //计算C12  C12=M1+M2
            Add(M1, M2, C12);
            //C21  C21=M3+M4
            Add(M3, M4, C21);
            //C22  C22=M5+M1-M3-M7
            Add(M5, M1, temp1);
            sub(temp1, M3, temp);
            sub(temp, M7, C22);

            //结果送回C中
            for(int i = 0; i < C.length/2; i++) {
                for(int j = 0; j < C.length/2; j++) {
                    C[i][j]=C11[i][j];
                    C[i][j+C.length/2]=C12[i][j];
                    C[i+C.length/2][j]=C21[i][j];
                    C[i+C.length/2][j+C.length/2]=C22[i][j];
                }
            }
        }
    }

}

