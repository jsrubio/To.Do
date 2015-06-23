package me.jrubio.todo.List;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

import me.jrubio.todo.model.Todo;
import me.jrubio.todo.model.TodoRepository;

/**
 * Example To.Do list app using MVP architecture.
 * Using android-support-v7 to support old Android versions.
 *
 * @author Jose I. Rubio (@joseirs)
 *
 */
public class ListPresenter implements IListPresenter {

    private IListView view;
    private IListInteractor interactor;
    private Context context;

    public ListPresenter(IListView view, Context context) {
        this.view       = view;
        this.interactor = new TodoRepository(context.getContentResolver());
        this.context    = context;
    }

    public ListPresenter(IListView view, IListInteractor interactor, Context context) {
        this.view       = view;
        this.interactor = interactor;
        this.context    = context;
    }

    @Override
    public void refreshSession() {
        view.setTodos(interactor.get());
        view.notifyListDataSetChanged();
    }

    @Override
    public void onAddTodoButtonClick() {
        view.showTodoView();
    }

    @Override
    public void onClickTodoItemToEdit(Todo todo) {
        view.showTodoViewToEdit(todo);
    }

    @Override
    public void onLongClickTodoItem(Todo todo) {
        view.showItemDialog(todo);
    }

    @Override
    public void updateTodoIsCompleted(Todo todo, boolean completed, int position) {
        todo.setCompleted(completed);
        interactor.update(todo);
        ArrayList<Todo> todoList = interactor.get();
        view.notifyListItemRemoved(position);
        view.setTodos(todoList);
        for (Todo todoObject : todoList) {
            if (todoObject.getId() == todo.getId()) {
                view.notifyListItemInserted(todoList.indexOf(todoObject));
            }
        }
    }

    @Override
    public void delete(Todo todo) {
        interactor.delete(todo);
        view.setTodos(interactor.get());
        view.notifyListDataSetChanged();
        Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
    }
}
