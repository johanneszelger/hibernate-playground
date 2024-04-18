package com.finidy.hibernateplayground.oneToManyOneLayerTwoNodes.eagerlazy.repo;

import com.finidy.hibernateplayground.oneToManyOneLayerTwoNodes.eagerlazy.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<Parent, Long>{
}
