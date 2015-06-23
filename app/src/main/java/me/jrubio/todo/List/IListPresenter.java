package me.jrubio.todo.List;

import me.jrubio.todo.model.Todo;

/**
 * Example To.Do list app using MVP architecture.
 * Using android-support-v7 to support old Android versions.
 *
 * @author Jose I. Rubio (@joseirs)
 *
 */
public interface IListPresenter {

    /**
     * Refresh data in the view layer
     */
    void refreshSession();

    /**
     * Action when the user click on Fab button
     */
    void onAddTodoButtonClick();

    /**
     * Action when the user click to edit To.Do
     *
     * @param todo
     */
    void onClickTodoItemToEdit(Todo todo);

    /**
     * Action when the user long click To.Do
     *
     * @param todo
     */
    void onLongClickTodoItem(Todo todo);

    /**
     * Update To.Do completed field
     *
     * @param todo
     * @param completed
     * @param position
     */
    void updateTodoIsCompleted(Todo todo, boolean completed, int position);

    /**
     * Delete old To.Do
     *
     * @param todo
     */
    void delete(Todo todo);

}
