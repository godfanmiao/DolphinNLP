package com.dolphinnlp.qmind.preprocess;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import com.dolphinnlp.qmind.model.Answer;
import com.dolphinnlp.qmind.model.QA;

public class ParallelCorpusGenerator {
    private File outputQ = null;
    private File outputA = null;
    private ArrayList<QA> buff = null;
    private int maxBufferSize = -1;
    private int size = 0;

    public ParallelCorpusGenerator(File outputDir, int bufferSize) {
        this.outputQ = new File(outputDir.getAbsolutePath() + "/question.txt");
        this.outputA = new File(outputDir.getAbsolutePath() + "/answer.txt");
        this.buff = new ArrayList<QA>(bufferSize);
        this.maxBufferSize = bufferSize;
    }
    public void append(QA qa) {
        buff.add(qa);
        if (buff.size() == maxBufferSize) {
            ArrayList<String> questionList = new ArrayList<String>();
            ArrayList<String> answerList = new ArrayList<String>();
            for (QA i : buff) {
                String question = i.getQuestion().getTword().replaceAll("/[a-z]+", " ");
                String ans = null;
                for (Answer a : i.getAnswers()) {
                    if (a.getIsbest().equals("1")) {
                        ans = a.getWords().replaceAll("/[a-z]+", " ");
                        break;
                    }
                }
                if (question != null && ans != null) {
                    questionList.add(question);
                    answerList.add(ans);
                }
            }
            try {
                FileUtils.writeLines(outputQ, questionList, true);
                FileUtils.writeLines(outputA, answerList, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            size += maxBufferSize;
            buff.clear();
        }
    }

    public int size() {
        return size;
    }
}
