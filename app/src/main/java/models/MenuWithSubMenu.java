package models;

/**
 * Created by vishalkheterpal on 5/2/17.
 */

public class MenuWithSubMenu {

    private String title;
    private String linkPath;
    private String langcode;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLinkPath() {
        return linkPath;
    }

    public void setLinkPath(String linkPath) {
        this.linkPath = linkPath;
    }

    public String getLangcode() {
        return langcode;
    }

    public void setLangcode(String langcode) {
        this.langcode = langcode;
    }
}
