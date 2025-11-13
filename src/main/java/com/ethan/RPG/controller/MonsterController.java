package com.ethan.RPG.controller;


import com.ethan.RPG.entity.Monster;
import com.ethan.RPG.service.MonsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @PutMapping("/monsters/{id}")
    public Monster updateMonster(@Valid @RequestBody Monster monster,
                                 @PathVariable("id") Long monsterId){
        return monsterService.updateMonster(monster, monsterId);
    }

    @DeleteMapping("monsters/{id}")
    public String deleteMonster(@PathVariable("id") Long monsterId){
        return monsterService.deleteMonsterById(monsterId);
    }

}
