package cn.bd.bank.server.bank.src.cn.bd.bank.server;

/**
 * 号码机,用于管理产生的号码
 */
public class NumberMachine {
    private NumberManager commonManager = new NumberManager ();
    private NumberManager expressManager = new NumberManager ();
    private NumberManager vipManager = new NumberManager ();
    private static NumberMachine numberMachine = new NumberMachine ();
    /**
     * 构造方法私有化
     */
    private NumberMachine() {
    }

    /**
     * 获取普通窗口 commonManager
     */
    public NumberManager getCommonManager(){
        return commonManager;
    }

    /**
     * 获取快速窗口 expressManager
     */
    public NumberManager getExpressManager() {
        return expressManager;
    }

    /**
     * 获取vip窗口 vipManager
     * @return
     */
    public NumberManager getVipManager() {
        return vipManager;
    }

    /**
     * 生成 NumberMachine实例
     * 单例实例
     */
    public static NumberMachine getInstance(){
        return numberMachine;
    }
}
