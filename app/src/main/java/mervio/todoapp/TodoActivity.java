package mervio.todoapp;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.UUID;

import mervio.todoapp.dummy.DummyContent;

public class TodoActivity extends AppCompatActivity implements
        TodoFragment.OnFragmentInteractionListener, AddTodoDialogFragment.AddTodoDialogListener {

    // TODO Save todo list
    private static final String TAG = "TodoActivity";
    private static final String ADDTODODIALOGTAG = "AddTodoDialogFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        TodoFragment todos = new TodoFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, todos).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_todo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                return true;
            case R.id.action_add:
                // Show dialog
                showAddTodoDialog();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onFragmentInteraction(String id) {
        //TODO Select fragment
    }

    @Override
    public void onNewTodo(String newTodo) {
        String id = UUID.randomUUID().toString();
        DummyContent.ITEMS.add(new DummyContent.DummyItem(id, newTodo));
        Log.i(TAG, "Added new todo item: " + newTodo);
    }

    private void showAddTodoDialog() {
        DialogFragment dialog = new AddTodoDialogFragment();
        dialog.show(getSupportFragmentManager(), ADDTODODIALOGTAG);
    }
}
