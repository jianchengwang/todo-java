package design.workerthread;

/**
 * @author jianchengwang
 * @date 2023/4/20
 */
public class Test {
    public static void main(String[] args) {
        //新建8个工人
        final PackageChannel channel = new PackageChannel(8);
        //开始工作
        channel.startWorker();
        //为流水线添加包裹
        for(int i=0; i<100; i++) {
            Package packagereq = new Package();
            packagereq.setAddress("test");
            packagereq.setName("test");
            channel.put(packagereq);
        }
    }
}
