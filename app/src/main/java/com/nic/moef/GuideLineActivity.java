package com.nic.moef;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import adapters.GuideLineAdapter;
import http.CallHttp;
import models.GuideLinesMetaData;
import models.NewReleaseResponse;
import utility.UrlConstants;

/**
 * Created by vishalkheterpal on 5/4/17.
 */

public class GuideLineActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ImageView backBtn;
    private GuideLineAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar abar = getSupportActionBar();
        View viewActionBar = getLayoutInflater().inflate(R.layout.header_xml, null);
        TextView textviewTitle = (TextView) viewActionBar.findViewById(R.id.actionbar_textview);
        textviewTitle.setText("GuideLines");
        abar.setCustomView(viewActionBar);
        abar.setDisplayShowCustomEnabled(true);
        abar.setDisplayShowTitleEnabled(false);
        setContentView(R.layout.activity_guidelines);
        mRecyclerView = (RecyclerView) findViewById(R.id.recylerview);
        backBtn = (ImageView) findViewById(R.id.backBtn);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        new DownLoadData().execute();
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private class DownLoadData extends AsyncTask {
        private String mUrl = UrlConstants.GuideLinesURL;
        @Override
        protected Object doInBackground(Object[] params) {

            try{

                CallHttp http = new CallHttp();
                String response =http.httpGetRequest(mUrl);
                return response;
            } catch (Exception e) {
                //return json;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            ArrayList<GuideLinesMetaData> items = new ArrayList<>();
            String response = (String)o;
            try {

                JSONArray jsonArray = new JSONArray(response);
                for (int i=0;i<jsonArray.length();i++) {
                    GuideLinesMetaData data = new GuideLinesMetaData();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String nid = jsonObject.optString("nid").toString();
                    data.setNid(nid);
                    String type = jsonObject.optString("type").toString();
                    data.setType(type);
                    String title = jsonObject.optString("title").toString();
                    data.setTitle(title);
//                    JSONArray filePathArray = jsonObject.getJSONArray("field_new_releases_upload");
//                    if(filePathArray.length()>0){
//                        try{
//                            JSONObject filePathObject = filePathArray.getJSONObject(0);
//                            String filePath = filePathObject.getString("filepath");
//                            data.setFilePath(filePath);
//                            String fileSize = filePathObject.getString("filesize");
//                            data.setFileSize(fileSize);
//                        }catch (Exception e){
//
//                        }
//
//                    }

                    items.add(data);
                }
                mAdapter = new GuideLineAdapter(items);
                mRecyclerView.setAdapter(mAdapter);
            }catch (Exception e){

            }

        }
    }
}
