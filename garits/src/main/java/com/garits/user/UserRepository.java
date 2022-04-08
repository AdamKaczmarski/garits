package com.garits.user;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface UserRepository extends CrudRepository<User, Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO users (email,password,salt,first_name,last_name) VALUES (?1,?2,?3,?4,?5)",nativeQuery = true)
    Integer addUser(String email, String password, String salt, String firstName, String lastName);
    @Query(value = "select id_user from users where email=?1",nativeQuery = true)
    Integer getUserId(String email);
    @Modifying
    @Transactional
    @Query(value="INSERT INTO users_roles(user_id,role_id) VALUES (?1,(SELECT id_role FROM roles where role_name=?2))",nativeQuery = true)
    void addUserRole(Integer userId, String role);

    @Modifying
    @Transactional
    @Query(value="UPDATE users_roles set role_id =(SELECT id_role FROM roles where role_name=?2) where user_id=?1",nativeQuery = true)
    void changeUserRole(Integer userId, String role);
    @Query(value="SELECT * from users where id_user IN (SELECT user_id FROM users_roles WHERE role_id=(SELECT id_role FROM roles where role_name='MECHANIC'))",nativeQuery = true)
    Iterable<User> findAllMechanics();
}
