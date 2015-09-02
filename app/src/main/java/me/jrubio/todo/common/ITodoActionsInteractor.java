package me.jrubio.todo.common;

import me.jrubio.todo.model.Entity.Todo;

/**
 * Example To.Do list app using MVP pattern.
 * Using android-support-v7 to support old Android versions.
 *
 * @author Jose I. Rubio (@joseirs)
 *
 */
public interface ITodoActionsInteractor {

    /**
     * Delete To.Do in the database
     * @param todo
     */
    void delete(Todo todo);

}
