package org.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TrustTransaction {
    private HashMap<List<Choice>, List<Integer>> trustRules;

    public TrustTransaction(){
        this.trustRules = new HashMap<>();
        trustRules.put(Arrays.asList(Choice.COOPERATE, Choice.COOPERATE), Arrays.asList(2,2));
        trustRules.put(Arrays.asList(Choice.COOPERATE, Choice.CHEAT), Arrays.asList(-1,3));
        trustRules.put(Arrays.asList(Choice.CHEAT, Choice.COOPERATE), Arrays.asList(3,-1));
        trustRules.put(Arrays.asList(Choice.CHEAT, Choice.CHEAT), Arrays.asList(0,0));
    }

    public List<Integer> scores(List<Choice> choices){
        return trustRules.get(choices);
    }


}
