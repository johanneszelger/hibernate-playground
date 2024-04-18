package com.finidy.hibernateplayground.oneToManyTwoLayerTwoNodes.lazyeager.repo;

import com.finidy.hibernateplayground.oneToManyTwoLayerTwoNodes.lazyeager.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<Parent, Long>{
}
