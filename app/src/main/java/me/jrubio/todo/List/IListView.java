package me.jrubio.todo.List;

import java.util.ArrayList;

import me.jrubio.todo.model.Todo;

/**
 * Example To.Do list app using MVP architecture.
 * Using android-support-v7 to support old Android versions.
 *
 * @author Jose I. Rubio (@joseirs)
 *
 */
public interface IListView {

    /**
     * Set To.Do's in the ListAdapter
     *
     * @param todos
     */
    void setTodos(ArrayList<Todo> todos);

    /**
     * Notify To.Do's data set has changed
     */
    void notifyListDataSetChanged();

    /**
     * Notify item removed from ListAdapter
     *
     * @param position
     */
    void notifyListItemRemoved(int position);

    /**
     * Notify item inserter from ListAdapter
     *
     * @param position
     */
    void notifyListItemInserted(int position);

    /**
     * Show Dialog with To.Do actions
     *
     * @param todo
     */
    void showItemDialog(Todo todo);

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
