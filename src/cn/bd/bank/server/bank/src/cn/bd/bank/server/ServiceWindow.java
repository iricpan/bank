package cn.bd.bank.server.bank.src.cn.bd.bank.server;

import java.util.Random;
import java.util.concurrent.Executors;

/**
 * 服务窗口
 */
public class ServiceWindow {
    //设置默认的窗口编号
    private int serverWinowId = 1;
    //设置默认的窗口类型
    public CustomerType type = CustomerType.COMMON;

    public int getServerWinowId() {
        return serverWinowId;
    }

    public CustomerType getType() {
        return type;
    }

    public void setServerWinowId(int serverWinowId) {
        this.serverWinowId = serverWinowId;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }

    public void start(){
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                while(true){
                    switch (type){
                        case COMMON:
                            commonService();
                            break;
                        case EXPRESS:
                            expressService();
                            break;
                        case VIP:
                            vipService();
                            break;
                    }
                }
            }
        });
    }

    /**
     * Vip客户服务
     */
    private void vipService() {
        String windowName = type+"窗口";
        System.out.println(windowName+"正在获取服务");
        Integer customerNumber = NumberMachine.getInstance().getVipManager().fetchNumber();
        if (customerNumber!=null){
            /**
             * 获取到vip客户,模拟窗口为客户服务的时间
             */
            System.out.println(windowName+"为第"+customerNumber+"号普通客户服务");
            long startTime = System.currentTimeMillis();
            int randomTime = Constants.MAX_SERVICE_TIME- Constants.MIN_SERVICE_TIME + 1 + Constants.MIN_SERVICE_TIME;
            int costTime = new Random().nextInt(randomTime);
            try {
                Thread.sleep(costTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long endTime = System.currentTimeMillis();
            System.out.println(windowName+"为第"+customerNumber+"号"+type+"客户服务,耗时:"+costTime/1000 + "秒,总耗时:"+(endTime-startTime)/1000+"秒");
        }else{
            /**
             * 没有获取到需要服务的vip客户,则获取普通客户
             */
           commonService();
        }
    }

    /**
     * 快速客户服务
     */
    private void expressService() {
        String windowName = type+"窗口";
        System.out.println(windowName+"正在获取服务");
        Integer customerNumber = NumberMachine.getInstance().getExpressManager().fetchNumber();
        if (customerNumber!=null){
            /**
             * 获取到客户,模拟窗口为客户服务的时间
             */
            System.out.println(windowName+"为第"+customerNumber+"号普通客户服务");
            long startTime = System.currentTimeMillis();
            int randomTime = Constants.MIN_SERVICE_TIME;
            int costTime = new Random().nextInt(randomTime);
            try {
                Thread.sleep(costTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long endTime = System.currentTimeMillis();
            System.out.println(windowName+"为第"+customerNumber+"号"+type+"客户服务,耗时:"+costTime/1000 + "秒,总耗时:"+(endTime-startTime)/1000+"秒");
        }else{
            /**
             * 没有获取到需要服务的快速客户,则获取普通客户
             */
            commonService();
        }
    }

    /**
     * 普通客户服务
     */
    private void commonService() {
        String windowName = null;
        if (type== CustomerType.COMMON){
            windowName = "第"+serverWinowId+"号"+type+"窗口";
        }else{
            windowName = type+"窗口";
        }
        System.out.println(windowName+"正在获取服务");
        Integer customerNumber = NumberMachine.getInstance().getCommonManager().fetchNumber();
        if (customerNumber!=null){
            /**
             * 获取到客户,模拟窗口为客户服务的时间
             */
            System.out.println(windowName+"为第"+customerNumber+"号普通客户服务");
            long startTime = System.currentTimeMillis();
            int randomTime = Constants.MAX_SERVICE_TIME- Constants.MIN_SERVICE_TIME + 1 + Constants.MIN_SERVICE_TIME;
            int costTime = new Random().nextInt(randomTime);
            try {
                Thread.sleep(costTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long endTime = System.currentTimeMillis();
            System.out.println(windowName+"为第"+customerNumber+"号普通客户服务,耗时:"+costTime/1000 + "秒,总耗时:"+(endTime-startTime)/1000+"秒");
        }else{
            /**
             * 没有获取到需要服务的客户,休息一秒
             */
            System.out.println(windowName+"没有获取到需要服务的客户,休息一秒");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
