package com.dolphinnlp.qmind.lucene;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.dolphinnlp.qmind.config.Config;
import com.dolphinnlp.qmind.config.Config.VAR;
import com.dolphinnlp.qmind.model.QA;

public class LuceneSearcher {
    IndexReader reader;
    IndexSearcher searcher;
    Analyzer analyzer;
    QueryParser titleQueryParser;

    public LuceneSearcher(String indexPath) {
        try {
            reader = IndexReader.open(FSDirectory.open(new File(indexPath)));
            searcher = new IndexSearcher(reader);
            analyzer = new IKAnalyzer();
            titleQueryParser = new QueryParser(Version.LUCENE_35, "qTitle", analyzer);
        } catch (CorruptIndexException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<QA> queryByQId(String qid) {
        ArrayList<QA> ret = new ArrayList<QA>();
        try {
            Query q = new TermQuery(new Term("qID", qid));
            TopDocs results = searcher.search(q, 1);
            System.out.println("find " + results.totalHits + " hits for query : " + qid);
            ScoreDoc[] hits = results.scoreDocs;
            for (ScoreDoc hit : hits) {
                Document doc = searcher.doc(hit.doc);
                ret.add(QA.fromJSON(doc.get("qaJSON")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
    public ArrayList<QA> queryByQTitle(String query) {
        ArrayList<QA> ret = new ArrayList<QA>();
        try {
            Query q = titleQueryParser.parse(query);
            TopDocs results = searcher.search(q, 1000);
            System.out.println("find " + results.totalHits + " hits for query : " + query);
            ScoreDoc[] hits = results.scoreDocs;
            for (ScoreDoc hit : hits) {
                Document doc = searcher.doc(hit.doc);
                QA qa = QA.fromJSON(doc.get("qaJSON"));
                qa.setScore(hit.score);
                ret.add(qa);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ret;
    }
    public void close() {
        try {
            searcher.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        LuceneSearcher s = new LuceneSearcher(Config.getValue(VAR.INDEX_PATH_STRING));
        ArrayList<QA> ans = s.queryByQTitle("重庆社保局");
        for (QA qa : ans) {
            System.out.println(qa.getQuestion().getQtitle());
        }
        s.close();
    }
}
