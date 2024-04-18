package com.finidy.hibernateplayground.oneToManyTwoLayerTwoNodes.lazy.repo;

import com.finidy.hibernateplayground.oneToManyTwoLayerTwoNodes.lazy.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<Parent, Long>{
}
