package com.finidy.hibernateplayground.oneToManyTwoLayerTwoNodes.eagerlazy.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class ChildB {
    @Id
    @SequenceGenerator(name = "child_b_seq", sequenceName = "child_b_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "child_b_seq")
    private Long id;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PARENT_ID")
    private Parent parent;

    @OneToMany(mappedBy = "childB", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GrandChildC> grandChildrenC;

    @OneToMany(mappedBy = "childB", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GrandChildD> grandChildrenD;

    @Override
    public String toString() {
        return "ChildB{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
