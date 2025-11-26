package com.ethan.RPG.serviceTest;


import com.ethan.RPG.entity.Monster;
import com.ethan.RPG.exceptions.MonsterNotFoundException;
import com.ethan.RPG.repository.MonsterRepository;
import com.ethan.RPG.service.MonsterServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MonsterServiceImplTest {

    @Mock
    MonsterRepository monsterRepository;

    @InjectMocks
    MonsterServiceImpl monsterService;

    @Test
    public void saveMonsterTest(){
        Monster monster = new Monster(1L, "Igor", "Helpful assistant to Dr. Frankenstein", "Human");

        when(monsterRepository.save(monster)).thenReturn(monster);

        Monster returnMonster = monsterService.saveMonster(monster);
        assertEquals(returnMonster, monster);
    }

    @Test
    public void testFetchByMonsterName_Success(){
        Monster monster = new Monster("Igor", "Helpful assistant to Dr. Frankenstein", "Human");
        when(monsterRepository.findByMonsterName("Igor")).thenReturn(Optional.of(monster));
        Monster returnMonster = monsterService.fetchMonsterByName("Igor");
        assertEquals(monster, returnMonster);
    }

    @Test
    public void testFetchByMonsterName_throwsException(){
        when(monsterRepository.findByMonsterName("Igor")).thenReturn(Optional.empty());
        MonsterNotFoundException exception = assertThrows(MonsterNotFoundException.class, () ->
                monsterService.fetchMonsterByName("Igor"));
        assertEquals("No monster found with name: Igor", exception.getMessage());
    }


    @Test
    public void testFetchMonsters(){
        Monster monster = new Monster("Igor", "Helpful assistant to Dr. Frankenstein", "Human");
        Monster monster2 = new Monster("The monster", "Creation of Dr. Frankenstein", "Monstrosity");
        List<Monster> monsterList = new ArrayList<>();
        monsterList.add(monster);
        monsterList.add(monster2);

        when(monsterRepository.findAll()).thenReturn(monsterList);

        List<Monster> returnList = monsterService.fetchMonsters();

        assertEquals(monsterList, returnList);
    }

    /**
     * When testing a method that alters an objects variables, we want to have the mock return the same object for both
     * mocks. That way we can see if they method correctly modified the object.
     * This works because the method that alters objects variables will impact the object in our test.
     * Then the mock returns the altered object during the save method.
     * The second when thenReturn works because it returns the object after it has been
     * mutated by the updateMonster method. So any mutations that occur in the method
     * are applied to the savedMonster object in the test class
     */
    @Test
    public void testUpdateMonsterById_success(){
        Monster savedMonster = new Monster(1L, "Igor", "Helpful assistant to Dr. Frankenstein", "Human");
        Monster updatedMonster = new Monster( "Igor", "Not so helpful assistant to Dr. Frankenstein", "Human");

        when(monsterRepository.findById(1L)).thenReturn(Optional.of(savedMonster));
        when(monsterRepository.save(savedMonster)).thenReturn(savedMonster);

        Monster returnMonster = monsterService.updateMonster(updatedMonster, 1L);

        assertEquals(updatedMonster.getMonsterName(), returnMonster.getMonsterName());
        assertEquals(updatedMonster.getMonsterDescription(), returnMonster.getMonsterDescription());
        assertEquals(updatedMonster.getMonsterType(), returnMonster.getMonsterType());
    }

    @Test
    public void testUpdateMonsterById_throwsException(){
        Monster updateMonster = new Monster(1L, "Igor", "Helpful assistant to Dr. Frankenstein", "Human");
        when(monsterRepository.findById(1L)).thenReturn(Optional.empty());
        MonsterNotFoundException exception = assertThrows(MonsterNotFoundException.class, () ->
                monsterService.updateMonster(updateMonster, 1L));

        assertEquals("No monster found with that id", exception.getMessage());

    }

    @Test
    public void testUpdateMonsterByName(){
        Monster savedMonster = new Monster(1L, "Igor", "Helpful assistant to Dr. Frankenstein", "Human");
        Monster updatedMonster = new Monster( "Igor", "Not so helpful assistant to Dr. Frankenstein", "Human");

        when(monsterRepository.findByMonsterName("Igor")).thenReturn(Optional.of(savedMonster));
        when(monsterRepository.save(savedMonster)).thenReturn(savedMonster);

        Monster returnMonster = monsterService.updateMonster(updatedMonster, "Igor");

        assertEquals(updatedMonster.getMonsterName(), returnMonster.getMonsterName());
        assertEquals(updatedMonster.getMonsterDescription(), returnMonster.getMonsterDescription());
        assertEquals(updatedMonster.getMonsterType(), returnMonster.getMonsterType());
    }

    @Test
    public void testDeleteMonster_success(){
        when(monsterRepository.existsById(1L)).thenReturn(false);

        String outputText = monsterService.deleteMonsterById(1L);
        verify(monsterRepository).deleteById(1L);
        assertEquals("Monster deleted from database", outputText);
    }

    @Test
    public void testDeleteMonster_fail(){
        when(monsterRepository.existsById(1L)).thenReturn(true);

        String outputText = monsterService.deleteMonsterById(1L);
        verify(monsterRepository).deleteById(1L);
        assertEquals("Failed to remove monster entry", outputText);
    }
}
