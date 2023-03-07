package com.busecnky.repository;

import com.busecnky.repository.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface IUserRepository extends JpaRepository<UserProfile,Long> {

    Optional<UserProfile> findOptionalByAuthid(Long id);



}
