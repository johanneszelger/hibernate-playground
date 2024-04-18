package com.finidy.hibernateplayground.oneToManyTwoLayerTwoNodes.eagerlazy.repo;

import com.finidy.hibernateplayground.oneToManyTwoLayerTwoNodes.eagerlazy.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<Parent, Long>{
}
