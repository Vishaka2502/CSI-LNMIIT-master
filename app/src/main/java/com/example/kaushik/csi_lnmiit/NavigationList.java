package com.example.kaushik.csi_lnmiit;

import android.content.Context;
import android.util.SparseIntArray;

import com.example.kaushik.csi_lnmiit.adapter.NavigationItemAdapter;
import com.example.kaushik.csi_lnmiit.utils.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vishaka on 12/13/2015.
 */
public class NavigationList {

    public static List<NavigationItemAdapter> getNavigationAdapter(
            Context context, List<Integer> listItemsHeader, SparseIntArray sparce, List<Integer> listItemsHide) {

        List<NavigationItemAdapter> mList = new ArrayList<NavigationItemAdapter>();
        String[] mMenuItems = context.getResources().getStringArray(R.array.nav_menu_items);

        int count = -1;
        boolean isheader = false;
        boolean isVisible = false;

        for (int i = 0; i < mMenuItems.length; i++) {

            String title = mMenuItems[i];
            NavigationItemAdapter itemNavigation;


            if (sparce != null) {
                count = sparce.get(i, -1);
            }

            if (listItemsHeader != null) {
                isheader = listItemsHeader.contains(i);
            }

            if (listItemsHide != null) {
                isVisible = listItemsHide.contains(i);
            }

            itemNavigation = new NavigationItemAdapter(title, Utils.iconNavigation[i], isheader, count, !isVisible);
            mList.add(itemNavigation);
        }

        return mList;
    }
}

