package one.example.com.mysample.main.proje;

public class HeadVisibilityBean {
    private boolean imageBack = false;
    private boolean tvBack = false;
    private boolean imageClose = false;
    private boolean tvTitle = false;
    private boolean tvEdit = false;

    public HeadVisibilityBean(boolean imageBack, boolean tvBack, boolean imageClose, boolean tvTitle,
            boolean tvEdit) {
        this.imageBack = imageBack;
        this.tvBack = tvBack;
        this.imageClose = imageClose;
        this.tvTitle = tvTitle;
        this.tvEdit = tvEdit;
    }


    public boolean isImageBack() {
        return imageBack;
    }

    public void setImageBack(boolean imageBack) {
        this.imageBack = imageBack;
    }

    public boolean isTvBack() {
        return tvBack;
    }

    public void setTvBack(boolean tvBack) {
        this.tvBack = tvBack;
    }

    public boolean isImageClose() {
        return imageClose;
    }

    public void setImageClose(boolean imageClose) {
        this.imageClose = imageClose;
    }

    public boolean isTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(boolean tvTitle) {
        this.tvTitle = tvTitle;
    }

    public boolean isTvEdit() {
        return tvEdit;
    }

    public void setTvEdit(boolean tvEdit) {
        this.tvEdit = tvEdit;
    }
}
