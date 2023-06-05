package com.tao;

import org.junit.Test;

public class GenerateSQL {
    public static void main(String[] args) {
        test();
    }

    @Test
    private static void test(){
        String data = "sellerid,\n" +
                "\t\t\tcountry,product_name,fnsku,merchant_sku,asin,condition,supplier,supplier_part_no,\n" +
                "\t\t\tcurrency_code,price,sales_last_30_days,units_sold_last_30_days,total_units,inbound,available,\n" +
                "\t\t\tfc_transfer,fc_processing,customer_order,unfulfillable,fulfilled_by,days_of_supply,\n" +
                "\t\t\talert,recommended_replenishment_qty,recommended_ship_date,lastupdate";
        String tableName = "AY_Amazon_FBA_RESTOCK_RECOMMENDATIONS";
        insert(data,tableName);
        update(data,tableName);
    }

    /**
     * 	insert字段使用
     * @param data
     */
    public static void insert(String data,String tableName) {
        String [] str = data.split(",");
        System.out.println(str.length);
        System.out.print("insert into "+tableName+"(");
        for (int i = 0; i < str.length; i++) {
            if(i%8==0) {
                System.out.println("");
            }
            if(i==str.length-1) {
                System.out.print(" "+str[i].trim());
            }else {
                System.out.print(" "+str[i].trim()+",");
            }
        }
        System.out.println();
        System.out.print(")values(");
        for (int i = 0; i < str.length; i++) {
            if(i%8==0) {
                System.out.println();
            }
            if(i==str.length-1) {
                System.out.print(" @"+str[i].trim());
            }else {
                System.out.print(" @"+str[i].trim()+",");
            }

        }
        System.out.println();
        System.out.println(")");
    }


    /**
     * 	update字段使用，每四个换一次行
     * @param data
     */
    public static void update(String data,String tableName) {
        String [] str = data.split(",");
        System.out.print("update "+tableName+" set ");
        for (int i = 0; i < str.length; i++) {
            if(i%4==0) {
                System.out.println();
            }
            if(i==str.length-1){
                System.out.print(" "+str[i].trim()+" = @"+str[i].trim());
            }else{
                System.out.print(" "+str[i].trim()+" = @"+str[i].trim()+",");
            }
        }
    }

    /**
     * 	逗号分隔，然后换行
     * @param data
     */
    public static void commaSplitToBr(String data) {
        String [] str = data.split(",");
        for (int i = 0; i < str.length; i++) {
            System.out.println(str[i]);
        }
    }
}
