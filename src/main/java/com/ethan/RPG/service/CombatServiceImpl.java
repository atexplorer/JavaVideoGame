package com.ethan.RPG.service;

import com.ethan.RPG.dto.request.DiceRequest;
import com.ethan.RPG.dto.response.DiceResponse;
import com.ethan.RPG.utility.DiceTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CombatServiceImpl implements CombatService{

    private final Random random;

    @Autowired
    public CombatServiceImpl(Random random){
        this.random = random;
    }

    @Override
    public int getToSucceedResult(DiceRequest diceRequest) {
        return random.nextInt(DiceTypes.d20.getDiceValue()) + 1 + diceRequest.getToSucceedMod();
    }
}
