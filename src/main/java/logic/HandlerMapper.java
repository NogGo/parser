package logic;

import dto.*;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

public class HandlerMapper {
    private static final Logger logger = Logger.getLogger(HandlerMapper.class);

    /**
     * Main Mapper
     * @param list
     */
    public void map(List<Document> list){
        logger.info("HandlerMapper, map() start");
        List<GameDto> games = new ArrayList<>();
        list.forEach(doc ->{
            GameDto dto = new GameDto();

            mapPeriud(dto, doc);
            mapTeams(dto, doc);
            dto.setTitleBk(Bk.ONEXBET);
            dto.setTimeG(doc.getElementsByAttributeValue("class", "scoreboard__time").text().trim());
            mapTitleGame(dto, doc);
            dto.setUrlGame(doc.select("link").first().attr("href"));
            getEvent(dto, doc);

            games.add(dto);
        });

//        games.forEach(li -> {
//            logger.info(li.toString());
//
//        });
    }

    private void mapTeams(GameDto dto, Document doc ){
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
    }

    private void mapTitleGame(GameDto dto, Document doc){
        StringBuilder strTitlGame = new StringBuilder();
        Elements nameGame = doc.getElementsByAttributeValue("class", "prevPageButMore");
        nameGame.forEach(el -> {
            el.attr("a[href]");
            strTitlGame.append(el.text());
        });
        dto.setTitleG(strTitlGame.toString());
    }

    private void mapPeriud(GameDto dto, Document doc){
        Elements periud = doc.getElementsByAttributeValue("class", "scoreboard__period");
        periud.forEach(per -> {
            if (!per.text().equals("Перерыв")){
                dto.setPeriud(per.text().substring(0, 1).trim());
            } else  dto.setPeriud("Перерыв");
        });
    }

    private  void getEvent(GameDto dto, Document doc){
        Elements bet_group = doc.getElementsByAttributeValue("class", "bet_group");
        bet_group.forEach(iter -> {
            if (iter.getElementsByAttributeValue("class", " bet-title bet-title_justify").text().trim().equals("Тотал. с ОТ")){
                Elements listTMTB = iter.getElementsByAttributeValue("class", "bets betCols2");
                if (!listTMTB.isEmpty()){
                    List<EventDto> eventDtos = new ArrayList<>();
                    for (int i = 0; i < listTMTB.first().childNodeSize(); i++){
                        EventDto eventDto = new EventDto();
                        Element element = listTMTB.first().child(i);
                        String bet = element.getElementsByAttributeValue("class", "bet_type").text().trim();
                        String kef = element.getElementsByAttributeValue("class", "koeff").text().trim();
                        eventDto.setTitle(bet);
                        if (!kef.isEmpty()){
                            eventDto.setKef(Double.parseDouble(kef));
                        }
                        eventDtos.add(eventDto);
                    }
                    dto.setEvent(eventDtos);
                }
            }
        });
    }
}
