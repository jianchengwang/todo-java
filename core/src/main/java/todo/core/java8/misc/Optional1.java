package todo.core.java8.misc;

import lombok.Data;

import java.util.Optional;

// Java .util. optional 可以有效的防止 NullPointerException，一般在返回值可以为空的时候建议使用
public class Optional1 {

    @Data
    static class Company {
        private Address address;
    }

    @Data
    static class Address {
        private String road;
        private String door;

        public Address() {
        }

        public Address(String road, String door) {
            this.road = road;
            this.door = door;
        }
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

        // before java8
        Company company = new Company();company.setAddress(new Address("达尔文街道", "123"));
        if(null != company) {
            if(null != company.getAddress()) {
                // do something
            }
        }
        if(null == company) return;
        if(null == company.getAddress()) return;

        // 声明一个空的Optional
        Optional<Company> optional1 = Optional.empty();
        // 依据一个非空值创建Optional
        Company company1 = optional1.orElse(new Company());
        // 可接受null的Optional
        Optional<Company> optionalAddress3 = Optional.ofNullable(new Company());
        // 如果空抛异常
//        Company company2 = optional1.orElseThrow(IllegalStateException::new);

        // 使用 map 从 Optional 对象中提取和转换值
        Optional<Address> optional3 = Optional.ofNullable(company).map(Company::getAddress);
        System.out.println(optional3.get());

    }
}
