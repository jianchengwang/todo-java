package todo.springboot2.hello.dao;

import todo.springboot2.hello.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

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
