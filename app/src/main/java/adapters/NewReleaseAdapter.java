package adapters;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nic.moef.R;

import java.util.ArrayList;

import models.NewReleaseResponse;

/**
 * Created by king on 4/17/2017.
 */
public class NewReleaseAdapter extends RecyclerView.Adapter<NewReleaseAdapter.MyViewHolder> {

    private ArrayList<NewReleaseResponse> newReleaseResponseList;

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


    public NewReleaseAdapter(ArrayList<NewReleaseResponse> list) {
        this.newReleaseResponseList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.new_release_item_row, parent, false);

        return new MyViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final NewReleaseResponse releaseResponse = newReleaseResponseList.get(position);
        holder.title.setText(releaseResponse.getTitle());
        holder.readMore.setText("Read More..."+ releaseResponse.getFileSize() +"bytes");
        holder.readMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPDF("http://www.moef.nic.in/"+releaseResponse.getFilePath(),holder.readMore.getContext());
            }
        });
        holder.content.setText("Accounts at a Glance of the MoEF&CC for 2015-2016");
        //holder.title.getContext(); needed for the context.

    }
private void openPDF(String url,Context context){
    Uri path = Uri.parse(url);
    Intent pdfOpenintent = new Intent(Intent.ACTION_VIEW);
    pdfOpenintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    pdfOpenintent.setDataAndType(path, "application/pdf");
    try {
         context.startActivity(pdfOpenintent);
    }
    catch (ActivityNotFoundException e) {

    }
}

    @Override
    public int getItemCount() {
        return newReleaseResponseList.size();
    }
}
