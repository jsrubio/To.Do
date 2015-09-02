package me.jrubio.todo.Todo;

import android.content.Context;
import android.widget.Toast;

import java.util.Date;

import me.jrubio.todo.common.ParseDate;
import me.jrubio.todo.model.Entity.Todo;
import me.jrubio.todo.model.TodoRepository;

/**
 * Example To.Do list app using MVP pattern.
 * Using android-support-v7 to support old Android versions.
 *
 * @author Jose I. Rubio (@joseirs)
 *
 */
public class TodoPresenter implements ITodoPresenter {

    private ITodoView view;
    private ITodoInteractor interactor;
    private Context context;

    private Todo editTodo;

    public TodoPresenter(ITodoView view, Context context) {
        this.view       = view;
        this.interactor = new TodoRepository(context.getContentResolver());
        this.context    = context;
    }

    @Override
    public void setEditTodo(Todo todo) {
        this.editTodo = todo;
        view.updateFields(todo.getTitle(), todo.getDescription(), todo.isCompleted());
        view.updateEditedField(ParseDate.parseDate(todo.getEdited(), ParseDate.HOUR_PATTERN));
    }

    @Override
    public void create(String title, String description, boolean completed) {
        if (!title.equals("") || !description.equals("")) {
            Todo todo = (this.editTodo != null) ? this.editTodo : new Todo();
            todo.setTitle(title);
            todo.setDescription(description);
            todo.setCompleted(completed);
            todo.setEdited(new Date().getTime());
            interactor.create(todo);
            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
        }
        view.finishView();
    }

    @Override
    public void delete() {
        if (editTodo != null) {
            interactor.delete(editTodo);
            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
        }
        view.finishView();
    }

    @Override
    public void discard() {
        Toast.makeText(context, "Discarded", Toast.LENGTH_SHORT).show();
        view.finishView();
    }

    @Override
    public void updateEditedTime() {
        view.updateEditedField(ParseDate.parseDate(null, ParseDate.HOUR_PATTERN));
    }

}
