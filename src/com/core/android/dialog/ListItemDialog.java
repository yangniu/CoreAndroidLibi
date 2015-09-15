package com.core.android.dialog;

import com.core.android.R;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;



public class ListItemDialog extends AbsBaseDialog {
	private ListView listView;

	public ListItemDialog(Context context) {
		super(context, DIALOG_BUTTON_STYLE_NONE);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}

	@Override
	protected void setContentLayoutView(LinearLayout contentLayout) {
		// TODO Auto-generated method stub
		View listViewLayout = LayoutInflater.from(getContext()).inflate(
				R.layout.expand_dialog_list_part, null);
		listView = (ListView)listViewLayout.findViewById(R.id.expand_dialog_list_view);
		contentLayout.addView(listViewLayout);
	}

	/**
	 * 
	 * @return
	 */
	public ListView getListView() {
		return listView;
	}
}
