package logic;

import dto.Article;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.Doc;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsoupWorks {
    private static final Logger logger = Logger.getLogger(JsoupWorks.class);

    public List<Article> getAllUrls(String url, String urlMain) throws Exception {
        return getLinks(url, urlMain);
    }

    /**
     * Get All links Basketball
     * @param url
     * @param urlMain
     * @return Object List<Article>
     * @throws Exception
     */
    private List<Article> getLinks(final String url, final String urlMain) throws Exception{
        logger.info("JsoupWorks, getLinks() start");
        List<Article> list = new ArrayList<Article>();
        Long timeReq = System.currentTimeMillis();
        Document doc = Jsoup.connect(url).get();
//        if (doc != null && !doc.empty().text().equals("")) {
        logger.info("TimeReqest(getAllLinksBasketball()): "
                + (((float) (System.currentTimeMillis() - timeReq)) / 1000) + " с.");
        Elements links = doc.select("div.c-events__item.c-events-scoreboard__wrap");
        links.forEach(link -> {
            Elements elements = link.getElementsByAttributeValue("class", "c-events__name");
            String urlAElement = elements.attr("href");
            String title = elements.select("span.c-events__teams").text();
            Elements periud = link.getElementsByAttributeValue("class", "c-events__overtime");
            list.add(new Article(urlMain + urlAElement, title, periud.text().trim()));
        });

        return list;
/*        TODO: Old code, does not work!
        Elements links = doc.getElementsByAttributeValue("class", "c-events__item c-events__item_col");
        logger.info("Size links: " + links.size());
        links.forEach(lin -> {
            Elements divElement = lin.getElementsByAttributeValue("class", "c-events-scoreboard");
            divElement.forEach(divEl -> {
                Element aElement = divEl.child(0);
                String urlAElement = aElement.attr("href");
                String title = aElement.child(0).text();

                Elements periud = divEl.getElementsByAttributeValue("class", "c-events__overtime");
                String strPeriud = "";
                if (!periud.text().trim().equals("Перерыв")){
                    Integer  per = Integer.parseInt(periud.text().substring(0, 1));
                }
                logger.info("getLinks Периуд = " + periud.text());
                list.add(new Article(urlMain + urlAElement, title, periud.text().trim()));
            });
        });
*/

    }



    /**
     * Transform
     * @param list
     * @return
     */
    public  List<Document> getDocFromStr(List<String> list){
        List<Document> listDoc = new ArrayList<>();
        list.forEach(li->{
           Document doc =  Jsoup.parse(li);
           listDoc.add(doc);
        });
        return listDoc;
    }
}
