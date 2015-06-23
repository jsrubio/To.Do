package me.jrubio.todo.Todo;

import me.jrubio.todo.model.Todo;

/**
 * Example To.Do list app using MVP architecture.
 * Using android-support-v7 to support old Android versions.
 *
 * @author Jose I. Rubio (@joseirs)
 *
 */
public interface ITodoPresenter {

    /**
     * The object To.Do is saved to edit
     *
     * @param todo
     */
    void setEditTodo(Todo todo);

    /**
     * Saves a new To.Do or updated an old one
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
