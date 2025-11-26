package com.ethan.RPG.service;

import com.ethan.RPG.entity.Monster;
import com.ethan.RPG.exceptions.MonsterNotFoundException;
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
    public Monster fetchMonsterByName(String monsterName) {
        return monsterRepository.findByMonsterName(monsterName).orElseThrow(() -> new MonsterNotFoundException("No monster found with name: " + monsterName));
    }

    @Override
    public Monster updateMonster(Monster monster, Long monsterId) {
        Monster monsterDb = monsterRepository.findById(monsterId).orElseThrow(() -> new MonsterNotFoundException("No monster found with that id"));
        transferMonsterData(monsterDb, monster);
        return saveMonster(monsterDb);
    }

    @Override
    public Monster updateMonster(Monster monster, String monsterName) {
        Monster monsterDb = fetchMonsterByName(monster.getMonsterName());
        transferMonsterData(monsterDb, monster);
        return saveMonster(monsterDb);
    }

    private void transferMonsterData(Monster monsterDb, Monster monsterUpdate){
        monsterDb.setMonsterName(monsterUpdate.getMonsterName());
        monsterDb.setMonsterDescription(monsterUpdate.getMonsterDescription());
        monsterDb.setMonsterType(monsterUpdate.getMonsterType());
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
