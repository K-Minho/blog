package shop.mtcoding.blog.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRepository {
      public List<User> findAllUser();

      public User findByUsername(@Param("username") String username);

      public User findUserById(int id);

      public int insertUser(@Param("username") String username, @Param("password") String password,
                  @Param("email") String email);

      public int updateUserById(@Param("id") int id, @Param("username") String username,
                  @Param("password") String password,
                  @Param("email") String email);

      public int deleteUserById(int id);
}