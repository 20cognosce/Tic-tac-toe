package tictactoe.model.impl;

import lombok.Getter;
import lombok.Setter;
import tictactoe.model.Field;
import tictactoe.model.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
public class ComputerPlayerImpl implements Player {
    @Getter
    @Setter
    private Field field;
    private String name;
    private final Integer figureInt;
    private final String figureStr;
    @Getter
    private int decision;

    private ArrayList<HashSet<Integer>> sets = new ArrayList<>();

    public ComputerPlayerImpl(String playerName, Field field, Integer figureInt) {
        this.name = playerName;
        this.field = field;
        this.figureInt = figureInt;

        if (figureInt == 0){
            this.figureStr = "0";
        } else
            this.figureStr = "X";

        InitializeSets();
        System.out.println(sets);
    }

    private void InitializeSets(){
        for(int i=0;i<8;++i){
            sets.add(i,new HashSet<>());
        }
        for(int i=0;i<9;++i){
            sets.get(i/3).add(i);
            sets.get(i%3+3).add(i);
            if(i%4==0)
                sets.get(6).add(i);
            if(i%2==0&&(i)*(i-8)<0)
                sets.get(7).add(i);
        }

    }

    public void MakeDecision() {
        int decision;
        List<Integer> emptyCells = field.getEmptyCellsList();
        List<Integer> analyzeResults = new ArrayList<>();
        for (HashSet<Integer> set: sets
             ) {
            analyzeResults.add(ScanElem(set,field.getFieldList()));
        }
        System.out.println(analyzeResults);
        decision = GetIndexToMove(new HashSet<>(emptyCells),emptyCells);

        for(int i=0;i<sets.size();++i) {
            if (analyzeResults.get(i).equals(2)) {
                decision = GetIndexToMove(sets.get(i), emptyCells);
                this.decision = decision;
                return;
            }
        }
        for(int i=0;i<sets.size();++i) {
            if (analyzeResults.get(i).equals(-2)) {
                decision = GetIndexToMove(sets.get(i), emptyCells);
            }
        }
        this.decision = decision;
    }

    private int GetIndexToMove(HashSet<Integer> set, List<Integer> emptyCells){
        Integer result = emptyCells.stream().filter(set::contains).findAny().orElse(0);
        return result;
    }

    private Integer ScanElem(HashSet<Integer> set, List<Integer> cells){
        Integer result=0;
        Integer enemyFigure = 1-this.figureInt;
        for (Integer elem: set
             ) {
            if(Objects.equals(cells.get(elem), enemyFigure)){
                --result;
                continue;
            }
            if(Objects.equals(cells.get(elem), figureInt)) {
                ++result;
            }
        }
        return result;
    }
}
