package com.example.lpiloguebe.repository;

import com.example.lpiloguebe.entity.CocktailData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocktailDataRepository extends JpaRepository<CocktailData, Long> {
    CocktailData findByName(String cocktailName);
}
