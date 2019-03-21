package cn.jianchengwang.todo.java8;

import java.util.Optional;

// Java .util. optional 可以有效的防止 NullPointerException，一般在返回值可以为空的时候建议使用
public class OptionalExample {

    static class Company {

    }

    public static void main(String[] args) {

        Optional<Integer> canBeEmpty1 = Optional.of(5); //
        canBeEmpty1.isPresent();                    // returns true
        canBeEmpty1.get();                          // returns 5

        Optional<Integer> canBeEmpty2 = Optional.empty();
        canBeEmpty2.isPresent(); // returns false

        Optional<Integer> possible1 = Optional.ofNullable(null);
        Optional<Integer> possible2 = Optional.ofNullable(5);

        Optional<Integer> possible = Optional.of(5);
        possible.ifPresent(System.out::println);

        Optional<Company> companyOptional = Optional.empty();
        Company company1 = companyOptional.orElse(new Company());
        Company company2 = companyOptional.orElseThrow(IllegalStateException::new);
    }
}
