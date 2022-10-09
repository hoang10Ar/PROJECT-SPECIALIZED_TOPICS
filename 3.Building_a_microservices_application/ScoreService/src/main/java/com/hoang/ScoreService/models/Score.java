package com.hoang.ScoreService.models;

public class Score {
    private String idStudent;
    private int score;

    public Score(String idStudent, int score) {
        this.idStudent = idStudent;
        this.score = score;
    }

    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
