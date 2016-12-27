package final_project.mobile.lecture.ma01_20141025.dto;

/**
 * Created by KwonYeJin on 2016. 12. 26..
 */
public class memberData {
    private int _id;

    public memberData(int _id, String name, String age, int gender, String height, String weight, String gweight, int kind) {
        this._id = _id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.gweight = gweight;
        this.kind = kind;
    }

    public int get_id() {

        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    private String name;
    private String age;
    private int gender;
    private String height;
    private String weight;
    private String gweight;
    private int kind;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getGweight() {
        return gweight;
    }

    public void setGweight(String gweight) {
        this.gweight = gweight;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }
}
