package info.jianchengwang.todo.springboot2.si.cache.redis.service;

import info.jianchengwang.todo.springboot2.si.cache.redis.entity.User;
import info.jianchengwang.todo.springboot2.si.cache.redis.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wjc
 * @date 2021/1/22
 */
@CacheConfig(cacheNames = "user")
@Slf4j
@Service
public class UserService {

    @Resource
    UserMapper userMapper;

    @Cacheable(cacheNames = {"user"}, key = "#id", condition = "#a0 > 0")
    public User get(Integer id) {
        return userMapper.get(id);
    }

    @CachePut(key = "#result.id")
    public User update(Integer id, User user) {
        user.setId(id);
        userMapper.update(id, user);
        return user;
    }

    @CacheEvict(key = "#id", allEntries = false, beforeInvocation = false)
    public void delete(Integer id) {
    }
}
