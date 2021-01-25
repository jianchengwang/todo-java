package info.jianchengwang.todo.springboot2.si.cache.redis.mapper;

import info.jianchengwang.todo.springboot2.si.cache.redis.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Select("select * from t_user where id = #{id}")
    User get(Integer id);
    @Update("update t_user set name = #{user.name} where id = #{id}")
    void update(Integer id, User user);
}
