package com.rs.playlist;

import java.util.ArrayList;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.examples.gg.adapters.EntryAdapter;
import com.examples.gg.data.EntryItem;
import com.examples.gg.data.Item;
import com.examples.gg.data.SectionItem;
import com.examples.gg.loadMore.FavoritesFragment;
import com.examples.gg.loadMore.HistoryFragment;
import com.examples.gg.loadMore.PlaylistFragment;
import com.examples.gg.loadMore.SubscriptionFragment;
import com.examples.gg.settings.SettingsActivity;

public class SideMenuActivity extends SherlockFragmentActivity {

	// Declare Variable
	DrawerLayout mDrawerLayout;
	ListView mDrawerList;
	ActionBarDrawerToggle mDrawerToggle;
	ArrayList<Item> items = new ArrayList<Item>();
	ActionBar mActionBar;
	EntryAdapter eAdapter;

	private FragmentManager fm;
	private final String firstTimePrefs = "firsttime";
	private String appName;
	private String packageName;
	private String mail;

	// private InterstitialAd interstitial;

	// private boolean doubleBackToExitPressedOnce = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drawer_main);

		// Create the interstitial
		// interstitial = new InterstitialAd(this,
		// "ca-app-pub-6718707824713684/7369125856");

		// Create ad request
		// AdRequest adRequest = new AdRequest();
		// adRequest.addTestDevice("5E4CA696BEB736E734DD974DD296F11A");
		// Begin loading your interstitial
		// interstitial.loadAd(adRequest);

		// Set Ad Listener to use the callbacks below
		// interstitial.setAdListener(this);
		appName = getResources().getString(R.string.app_name);
		packageName = getResources().getString(R.string.package_name);
		mail = getResources().getString(R.string.mail);
		// Initial fragment manager
		fm = this.getSupportFragmentManager();

		// Locate DrawerLayout in drawer_main.xml
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		// Locate ListView in drawer_main.xml
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		// Do not allow list view to scroll over
		// mDrawerList.setOverScrollMode(ListView.OVER_SCROLL_NEVER);

		// Set a custom shadow that overlays the main content when the drawer
		// opens
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);

		// Feed
		items.add(new SectionItem("Everyday's Feed"));
		items.add(new EntryItem("Youtube Mix", "Your daily top tracks",
				R.drawable.live));

		// Favorites
		items.add(new SectionItem("My Youtube"));
//		items.add(new EntryItem("Search", "Videos, channels, Playlists", R.drawable.abs__ic_search));
		items.add(new EntryItem("Favorites", "Things I like", R.drawable.medal));
		items.add(new EntryItem("Subscriptions", "Local subscriptions",
				R.drawable.upcoming));
		items.add(new EntryItem("History", "Watched videos", R.drawable.minutes));

		// Music Playlists
		items.add(new SectionItem("Popular Music Playlists"));
		items.add(new EntryItem("Rihanna", null, R.drawable.highlights));
		items.add(new EntryItem("One Direction", null, R.drawable.highlights));
		items.add(new EntryItem("Katy Perry", null, R.drawable.highlights));
		items.add(new EntryItem("Justin Bieber", null, R.drawable.highlights));
		items.add(new EntryItem("Taylor Swift", null, R.drawable.highlights));
		items.add(new EntryItem("Bruno Mars", null, R.drawable.highlights));

		// "About" section
		items.add(new SectionItem("About App"));
		items.add(new EntryItem("Feedback", "Help us make it better",
				R.drawable.feedback));

		items.add(new EntryItem("Share", "Share our app",
				R.drawable.ic_action_social_share));

		items.add(new EntryItem("Rate", "Like it?",
				R.drawable.ic_action_rating_good));

		eAdapter = new EntryAdapter(this, items);

		// setListAdapter(adapter);

		// Set the MenuListAdapter to the ListView
		mDrawerList.setAdapter(eAdapter);

		// Capture button clicks on side menu
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		// Enable ActionBar app icon to behave as action to toggle nav drawer
		mActionBar = getSupportActionBar();

		mActionBar.setHomeButtonEnabled(true);
		mActionBar.setDisplayHomeAsUpEnabled(true);
		// mActionBar.setTitle("Main Menu");

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {

			public void onDrawerClosed(View view) {
				// TODO Auto-generated method stub
				super.onDrawerClosed(view);
			}

			public void onDrawerOpened(View drawerView) {
				// TODO Auto-generated method stub
				super.onDrawerOpened(drawerView);
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			selectItem(1);

		}
		isFirstTimeUser();
//		openDrawerOnStart();
//		this.validatingTips(this);

	}

	private void isFirstTimeUser() {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(this);
		String result = prefs.getString(firstTimePrefs, "");
		if (result.equals("")) {
			// This is first time to start the activity
			// Open drawer for the user
			Handler handler = new Handler();
			handler.postDelayed(new Runnable() {
				public void run() {
					mDrawerLayout.openDrawer(mDrawerList);
				}
			}, 0);

			// Save the state
			prefs.edit().putString(firstTimePrefs, "No").commit();
		}

	}

	private void openDrawerOnStart() {
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			public void run() {
				mDrawerLayout.openDrawer(mDrawerList);
			}
		}, 0);
	}

	private void validatingTips(SherlockFragmentActivity sfa) {
		TipsValidator tv = new TipsValidator(sfa);
		tv.Validation();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.menu, menu);
		// SearchView searchView = new SearchView(getSupportActionBar()
		// .getThemedContext());
		// searchView.setQueryHint("Search Youtube");
		// searchView.setOnQueryTextListener(this);
		//
		// menu.add(0, 20, 0, "Search")
		// .setIcon(R.drawable.abs__ic_search)
		// .setActionView(searchView)
		// .setShowAsAction(
		// MenuItem.SHOW_AS_ACTION_IF_ROOM
		// | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (item.getItemId() == android.R.id.home) {

			Handler handler = new Handler();

			if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
				handler.postDelayed(new Runnable() {
					public void run() {
						mDrawerLayout.closeDrawer(mDrawerList);
					}
				}, 0);
			} else {
				handler.postDelayed(new Runnable() {
					public void run() {
						mDrawerLayout.openDrawer(mDrawerList);
					}
				}, 0);
			}
		}

		if (item.getItemId() == R.id.menu_settings) {

			// Start setting activity
			Intent i = new Intent(this, SettingsActivity.class);
			startActivity(i);

		}

		return super.onOptionsItemSelected(item);
	}

	// The click listener for ListView in the navigation drawer
	public class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// if (row != null) {
			// row.setBackgroundColor(Color.WHITE);
			// }
			// row = view;
			// view.setBackgroundColor(Color.YELLOW);
			//
			selectItem(position);
		}
	}

	private void selectItem(int position) {

		// Set the indicator in drawer to correct position
		// if (position <= 10)
		setDrawerIndicator(position);

		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

		// Clear the fragment stack first
		clearFragmentStack();

		mDrawerList.setItemChecked(position, true);

		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			public void run() {
				mDrawerLayout.closeDrawer(mDrawerList);
			}
		}, 0);

		switch (position) {
		// Section divider case 0------------------------
		case 1:
			// News
			ft.replace(R.id.content_frame, new PlaylistFragment("Youtube Mix",
					"MCUS"));
			break;
		// Section divider case 2------------------------
		case 3:
			ft.replace(R.id.content_frame, new FavoritesFragment());
			break;

		case 4:
			ft.replace(R.id.content_frame, new SubscriptionFragment());
			break;
		case 5:
			ft.replace(R.id.content_frame, new HistoryFragment());
			break;

		// Section divider case 6------------------------

		case 7:
			ft.replace(R.id.content_frame, new PlaylistFragment("Rihanna",
					"PL59B464DFF08AEE66"));
			break;
		case 8:
			ft.replace(R.id.content_frame, new PlaylistFragment(
					"One Direction", "PLy5C65bvjyurvHyQdB8KtjB2M_HQixXlA"));
			break;
		case 9:
			ft.replace(R.id.content_frame, new PlaylistFragment("Katy Perry",
					"PLwn8mT6-a4yL8IUoORhtMDH6bsdv8en80"));
			break;
		case 10:
			ft.replace(R.id.content_frame, new PlaylistFragment(
					"Justin Bieber", "PL623E00CA581C3D83"));
			break;
		case 11:
			ft.replace(R.id.content_frame, new PlaylistFragment("Taylor Swift",
					"PLdnEKz0ib5DD8X0JeJ9MgbTLKNESWNlRe"));
			break;
		case 12:
			ft.replace(R.id.content_frame, new PlaylistFragment("Bruno Mars",
					"PL6A33993EF508A934"));
			break;

		// Section divider case 12------------------------
		case 14:
			// Feedback

			Intent email = new Intent(Intent.ACTION_VIEW);
			email.setData(Uri.parse(mail));
			startActivity(Intent.createChooser(email, "Send feedback via.."));
			// startActivity(email);
			break;

		case 15:
			// Share Dota2TV
			Intent sendIntent = new Intent();
			sendIntent.setAction(Intent.ACTION_SEND);
			sendIntent.putExtra(Intent.EXTRA_TEXT,
					"https://play.google.com/store/apps/details?id="
							+ packageName);
			sendIntent.setType("text/plain");
			startActivity(Intent.createChooser(sendIntent, "Share " + appName
					+ " to.."));
			// startActivity(sendIntent);
			break;

		case 16:
			// Rate Dota2TV
			Intent rateIntent = new Intent(Intent.ACTION_VIEW);
			// Try Google play
			rateIntent.setData(Uri.parse("market://details?id=" + packageName));
			if (tryStartActivity(rateIntent) == false) {
				// Market (Google play) app seems not installed, let's try to
				// open a webbrowser
				rateIntent.setData(Uri
						.parse("https://play.google.com/store/apps/details?id="
								+ packageName));
				if (tryStartActivity(rateIntent) == false) {
					// Well if this also fails, we have run out of options,
					// inform the user.
					Toast.makeText(
							this,
							"Could not open Google Play, please install Google Play.",
							Toast.LENGTH_SHORT).show();
				}
			}

			break;
		}

		ft.commit();
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggles
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	// Clear fragment back stack
	public void clearFragmentStack() {
		fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
	}

	// Handles exit
	// @Override
	// public void onBackPressed() {
	// if (fm.getBackStackEntryCount() == 0) {
	//
	// // No fragment in back stack
	//
	// if (doubleBackToExitPressedOnce) {
	// super.onBackPressed();
	// return;
	// }
	// this.doubleBackToExitPressedOnce = true;
	// Toast.makeText(this, "Please click BACK again to exit",
	// Toast.LENGTH_SHORT).show();
	//
	// // reset doubleBackToExitPressedOnce to false after 2 seconds
	// new Handler().postDelayed(new Runnable() {
	//
	// @Override
	// public void run() {
	// doubleBackToExitPressedOnce = false;
	//
	// }
	// }, 2000);
	// } else {
	//
	// // Fragment back stack is empty
	//
	// super.onBackPressed();
	// }
	// }

	public void setDrawerIndicator(int position) {
		for (Item i : items)
			i.setUnchecked();
		items.get(position).setChecked();
		eAdapter.notifyDataSetChanged();
	}

	private boolean tryStartActivity(Intent aIntent) {
		try {
			startActivity(aIntent);
			return true;
		} catch (ActivityNotFoundException e) {
			return false;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// if ((mForumFragment != null && keyCode == KeyEvent.KEYCODE_BACK)
		// && mForumFragment.canGoBack()) {
		// // if Back key pressed and webview can navigate to previous page
		// mForumFragment.goBack();
		// // go back to previous page
		// return true;
		// } else {
		// this.finish();
		// // finish the activity
		// }
		return super.onKeyDown(keyCode, event);
	}

}
