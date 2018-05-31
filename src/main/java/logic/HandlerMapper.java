package logic;

import com.sun.xml.internal.bind.v2.schemagen.XmlSchemaGenerator;
import dto.Bk;
import dto.EventDto;
import dto.GemeDto;
import dto.TeamDto;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HandlerMapper {
    private static final Logger logger = Logger.getLogger(HandlerMapper.class);

    public void map(List<Document> list){
        logger.info("map() start");
        List<GemeDto> games = new ArrayList<>();
        list.forEach(doc->{
            GemeDto dto = new GemeDto();
            Elements divs = doc.getElementsByAttributeValue("class", "scoreboard__team-name");
            int i = 0;
            TeamDto teamDto;
            for(Element el : divs){
                if (i == 0 ){
                    teamDto = new TeamDto();
                    teamDto.setNameTeam(el.text());
                    dto.setTeam1(teamDto);
                }else{
                    teamDto = new TeamDto();
                    teamDto.setNameTeam(el.text().trim());
                    dto.setTeam2(teamDto);
                }
                i++;
            }
            dto.setTitleBk(Bk.ONEXBET);
            Elements div = doc.getElementsByAttributeValue("class", "scoreboard__time");
//            dto.setTimeG(new Date(div.text()));// ???
            StringBuilder ng = new StringBuilder();
            Elements nameGame = doc.getElementsByAttributeValue("class", "prevPageButMore");
            nameGame.forEach(el -> {
                ng.append(el.child(0).text())
                .append(" / ").append(el.child(2).text()).append(" / ").append(el.child(4).text())
                .append(" / ").append(el.child(6).text());
            });
            //scoreboard__period
            Elements periud = doc.getElementsByAttributeValue("class", "scoreboard__period");
            periud.forEach(per -> {
                dto.setPeriud(Integer.valueOf(per.text()));
            });
            dto.setEvent(getEvent(doc));
        });
        //bet-title__star
        games.forEach(li -> {
            logger.info(li.toString());
        });
    }
    private  List<EventDto> getEvent(Document doc){
        List<EventDto> eventDtos = new ArrayList<>();
//        EventDto eventDto = new EventDto();
//        Elements events = doc.getElementsByAttributeValue("class", "bets betCols2");
//        events.forEach(event -> {
//            Element element0 = event.child(0);
//            eventDto.setTitle(Integer.valueOf(element0.select(".bet_type").text().substring(0, 4)));
//            eventDto.setKefB(element0.select(".koeff rc_lite"));
//        });

        return eventDtos;
    }
}
