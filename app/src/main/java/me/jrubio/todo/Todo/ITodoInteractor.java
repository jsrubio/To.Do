package me.jrubio.todo.Todo;

import me.jrubio.todo.common.ITodoActionsInteractor;
import me.jrubio.todo.model.Entity.Todo;

/**
 * Example To.Do list app using MVP pattern.
 * Using android-support-v7 to support old Android versions.
 *
 * @author Jose I. Rubio (@joseirs)
 *
 */
public interface ITodoInteractor extends ITodoActionsInteractor {

    /**
     * Insert new To.Do in the database
     *
     * @param todo
     */
    void create(Todo todo);

}
