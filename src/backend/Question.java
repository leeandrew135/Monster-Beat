package backend;

public class Question {
    public int answerKey;
    public String questionString;
    public String[] optionList;

    Question(int answerKey, String questionString, String[] optionList){
        this.answerKey = answerKey;
        this.questionString = questionString;
        this.optionList = optionList;
    }
}
