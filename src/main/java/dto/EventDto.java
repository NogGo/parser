package dto;


public class EventDto {

    private String  title;
    private Double kef;

    public String  getTitle() {
        return title;
    }

    public void setTitle(String  title) {
        this.title = title;
    }

    public Double getKef() {
        return kef;
    }

    public void setKef(Double kef) {
        this.kef = kef;
    }

    @Override
    public String toString() {
        return "EventDto{" +
                "title='" + title + '\'' +
                ", kef=" + kef +
                '}';
    }
}
