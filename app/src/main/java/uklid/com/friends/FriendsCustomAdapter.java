package uklid.com.friends;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Uklid on 5/23/2015.
 */
public class FriendsCustomAdapter extends ArrayAdapter<Friend> {
    private LayoutInflater mLayoutInflater;
    private static FragmentManager sFragmentManager;

    public FriendsCustomAdapter(Context context, FragmentManager fragmentManager) {
        super(context, android.R.layout.simple_list_item_2);
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        sFragmentManager = fragmentManager;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View view;
        if(convertView == null) {
            view = mLayoutInflater.inflate(R.layout.custom_friends,parent,false);
        } else {
            view = convertView;
        }
        final Friend friend = getItem(position);
        final int _id = friend.getId();
        final String name = friend.getName();
        final String phone = friend.getPhone();
        final String email = friend.getEmail();

        ((TextView) view.findViewById(R.id.friend_name)).setText(name);
        ((TextView) view.findViewById(R.id.friend_phone)).setText(phone);
        ((TextView) view.findViewById(R.id.friend_email)).setText(email);

        Button editButton = (Button) view.findViewById(R.id.edit);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EditActivity.class);
                intent.putExtra(FriendsContract.FriendsColumns.FRIENDS_ID, String.valueOf(_id));
                intent.putExtra(FriendsContract.FriendsColumns.FRIENDS_NAME, String.valueOf(name));
                intent.putExtra(FriendsContract.FriendsColumns.FRIENDS_EMAIL, String.valueOf(email));
                intent.putExtra(FriendsContract.FriendsColumns.FRIENDS_PHONE, String.valueOf(phone));
                getContext().startActivities(intent);
                R.
            }
        });

        Button deleteButton = (Button) view.findViewById(R.id.delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                FriendsDialog dialog = new FrindsDialog();
                                                Bundle args = new Bundle();
                                                args.putString(FriendsDialog.DIALOG_TYPE, FriendsDialog.DELETE_RECORD);
                                                args.putString(FriendsContract.FriendsColumns.FRIENDS_ID, String.valueOf(id));
                                                args.putString(FriendsContract.FriendsColumns.FRIENDS_NAME, name);
                                                dialog.setArguments(args);
                                                dialog.show(sFragmentManager, "delete-record");
                                            }
                                        }
        );
    }
}
