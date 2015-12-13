package com.example.kaushik.csi_lnmiit;

import android.app.Activity;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.kaushik.csi_lnmiit.adapter.NavigationAdapter;
import com.example.kaushik.csi_lnmiit.fragments.HomeFragment;
import com.example.kaushik.csi_lnmiit.fragments.AboutFragment;
import com.example.kaushik.csi_lnmiit.utils.Constant;
import com.example.kaushik.csi_lnmiit.utils.Menus;
import com.example.kaushik.csi_lnmiit.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class NavigationMain extends AppCompatActivity {

    private int mLastPosition = 1;
    private ListView mListDrawer;

    private DrawerLayout mLayoutDrawer;
    private RelativeLayout mUserDrawer;
    private RelativeLayout mRelativeDrawer;

    private FragmentManager mFragmentManager;
    private NavigationAdapter mNavigationAdapter;
    private ActionBarDrawerToggleCompat mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setIcon(R.drawable.csi_logo);

        setContentView(R.layout.navigation_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mListDrawer = (ListView) findViewById(R.id.listDrawer);
        mRelativeDrawer = (RelativeLayout) findViewById(R.id.relativeDrawer);
        mLayoutDrawer = (DrawerLayout) findViewById(R.id.layoutDrawer);

        mUserDrawer = (RelativeLayout) findViewById(R.id.userDrawer);
        mUserDrawer.setOnClickListener(userOnClick);

        if (mListDrawer != null) {

            // All header menus should be informed here
            // listHeader.add(MENU POSITION)
            List<Integer> mListHeader = new ArrayList<Integer>();
            mListHeader.add(0);
            mListHeader.add(1);
            mListHeader.add(6);
            mListHeader.add(10);
            mListHeader.add(11);

            mNavigationAdapter = new NavigationAdapter(this, NavigationList.getNavigationAdapter(this, mListHeader, null, null));
        }

        mListDrawer.setAdapter(mNavigationAdapter);
        mListDrawer.setOnItemClickListener(new DrawerItemClickListener());

        mDrawerToggle = new ActionBarDrawerToggleCompat(this, mLayoutDrawer);
        mLayoutDrawer.setDrawerListener(mDrawerToggle);

        if (savedInstanceState != null) {
            setLastPosition(savedInstanceState.getInt(Constant.LAST_POSITION));

            setTitleFragments(mLastPosition);
            mNavigationAdapter.resetarCheck();
            mNavigationAdapter.setChecked(mLastPosition, true);
        }else{
            setLastPosition(mLastPosition);
            setFragmentList(mLastPosition);
        }
    }

    private void setFragmentList(int position){

        Fragment mFragment = null;
        mFragmentManager = getSupportFragmentManager();

        switch (position) {
            case Constant.MENU_HOME:
                mFragment = new HomeFragment().newInstance(Utils.getTitleItem(NavigationMain.this, Constant.MENU_HOME));
                break;
            case Constant.MENU_ABOUT:
                mFragment = new AboutFragment().newInstance(Utils.getTitleItem(NavigationMain.this, Constant.MENU_ABOUT));
                break;
        }

        if (mFragment != null){
            setTitleFragments(mLastPosition);
            mNavigationAdapter.resetarCheck();
            mNavigationAdapter.setChecked(position, true);
            mFragmentManager.beginTransaction().replace(R.id.content_frame, mFragment).commit();
        }
    }

    private void hideMenus(Menu menu, int position) {

        boolean drawerOpen = mLayoutDrawer.isDrawerOpen(mRelativeDrawer);

        switch (position) {
            case Constant.MENU_HOME:

                break;

            case Constant.MENU_ABOUT:

                break;
        }
    }

    private void setTitleFragments(int position){
        setIconActionBar(Utils.iconNavigation[position]);
        setSubtitleActionBar(Utils.getTitleItem(NavigationMain.this, position));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
        super.onSaveInstanceState(outState);
        outState.putInt(Constant.LAST_POSITION, mLastPosition);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case Menus.HOME:
                if (mLayoutDrawer.isDrawerOpen(mRelativeDrawer)) {
                    mLayoutDrawer.closeDrawer(mRelativeDrawer);
                } else {
                    mLayoutDrawer.openDrawer(mRelativeDrawer);
                }
                return true;
            default:

                if (mDrawerToggle.onOptionsItemSelected(item)) {
                    return true;
                }

                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        hideMenus(menu, mLastPosition);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    public void setTitleActionBar(CharSequence information) {
        getSupportActionBar().setTitle(information);
    }

    public void setSubtitleActionBar(CharSequence information) {
        getSupportActionBar().setSubtitle(information);
    }

    public void setIconActionBar(int icon) {
        getSupportActionBar().setIcon(icon);
    }

    public void setLastPosition(int position){
        this.mLastPosition = position;
    }

    private class ActionBarDrawerToggleCompat extends ActionBarDrawerToggle {

        public ActionBarDrawerToggleCompat(Activity mActivity, DrawerLayout mDrawerLayout){
            super(
                    mActivity,
                    mDrawerLayout,
                    R.drawable.ic_action_navigation_drawer,
                    R.string.drawer_open,
                    R.string.drawer_close);
        }

        @Override
        public void onDrawerClosed(View view) {
            supportInvalidateOptionsMenu();
        }

        @Override
        public void onDrawerOpened(View drawerView) {
            mNavigationAdapter.notifyDataSetChanged();
            supportInvalidateOptionsMenu();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // TODO Auto-generated method stub
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            setLastPosition(position);
            setFragmentList(mLastPosition);
            mLayoutDrawer.closeDrawer(mRelativeDrawer);
        }
    }

    private View.OnClickListener userOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            mLayoutDrawer.closeDrawer(mRelativeDrawer);
        }
    };

}