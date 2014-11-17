package com.examples.gg.loadMore;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.os.Bundle;

import com.examples.gg.feedManagers.FeedManager_Base_v3;

public class SearchResultFragment extends LoadMore_Base {

	private String mQuery;
	private String mTitle;
	private String api;

//	public SearchResultFragment(){}
//	public SearchResultFragment(String query, String title) {
//		this.mQuery = query;
//		this.mTitle = title;
//	}
	public static final SearchResultFragment newInstance(String query, String title)
	{
		SearchResultFragment f = new SearchResultFragment();
	    Bundle bdl = new Bundle(2);
	    bdl.putString("query", query);
	    bdl.putString("title", title);
	    f.setArguments(bdl);
	    return f;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mQuery = getArguments().getString("query");
        this.mTitle = getArguments().getString("title");
    }
	
	@Override
	public void Initializing() {
		if (mTitle != null) {
			abTitle = mTitle;
		}

		// Get the query for search
		if (mQuery != null) {
			// Add the complete API
			String cAPI = buildAPI(mQuery);
			if (cAPI != null) {
				API.add(cAPI);
			}
		}
		// set a feed manager
		feedManager = new FeedManager_Base_v3("video", api, browserKey, gv, numOfResults);

		// Show menu
		setHasOptionsMenu(true);
		setOptionMenu(true, false);

	}

	public String buildAPI(String q) {
		
		try {
			// api =
			// "https://gdata.youtube.com/feeds/api/videos?q="+URLEncoder.encode(q,"UTF-8")+"&orderby=relevance&start-index=1&max-results=10&v=2&alt=json";
			api = "https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=20&order=relevance&q="
					+ URLEncoder.encode(q, "UTF-8")
					+ "&type=video&key="
					+ browserKey;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return api;
	}
}
