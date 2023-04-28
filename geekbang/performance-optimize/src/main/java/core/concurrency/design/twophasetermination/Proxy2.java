package core.concurrency.design.twophasetermination;

/**
 * @author jianchengwang
 * @date 2023/4/28
 */

class Proxy2 {
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
        rptThread = new Thread(()->{
            while (!Thread.currentThread().isInterrupted()){
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
        rptThread.interrupt();
    }
}
