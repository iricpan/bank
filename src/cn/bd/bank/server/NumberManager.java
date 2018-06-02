package cn.bd.bank.server;

import java.util.ArrayList;
import java.util.List;

/**
 * 号码管理器,用于产生服务的号码
 */
public class NumberManager {
    //产生的号码,默认从1开始
    private Integer number = 1;

    //将产生的号码放入集合
   private List<Integer> numberList = new ArrayList<>();

   //产生号码,并放入集合
   public synchronized Integer generateNumber(){
       numberList.add(number);
       return number++;
   }

   //获取号码
   public synchronized Integer fetchNumber(){
       if (numberList.size()>0)
           return numberList.remove(0);
       return null;
   }
}
