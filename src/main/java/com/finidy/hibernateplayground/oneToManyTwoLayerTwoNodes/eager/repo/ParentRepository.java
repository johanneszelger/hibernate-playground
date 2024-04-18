package com.finidy.hibernateplayground.oneToManyTwoLayerTwoNodes.eager.repo;

import com.finidy.hibernateplayground.oneToManyTwoLayerTwoNodes.eager.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<Parent, Long>{
}
