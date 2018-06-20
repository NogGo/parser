package dto;

public class Article{
    private String url;
    private String name;
    private String  periud;

    public Article(String url) {
        this.url = url;
    }

    public Article(String url, String name, String periud) {
        this.url = url;
        this.name = name;
        this.periud = periud;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String  getPeriud() {
        return periud;
    }

    public void setPeriud(String  periud) {
        this.periud = periud;
    }

    @Override
    public String toString() {
        return "Article{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}