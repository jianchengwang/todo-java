package todo.core.source.java.util.hashmap;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

public class Test {
    public static void main(String[] args) {
        MyHashMap map = new MyHashMap<User, String>();

        for(int i=0; i<32; i++) {
            char a = (char) ('a' + i);
            for(int j=0; j<2; j++) {
                User user = new User(a + ":" + j);
                map.put(user, user.getName());
            }
        }

        System.out.println(map.get(new User("a:0")));
        System.out.println(map.get(new User("a:1")));
        System.out.println(map.get(new User("a:7")));
        System.out.println(map.get(new User("f:0")));
    }

    @Data
    @AllArgsConstructor
    static
    class User {
        private String name;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name.substring(0, 1));
        }
    }
}
