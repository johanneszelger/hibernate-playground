package com.finidy.hibernateplayground.oneToManyOneLayerTwoNodes.eager.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class ChildA {
    @Id
    @SequenceGenerator(name = "child_a_seq", sequenceName = "child_a_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "child_a_seq")
    private Long id;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PARENT_ID")
    private Parent parent;

    @OneToMany(mappedBy = "childA", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GrandChildA> grandChildrenA;

    @OneToMany(mappedBy = "childA", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GrandChildB> grandChildrenB;

    @Override
    public String toString() {
        return "ChildA{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
