package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nic.moef.R;

import java.util.List;

import models.SideMenu;

/**
 * Created by Yogesh on 6/23/2016.
 */
public class SideMenuAdapter extends BaseAdapter {
    private Context mcontext;
    private static LayoutInflater inflater = null;
    List<SideMenu> msideMenuListData;
    int icons[]={R.drawable.home,
            R.drawable.about,
            R.drawable.update,
            R.drawable.infocus,
            R.drawable.spotlight,
            R.drawable.division,
            R.drawable.award,
            R.drawable.publication,
            R.drawable.reports,
            R.drawable.faq,
            R.drawable.settings,
            R.drawable.blog,
            R.drawable.feedback};
    public SideMenuAdapter(Context context, List<SideMenu> list) {
        this.mcontext = context;
        this.msideMenuListData = list;
        inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return msideMenuListData.size();
    }

    @Override
    public Object getItem(int i) {
        return msideMenuListData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        View rowView;
        rowView = inflater.inflate(R.layout.sidemenu_group, null);
        TextView category = (TextView) rowView.findViewById(R.id.lblListHeader);
        category.setText(msideMenuListData.get(position).getCateGoryName());
        ImageView menuIcon = (ImageView) rowView.findViewById(R.id.icon);
        menuIcon.setImageResource(icons[position]);
        return rowView;
    }
}
