package com.dolphinnlp.qmind.preprocess;
import java.io.*;
import java.util.*;


import com.dolphinnlp.qmind.config.Config;
import com.dolphinnlp.qmind.config.Config.VAR;
import com.dolphinnlp.qmind.model.Answer;
import com.dolphinnlp.qmind.model.QA;
import com.dolphinnlp.qmind.model.Question;
import com.rupeng.*;
import com.rupeng.jero.*;
public class PreProcess {
    public static String F2J(String fStr)
    {
        List list = new ArrayList();
        ConvertInto conFDao = new ConvertIntoFDao();
        list = conFDao.getIndex(fStr);

        ConvertIntoJDao conJDao = new ConvertIntoJDao();
        return conJDao.getString(list);
    }
    public static void main(String[] args) throws Exception
    {
        File dataDir = new File(Config.getValue(VAR.DATASET_PATH_STRING));
        File[] datasets = dataDir.listFiles();

        LuceneIndexer indexer = new LuceneIndexer(Config.getValue(VAR.INDEX_PATH_STRING));
        int filenum = 0;
        int indexnum = 0;
        for(File dataset : datasets)
        {
            if (dataset.toString().endsWith(".parsed") == false)
            {
                continue;
            }
            filenum ++;
            BufferedReader br = new BufferedReader(new FileReader(dataset));
            String str = null;
            while((str = br.readLine()) != null)
            {
                //String str = "<question><qid>soso_414162839</qid><url>http://wenwen.soso.com/z/q414162839.htm</url><qtitle>听说济南上岛咖啡最近有个套餐，才十来块钱，谁给个团购的地址？</qtitle><qcontent></qcontent><closedbyasker>0</closedbyasker><closedate>20121109153300</closedate><asker>季候</asker><category>生活家居>购物</category><score>1.0</score><tword>听说/v 济南/ns 上/f 岛/n 咖啡/n 最近/t 有/v 个/q 套餐/n ，/w 才/c 十/m 来/m 块/q 钱/n ，/w 谁/r 给/v 个/q 团购/n 的/u 地址/n ？/w</tword><cword></cword><answers><answer><aid>soso_414162839_0</aid><acontent>        <br/><br/>  <br/>    不错，是有这个套餐，在那个订餐在线网，网址应该是dingcanzaixian.com/tuangou最近挺火的，好多人都团了，10来块钱的是最便宜的，还有20多块钱的情侣套餐呢，我就团了一个20多的带女朋友去的 还不错啊。<br/><br/><br/><br/><br/><br/></acontent><adate>20121109152400</adate><answerer>√夢幻紅亼‵</answerer><words>不错/a ，/w 是/v 有/v 这个/r 套餐/n ，/w 在/p 那个/r 订餐/v 在线/nz 网/n ，/w 网址/n 应该/v 是/v dingcanzaixian.com/nx //w tuangou/nx 最近/t 挺/d 火/a 的/u ，/w 好多/m 人/n 都/d 团/n 了/u ，/w 10/m 来/m 块/q 钱/n 的/u 是/v 最/d 便宜/a 的/u ，/w 还有/v 20/m 多/m 块/q 钱/n 的/u 情侣/n 套餐/n 呢/y ，/w 我/r 就/p 团/n 了/u 一个/m 20/m 多/a 的/u 带/v 女朋友/n 去/v 的/u  /w 还/d 不错/a 啊/y 。/w</words><isbest>1</isbest></answer><answer><aid>soso_414162839_0</aid><acontent>      <br/><br/>  <br/>   是订餐在线网的团购吧，在百度上搜 订餐在线 就行了<br/><br/><br/><br/><br/><br/></acontent><adate>20121109151800</adate><answerer>lntge</answerer><words>是/v 订餐/vn 在线/vn 网/n 的/u 团购/n 吧/y ，/w 在/p 百度/nz 上/f 搜/v  /w 订餐/vn 在线/vn  /w 就/d 行/v 了/u</words><isbest>0</isbest></answer></answers></question>";
                str = F2J(str);
                QA qaTest = processLine(str);
                WriteXML.createXML(qaTest);
                indexer.write(qaTest);
                indexnum ++;
                if (indexnum % 1000 == 0)
                {
                    System.out.println(filenum + " " + indexnum);
                }
            }
            br.close();
        }
        indexer.close();
    }
    public static QA processLine(String str)
    {
        Question question = new Question();
        ArrayList<Answer> answers = new ArrayList<Answer>();
        QA qa = new QA();

        question.setQid(processTag(str,Tag.QIDSTAG,Tag.QIDETAG));
        question.setQtitle(processTag(str,Tag.QTITLESTAG,Tag.QTITLEETAG));
        question.setQcontent(processTag(str,Tag.QCONTENTSTAG,Tag.QCONTENTETAG).replaceAll("<.?>|<.+?>| ", " ").trim());
        question.setCategory(processTag(str,Tag.QCATEGORYSTAG,Tag.QCATEGORYETAG));
        question.setTword(processTag(str,Tag.TWORDSTAG,Tag.TWORDETAG));
        question.setCword(processTag(str,Tag.CWORDSTAG,Tag.CWORDETAG));

        String strAnswers = processTag(str,Tag.ANSWERSSTAG,Tag.ANSWERSETAG);
        for(int i = 0; strAnswers.length() != 0; i++)
        {
            String strAnswer = processTag(strAnswers,Tag.ANSWERSTAG,Tag.ANSWERETAG);
            strAnswers = strAnswers.substring(strAnswers.indexOf(Tag.ANSWERETAG)+Tag.ANSWERETAG.length());
            Answer temp = new Answer();
            temp.setAid(processTag(strAnswer,Tag.AIDSTAG,Tag.AIDETAG));
            temp.setAcontent(processTag(strAnswer,Tag.ACONTENTSTAG,Tag.ACONTENTETAG).replaceAll("<.?>|<.+?>| ", " ").trim());
            temp.setWords(processTag(strAnswer,Tag.WORDSSTAG,Tag.WORDSETAG));
            temp.setIsbest(processTag(strAnswer,Tag.ISBESTSTAG,Tag.ISBESTETAG));
            answers.add(temp);
        }
        qa.setQuestion(question);
        qa.setAnswers(answers);
        return qa;
    }
    public static String processTag(String str, String stag, String etag)
    {
        int start = str.indexOf(stag) + stag.length();
        int end = str.indexOf(etag);
        return str.substring(start, end);
    }
}

