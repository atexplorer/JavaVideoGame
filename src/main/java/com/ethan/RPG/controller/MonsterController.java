package com.ethan.RPG.controller;


import com.ethan.RPG.entity.Monster;
import com.ethan.RPG.service.MonsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
public class MonsterController {

    @Autowired
    private MonsterService monsterService;

    @PostMapping("/monsters")
    public Monster saveMonster(@Valid @RequestBody Monster monster){
        return monsterService.saveMonster(monster);
    }

    @GetMapping("/monsters")
    public List<Monster> getMonsters(){
        return monsterService.fetchMonsters();
    }

    @GetMapping("/monsters/{monsterName}")
    public Monster getMonsterByName(@PathVariable("monsterName") String monsterName){
        return monsterService.fetchMonsterByName(monsterName);
    }

    @PutMapping("/monsters/{id}")
    public Monster updateMonster(@Valid @RequestBody Monster monster,
                                 @PathVariable("id") Long monsterId){
        return monsterService.updateMonster(monster, monsterId);
    }

    @PutMapping("/monsters/{monsterName}")
    public Monster updateMonster(@Valid @RequestBody Monster monster,
                                 @PathVariable("monsterName") String monsterName){
        return monsterService.updateMonster(monster, monsterName);
    }

    @DeleteMapping("monsters/{id}")
    public String deleteMonster(@PathVariable("id") Long monsterId){
        return monsterService.deleteMonsterById(monsterId);
    }

}
