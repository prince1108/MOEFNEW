package adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.github.siyamed.shapeimageview.RoundedImageView;
import com.nic.moef.R;
import com.squareup.picasso.Picasso;


/**
 * Created by Ravi on 8/25/2016.
 */
public class CustomPagerAdapter extends PagerAdapter {
    Context mContext;
    LayoutInflater mLayoutInflater;

    String[] imageIDs = {"http://www.moef.nic.in/sites/default/files/media_gallery/7.jpg","http://www.moef.nic.in/sites/default/files/media_gallery/04.jpg",
            "http://www.moef.nic.in/sites/default/files/media_gallery/03.jpg","http://www.moef.nic.in/sites/default/files/media_gallery/06.jpg" };
    public CustomPagerAdapter(Context context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return imageIDs.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.homefrag_pager_item,
                container, false);
        final RoundedImageView imageView = (RoundedImageView) itemView.findViewById(R.id.imageView);
//        imageView.setImageResource(imageIDs[position]);
        Picasso.with(mContext).load(imageIDs[position]).resize(300,250).into(imageView);
//        Picasso.with(mContext)
//                .load(imageIDs[position]).resize(300,250)
//                .into(new Target() {
//                    @Override
//                    public void onBitmapLoaded (final Bitmap bitmap, Picasso.LoadedFrom from){
//            /* Save the bitmap or do something with it here */
//
//                        //Set it in the ImageView
//                        imageView.setImageBitmap(Util.getclip(bitmap));
//                    }
//
//                    @Override
//                    public void onBitmapFailed(Drawable errorDrawable) {
//
//                    }
//
//                    @Override
//                    public void onPrepareLoad(Drawable placeHolderDrawable) {
//
//                    }
//                });
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
