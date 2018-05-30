package dto;

import java.util.Date;
import java.util.List;

public class TeamDto {

    private Long id;
    private Bk titleBk;
    private Date timeMacth;
    private String nameTeam;
    private List<EventDto> event;
    private Double kef;

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

    public Date getTimeMacth() {
        return timeMacth;
    }

    public void setTimeMacth(Date timeMacth) {
        this.timeMacth = timeMacth;
    }

    public String getNameTeam() {
        return nameTeam;
    }

    public void setNameTeam(String nameTeam) {
        this.nameTeam = nameTeam;
    }

    public  List<EventDto> getEvent() {
        return event;
    }

    public void setEvent( List<EventDto> event) {
        this.event = event;
    }

    public Double getKef() {
        return kef;
    }

    public void setKef(Double kef) {
        this.kef = kef;
    }
}
