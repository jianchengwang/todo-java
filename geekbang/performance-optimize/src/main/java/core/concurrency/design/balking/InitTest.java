package core.concurrency.design.balking;

/**
 * @author jianchengwang
 * @date 2023/4/28
 */

public class InitTest{
    boolean inited = false;
    synchronized void init(){
        if(inited){
            return;
        }
        //省略doInit的实现
       // doInit();
        inited=true;
    }
}
