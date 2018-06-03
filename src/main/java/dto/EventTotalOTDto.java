package dto;

public class EventTotalOTDto {

    private Double title;
    private Double kefB;
    private Double kefM;

    public Double getTitle() {
        return title;
    }

    public void setTitle(Double title) {
        this.title = title;
    }

    public Double getKefB() {
        return kefB;
    }

    public void setKefB(Double kefB) {
        this.kefB = kefB;
    }

    public Double getKefM() {
        return kefM;
    }

    public void setKefM(Double kefM) {
        this.kefM = kefM;
    }

    @Override
    public String toString() {
        return "EventTotalOTDto{" +
                "title=" + title +
                ", kefB=" + kefB +
                ", kefM=" + kefM +
                '}';
    }
}
