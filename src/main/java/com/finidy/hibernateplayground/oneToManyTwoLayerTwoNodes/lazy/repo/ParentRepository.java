package com.finidy.hibernateplayground.oneToManyTwoLayerTwoNodes.lazy.repo;

import com.finidy.hibernateplayground.oneToManyTwoLayerTwoNodes.lazy.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ParentRepository extends JpaRepository<Parent, Long>{
    @Query("select p from Parent p join fetch p.childrenA join fetch p.childrenB")
    List<Parent> findAllWithChildren();


    @Query("select p from Parent p " +
            "join fetch p.childrenA ca " +
            "join fetch p.childrenB cb " +
            "join fetch ca.grandChildrenA " +
            "join fetch ca.grandChildrenB " +
            "join fetch cb.grandChildrenC " +
            "join fetch cb.grandChildrenD")
    List<Parent> findAllWithGrandChildren();


    @Query("select p from Parent p join fetch p.childrenA join fetch p.childrenB where p.id = ?1")
    Optional<Parent> findByIdWithChildren(long l);

    @Query("select p from Parent p " +
            "join fetch p.childrenA ca " +
            "join fetch p.childrenB cb " +
            "join fetch ca.grandChildrenA " +
            "join fetch ca.grandChildrenB " +
            "join fetch cb.grandChildrenC " +
            "join fetch cb.grandChildrenD " +
            "where p.id = ?1")
    Optional<Parent> findByIdWithGrandChildren(long l);
}
