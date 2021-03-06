package com.examples.gg.adapters;

import java.util.ArrayList;

import android.content.Context;

import com.examples.gg.data.Video;
import com.nostra13.universalimageloader.core.ImageLoader;

public class VaaForFavorites extends VideoArrayAdapter{

	protected FavoriteVideoRemovedCallback mfc;
	protected ArrayList<Video> mVideos;
	public VaaForFavorites(Context context,	ArrayList<Video> videos, ImageLoader imageLoader) {
		super(context, videos, imageLoader);
	}
	public VaaForFavorites(Context context,	ArrayList<Video> videos, ImageLoader imageLoader, FavoriteVideoRemovedCallback fc) {
		super(context, videos, imageLoader);
		this.mfc = fc;
		this.mVideos = videos;
	}
	
	@Override
	public int getCount() {
	    return mVideos.size();
	}
	
	@Override
	public void setMenuListener(int pos){
		holder.menuIcon.setOnClickListener(new MenuIconViewFavorites(this.mContext, holder,
				videos.get(pos), mfc));
		
	}

}
