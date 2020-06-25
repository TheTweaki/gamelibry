package com.example.gamelibry.repositories;

import com.example.gamelibry.model.Creator;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CreatorRepository extends CrudRepository<Creator, Long> {
    Optional<Creator> getFirstByFirstNameAndLastName(String firstName, String lastName);
}