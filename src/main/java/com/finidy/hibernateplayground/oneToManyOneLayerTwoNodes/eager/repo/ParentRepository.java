package com.finidy.hibernateplayground.oneToManyOneLayerTwoNodes.eager.repo;

import com.finidy.hibernateplayground.oneToManyOneLayerTwoNodes.eager.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<Parent, Long>{
}
