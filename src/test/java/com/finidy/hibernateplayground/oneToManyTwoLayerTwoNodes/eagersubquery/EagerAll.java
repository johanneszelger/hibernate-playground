package com.finidy.hibernateplayground.oneToManyTwoLayerTwoNodes.eagersubquery;

import com.finidy.hibernateplayground.AbstractTest;
import com.finidy.hibernateplayground.DatasourceWrapperConfiguration;
import com.finidy.hibernateplayground.FlywayConfiguration;
import com.finidy.hibernateplayground.oneToManyTwoLayerTwoNodes.eagersubquery.model.ChildA;
import com.finidy.hibernateplayground.oneToManyTwoLayerTwoNodes.eagersubquery.model.ChildB;
import com.finidy.hibernateplayground.oneToManyTwoLayerTwoNodes.eagersubquery.model.Parent;
import com.finidy.hibernateplayground.oneToManyTwoLayerTwoNodes.eagersubquery.repo.ParentRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.vladmihalcea.sql.SQLStatementCountValidator.assertSelectCount;

@SpringBootTest(classes = {FlywayConfiguration.class, DatasourceWrapperConfiguration.class, PackageConfiguration.class})
public class EagerAll extends AbstractTest {
    @Autowired
    private ParentRepository parentRepository;


    @Test
    void loadAll() {
        long start = System.currentTimeMillis();
        List<Parent> parents = parentRepository.findAll();
        System.out.println("Time taken: " + (System.currentTimeMillis() - start) + "ms");
        assertSelectCount(1 + 2 + 4);
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
        assertSelectCount(1 + 2 + 4);
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
        assertSelectCount(1 + 2 + 4);
    }
}
