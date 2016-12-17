package final_project.mobile.lecture.ma01_20141025;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;

import final_project.mobile.lecture.ma01_20141025.adapter.CardAdapter;
import final_project.mobile.lecture.ma01_20141025.db.MyData;

public class Fragment1 extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<MyData> myDataset;

    ArrayList<HashMap<String,String>> card = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content1, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.i("ohodfd","SSF");
        // specify an adapter (see also next example)
        myDataset = new ArrayList<>();

        mRecyclerView = (RecyclerView)view.findViewById(R.id.my_recycler_view);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new CardAdapter(myDataset, getContext());
        // use this setting to imprve performance if you know that changes
        // in content do not change the layout size of the RecyclerView

        //mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);


        //cardview item 추가
        myDataset.add(new MyData(R.mipmap.ic_launcher,"복부 집중 운동", "1시간 15분",3));
        myDataset.add(new MyData(R.mipmap.ic_launcher,"복부 근력 운동", "45분",2));
        myDataset.add(new MyData(R.mipmap.ic_launcher,"옆구리 집중 운동", "1시간 3분",1));



        mRecyclerView.setAdapter(mAdapter);



    }
}
