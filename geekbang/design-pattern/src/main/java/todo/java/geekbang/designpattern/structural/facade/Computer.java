package todo.java.geekbang.designpattern.structural.facade;

/**
 * @author jianchengwang
 * @date 2023/6/15
 */
public class Computer {
    private CPU cpu;
    private Memory memory;
    private HardDrive hardDrive;

    public Computer() {
        this.cpu = new CPUImpl();
        this.memory = new MemoryImpl();
        this.hardDrive = new HardDriveImpl();
    }

    public void startComputer() {
        System.out.println("Starting computer...");
        cpu.start();
        memory.start();
        hardDrive.start();
        System.out.println("Computer started.");
    }

    public void shutdownComputer() {
        System.out.println("Shutting down computer...");
        cpu.shutdown();
        memory.shutdown();
        hardDrive.shutdown();
        System.out.println("Computer shutdown.");
    }
}
