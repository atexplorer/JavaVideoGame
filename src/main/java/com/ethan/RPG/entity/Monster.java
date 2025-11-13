package com.ethan.RPG.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
//@Table(name = "Monsters", uniqueConstraints = {
//        @UniqueConstraint(columnNames = {"monsterName"})
//})
public class Monster {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long monsterId;
    @Column(unique = true)
    private String monsterName;
    private String monsterDescription;
    private String monsterType;

    public Monster(String monsterName, String monsterDescription, String monsterType){
        this.monsterName = monsterName;
        this.monsterDescription = monsterDescription;
        this.monsterType = monsterType;
    }

}
