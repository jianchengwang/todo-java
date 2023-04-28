package core.concurrency.juc.feature;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author jianchengwang
 * @date 2023/4/28
 */
public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executor
                = Executors.newFixedThreadPool(1);
// 创建Result对象r
        Result r = new Result();
        r.setAAA(1);
// 提交任务
        Future<Result> future =
                executor.submit(new Task(r), r);
        Result fr = future.get();
// 下面等式成立
        boolean b1 = fr.getAAA() == 1;
        boolean b = fr.getXXX() == 2;

        System.out.println(b1);
        System.out.println(b);
        executor.shutdown();
    }

    static class Task implements Runnable{
        Result r;
        //通过构造函数传入result
        Task(Result r){
            this.r = r;
        }
        public void run() {
            //可以操作result
            var a = r.getAAA();
            r.setXXX(2);
        }
    }

    static class Result {
        private int AAA;
        private int XXX;
        public int getAAA() {
            return AAA;
        }
        public void setAAA(int aAA) {
            AAA = aAA;
        }
        public int getXXX() {
            return XXX;
        }
        public void setXXX(int xXX) {
            XXX = xXX;
        }
    }
}
