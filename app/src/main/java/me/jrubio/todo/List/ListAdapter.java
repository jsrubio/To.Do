package me.jrubio.todo.List;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import me.jrubio.todo.R;
import me.jrubio.todo.common.ParseDate;
import me.jrubio.todo.model.Entity.Todo;

/**
 * Example To.Do list app using MVP pattern.
 * Using android-support-v7 to support old Android versions.
 *
 * @author Jose I. Rubio (@joseirs)
 *
 */
public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Todo> todos;
    private Context context;

    private TodoViewHolder.IOnClickToEditListener todoIOnClickToEditListener;
    private TodoViewHolder.IOnLongClickListener todoIOnLongClickListener;

    private static final int TYPE_NO_TODO = 0;
    private static final int TYPE_TODO = 1;

    public ListAdapter(Context context, ArrayList<Todo> todos, TodoViewHolder.IOnClickToEditListener todoIOnClickToEditListener, TodoViewHolder.IOnLongClickListener todoIOnLongClickListener) {
        this.context = context;
        this.todos   = todos;
        this.todoIOnClickToEditListener = todoIOnClickToEditListener;
        this.todoIOnLongClickListener   = todoIOnLongClickListener;
    }

    public void setTodos(ArrayList<Todo> todos) {
        this.todos = todos;
    }

    public ArrayList<Todo> getTodos() {
        return todos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_NO_TODO:
                View noTodoView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_no_todo, parent, false);
                return new NoTodoViewHolder(noTodoView);
            case TYPE_TODO:
                View todoView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_todo, parent, false);
                return new TodoViewHolder(todoView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case TYPE_NO_TODO:
                break;
            case TYPE_TODO:
                final Todo todo = todos.get(position);
                TodoViewHolder todoViewHolder = (TodoViewHolder) holder;
                String messageTodoDateCreated = context.getString(R.string.created, ParseDate.parseDate(todo.getEdited(), ParseDate.DATE_PATTERN));

                // Data
                todoViewHolder.title.setText(todo.getTitle());
                todoViewHolder.description.setText(todo.getDescription());
                todoViewHolder.edited.setText(Html.fromHtml(messageTodoDateCreated));
                todoViewHolder.completed.setChecked(todo.isCompleted());

                // Listeners
                todoViewHolder.card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        todoIOnClickToEditListener.onClickToEditListener(todo, holder.getAdapterPosition());
                    }
                });
                todoViewHolder.card.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        todoIOnLongClickListener.onLongClickListener(todo, holder.getAdapterPosition());
                        return true;
                    }
                });
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return (todos.size() == 0) ? TYPE_NO_TODO : TYPE_TODO;
    }

    @Override
    public int getItemCount() {
        return (todos.size() == 0) ? 1 : todos.size();
    }

    /**
     * To.Do ViewHolder
     */
    public static class TodoViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout cardBackground;
        public CardView card;
        public TextView title;
        public TextView description;
        public TextView edited;
        public CheckBox completed;

        public TodoViewHolder(View itemView) {
            super(itemView);
            cardBackground  = (RelativeLayout) itemView.findViewById(R.id.card_background);
            card            = (CardView) itemView.findViewById(R.id.card_todo);
            title           = (TextView) itemView.findViewById(R.id.title);
            description     = (TextView) itemView.findViewById(R.id.description);
            edited          = (TextView) itemView.findViewById(R.id.edited);
            completed       = (CheckBox) itemView.findViewById(R.id.completed);
        }

        /**
         * Interface to ViewHolder onClick
         */
        public interface IOnClickToEditListener {

            void onClickToEditListener(Todo todo, int position);

        }

        /**
         * Interface to ViewHolder onLongClick
         */
        public interface IOnLongClickListener {

            void onLongClickListener(Todo todo, int position);

        }
    }

    /**
     * If there are no To.Do's, use this ViewHolder
     */
    public static class NoTodoViewHolder extends RecyclerView.ViewHolder {

        public TextView noTodoText;

        public NoTodoViewHolder(View itemView) {
            super(itemView);
            noTodoText = (TextView) itemView.findViewById(R.id.no_todo_text);
        }
    }

}
