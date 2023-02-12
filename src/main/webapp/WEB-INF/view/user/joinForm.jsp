<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="shop.mtcoding.blog.model.UserRepository">
   <select id="findAllUser" resultType="shop.mtcoding.blog.model.User">
        select * from user_tb
    </select>

    <select id="findUserById" resultType="shop.mtcoding.blog.model.User">
        select * from user_tb where id = #{id}
    </select>

    <insert id="insertUser">
        insert into user_tb (username, password, email, created_at) values(#{username}, #{password}, #{email}, now())
    </insert>    

    <delete id="deleteUserById" >
        delete from user_tb where id = #{id} 
    </delete>    

    <update id="updateUserById" >
        update user_tb set username= #{username},password= #{password}, email= #{email} where id = #{id} 
    </update>    

</mapper>