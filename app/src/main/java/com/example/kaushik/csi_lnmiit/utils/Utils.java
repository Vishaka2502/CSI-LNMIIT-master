package com.example.kaushik.csi_lnmiit.utils;

import android.content.Context;

import com.example.kaushik.csi_lnmiit.R;

public class Utils {

	//Set all the navigation icons and always to set "zero 0" for the item is a category
	public static int[] iconNavigation = new int[] {
			R.drawable.ic_action_home, R.drawable.ic_action_about, R.drawable.ic_action_highlight,
			R.drawable.ic_action_message, R.drawable.ic_action_message, R.drawable.ic_action_message,
			R.drawable.ic_action_events, 0, 0 ,0, R.drawable.ic_action_contact, R.drawable.ic_action_csi_india};

	//get title of the item navigation
	public static String getTitleItem(Context context, int posicao){
		String[] titulos = context.getResources().getStringArray(R.array.nav_menu_items);
		return titulos[posicao];
	}

}
