package com.finidy.hibernateplayground.oneToManyTwoLayerTwoNodes.lazyeager.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class GrandChildC {
    @Id
    @SequenceGenerator(name = "grand_child_c_seq", sequenceName = "grand_child_c_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grand_child_c_seq")
    private Long id;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHILD_ID")
    private ChildB childB;

    @Override
    public String toString() {
        return "GrantChildB{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
