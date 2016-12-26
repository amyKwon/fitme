package final_project.mobile.lecture.ma01_20141025.dto;

/**
 * Created by KwonYeJin on 2016. 12. 23..
 */
public class fmyData {
    private String f_courseName;
    private String f_courseTime;
    private int f_courseCnt;
    private int f_Img;
    private int _id;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getF_courseName() {
        return f_courseName;
    }

    public void setF_courseName(String f_courseName) {
        this.f_courseName = f_courseName;
    }

    public String getF_courseTime() {
        return f_courseTime;
    }

    public void setF_courseTime(String f_courseTime) {
        this.f_courseTime = f_courseTime;
    }

    public int getF_courseCnt() {
        return f_courseCnt;
    }

    public void setF_courseCnt(int f_courseCnt) {
        this.f_courseCnt = f_courseCnt;
    }

    public int getF_Img() {
        return f_Img;
    }

    public void setF_Img(int f_Img) {
        this.f_Img = f_Img;
    }
}
