package com.ethan.RPG.serviceTest;

import com.ethan.RPG.dto.request.DiceRequest;
import com.ethan.RPG.service.CombatServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CombatServiceImplTest {

    @Mock
    Random mockRandom;

    @InjectMocks
    CombatServiceImpl combatService;

    @Test
    public void getToSucceedResultNoMod(){
        DiceRequest diceRequest = new DiceRequest(0);
        when(mockRandom.nextInt(20)).thenReturn(0);
        int toSucceedResult = combatService.getToSucceedResult(diceRequest);
        assertEquals(1, toSucceedResult, "getToSucceedResultNoMod result: ");
    }

    @Test
    public void getToSucceedResultPositiveMod(){
        DiceRequest diceRequest = new DiceRequest(4);
        when(mockRandom.nextInt(20)).thenReturn(0);
        int toSucceedResult = combatService.getToSucceedResult(diceRequest);
        assertEquals(diceRequest.getToSucceedMod()+1, toSucceedResult, "getToSucceedResultNoMod result: ");
    }

    @Test
    public void getToSucceedResultNegativeMod(){
        DiceRequest diceRequest = new DiceRequest(-4);
        when(mockRandom.nextInt(20)).thenReturn(0);
        int toSucceedResult = combatService.getToSucceedResult(diceRequest);
        assertEquals((diceRequest.getToSucceedMod()+1), toSucceedResult, "getToSucceedResultNoMod result: ");
    }

}
