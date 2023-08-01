package todo.java.geekbang.designpattern.structural.flyweight;

/**
 * @author jianchengwang
 * @date 2023/6/15
 */
import java.util.HashMap;
import java.util.Map;

public class TreeFactory {
    private static Map<String, Tree> treeMap = new HashMap<>();

    public static Tree getTree(String type) {
        Tree tree = treeMap.get(type);

        if (tree == null) {
            tree = new TreeImpl(type);
            treeMap.put(type, tree);
        }

        return tree;
    }
}

