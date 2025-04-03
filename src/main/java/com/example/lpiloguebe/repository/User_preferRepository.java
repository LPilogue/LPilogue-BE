package com.example.lpiloguebe.repository;

import com.example.lpiloguebe.entity.User_prefer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface User_preferRepository extends JpaRepository<User_prefer, Long> {
}
