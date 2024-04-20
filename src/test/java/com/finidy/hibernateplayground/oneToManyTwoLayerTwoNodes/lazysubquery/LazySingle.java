package com.finidy.hibernateplayground.oneToManyTwoLayerTwoNodes.lazysubquery;

import com.finidy.hibernateplayground.AbstractTest;
import com.finidy.hibernateplayground.DatasourceWrapperConfiguration;
import com.finidy.hibernateplayground.FlywayConfiguration;
import com.finidy.hibernateplayground.oneToManyTwoLayerTwoNodes.lazysubquery.model.ChildA;
import com.finidy.hibernateplayground.oneToManyTwoLayerTwoNodes.lazysubquery.model.ChildB;
import com.finidy.hibernateplayground.oneToManyTwoLayerTwoNodes.lazysubquery.model.Parent;
import com.finidy.hibernateplayground.oneToManyTwoLayerTwoNodes.lazysubquery.repo.ParentRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.vladmihalcea.sql.SQLStatementCountValidator.assertSelectCount;

@SpringBootTest(classes = {FlywayConfiguration.class, DatasourceWrapperConfiguration.class, PackageConfiguration.class})
public class LazySingle extends AbstractTest {
    @Autowired
    private ParentRepository parentRepository;

    @Test
    @Transactional
    void loadSingle(){
        long start = System.currentTimeMillis();
        Parent parent = parentRepository.findById(1L).get();
        System.out.println("Time taken: " + (System.currentTimeMillis() - start) + "ms");
        assertSelectCount(1);
    }

    @Test
    @Transactional
    void loadSingleWithChildren(){
        long start = System.currentTimeMillis();
        Parent parent = parentRepository.findById(1L).get();
        parent.getChildrenA().isEmpty();
        parent.getChildrenB().isEmpty();
        System.out.println("Time taken: " + (System.currentTimeMillis() - start) + "ms");
        assertSelectCount(1 + 2);
    }

    @Test
    @Transactional
    void loadSingleWithChildrenAndGrandChildren(){
        long start = System.currentTimeMillis();
        Parent parent = parentRepository.findById(1L).get();
        for (ChildA childA : parent.getChildrenA()) {
            childA.getGrandChildrenA().isEmpty();
            childA.getGrandChildrenB().isEmpty();
        }
        for (ChildB childB : parent.getChildrenB()) {
            childB.getGrandChildrenC().isEmpty();
            childB.getGrandChildrenD().isEmpty();
        };
        System.out.println("Time taken: " + (System.currentTimeMillis() - start) + "ms");
        assertSelectCount(1 + 2 + 4);
    }
}
