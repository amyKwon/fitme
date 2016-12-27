package final_project.mobile.lecture.ma01_20141025.dto;

/**
 * Created by KwonYeJin on 2016. 10. 13..
 */
public class BlogData {
    private String Title;
    private String description;

    public String getBloggerlink() {
        return bloggerlink;
    }

    public void setBloggerlink(String bloggerlink) {
        this.bloggerlink = bloggerlink;
    }

    private String link;
    private String bloggerlink;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
