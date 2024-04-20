package com.finidy.hibernateplayground.oneToManyTwoLayerTwoNodes.lazysubquery.repo;

import com.finidy.hibernateplayground.oneToManyTwoLayerTwoNodes.lazysubquery.model.Parent;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomParentRepository {
    List<Parent> findAll();

    List<Parent> findAllWithChildren();

    List<Parent> findAllWithGrandChildren();
}
