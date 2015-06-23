package me.jrubio.todo.Todo;

/**
 * Example To.Do list app using MVP architecture.
 * Using android-support-v7 to support old Android versions.
 *
 * @author Jose I. Rubio (@joseirs)
 *
 */
public interface ITodoView {

    /**
     * When a To.Do to edit is sent, update contents in the UI fields
     *
     * @param title field
     * @param description field
     * @param completed checkbox
     */
    void updateFields(String title, String description, boolean completed);

    /**
     * Updates the date of the edited field
     *
     * @param date string
     */
    void updateEditedField(String date);

    /**
     * Finish the view layer
     */
    void finishView();

}
