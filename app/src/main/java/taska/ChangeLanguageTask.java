package taska;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.nic.moef.R;

import java.util.ArrayList;
import java.util.List;

import adapters.SideMenuAdapter;
import models.SideMenu;

/**
 * Created by king on 4/9/2017.
 */

public class ChangeLanguageTask extends AsyncTask<String, Void, String> {
    private Context mContext;
    ProgressDialog progressDialog;
    private ListView mlistView;
    private int mValue=1;
    List<SideMenu> sideMenusList=new ArrayList<SideMenu>();
    public ChangeLanguageTask(Context context, ListView listView,int value) {
        this.mContext = context;
        this.mlistView=listView;
        this.mValue=value;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(mContext, "FeedBack", "Please wait...", true, false);
    }

    @Override
    protected String doInBackground(String... params) {
        String menuArraye[]=null;
        if(mValue==1)
        menuArraye=mContext.getResources().getStringArray(R.array.SideMenu_English);
        else if(mValue==2)
            menuArraye=mContext.getResources().getStringArray(R.array.SideMenu_Hindi);
        for(int i=0;i<menuArraye.length;i++){
            SideMenu sideMenu=new SideMenu();
            sideMenu.setCateGoryName(menuArraye[i]);
            sideMenu.setGetCateGoryId(i+"");
            sideMenusList.add(sideMenu);
        }
        // Show response on activity
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        progressDialog.dismiss();
        SideMenuAdapter sideMenuAdapter=new SideMenuAdapter(mContext,sideMenusList);
        // Assign adapter to ListView
        mlistView.setAdapter(sideMenuAdapter);
    }
}

