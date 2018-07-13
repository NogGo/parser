import dto.Article;
import logic.HandlerMapper;
import logic.JsoupWorks;
import logic.SelenWorks;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AppMain {
    private static final Logger logger = Logger.getLogger(AppMain.class);

    private static final String URL_MAIN = "https://1xstavka.ru/";
    private static final String URL = "https://1xstavka.ru/live/Basketball/";
    private static final String LINE = "===================================";
    private static final String URL_MAIN_BETSITY = "https://1xstavka.ru/";
    private static final String URL_BETSITY = "https://1xstavka.ru/live/Basketball/";

    public static void main(String[] args) {
        logger.info(LINE);
        JsoupWorks jsoupWorks = new JsoupWorks();
        SelenWorks selen = new SelenWorks();
        HandlerMapper mapper = new HandlerMapper();


        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
//                synchronized (this){
                try {
                    List<Article> listUrls =  jsoupWorks.getAllUrls(URL, URL_MAIN);
                    if (listUrls == null || listUrls.isEmpty()){
                        exProg(selen, "Is Empty!!!!!!! Exit!!!");
                    }
                    List<Document> listDoc =  jsoupWorks.getDocFromStr(selen.reqPages(listUrls));
                    mapper.map(listDoc);
                } catch (Exception e) {
                    logger.error(e);
                }
//                }finally {
//                    exProg(selen, null);
//                }
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, 30*1000);
        TimerTask timerTaskExit = new TimerTask() {
            @Override
            public void run() {
                exProg(selen, "EXIT!");
                timer.cancel();
                timer.purge();
            }
        };
//        Timer timer = new Timer(true);

//        selen.stop();
        timer.scheduleAtFixedRate(timerTaskExit, 60*1000,1);
    }
    public static void exProg(SelenWorks selen, String errMess){
        if (errMess != null){
        logger.info(errMess);
        }
        selen.stop();
        System.exit(0);
    }
}

