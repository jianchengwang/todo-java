package todo.springboot2.hello.dao;

import todo.springboot2.hello.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() throws Exception {


        userRepository.save(new User("aa1", "aa123"));
        userRepository.save(new User("bb2", "bb@234"));
        userRepository.save(new User("cc3", "cc456"));

        userRepository.findAll().forEach(user -> System.out.println(user.getName()));

        Assert.assertEquals(3, userRepository.findAll().size());
        Assert.assertEquals("bb", userRepository.findUserByName("bb").getName());
        userRepository.delete(userRepository.findUserByName("aa1"));
    }

    @Test
    public void testPageQuery() throws Exception {
        int page=1,size=10;
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        userRepository.findAll(pageable).forEach(user -> {System.out.println(user.getName());});
    }

}