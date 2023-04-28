package core.concurrency.design.copyonwrite;

/**
 * @author jianchengwang
 * @date 2023/4/28
 */

//路由信息
public final class Router{
    private final String  ip;
    private final Integer port;
    private final String  iface;
    //构造函数
    public Router(String ip,
                  Integer port, String iface){
        this.ip = ip;
        this.port = port;
        this.iface = iface;
    }

    public String getIp() {
        return ip;
    }

    public Integer getPort() {
        return port;
    }

    public String getIface() {
        return iface;
    }

    //重写equals方法
    public boolean equals(Object obj){
        if (obj instanceof Router) {
            Router r = (Router)obj;
            return iface.equals(r.iface) &&
                    ip.equals(r.ip) &&
                    port.equals(r.port);
        }
        return false;
    }


}
