package cn.bd.bank.server;

/**
 * 客户类型
 * 普通客户,快速客户,VIP客户
 */
public enum CustomerType {
    COMMON,EXPRESS,VIP;

    /**
     * 重写toString方法
     */
    @Override
    public String toString() {
       switch (this){
           case COMMON:
               return "普通";
           case EXPRESS:
               return "快速";
           case VIP:
               return name();
       }
       return null;
    }
}
