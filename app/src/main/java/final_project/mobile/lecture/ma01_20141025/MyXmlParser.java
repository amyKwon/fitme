package final_project.mobile.lecture.ma01_20141025;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

import final_project.mobile.lecture.ma01_20141025.dto.HealthCenterData;

/**
 * Created by cooling on 2016-10-05.
 */

public class MyXmlParser {


    public enum TagType { NONE, TITLE, TELEPHONE, ADDRESS, MAPX, MAPY};

    public MyXmlParser() {
    }



    public ArrayList<HealthCenterData> parse(String xml) {

        ArrayList<HealthCenterData> resultList = new ArrayList();
        HealthCenterData dto = null;

        TagType tagType = TagType.NONE;

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(xml));

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.END_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:

                        if (parser.getName().equals("item")) {
                            dto = new HealthCenterData();
                        } else if (parser.getName().equals("title")) {
                           //item 만들어지기전에도 API title 태그가 존재하므로 item이 나오기전 발생하면안됨,따라서 dto가 null이 아닐 경우에, 이미 만들어진 경우에만 작동하도록
                           if(dto != null) tagType = TagType.TITLE;

                        } else if (parser.getName().equals("telephone")) {

                            if(dto != null) tagType = TagType.TELEPHONE;

                        } else if (parser.getName().equals("address")) {

                            if(dto != null)tagType = TagType.ADDRESS;

                        }
                        else if (parser.getName().equals("mapx")) {

                            if(dto != null) tagType = TagType.MAPX;

                        }
                        else if (parser.getName().equals("mapy")) {

                            if(dto != null) tagType = TagType.MAPY;

                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("item")) {
                            //만든 dto 객체를 resultList에 저장해주고 dto 다시 초기화해주어야함
                            resultList.add(dto);
                            dto = null;
                        }
                        break;
                    case XmlPullParser.TEXT:
                        switch(tagType) {
                            case TITLE:
                                dto.setName(parser.getText());
                                break;
                            case TELEPHONE:
                                dto.setTelephone(parser.getText());
                                break;
                            case ADDRESS:
                                dto.setAddress(parser.getText());
                                break;
                            case MAPX:
                                dto.setMapx(Integer.parseInt(parser.getText()));
                                break;
                            case MAPY:
                                dto.setMapy(Integer.parseInt(parser.getText()));
                                break;
                        }
                        tagType = TagType.NONE;
                        break;
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultList;
    }



}

