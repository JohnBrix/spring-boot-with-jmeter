package com.sample.demo.repository;

import com.sample.demo.entity.UserInfoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <UserInfoModel, Long> {
}
