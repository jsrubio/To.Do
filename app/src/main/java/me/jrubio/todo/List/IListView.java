package me.jrubio.todo.List;

import java.util.ArrayList;

import me.jrubio.todo.model.Entity.Todo;

/**
 * Example To.Do list app using MVP pattern.
 * Using android-support-v7 to support old Android versions.
 *
 * @author Jose I. Rubio (@joseirs)
 *
 */
public interface IListView {

    /**
     * Set To.Dos in the Adapter
     *
     * @param todos
     */
    void setTodos(ArrayList<Todo> todos);

    /**
     * Notify To.Dos data set has changed
     */
    void notifyListDataSetChanged();

    /**
     * Notify item removed in Adapter
     *
     * @param position
     */
    void notifyListItemRemoved(int position);

    /**
     * Notify item inserted in Adapter
     *
     * @param position
     */
    void notifyListItemInserted(int position);

    /**
     * Show Dialog with To.Do actions
     *
     * @param todo
     */
    void showItemDialog(Todo todo, CharSequence items[]);

    /**
     * Show TodoView to edit old To.Do
     *
     * @param todo
     */
    void showTodoViewToEdit(Todo todo);

    /**
     * Show TodoView to create new To.Do
     */
    void showTodoView();

}
