package me.jrubio.todo.Todo;

import me.jrubio.todo.common.ITodoActionsInteractor;
import me.jrubio.todo.model.Todo;

/**
 * Example To.Do list app using MVP architecture.
 * Using android-support-v7 to support old Android versions.
 *
 * @author Jose I. Rubio (@joseirs)
 *
 */
public interface ITodoInteractor extends ITodoActionsInteractor {

    /**
     * Insert a new To.Do in the database
     *
     * @param todo
     */
    void create(Todo todo);

}
