package com.dolphinnlp.qmind.preprocess;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.dolphinnlp.qmind.model.QA;

public class LuceneIndexer {
    private IndexWriter writer = null;

    public LuceneIndexer(String indexPath)
    {
        try {
            Directory dir = FSDirectory.open(new File(indexPath));
            Analyzer analyzer = new IKAnalyzer();
            IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_35, analyzer);
            writer = new IndexWriter(dir, iwc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(QA qa)
    {
        Document doc = new Document();

        Field qID = new Field("qID", qa.getQuestion().getQid(), Field.Store.YES, Field.Index.NO);
        Field qTitle = new Field("qTitle", qa.getQuestion().getQtitle(), Field.Store.NO, Field.Index.ANALYZED);
        Field qContent = new Field("qContent", qa.getQuestion().getQcontent(), Field.Store.NO, Field.Index.ANALYZED);
        Field qaJSON = new Field("qaJSON", qa.toJSON().toJSONString(), Field.Store.YES, Field.Index.NO);

        doc.add(qID);
        doc.add(qTitle);
        doc.add(qContent);
        doc.add(qaJSON);

        try {
            writer.addDocument(doc);
        } catch (CorruptIndexException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close()
    {
        try {
            writer.close();
        } catch (CorruptIndexException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
