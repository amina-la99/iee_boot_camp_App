package com.icosium.ieebootcampapp;

public class Question {
    private String question,reponse;

    public Question(String question,String reponse){
        this.question=question;
        this.reponse=reponse;

    }

    public String getQuestion(){
        return question;
    }
    public void setQuestion(String question){
        this.question=question;
    }
    public  String getReponse(){
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }
}
