package me.jrubio.todo.model.ContentProvider;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Example To.Do list app using MVP pattern.
 * Using android-support-v7 to support old Android versions.
 *
 * @author Jose I. Rubio (@joseirs)
 *
 */
public class TodoContract implements BaseColumns {

    private TodoContract() {}

    public static final String AUTHORITY = "me.jrubio.todo.model.provider.TodoProvider";

    public static final String ENDPOINT = "todo";

    public static final Uri CONTENT_URI =
            Uri.parse("content://" + AUTHORITY + "/" + ENDPOINT);

    public static final String CONTENT_TYPE =
            ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd.me.jrubio.todo.model.todo";

    public static final String TABLE = "todo";

    public static final String TITLE        = "title";
    public static final String DESCRIPTION  = "description";
    public static final String EDITED       = "edited";
    public static final String COMPLETED    = "completed";

    public static final String[] PROJECTION_ALL = {_ID, TITLE, DESCRIPTION, EDITED, COMPLETED};

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE
                    + " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + TITLE + " TEXT, "
                    + DESCRIPTION + " TEXT, "
                    + EDITED + " INTEGER, "
                    + COMPLETED + " INTEGER DEFAULT 0 "
                    + ");";

    public static final String FIXTURE =
            "INSERT INTO " + TABLE
                    + " (" + TITLE
                    + ", " + DESCRIPTION
                    + ", " + EDITED
                    + ", " + COMPLETED
                    + ") "
                    + "VALUES ('TodoFixture', 'Description', 1434703928, 0);";
}
