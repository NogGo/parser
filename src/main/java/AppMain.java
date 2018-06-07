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
    private static final String CERTA = "===================================";

    public static void main(String[] args) {
        logger.info(CERTA);
        JsoupWorks jsoupWorks = new JsoupWorks();
        SelenWorks selen = new SelenWorks();
        HandlerMapper mapper = new HandlerMapper();


//        TimerTask timerTask = new TimerTask() {
//            @Override
//            public void run() {
//                synchronized (this){
                try {
                    List<Article> listUrls =  jsoupWorks.getAllUrls(URL, URL_MAIN);
                    if (listUrls == null || listUrls.isEmpty()){
                        exProg(selen);
                    }
                    logger.info(listUrls.size());
                    List<Document> listDoc =  jsoupWorks.getDocFromStr(selen.reqPages(listUrls));
                    mapper.map(listDoc);
                } catch (Exception e) {
                    logger.error(e);
//                }
                }
//            }
//        };
//        Timer timer = new Timer(true);
//        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(timerTask, 0, 180*1000);
//        selen.stop();
    }
    public static void exProg(SelenWorks selen){
        logger.info("Is Empty!!!!!!! Exit!!!");
        selen.stop();
        System.exit(0);
    }
}

