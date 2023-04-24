package core.concurrency.basic.lifecycle;

/**
 * @author jianchengwang
 * @date 2023/4/24
 */
public class InterruptTest {
    public static void main(String[] args) {
        Thread th = Thread.currentThread();
        while(true) {
            if(th.isInterrupted()) {
                break;
            }
            // 省略业务代码无数
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                // 重新设置中断标志
                th.interrupt();
                e.printStackTrace();
            }
        }
    }
}
