package org.example.springjpalevel1.Repo;

import org.example.springjpalevel1.model.TestEntity;
import org.springframework.data.repository.CrudRepository;

public interface TestEntityRepo extends CrudRepository<TestEntity,Integer> {
}
