package com.example.testandroidapplication.objects;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class Faq {
    private String question;
    private String answer;

    public Faq(String question, String answer){
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public static ArrayList<Faq> fromJson(JSONArray jsonArray) throws JSONException {

        ArrayList<Faq> faqs = new ArrayList<>();
        if (jsonArray != null){
            for (int i = 0; i < jsonArray.length(); i++){
                Faq faq = new Faq(
                        jsonArray.getJSONObject(i).getString("Question"),
                        jsonArray.getJSONObject(i).getString("Answer"));
                faqs.add(faq);
            }
        }
        return faqs;
    }
}
