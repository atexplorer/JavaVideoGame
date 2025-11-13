package com.ethan.RPG.service;

import com.ethan.RPG.entity.Monster;
import com.ethan.RPG.repository.MonsterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonsterServiceImpl implements MonsterService{

    @Autowired
    MonsterRepository monsterRepository;

    @Override
    public Monster saveMonster(Monster monster) {
        return monsterRepository.save(monster);
    }

    @Override
    public List<Monster> fetchMonsters() {
        return monsterRepository.findAll();
    }

    @Override
    public Monster updateMonster(Monster monster, Long monsterId) {
        Monster monsterDb = monsterRepository.findById(monsterId).orElseThrow(() -> new RuntimeException("No monster found with that Id"));

        monsterDb.setMonsterName(monster.getMonsterName());
        monsterDb.setMonsterType(monster.getMonsterType());
        monsterDb.setMonsterDescription(monster.getMonsterDescription());

        return monsterRepository.save(monsterDb);
    }

    @Override
    public String deleteMonsterById(Long monsterId) {
        monsterRepository.deleteById(monsterId);

        if(monsterRepository.existsById(monsterId)){
            return "Failed to remove monster entry";
        }else{
            return "Monster deleted from database";
        }
    }
}
