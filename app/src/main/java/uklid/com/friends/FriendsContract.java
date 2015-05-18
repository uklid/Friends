package uklid.com.friends;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Uklid on 5/18/2015.
 */
public class FriendsContract {
    interface FriendsColumns {
        String FRIENDS_NAME = "friends_name";
        String FRIENDS_EMAIL = "friends_email";
        String FRIENDS_PHONE = "friends_phone";
    }

    public static final String CONTENT_AUTHORITY = "uklid.com.friends.provider"; //high level provider
    public static final Uri BASE_CONTEXT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    private static final String PATH_FRIENDS = "friends";

    public static final String[] TOP_LEVEL_PATHS = {
            PATH_FRIENDS
    };

    public static  class Friends implements FriendsColumns, BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTEXT_URI.buildUpon().appendEncodedPath(PATH_FRIENDS).build(); //access to the contract provider
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd" + CONTENT_AUTHORITY +".friends"; //access morethan 1 data
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd" + CONTENT_AUTHORITY +".friends";

        public static Uri buildFriendUri(String friendId) {
            return CONTENT_URI.buildUpon().appendEncodedPath(friendId).build();
        }
        public static String getFriendId(Uri uri) {
            return  uri.getPathSegments().get(1);
        }
    }
}
