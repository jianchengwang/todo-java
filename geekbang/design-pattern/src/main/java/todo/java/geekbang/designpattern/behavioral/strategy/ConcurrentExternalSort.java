package todo.java.geekbang.designpattern.behavioral.strategy;

/**
 * @author jianchengwang
 * @date 2023/8/1
 */
public class ConcurrentExternalSort implements ISortAlg {

    @Override
    public void sort(String filePath) {
        System.out.println("ConcurrentExternalSort");
    }
}