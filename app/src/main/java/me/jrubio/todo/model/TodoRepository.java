package me.jrubio.todo.model;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.Date;

import me.jrubio.todo.List.IListInteractor;
import me.jrubio.todo.Todo.ITodoInteractor;
import me.jrubio.todo.model.ContentProvider.TodoContract;

/**
 * Example To.Do list app using MVP architecture.
 * Using android-support-v7 to support old Android versions.
 *
 * @author Jose I. Rubio (@joseirs)
 *
 */
public class TodoRepository implements IListInteractor, ITodoInteractor {

    private ContentResolver contentResolver;

    public ContentResolver getContentResolver() {
        return contentResolver;
    }

    public TodoRepository(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }

    @Override
    public ArrayList<Todo> get() {
        ArrayList<Todo> todoList = new ArrayList<>();
        Cursor cursor = getContentResolver().query(
                TodoContract.CONTENT_URI,
                TodoContract.PROJECTION_ALL,
                null,
                null,
                TodoContract.COMPLETED + " ASC, " +
                TodoContract.EDITED + " DESC");

        if (null == cursor || !cursor.moveToNext()) {
            return todoList;
        }

        do {
            Todo todo = new Todo(cursor);
            todoList.add(todo);
        } while (cursor.moveToNext());

        cursor.close();
        return todoList;
    }

    @Override
    public void update(Todo todo) {
        todo.setEdited(new Date().getTime());
        save(todo);
    }

    @Override
    public void create(Todo todo) {
        save(todo);
    }

    @Override
    public void delete(Todo todo) {
        String[] selectionArgs = {String.valueOf(todo.getId())};
        getContentResolver().delete(
                TodoContract.CONTENT_URI,
                TodoContract._ID + " = ?",
                selectionArgs);
    }

    private void save(Todo todo) {
        ContentValues values = new ContentValues();
        values.put(TodoContract.TITLE, todo.getTitle());
        values.put(TodoContract.DESCRIPTION, todo.getDescription());
        values.put(TodoContract.EDITED, todo.getEdited());
        values.put(TodoContract.COMPLETED, todo.isCompleted());

        if (todo.getId() == Todo.UNSAVED_ID) {
            Uri insertUri = getContentResolver().insert(TodoContract.CONTENT_URI, values);
            todo.setId(Long.valueOf(insertUri.getLastPathSegment()));
        } else {
            String[] selectionArgs = {String.valueOf(todo.getId())};
            getContentResolver().update(
                    TodoContract.CONTENT_URI,
                    values,
                    TodoContract._ID + " = ?",
                    selectionArgs);
        }
    }
}
