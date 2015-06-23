package me.jrubio.todo.List;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.RelativeLayout;

import com.software.shell.fab.ActionButton;

import java.util.ArrayList;

import me.jrubio.todo.BaseActivity;
import me.jrubio.todo.R;
import me.jrubio.todo.Todo.TodoActivity;
import me.jrubio.todo.model.Todo;

/**
 * Example To.Do list app using MVP architecture.
 * Using android-support-v7 to support old Android versions.
 *
 * @author Jose I. Rubio (@joseirs)
 *
 */
public class ListActivity extends BaseActivity implements IListView {

    public static final String STATE_LIST = "TodoList";

    private IListPresenter presenter;

    private ListAdapter listAdapter;

    public IListPresenter getPresenter() {
        if (presenter == null) {
            presenter = new ListPresenter(this, this);
        }
        return presenter;
    }

    public RecyclerView getTodoList() {
        return (RecyclerView) findViewById(R.id.todo_list);
    }

    public ActionButton getFabButton() {
        return (ActionButton) findViewById(R.id.fab_button);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // RecyclerView
        if (savedInstanceState == null) {
            listAdapter = new ListAdapter(this, new ArrayList<Todo>(), onTodoClickToEditListener, onTodoLongClickListener);
        } else {
            ArrayList<Todo> todoArrayList = savedInstanceState.getParcelableArrayList(STATE_LIST);
            listAdapter = new ListAdapter(this, todoArrayList, onTodoClickToEditListener, onTodoLongClickListener);
        }
        getTodoList().setHasFixedSize(true);
        getTodoList().setLayoutManager(new LinearLayoutManager(this));
        getTodoList().setAdapter(listAdapter);

        // Listeners
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(cardSwipeCallback);
        itemTouchHelper.attachToRecyclerView(getTodoList());
        getFabButton().setOnClickListener(fabClickListener);

        // Presenter
        getPresenter().refreshSession();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().refreshSession();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_list;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(STATE_LIST, listAdapter.getTodos());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    private ListAdapter.TodoViewHolder.IOnClickToEditListener onTodoClickToEditListener = new ListAdapter.TodoViewHolder.IOnClickToEditListener() {
        @Override
        public void onClickToEditListener(Todo todo, int position) {
            presenter.onClickTodoItemToEdit(todo);
        }
    };

    private ListAdapter.TodoViewHolder.IOnLongClickListener onTodoLongClickListener = new ListAdapter.TodoViewHolder.IOnLongClickListener() {
        @Override
        public void onLongClickListener(Todo todo, int position) {
            presenter.onLongClickTodoItem(todo);
        }
    };


    private ItemTouchHelper.SimpleCallback cardSwipeCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            listAdapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
            int position = viewHolder.getAdapterPosition();
            Todo todo = listAdapter.getTodos().get(position);
            boolean completed = (!todo.isCompleted());
            presenter.updateTodoIsCompleted(todo, completed, position);
        }

        @Override
        public void onChildDraw(Canvas c, RecyclerView recyclerView,
                                RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                int actionState, boolean isCurrentlyActive) {
            ListAdapter.TodoViewHolder vh = (ListAdapter.TodoViewHolder) viewHolder;
            if (vh.getAdapterPosition() != -1) {
                RelativeLayout cardBackground = vh.cardBackground;
                Paint backgroundColor = new Paint();
                Todo todo = listAdapter.getTodos().get(vh.getAdapterPosition());
                int colorCardBackgroundActive = (todo.isCompleted()) ? R.color.noArchive : R.color.colorArchive;
                backgroundColor.setColor(getResources().getColor(colorCardBackgroundActive));
                c.drawRect(cardBackground.getLeft(), cardBackground.getTop(), cardBackground.getRight(), cardBackground.getBottom() - 5, backgroundColor);
            }
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };

    private View.OnClickListener fabClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            presenter.onAddTodoButtonClick();
        }
    };

    @Override
    public void setTodos(ArrayList<Todo> todos) {
        listAdapter.setTodos(todos);
    }

    @Override
    public void notifyListDataSetChanged() {
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void notifyListItemRemoved(int position) {
        listAdapter.notifyItemRemoved(position);
    }

    @Override
    public void notifyListItemInserted(int position) {
        listAdapter.notifyItemInserted(position);
    }

    @Override
    public void showItemDialog(final Todo todo) {
        final CharSequence items[] = new CharSequence[] {getString(R.string.edit), getString(R.string.delete)};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (items[which] == getString(R.string.edit)) {
                    presenter.onClickTodoItemToEdit(todo);
                }
                if (items[which] == getString(R.string.delete)) {
                    presenter.delete(todo);
                }
            }
        });
        builder.show();
    }

    @Override
    public void showTodoViewToEdit(Todo todo) {
        Intent i = new Intent(this, TodoActivity.class);
        i.putExtra(TodoActivity.EXTRA_EDIT_TODO, todo);
        startActivity(i);
    }

    @Override
    public void showTodoView() {
        Intent intent = new Intent(this, TodoActivity.class);
        startActivity(intent);
    }
}
