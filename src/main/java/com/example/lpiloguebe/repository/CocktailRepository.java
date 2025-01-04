package com.example.lpiloguebe.repository;

import com.example.lpiloguebe.entity.Cocktail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocktailRepository extends JpaRepository<Cocktail, Long> {

}
