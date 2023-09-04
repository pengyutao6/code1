package com.tao.javase.basedemo;

public class PrintKongXinLengXing {
    //一般棱形分别上半和下半，所以是两个for循环
    //然后里面有三个元素。一个是从上到下的行数，一个是从左到右的空格，以及*符合
    //所以一个for循环里面会有两个for循环，一个根据位置规则打印出空格，一个打印出*
    public static void main(String[] args) {
        //上半部分：正的等腰三角形
        //5行
        for(int i=1; i<=5; i++){
            //(1)打印空格
			/*
			当i=1,打印4个空格，j=4,3,2,1	j>=i
			当i=2,打印3个空格，j=4,3,2
			当i=3,打印2个空格，j=4,3
			当i=4,打印1个空格，j=4
			当i=5,打印0个空格，j=4,让循环条件一次都不满足
			*/
            for(int j=4; j>=i; j--){
                System.out.print("  ");
            }
            //(2)打印*
			/*
			当i=1,打印1个,j=1					j<=2*i-1
			当i=2,打印3个,j=1,2,3,
			当i=3,打印5个,j=1,2,3,4,5
			当i=4,打印7个,j=1,2,3,4,5,6,7
			当i=5,打印9个,j=1,2,3,4,5,6,7,8,9
			*/
            for(int j=1; j<=2*i-1; j++){
                //不是全部打印*
                if(j==1 || j==2*i-1){
                    System.out.print("* ");
                }else{
                    System.out.print("  ");
                }
            }
            //(3)换行
            System.out.println();
        }


        //下半部分：倒立的等腰三角形
        //4行
        for(int i=1; i<=4; i++){
            //(1)打印空格
			/*
			当i=1,1个空格,j=1		j<=i
			当i=2,2个空格,j=1,2
			当i=3,3个空格,j=1,2,3
			当i=4,4个空格,j=1,2,3,4
			*/
            for(int j=1; j<=i; j++){
                System.out.print("  ");
            }
            //(2)打印*
			/*
			当i=1,7个*，j=1,2,3,4,5,6,7  j<=9-2*i;
			当i=2,5个*，j=1,2,3,4,5
			当i=3,3个*，j=1,2,3
			当i=4,1个*，j=1
			*/
            for(int j=1; j<=9-2*i; j++){
                //不是全部打印*
                if(j==1 || j==9-2*i){
                    System.out.print("* ");
                }else{
                    System.out.print("  ");
                }
            }
            //(3)换行
            System.out.println();
        }
    }
}
