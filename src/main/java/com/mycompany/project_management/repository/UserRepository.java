package com.mycompany.project_management.repository;


import com.mycompany.project_management.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity,Long> {

}
