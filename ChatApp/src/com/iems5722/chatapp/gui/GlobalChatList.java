package com.iems5722.chatapp.gui;

import com.iems5722.chatapp.R;
import com.iems5722.chatapp.database.DbProvider;
import com.iems5722.chatapp.database.TblGlobalChat;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class GlobalChatList extends ListFragment implements LoaderCallbacks<Cursor> {
	private static final String TAG = "GlobalChatList";
	private GlobalChatAdapter mAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Log.d(APP_TAG, "onCreate");

		String[] from = new String[] {TblGlobalChat.MESSAGE_ID};
		int[] to = new int[] {R.id.msg_id};
		mAdapter = new GlobalChatAdapter(getActivity(), R.layout.chat_message_recv, null, from, to, 0);
		setListAdapter(mAdapter);
		getLoaderManager().initLoader(0, null, this);
	}	
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		//Log.d(TAG, "onViewCreated");
		super.onViewCreated(view, savedInstanceState);
		setEmptyText(getResources().getString(R.string.chat_empty));
		getListView().setDivider(getResources().getDrawable(android.R.color.transparent));
		getListView().setDividerHeight(10);
		getListView().setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
		getListView().setStackFromBottom(true);
	}	
	
	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		String[] projection = {};
		return new CursorLoader(getActivity(), DbProvider.GCHAT_URI, projection, null, null, null);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		mAdapter.swapCursor(cursor);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		mAdapter.swapCursor(null);
	}
	
	

}
