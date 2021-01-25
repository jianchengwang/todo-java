package info.jianchengwang.todo.springboot2.si.cache.redis.controller;

import info.jianchengwang.todo.springboot2.si.cache.redis.entity.User;
import info.jianchengwang.todo.springboot2.si.cache.redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wjc
 * @date 2021/1/22
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("{id}")
    public User get(@PathVariable Integer id) {
        return userService.get(id);
    }

    @PutMapping("{id}")
    public User update(@PathVariable Integer id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @DeleteMapping("{id}")
    public User delete(@PathVariable Integer id) {
        userService.delete(id);
        return userService.get(id);
    }
}
