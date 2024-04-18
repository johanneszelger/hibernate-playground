package com.finidy.hibernateplayground.oneToManyOneLayerTwoNodes.lazy.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class GrandChildA {
    @Id
    @SequenceGenerator(name = "grand_child_a_seq", sequenceName = "grand_child_a_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grand_child_a_seq")
    private Long id;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHILD_ID")
    private ChildA childA;

    @Override
    public String toString() {
        return "GrandChildA{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
