package com.example.kaushik.csi_lnmiit.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.example.kaushik.csi_lnmiit.R;
import com.example.kaushik.csi_lnmiit.utils.Constant;


public class HomeFragment extends Fragment {

	private boolean mSearchCheck;
	private TextView mTxtHome;

	public HomeFragment newInstance(String text){
		HomeFragment mFragment = new HomeFragment();
		Bundle mBundle = new Bundle();
		mBundle.putString(Constant.TEXT_FRAGMENT, text);
		mFragment.setArguments(mBundle);
		return mFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// TODO Auto-generated method stub		
		View rootView = inflater.inflate(R.layout.home_fragment, container, false);

		mTxtHome = (TextView) rootView.findViewById(R.id.txthome);
		mTxtHome.setText(getArguments().getString(Constant.TEXT_FRAGMENT));

		rootView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT ));
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.menu_main, menu);

//		SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(SEARCH));
//		searchView.setQueryHint(this.getString(R.string.search));
//
//		((EditText)searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text))
//				.setHintTextColor(getResources().getColor(R.color.white));
//		searchView.setOnQueryTextListener(OnQuerySearchView);
//
//		menu.findItem(ADD).setVisible(true);
//		menu.findItem(UPDATE).setVisible(true);
//		menu.findItem(SEARCH).setVisible(true);

		mSearchCheck = false;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub


		return true;
	}

	private OnQueryTextListener OnQuerySearchView = new OnQueryTextListener() {

		@Override
		public boolean onQueryTextSubmit(String arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean onQueryTextChange(String arg0) {
			// TODO Auto-generated method stub
			if (mSearchCheck){
				// implement your search here
			}
			return false;
		}
	};
}
