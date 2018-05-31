package logic;

import dto.Bk;
import dto.EventDto;
import dto.GameDto;
import dto.TeamDto;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

public class HandlerMapper {
    private static final Logger logger = Logger.getLogger(HandlerMapper.class);

    public void map(List<Document> list){
        logger.info("map() start");
        List<GameDto> games = new ArrayList<>();
        list.forEach(doc ->{
            GameDto dto = new GameDto();

            mapPeriud(dto, doc);
            mapTeams(dto, doc);
            dto.setTitleBk(Bk.ONEXBET);
            //Время периуда
            dto.setTimeG(doc.getElementsByAttributeValue("class", "scoreboard__time").text().trim());
            mapTitleGame(dto, doc);
            dto.setUrlGame(doc.select("link").first().attr("href"));
//            dto.setEvent(getEvent(doc));


            Elements events = doc.getElementsByAttributeValue("class", "bet_group");
//            logger.info("Размер Всех ТМТБ bet_group = " +  events.size());
            events.forEach(li -> {
                if (li.select("div.bet-title.bet-title_justify").text().trim().equals("Тотал. с ОТ")){
                    logger.info("EHHHHHHFFFFFFFFFFFFFFFFFFFFFFF");
                }
            });
            String test1 = events.select("div.bet-title.bet-title_justify").text().trim();
            logger.info(test1);
            if (test1.equals("Тотал. с ОТ")){
                logger.info("Размер Всех ТМТБ Тотал. с ОТ = ");
                Elements TMTB = events.select("div.bets.betCols2");
                 final int TmTbSize = TMTB.select("div[.bet_type]").size();
                 logger.info("Размер Всех ТМТБ = " +  TmTbSize) ;
//                TMTB.forEach(ev ->{
////                if (!ev.child(0).isBlock() ){
////                    for(int i = 0; i < TmTbSize; i++){
////                for(Element iter : TMTB){
//                    Element element = TMTB.child(i);
//                    if (element.getElementsByAttributeValue("class", "blockSob").isEmpty()){
//
//                        logger.info("!!!!!!!!!!!!!!!" + element.getElementsByAttributeValue("class", "bet_type").text());
//
//                    }
//                }

//                    }
//
//                });
            }


            games.add(dto);

        });

        //bet-title__star
        logger.info("SIZE GEME: " + games.size());
        games.forEach(li -> {
            logger.info(li.toString());

        });
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
            try {
                dto.setPeriud(Integer.valueOf(per.text().substring(0, 1).trim()));
                //TODO: Тут нужно проверки, что игра не знакочилась!!!!
            }catch (NumberFormatException ex){
                logger.error("ERRRRRRRRRRR" + per.text());
                //TODO: Учесть перерыв!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            }
        });
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
