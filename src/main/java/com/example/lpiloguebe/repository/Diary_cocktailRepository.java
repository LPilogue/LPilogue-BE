package com.example.lpiloguebe.repository;

import com.example.lpiloguebe.entity.Cocktail;
import com.example.lpiloguebe.entity.Diary_cocktail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Diary_cocktailRepository extends JpaRepository<Diary_cocktail, Long> {

}
