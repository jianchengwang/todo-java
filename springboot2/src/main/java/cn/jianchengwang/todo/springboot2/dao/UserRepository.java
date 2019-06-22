package cn.jianchengwang.todo.springboot2.dao;

import cn.jianchengwang.todo.springboot2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(Long id);
    User findUserByName(String name);

//    @Modifying
//    @Query("update user u set u.name = ?1 where u.id = ?2")
//    int modifyByIdAndUserId(String  name, Long id);
//
//    @Transactional
//    @Modifying
//    @Query("delete from user where id = ?1")
//    void deleteByUserId(Long id);


}
