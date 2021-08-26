package com.kelaskoding.repo;

import com.kelaskoding.entity.UserDetail;

import org.springframework.data.repository.CrudRepository;

public interface UserDetailRepo extends CrudRepository<UserDetail, Long> {

}
