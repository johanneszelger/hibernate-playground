package com.finidy.hibernateplayground.oneToManyTwoLayerTwoNodes.lazy.repo;

import com.finidy.hibernateplayground.oneToManyTwoLayerTwoNodes.lazy.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class CustomParentRepositoryImpl implements CustomParentRepository {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public List<Parent> findAll() {
        EntityManager em = entityManagerFactory.createEntityManager();

        List<Parent> parents = em.createQuery(
                "select p from Parent p",
                        Parent.class).getResultList();

        em.close();
        return parents;
    }

    @Override
    public List<Parent> findAllWithChildren() {
        EntityManager em = entityManagerFactory.createEntityManager();

        List<Parent> parents = em.createQuery(
                "select p from Parent p",
                Parent.class).getResultList();

        loadChildrenA(em, parents);
        loadChildrenB(em, parents);

        em.close();
        return parents;
    }

    @Override
    public List<Parent> findAllWithGrandChildren() {
        EntityManager em = entityManagerFactory.createEntityManager();

        List<Parent> parents = em.createQuery(
                "select p from Parent p",
                Parent.class).getResultList();

        loadChildrenA(em, parents);
        loadChildrenB(em, parents);
        loadGrandChildrenA(em, parents.stream().flatMap(p -> p.getChildrenA().stream()).collect(Collectors.toList()));
        loadGrandChildrenB(em, parents.stream().flatMap(p -> p.getChildrenA().stream()).collect(Collectors.toList()));
        loadGrandChildrenC(em, parents.stream().flatMap(p -> p.getChildrenB().stream()).collect(Collectors.toList()));
        loadGrandChildrenD(em, parents.stream().flatMap(p -> p.getChildrenB().stream()).collect(Collectors.toList()));

        em.close();
        return parents;
    }

    private void loadChildrenA(EntityManager em, List<Parent> parents) {
        Query childAQuery = em.createQuery("select ca.parent.id as parentId, ca from ChildA ca where ca.parent in :parents",
                Tuple.class);
        childAQuery.setParameter("parents", parents);
        Map<Long, Set<ChildA>> childrenA = (Map<Long, Set<ChildA>>) childAQuery.getResultList().stream()
                .collect(Collectors.groupingBy(
                        tuple -> ((Tuple) tuple).get(0),
                        Collectors.mapping(tuple -> ((Tuple) tuple).get(1),
                                Collectors.toSet())
                ));

        for (Parent parent : parents) {
            parent.setChildrenA(childrenA.get(parent.getId()));
        }
    }

    private void loadChildrenB(EntityManager em, List<Parent> parents) {
        Query childBQuery = em.createQuery("select cb.parent.id as parentId, cb from ChildB cb where cb.parent in :parents",
                Tuple.class);
        childBQuery.setParameter("parents", parents);
        Map<Long, Set<ChildB>> childrenB = (Map<Long, Set<ChildB>>) childBQuery.getResultList().stream()
                .collect(Collectors.groupingBy(
                        tuple -> ((Tuple) tuple).get(0),
                        Collectors.mapping(tuple -> ((Tuple) tuple).get(1),
                                Collectors.toSet())
                ));

        for (Parent parent : parents) {
            parent.setChildrenB(childrenB.get(parent.getId()));
        }
    }

    private void loadGrandChildrenA(EntityManager em, List<ChildA> children) {
        Query grandChildAQuery = em.createQuery("select gca.childA.id as childAId, gca from GrandChildA gca where gca.childA in :children",
                Tuple.class);
        grandChildAQuery.setParameter("children", children);
        Map<Long, Set<GrandChildA>> grandChildrenA = (Map<Long, Set<GrandChildA>>) grandChildAQuery.getResultList().stream()
                .collect(Collectors.groupingBy(
                        tuple -> ((Tuple) tuple).get(0),
                        Collectors.mapping(tuple -> ((Tuple) tuple).get(1),
                                Collectors.toSet())
                ));

        for (ChildA child : children) {
            child.setGrandChildrenA(grandChildrenA.get(child.getId()));
        }
    }

    private void loadGrandChildrenB(EntityManager em, List<ChildA> children) {
        Query grandChildBQuery = em.createQuery("select gcb.childA.id as childAId, gcb from GrandChildB gcb where gcb.childA in :children",
                Tuple.class);
        grandChildBQuery.setParameter("children", children);
        Map<Long, Set<GrandChildB>> grandChildrenB = (Map<Long, Set<GrandChildB>>) grandChildBQuery.getResultList().stream()
                .collect(Collectors.groupingBy(
                        tuple -> ((Tuple) tuple).get(0),
                        Collectors.mapping(tuple -> ((Tuple) tuple).get(1),
                                Collectors.toSet())
                ));

        for (ChildA child : children) {
            child.setGrandChildrenB(grandChildrenB.get(child.getId()));
        }
    }

    private void loadGrandChildrenC(EntityManager em, List<ChildB> children) {
        Query grandChildCQuery = em.createQuery("select gcc.childB.id as childBId, gcc from GrandChildC gcc where gcc.childB in :children",
                Tuple.class);
        grandChildCQuery.setParameter("children", children);
        Map<Long, Set<GrandChildC>> grandChildrenC = (Map<Long, Set<GrandChildC>>) grandChildCQuery.getResultList().stream()
                .collect(Collectors.groupingBy(
                        tuple -> ((Tuple) tuple).get(0),
                        Collectors.mapping(tuple -> ((Tuple) tuple).get(1),
                                Collectors.toSet())
                ));

        for (ChildB child : children) {
            child.setGrandChildrenC(grandChildrenC.get(child.getId()));
        }
    }

    private void loadGrandChildrenD(EntityManager em, List<ChildB> children) {
        Query grandChildDQuery = em.createQuery("select gcd.childB.id as childBId, gcd from GrandChildD gcd where gcd.childB in :children",
                Tuple.class);
        grandChildDQuery.setParameter("children", children);
        Map<Long, Set<GrandChildD>> grandChildrenD = (Map<Long, Set<GrandChildD>>) grandChildDQuery.getResultList().stream()
                .collect(Collectors.groupingBy(
                        tuple -> ((Tuple) tuple).get(0),
                        Collectors.mapping(tuple -> ((Tuple) tuple).get(1),
                                Collectors.toSet())
                ));

        for (ChildB child : children) {
            child.setGrandChildrenD(grandChildrenD.get(child.getId()));
        }
    }
}
