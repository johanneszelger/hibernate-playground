package com.finidy.hibernateplayground.oneToManyTwoLayerTwoNodes.lazysubquery.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class Parent {
    @Id
    @SequenceGenerator(name = "parent_seq", sequenceName = "parent_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parent_seq")
    private Long id;

    @Column
    private String name;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ChildA> childrenA;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ChildB> childrenB;

    @Override
    public String toString() {
        return "Parent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
