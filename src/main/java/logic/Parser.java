package logic;

import dto.Article;
import dto.TeamDto;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private static final Logger logger = Logger.getLogger(Parser.class);

    private static final String urlMain = "https://1xstavka.ru/";
    private static final String url = "https://1xstavka.ru/live/Basketball/";

    public List<TeamDto> startPars() throws IOException {
        logger.info("start parsing...");
        List<Article> listURLs = getLinks(url, urlMain);
        List<Document> listDocs = new ArrayList<>(); //list docs
        listURLs.forEach(iterURL ->{
            Document doc;
            try {
                doc = Jsoup.connect(iterURL.getUrl()).get();
                listDocs.add(doc);
            } catch (IOException e) {
                logger.error(e);
            }
        });
        /**
         *  TODO: Парсер требует реализации JS, JSOUP не поддерживает JS
         *  Вариатры: WebView -> javaFX or Selenium ?
         *  (phantomjsdriver совместно с selenium)
         *
         *  автоматизировать настоящий браузер через selenium, открыть желаемую страницу в
         *  selenium-powered браузере, дать ему выполнить свою работу по загрузке страницы
         *  и пропарсить нужные данные; далее либо получить исходный код страницы через
         *  getPageSource() и отдать это JSoup на парсинг, либо продолжить спользовать
         *  selenium webdriver API
         */
//        List<TeamDto> dtos = parseDocs(listDocs);
//        return dtos;
        logger.info("-----------------------------------------------------");
        return null;
    }

    private List<Article> getLinks(final String url, final String urlMain) throws IOException{
        logger.info("get All Links in basketball...");
        List<Article> list = new ArrayList<Article>();
        Long timeReq = System.currentTimeMillis();
        Document doc = Jsoup.connect(url).get();
        logger.info("TimeReqest(getAllEventsBasketball): "
                + (((float)(System.currentTimeMillis() - timeReq)) / 1000) + " с.");
        Elements links = doc.getElementsByAttributeValue("class", "c-events__item c-events__item_col");
        logger.info("Size links: " + links.size());
        links.forEach(lin -> {
            Elements divElement = lin.getElementsByAttributeValue("class", "c-events__item");
            divElement.forEach(divEl -> {
                Element aElement = divEl.child(4);
                String urlAElement = aElement.attr("href");
                String title = aElement.child(0).text();
                list.add(new Article(urlMain + urlAElement, title));
            });
        });
        list.forEach(li ->{
            System.out.println(li.getUrl());
        });
        return list;
    }
    private List<TeamDto> parseDocs(List<Document> docs){
        logger.info("This is main method for parsing webSite!!! ");
        List<TeamDto> dto = new ArrayList<>();
        //TODO: доделать !!!!
//        docs.forEach(doc -> {
//            Elements nameTeams = doc.getElementsByAttributeValue("class","scoreboard__team-name");
//            System.out.println(nameTeams);
//            nameTeams.forEach(li -> {
////                logger.info("TeamName: "  + li.text().trim() + "\n");
//                Tag nameTeams2 = li.tag();
//                System.out.println(nameTeams2);
//                System.out.println("TeamName: "  + li.text().trim() + "\n");
//            });
////            logger.info("-------------------------------------------");
//            System.out.println("--------------------------");
//        });

        return dto;
    }
}
