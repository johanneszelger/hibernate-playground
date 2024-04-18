package com.finidy.hibernateplayground.oneToManyOneLayerTwoNodes.eager.model;

import jakarta.persistence.*;
import lombok.Data;

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

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ChildA> childrenA;

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
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
