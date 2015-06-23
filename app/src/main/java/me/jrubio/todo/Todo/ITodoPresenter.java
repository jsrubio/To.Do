package me.jrubio.todo.Todo;

import me.jrubio.todo.model.Todo;

/**
 * Example To.Do list app using MVP pattern.
 * Using android-support-v7 to support old Android versions.
 *
 * @author Jose I. Rubio (@joseirs)
 *
 */
public interface ITodoPresenter {

    /**
     * To.Do to edit is saved
     *
     * @param todo
     */
    void setEditTodo(Todo todo);

    /**
     * Create new To.Do or update an old one
     *
     * @param title
     * @param description
     * @param completed
     */
    void create(String title, String description, boolean completed);

    /**
     * Delete or discard To.Do
     */
    void delete();

    /**
     * Discard To.Do
     */
    void discard();

    /**
     * Get new Edited Time
     */
    void updateEditedTime();

}
