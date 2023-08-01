package todo.java.geekbang.designpattern.structural.composite;

import java.util.List;

public interface DepartmentRepo {
    List<Long> getSubDepartmentIds(long id);
}
