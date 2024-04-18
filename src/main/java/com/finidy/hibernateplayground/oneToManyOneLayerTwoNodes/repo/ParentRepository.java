package com.finidy.hibernateplayground.oneToManyOneLayerTwoNodes.repo;

import com.finidy.hibernateplayground.oneToManyOneLayerTwoNodes.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<Parent, Long>{
}
