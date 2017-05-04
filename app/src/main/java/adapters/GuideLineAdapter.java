package adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nic.moef.R;

import java.util.ArrayList;

import models.GuideLinesMetaData;
import models.NewReleaseResponse;

/**
 * Created by vishalkheterpal on 5/4/17.
 */

public class GuideLineAdapter extends RecyclerView.Adapter<GuideLineAdapter.MyViewHolder> {
    private ArrayList<GuideLinesMetaData> guideLinesMetaData;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, readMore, content;
        public ImageView imageView;
        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.titleTxt);
            readMore = (TextView) view.findViewById(R.id.readMoreTxt);
            content = (TextView) view.findViewById(R.id.contentTxt);
            imageView= (ImageView) view.findViewById(R.id.newReleaseImage);
        }
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.new_release_item_row, parent, false);

        return new MyViewHolder(itemView);
    }
    public GuideLineAdapter(ArrayList<GuideLinesMetaData> list) {
        this.guideLinesMetaData = list;
    }

    @Override
    public void onBindViewHolder(final GuideLineAdapter.MyViewHolder holder, int position) {
        final GuideLinesMetaData releaseResponse = guideLinesMetaData.get(position);
        holder.title.setText(releaseResponse.getTitle());
        //holder.readMore.setText("Read More..."+ releaseResponse.getFileSize() +"bytes");
        holder.readMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //openPDF("http://www.moef.nic.in/"+releaseResponse.getFilePath(),holder.readMore.getContext());
            }
        });
        holder.content.setText("Accounts at a Glance of the MoEF&CC for 2015-2016");
        //holder.title.getContext(); needed for the context.

    }
    @Override
    public int getItemCount() {
        return guideLinesMetaData.size();
    }
}
