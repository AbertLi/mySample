package one.example.com.mysample.main.webservice.fz;
public class TopicBean {
    private String id;
    private String alt;//电影详情的网页
    private String year;//年份
    private String title;//名字
    private String original_title;//原标题
    private String collect_count;//集合计数
    private String subtype;//类型（电影或者小说）

    private String[] genres;//内容类型
    private String[] images;//图片

    private Director[] directors;//导演
    private Cast[] Cast;//演员
    private Rating[] rating;//评分

    //导演
    class Director {
    }
    //演员
    class Cast {
    }
    //评分
    class Rating {
    }









    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(String collect_count) {
        this.collect_count = collect_count;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public Director[] getDirectors() {
        return directors;
    }

    public void setDirectors(Director[] directors) {
        this.directors = directors;
    }

    public TopicBean.Cast[] getCast() {
        return Cast;
    }

    public void setCast(TopicBean.Cast[] cast) {
        Cast = cast;
    }

    public Rating[] getRating() {
        return rating;
    }

    public void setRating(Rating[] rating) {
        this.rating = rating;
    }
}
