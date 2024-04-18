package com.finidy.hibernateplayground.oneToManyOneLayerTwoNodes.lazy.repo;

import com.finidy.hibernateplayground.oneToManyOneLayerTwoNodes.lazy.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<Parent, Long>{
}
