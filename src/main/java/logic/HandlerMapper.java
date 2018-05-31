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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HandlerMapper {
    private static final Logger logger = Logger.getLogger(HandlerMapper.class);

    public void map(List<Document> list){
        logger.info("map() start");
        List<GemeDto> games = new ArrayList<>();
        list.forEach(doc ->{

            GemeDto dto = new GemeDto();
            Elements team_name = doc.getElementsByAttributeValue("class", "scoreboard__team-name");
            int i = 0;
            TeamDto teamDto;
            for(Element el : team_name){
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
//            logger.info(div.text().trim());
//            LocalDateTime time = LocalDateTime.of(LocalDate.now(), LocalTime.parse(div.text().trim()));
//            logger.info(time + "   !!!!!!!!!!!!");
            dto.setTimeG(div.text().trim());


            StringBuilder strTitlGame = new StringBuilder();
            Elements nameGame = doc.getElementsByAttributeValue("class", "prevPageButMore");
            nameGame.forEach(el -> {
//                logger.info(el.child(0).text());
//                logger.info(el.child(2).text());
//                logger.info(el.child(4).text());
//                logger.info(el.child(6).text());
//                strTitlGame.append(el.child(0).text())
//                .append(" / ").append(el.child(2).text()).append(" / ").append(el.child(4).text())
//                .append(" / ");
//                if (el.child(6) != null && !el.child(6).text().isEmpty()){
//                    strTitlGame.append(el.child(6).text());
//                }
                el.attr("a[href]");
                strTitlGame.append(el.text());
            });
            dto.setTitleG(strTitlGame.toString());


            //scoreboard__period
            Elements periud = doc.getElementsByAttributeValue("class", "scoreboard__period");
            periud.forEach(per -> {
//                logger.info(per.text().substring(0, 1).trim());
                try {
                    dto.setPeriud(Integer.valueOf(per.text().substring(0, 1).trim()));
                }catch (NumberFormatException ex){
                    logger.error(per.text());
                }
            });

            dto.setUrlGame(doc.select("link").first().attr("href"));
//            dto.setEvent(getEvent(doc));

            Elements events = doc.getElementsByAttributeValue("class", "bet_group");
            events.forEach(ev ->{
//                if (!ev.child(0).isBlock() ){
                Element element = ev.child(1);

                logger.info("!!!!!!!!!!!!!!!" + element.getElementsByAttributeValue("class", "bet_type").text());

//                }
            });


            games.add(dto);

        });

        //bet-title__star
        logger.info("SIZE GEME: " + games.size());
//        games.forEach(li -> {
//            logger.info(li.toString());
//
//        });
    }
    private  List<EventDto> getEvent(Document doc){
        List<EventDto> eventDtos = new ArrayList<>();
//        bets betCols2
        //blockSob
        EventDto eventDto = new EventDto();
        Elements events = doc.getElementsByAttributeValue("class", "bets betCols2");
        events.forEach(ev ->{
            if (!ev.child(0).isBlock() ){
                logger.info("!!!!!!!!!!!!!!!" + ev.child(0).text());

            }

            eventDtos.add(eventDto);
        });


//        events.forEach(event -> {
//            Element element0 = event.child(0);
//            eventDto.setTitle(Integer.valueOf(element0.select(".bet_type").text().substring(0, 4)));
//            eventDto.setKefB(element0.select(".koeff rc_lite"));
//        });

        return eventDtos;
    }
}
