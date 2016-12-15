package final_project.mobile.lecture.ma01_20141025;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by KwonYeJin on 2016. 12. 15..
 */
public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ImageView iv_exerImg;
    public TextView tv_exerName;
    public TextView tv_reqTime;
    public TextView tv_stateCnt;
    public CardView cardView;

    public MyViewHolder(View v) {
        super(v);

        iv_exerImg = (ImageView)v.findViewById(R.id.iv_exerImg);
        tv_exerName = (TextView)v.findViewById(R.id.tv_exerName);
        tv_reqTime = (TextView)v.findViewById(R.id.tv_reqTime);
        tv_stateCnt = (TextView)v.findViewById(R.id.tv_stateCnt);
        cardView = (CardView)v.findViewById(R.id.cardview);

    }

    @Override
    public void onClick(View view) {

    }
}
