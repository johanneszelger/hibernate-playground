package com.finidy.hibernateplayground.oneToManyTwoLayerTwoNodes.lazy;

import com.finidy.hibernateplayground.AbstractTest;
import com.finidy.hibernateplayground.DatasourceWrapperConfiguration;
import com.finidy.hibernateplayground.FlywayConfiguration;
import com.finidy.hibernateplayground.oneToManyTwoLayerTwoNodes.lazy.model.ChildA;
import com.finidy.hibernateplayground.oneToManyTwoLayerTwoNodes.lazy.model.ChildB;
import com.finidy.hibernateplayground.oneToManyTwoLayerTwoNodes.lazy.model.Parent;
import com.finidy.hibernateplayground.oneToManyTwoLayerTwoNodes.lazy.repo.CustomParentRepository;
import com.finidy.hibernateplayground.oneToManyTwoLayerTwoNodes.lazy.repo.ParentRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.vladmihalcea.sql.SQLStatementCountValidator.assertSelectCount;


@SpringBootTest(classes = {FlywayConfiguration.class, DatasourceWrapperConfiguration.class, PackageConfiguration.class})
public class LazyAllCustom extends AbstractTest {
    @Autowired
    private CustomParentRepository parentRepository;
    @Autowired
    private ParentRepository parentRepositoryNonCustom;

    @Test
    void loadAll() {
        long start = System.currentTimeMillis();
        List<Parent> parents = parentRepository.findAll();
        System.out.println("Time taken: " + (System.currentTimeMillis() - start) + "ms");
        assertSelectCount(1);

        parentRepositoryNonCustom.saveAll(parents);
    }

    @Test
    @Transactional
    void loadAllWithChildren() {
        long start = System.currentTimeMillis();
        List<Parent> parents = parentRepository.findAllWithChildren();
        for (Parent parent : parents) {
            parent.getChildrenA().isEmpty();
            parent.getChildrenB().isEmpty();
        }
        System.out.println("Time taken: " + (System.currentTimeMillis() - start) + "ms");
        assertSelectCount(1 + 2);

        saveModifications(parents);
    }

    @Test
    @Transactional
    void loadAllWithChildrenAndGrandChildren() {
        long start = System.currentTimeMillis();
        List<Parent> parents = parentRepository.findAllWithGrandChildren();
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

        saveModifications(parents);
    }


    private void saveModifications(List<Parent> parents) {
        for (Parent parent : parents) {
            parent.setName("new name");
            for (ChildA childA : parent.getChildrenA())
                childA.setName("new name");
            for (ChildB childB : parent.getChildrenB())
                childB.setName("new name");
        }
        parentRepositoryNonCustom.saveAll(parents);
        parentRepositoryNonCustom.flush();

        for (Parent parent : parents) {
            parent.setName("parent " +  parent.getId());
            for (ChildA childA : parent.getChildrenA())
                childA.setName("child " +  childA.getId());
            for (ChildB childB : parent.getChildrenB())
                childB.setName("child" +  childB.getId());
        }
        parentRepositoryNonCustom.saveAll(parents);
        parentRepositoryNonCustom.flush();
    }

}
