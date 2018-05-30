package dto;


public class EventDto {

    private Long id;
    private String title;
    private Double kefM;
    private Double getKefB;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getKefM() {
        return kefM;
    }

    public void setKefM(Double kefM) {
        this.kefM = kefM;
    }

    public Double getGetKefB() {
        return getKefB;
    }

    public void setGetKefB(Double getKefB) {
        this.getKefB = getKefB;
    }
}
