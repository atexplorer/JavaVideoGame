package com.ethan.RPG.service;

import com.ethan.RPG.entity.Monster;

import java.util.List;

public interface MonsterService {

    Monster saveMonster(Monster monster);

    List<Monster> fetchMonsters();

    Monster fetchMonsterByName(String monsterName);

    Monster updateMonster(Monster monster,
                          Long monsterId);

    Monster updateMonster(Monster monster,
                          String monsterName);

    String deleteMonsterById(Long monsterId);
}
