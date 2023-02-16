package com.todoapp.todoapp.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {


    List<User> findByType(int type);

    List<User> findByFirstName(String firstName);

    User findByEmailAndPassword(String email, String password);

    @Query(value = "SELECT * FROM app_user t WHERE t.first_name LIKE %?1%", nativeQuery = true)
    List<User> findByTitleAndSortNative(String first_name);


}
