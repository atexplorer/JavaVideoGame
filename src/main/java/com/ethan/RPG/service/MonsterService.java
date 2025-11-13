package com.ethan.RPG.service;

import com.ethan.RPG.entity.Monster;

import java.util.List;

public interface MonsterService {

    Monster saveMonster(Monster monster);

    List<Monster> fetchMonsters();

    Monster updateMonster(Monster monster,
                          Long monsterId);

    String deleteMonsterById(Long monsterId);
}
