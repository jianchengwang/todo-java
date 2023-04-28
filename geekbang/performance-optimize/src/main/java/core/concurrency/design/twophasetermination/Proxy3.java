package core.concurrency.design.twophasetermination;

/**
 * @author jianchengwang
 * @date 2023/4/28
 */

class Proxy3 {
    //线程终止标志位
    volatile boolean terminated = false; //stop和start方法对于terminated访问由于syn关键字，线程安全，但是start中新起了一个线程rptthread，导致stop方法中对于terminated存在可见性问题，因此需要volatie，原子性问题对这个代码段没有影响，所以原子性问题无需关注。
    boolean started = false;
    //采集线程
    Thread rptThread;
    //启动采集功能
    synchronized void start(){
        //不允许同时启动多个采集线程
        if (started) {
            return;
        }
        started = true;
        terminated = false;
        rptThread = new Thread(()->{
            while (!terminated){
                //省略采集、回传实现
                // report();
                //每隔两秒钟采集、回传一次数据
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e){
                    //重新设置线程中断状态
                    Thread.currentThread().interrupt();
                }
            }
            //执行到此处说明线程马上终止
            started = false;
        });
        rptThread.start();
    }
    //终止采集功能
    synchronized void stop(){
        //设置中断标志位
        terminated = true;
        //中断线程rptThread
        rptThread.interrupt();
    }
}
