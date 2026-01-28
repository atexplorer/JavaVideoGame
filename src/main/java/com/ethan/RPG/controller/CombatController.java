package com.ethan.RPG.controller;

import com.ethan.RPG.dto.request.DiceRequest;
import com.ethan.RPG.dto.response.DiceResponse;
import com.ethan.RPG.service.CombatService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/combat")
public class CombatController {

    @Autowired
    CombatService combatService;

    @PostMapping("/toSucceed")
    public DiceResponse getToSucceedResult(@Valid @RequestBody DiceRequest diceRequest){
        DiceResponse diceResponse = new DiceResponse();
        diceResponse.setToSucceedResult(combatService.getToSucceedResult(diceRequest));
        return diceResponse;
    }
}
