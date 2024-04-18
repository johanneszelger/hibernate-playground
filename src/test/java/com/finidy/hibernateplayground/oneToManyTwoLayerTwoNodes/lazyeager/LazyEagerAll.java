package com.finidy.hibernateplayground.oneToManyTwoLayerTwoNodes.lazyeager;

import com.finidy.hibernateplayground.AbstractTest;
import com.finidy.hibernateplayground.DatasourceWrapperConfiguration;
import com.finidy.hibernateplayground.FlywayConfiguration;
import com.finidy.hibernateplayground.oneToManyTwoLayerTwoNodes.lazyeager.model.ChildA;
import com.finidy.hibernateplayground.oneToManyTwoLayerTwoNodes.lazyeager.model.ChildB;
import com.finidy.hibernateplayground.oneToManyTwoLayerTwoNodes.lazyeager.model.Parent;
import com.finidy.hibernateplayground.oneToManyTwoLayerTwoNodes.lazyeager.repo.ParentRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.vladmihalcea.sql.SQLStatementCountValidator.assertSelectCount;


@SpringBootTest(classes = {FlywayConfiguration.class, DatasourceWrapperConfiguration.class, PackageConfiguration.class})
public class LazyEagerAll extends AbstractTest {
    @Autowired
    private ParentRepository parentRepository;

    @Test
    void loadAll() {
        long start = System.currentTimeMillis();
        parentRepository.findAll();
        System.out.println("Time taken: " + (System.currentTimeMillis() - start) + "ms");
        assertSelectCount(1);
    }

    @Test
    @Transactional
    void loadAllWithChildren() {
        long start = System.currentTimeMillis();
        List<Parent> parents = parentRepository.findAll();
        for (Parent parent : parents) {
            parent.getChildrenA().isEmpty();
            parent.getChildrenB().isEmpty();
        }
        System.out.println("Time taken: " + (System.currentTimeMillis() - start) + "ms");
        assertSelectCount(parents.size() * 2 + 1);
    }

    @Test
    @Transactional
    void loadAllWithChildrenAndGrandChildren() {
        long start = System.currentTimeMillis();
        List<Parent> parents = parentRepository.findAll();
        for (Parent parent : parents) {
            for (ChildA childA : parent.getChildrenA()) {
                childA.getGrandChildrenA().isEmpty();
                childA.getGrandChildrenB().isEmpty();
            }
            for (ChildB childB : parent.getChildrenB()) {
                childB.getGrandChildrenC().isEmpty();
                childB.getGrandChildrenD().isEmpty();
            };
        }
        System.out.println("Time taken: " + (System.currentTimeMillis() - start) + "ms");
        assertSelectCount(parents.size() * 2 + 1);
    }
}
