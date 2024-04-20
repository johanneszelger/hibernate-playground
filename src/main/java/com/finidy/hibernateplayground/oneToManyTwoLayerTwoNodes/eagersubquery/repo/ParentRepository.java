package com.finidy.hibernateplayground.oneToManyTwoLayerTwoNodes.eagersubquery.repo;

import com.finidy.hibernateplayground.oneToManyTwoLayerTwoNodes.eagersubquery.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<Parent, Long>{
}
