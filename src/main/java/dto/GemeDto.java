package dto;


import java.util.Date;
import java.util.List;

public class GemeDto {

    private Long id;
    private Bk titleBk;
    private String  timeG;
    private TeamDto team1;
    private TeamDto team2;
    private String titleG;
    private String urlGame; // из jsoup
    private Integer periud; // <= 3
    private List<EventDto> event;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bk getTitleBk() {
        return titleBk;
    }

    public void setTitleBk(Bk titleBk) {
        this.titleBk = titleBk;
    }

    public String  getTimeG() {
        return timeG;
    }

    public void setTimeG(String  timeG) {
        this.timeG = timeG;
    }

    public TeamDto getTeam1() {
        return team1;
    }

    public void setTeam1(TeamDto team1) {
        this.team1 = team1;
    }

    public TeamDto getTeam2() {
        return team2;
    }

    public void setTeam2(TeamDto team2) {
        this.team2 = team2;
    }

    public String getTitleG() {
        return titleG;
    }

    public void setTitleG(String titleG) {
        this.titleG = titleG;
    }

    public String getUrlGame() {
        return urlGame;
    }

    public void setUrlGame(String urlGame) {
        this.urlGame = urlGame;
    }

    public Integer getPeriud() {
        return periud;
    }

    public void setPeriud(Integer periud) {
        this.periud = periud;
    }

    public List<EventDto> getEvent() {
        return event;
    }

    public void setEvent(List<EventDto> event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "GemeDto{" +
                "id=" + id +
                ", titleBk=" + titleBk +
                ", timeG=" + timeG +
                ", team1=" + team1 +
                ", team2=" + team2 +
                ", titleG='" + titleG + '\'' +
                ", urlGame='" + urlGame + '\'' +
                ", periud=" + periud +
                ", event=" + event +
                '}';
    }
}

