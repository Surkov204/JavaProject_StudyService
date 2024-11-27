package model;

import java.util.ArrayList;

public class LearningAIList {
    private ArrayList<LearningAI> learningAIList;

    public LearningAIList() {
        learningAIList = new ArrayList<>();
    }

    public void add(LearningAI learningAI) {
        learningAIList.add(learningAI);
    }

    public ArrayList<LearningAI> getAll() {
        return learningAIList;
    }
}
