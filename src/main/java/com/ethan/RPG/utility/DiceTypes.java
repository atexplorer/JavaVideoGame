package com.ethan.RPG.utility;

public enum DiceTypes {
    d100(100),
    d20(20),
    d12(12),
    d10(10),
    d8(8),
    d6(6),
    d4(4);

    private int diceValue;

    DiceTypes(int diceValue){
        this.diceValue = diceValue;
    }

    public int getDiceValue(){
        return diceValue;
    }
}
