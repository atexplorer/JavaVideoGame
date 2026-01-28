package com.ethan.RPG.service;

import com.ethan.RPG.dto.request.DiceRequest;
import com.ethan.RPG.dto.response.DiceResponse;

public interface CombatService {

    int getToSucceedResult(DiceRequest diceRequest);
}
