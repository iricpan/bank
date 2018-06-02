package cn.bd.bank.server;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 主程序运行
 */
public class MainService {
    public static void main(String[] args) {
        /**
         * 4个普通窗口
         */
        for (int i = 1; i <= 4; i++){
            ServiceWindow commonServiceWindow = new ServiceWindow();
            commonServiceWindow.setServerWinowId(i);
            commonServiceWindow.setType(CustomerType.COMMON);
            commonServiceWindow.start();
        }

        /**
         * 5号快速窗口
         */
        ServiceWindow expressServiceWindow = new ServiceWindow();
//        expressServiceWindow.setServerWinowId(5);
        expressServiceWindow.setType(CustomerType.EXPRESS);
        expressServiceWindow.start();

        /**
         * 6号Vip窗口
         */
        ServiceWindow vipServiceWindow = new ServiceWindow();
//        vipServiceWindow.setServerWinowId(6);
        vipServiceWindow.setType(CustomerType.VIP);
        vipServiceWindow.start();


        /**
         * 产生快速客户
         */
        Executors.newScheduledThreadPool(1).scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                Integer waitNumber = NumberMachine.getInstance().getExpressManager().generateNumber();
                System.out.println("第"+waitNumber + "号快速客户正在等待服务");
            }
        },
        0,
        Constants.COMMON_CUTOMER_COMING_INTERVAL*2,
        TimeUnit.SECONDS
        );

        /**
         * 产生vip客户
         */
        Executors.newScheduledThreadPool(1).scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                Integer waitNumber = NumberMachine.getInstance().getVipManager().generateNumber();
                System.out.println("第"+waitNumber + "号vip客户正在等待服务");
            }
        },
        0,
        Constants.COMMON_CUTOMER_COMING_INTERVAL*6,
        TimeUnit.SECONDS
        );
        /**
         * 产生普通客户
         */
        Executors.newScheduledThreadPool(1).scheduleWithFixedDelay(new Runnable() {
           @Override
           public void run() {
               Integer waitNumber = NumberMachine.getInstance().getCommonManager().generateNumber();
               System.out.println("第"+waitNumber + "号普通客户正在等待服务");
           }
       },
        0,
        Constants.COMMON_CUTOMER_COMING_INTERVAL,
        TimeUnit.SECONDS
        );
    }
}
