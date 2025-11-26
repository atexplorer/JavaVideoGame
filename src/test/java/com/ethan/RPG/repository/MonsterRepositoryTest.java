package com.ethan.RPG.repository;


import com.ethan.RPG.entity.Monster;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class MonsterRepositoryTest {

    @Autowired
    MonsterRepository monsterRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    void givenNewMonster_whenSave_thenSuccess(){
        Monster monster = new Monster("Igor", "Helpful assistant to Dr. Frankenstein", "Human");
        Monster insertedMonster = monsterRepository.save(monster);
        assertThat(testEntityManager.find(Monster.class, insertedMonster.getMonsterId())).isEqualTo(monster);
    }

    @Test
    void givenCreatedMonster_whenUpdated_thenSuccess(){
        Monster monster = new Monster("Igor", "Helpful assistant to Dr. Frankenstein", "Human");
        testEntityManager.persist(monster);
        String newDescription = "Not so helpful assistant to Dr. Frankenstein";
        monster.setMonsterDescription(newDescription);
        monsterRepository.save(monster);
        assertThat(testEntityManager.find(Monster.class, monster.getMonsterId()).getMonsterDescription()).isEqualTo(newDescription);
    }

    @Test
    void givenCreatedMonster_whenFindById_thenSuccess(){
        Monster monster = new Monster("Igor", "Helpful assistant to Dr. Frankenstein", "Human");
        testEntityManager.persist(monster);
        Optional<Monster> newMonster = monsterRepository.findById(monster.getMonsterId());
        assertThat(newMonster).contains(monster);
    }

    @Test
    void givenCreatedMonster_whenFindByMonsterName_thenSuccess(){
        Monster monster = new Monster("Igor", "Helpful assistant to Dr. Frankenstein", "Human");
        testEntityManager.persist(monster);
        Optional<Monster> foundMonster = monsterRepository.findByMonsterName(monster.getMonsterName());
        assertThat(foundMonster).contains(monster);
    }

    @Test
    void givenCreatedMonsters_whenFindAll_thenSuccess(){
        Monster monster = new Monster("Igor", "Helpful assistant to Dr. Frankenstein", "Human");
        Monster monster2 = new Monster("The monster", "Creation of Dr. Frankenstein", "Monstrosity");
        testEntityManager.persist(monster);
        testEntityManager.persist(monster2);
        List<Monster> newMonster = monsterRepository.findAll();
        assertThat(newMonster.containsAll(List.of(monster, monster2)));
    }

    @Test
    void givenCreatedMonster_whenDeleteById_thenSuccess(){
        Monster monster = new Monster("Igor", "Helpful assistant to Dr. Frankenstein", "Human");
        testEntityManager.persist(monster);
        monsterRepository.deleteById(monster.getMonsterId());
        assertThat(testEntityManager.find(Monster.class, monster.getMonsterId())).isNull();
    }

}
