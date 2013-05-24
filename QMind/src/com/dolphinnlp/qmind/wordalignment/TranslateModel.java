package com.dolphinnlp.qmind.wordalignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.regex.Pattern;

import com.dolphinnlp.qmind.config.Config;

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
            br.close();

            br = new BufferedReader(new InputStreamReader(new FileInputStream(questionVcb)));
            line = null;
            while((line = br.readLine()) != null) {
                String[] sp = line.split(" ");
                Integer id = Integer.valueOf(sp[0]);
                String word = sp[1];
                if (wordToIgnore.matcher(word).matches()) {
                    System.out.println("Ignore word : " + word);
                    continue;
                }
                questionVocabulary.put(id, word);
                
            }
            br.close();

            br = new BufferedReader(new InputStreamReader(new FileInputStream(modelFile)));
            line = null;
            while((line = br.readLine()) != null) {
                String[] sp = line.split(" ");
                Integer src_id = Integer.valueOf(sp[0]);
                Integer dst_id = Integer.valueOf(sp[1]);
                Double prob = Double.valueOf(sp[2]);
                if (prob < 1E-4) {
                    continue;
                }
                Double logProb = Math.log(prob);
                String srcWord = answerVocabulary.get(src_id);
                String trgWord = questionVocabulary.get(dst_id);
                translateProb.put(srcWord + "_" + trgWord, logProb);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //public double getProb(String srcWord, String trgWord) {
        //return translateProb.containsKey(src + "_" + trg) ? translateProb.get(src + "_" + trg) : 0;
    //}

    public double getProb(String srcSentence, String trgSentence) {
        String[] srcWords = srcSentence.split(" ");
        String[] trgWords = trgSentence.split(" ");

        double prob = 0;
        for (String srcWord : srcWords){
            for (String trgWord : trgWords){
                String sw = srcWord.replaceAll("/[a-z]+", "");
                String tw = trgWord.replaceAll("/[a-z]+", "");
                prob += translateProb.containsKey(sw + "_" + tw) ? translateProb.get(sw + "_" + tw) : -20;
            }
        }
        return prob;
    }
    public static void main(String[] args) {
        TranslateModel model = new TranslateModel();
        model.load(Config.getValue(Config.VAR.INDEX_PATH_STRING));
        System.out.println(model.getProb("我/z", "你/z"));
    }
}
