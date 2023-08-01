package todo.java.geekbang.designpattern.structural.composite;

import java.util.List;

public interface EmployeeRepo {
    List<Long> getDepartmentEmployeeIds(long id);

    double getEmployeeSalary(Long employeeId);
}
