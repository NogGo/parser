package dto;

import java.util.Date;
import java.util.List;

public class TeamDto {

    private Long id;
    private String nameTeam;
    private List<EventDto> event;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
