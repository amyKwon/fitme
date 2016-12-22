package final_project.mobile.lecture.ma01_20141025.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import final_project.mobile.lecture.ma01_20141025.MyViewHolder;
import final_project.mobile.lecture.ma01_20141025.R;
import final_project.mobile.lecture.ma01_20141025.db.MyData;

/**
 * Created by KwonYeJin on 2016. 12. 15..
 */
public class CardAdapter extends RecyclerView.Adapter<MyViewHolder>{

    private ArrayList<MyData> mDataset;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    Context c;
    MyViewHolder vh;
    // Provide a suitable constructor (depends on the kind of dataset)
    public CardAdapter(ArrayList<MyData> myDataset, Context context) {
        c = context;
        mDataset = myDataset;
    }

    @Override
    public  MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
        vh = new MyViewHolder(v);
        Log.i("ohdoking","!23312");
        return vh;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.iv_exerImg.setOnClickListener(listener);
        holder.tv_exerName.setText(mDataset.get(position).getCourse_name());
        holder.tv_reqTime.setText((CharSequence) mDataset.get(position).getReqTime());

        //holder.tv_stateCnt.setText(mDataset.get(position).getCount());
        Log.i("ohdoking","!??");
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            //cardview 전체를 아우르는 뷰에 onclicklistener 걸기, RecyclerView에는 listview의 onItemClickListener가 존재하지 않음.
            @Override
            public void onClick(View v) {
                //상세페이지
            }
        });
    }

    Button.OnClickListener listener = new View.OnClickListener() {
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.iv_exerImg:
                    //상세 페이지로 intent
                    break;
            }
        }
    };

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
