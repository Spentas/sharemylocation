package com.spentas.javad.sharemylocation.fragments;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.spentas.javad.sharemylocation.MainActivity;
import com.spentas.javad.sharemylocation.R;
import com.spentas.javad.sharemylocation.adapters.FriendListAdapter;
import com.spentas.javad.sharemylocation.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by javad on 10/16/2015.
 */
public class FriendsListFragment extends Fragment {
    private List<User> users;
    private ListView listView;
    private HashMap<String, String> data = new HashMap<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        users = new ArrayList<>();
        final View v = inflater.inflate(R.layout.friendlist_fragment, container, false);
        listView = (ListView) v.findViewById(R.id.friend_listview);
        final FriendListAdapter adapter = new FriendListAdapter(users, getActivity().getApplicationContext());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                users.get(position).getPhoneNumber();
                ((MainActivity) getActivity()).callback(users.get(position));

            }
        });


        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPostExecute(Void aVoid) {
                v.findViewById(R.id.progressbar).setVisibility(View.INVISIBLE);
                adapter.notifyDataSetChanged();
                super.onPostExecute(aVoid);
            }

            @Override
            protected void onPreExecute() {
                v.findViewById(R.id.progressbar).setVisibility(View.VISIBLE);

                super.onPreExecute();
            }

            @Override
            protected Void doInBackground(Void... params) {
                readContact();
                return null;
            }
        }.execute();


        return v;
    }


    public void readContact() {
        ContentResolver cr = getActivity().getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {

                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                if (Integer.parseInt(cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        // System.out.println("Name: " + name + ", Phone No: " + phoneNo);
                        try {

                            User user = new User();
                            user.setUsername(name);
                            user.setPhoneNumber(phoneNo);
                            if (!data.containsKey(phoneNo)) {
                                System.out.println(name);
                                data.put(name, phoneNo);
                                users.add(user);

                            }
                        } catch (NumberFormatException e) {
                        }
                    }
                    pCur.close();
                }
            }
        }
    }

    public interface FragmentCallback {
        public void callback(User user);
    }
}
