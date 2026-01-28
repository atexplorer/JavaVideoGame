package com.ethan.RPG.dto.request;

import com.ethan.RPG.utility.DiceTypes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DiceRequest {

    //Need to reconsider how we handle multiple dice types being submitted.
    //A possible idea is to have a map, where we have dice type as the key and a dice total as the value
    //Have all possible dice types be fields/variables and assign a number to them, I don't like this as much
    private int toSucceedMod;
    private int damageMod;
    private DiceTypes diceType;
    private int diceTotal;

    public DiceRequest(int toSucceedMod){
        this.toSucceedMod = toSucceedMod;
    }

}
