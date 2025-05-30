package com.example.lpiloguebe.repository;

import com.example.lpiloguebe.entity.Cocktail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CocktailRepository extends JpaRepository<Cocktail, Long> {
    Optional<Cocktail> findByName(String cocktailName);
}
