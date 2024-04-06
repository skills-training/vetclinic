package com.szlify.veterinaryclinic.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@SQLDelete(sql = "UPDATE pet SET deleted = false WHERE id = ?")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String species;
    private int age;

    @ManyToOne
    private Owner owner;

    @OneToMany(mappedBy = "pet")
    private Set<Visit> visits;

    private boolean deleted = false;
}
