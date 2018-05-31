import dto.Article;
import logic.HandlerMapper;
import logic.JsoupWorks;
import logic.SelenWorks;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

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
        List<Article> listUrls = null;
        try {
            listUrls =  jsoupWorks.getAllUrls(URL, URL_MAIN);
            List<Document> listDoc =  jsoupWorks.getDocFromStr(selen.reqPages(listUrls));
            mapper.map(listDoc);
        } catch (IOException e) {
            logger.error(e);
        }
        selen.stop();
    }
}

