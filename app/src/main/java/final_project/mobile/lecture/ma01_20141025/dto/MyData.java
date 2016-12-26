package final_project.mobile.lecture.ma01_20141025.dto;

/**
 * Created by KwonYeJin on 2016. 12. 15..
 */
public class MyData {

    private int _id;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    private int img;
    private String course_name;
    private String reqTime;
    private int count;

    public MyData(int img, String course_name, String reqTime, int count) {
        this.img = img;
        this.course_name = course_name;
        this.reqTime = reqTime;
        this.count = count;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getCourse_name() {
        return course_name;
    }



    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getReqTime() {
        return reqTime;
    }

    public void setReqTime(String reqTime) {
        this.reqTime = reqTime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
