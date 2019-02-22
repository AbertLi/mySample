package one.example.com.mysample.main.proje;

public class HeadBean {
    private String left;
    private String center;
    private String right;

    public HeadBean(String left, String center, String right) {
        this.left = left;
        this.center = center;
        this.right = right;
    }


    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }
}
