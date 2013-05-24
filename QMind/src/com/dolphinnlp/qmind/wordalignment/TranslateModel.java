package com.dolphinnlp.qmind.wordalignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.regex.Pattern;

public class TranslateModel {
    HashMap<String, Double> translateProb = new HashMap<String, Double>();

    public void load(String dicPath) {
        HashMap<Integer, String> answerVocabulary = new HashMap<Integer, String>();
        HashMap<Integer, String> questionVocabulary = new HashMap<Integer, String>();

        File modelFile = new File(dicPath + "/ibm_model_1");
        File answerVcb = new File(dicPath + "/answer.vcb");
        File questionVcb = new File(dicPath + "/question.vcb");

        Pattern wordToIgnore = Pattern.compile("[0-9|A-Z|a-z]+");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(answerVcb)));
            String line = null;
            while((line = br.readLine()) != null) {
                String[] sp = line.split(" ");
                Integer id = Integer.valueOf(sp[0]);
                String word = sp[1];
                if (wordToIgnore.matcher(word).matches()) {
                    System.out.println("Ignore word : " + word);
                    continue;
                }
                answerVocabulary.put(id, word);
                
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
