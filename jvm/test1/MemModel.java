public class MemModel {
    static int x = 0;
    static int y = 0;
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                x = 1;
                y = 1;
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (y == 0);
                if (x != 1) {
                    System.out.println("Error!");
                }
            }
        });
        t2.start();
        t1.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}