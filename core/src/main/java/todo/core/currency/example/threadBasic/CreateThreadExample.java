package todo.core.currency.example.threadBasic;

import java.util.concurrent.*;

public class CreateThreadExample {

    public static void main(String[] args) {

        System.out.println("main:" + Thread.currentThread().getName());

        // 1.extend Thread
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                System.out.println("thread1:" + Thread.currentThread().getName());
                super.run();
            }
        };
        thread1.start();

        // 2.Runnable interface
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.currentThread().sleep(1000);
                    System.out.println("thread2:" + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread2.start();

        // 3.Callable interface
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<String> future = service.submit(new Callable() {
            @Override
            public String call() throws Exception {
                return "thread3:" + Thread.currentThread().getName();
            }
        });
        try {
            String result = future.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
