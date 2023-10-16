package edu.lewisu.cs.example.todolister

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import edu.lewisu.cs.example.todotext.R

class MainActivity : AppCompatActivity() {
    private lateinit var inputEditText: EditText
    private lateinit var itemsTextView: TextView
    private lateinit var todoManager: TodoManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoManager = TodoManager(this) // Initialize TodoManager with the activity's context

        inputEditText = findViewById(R.id.task_input)
        itemsTextView = findViewById(R.id.task_list)

        findViewById<Button>(R.id.add_task_button).setOnClickListener { onAddButtonClick() }
        findViewById<Button>(R.id.clear_button).setOnClickListener { onClearButtonClick() }
    }

    override fun onPause() {
        super.onPause()
        todoManager.saveToStorage()
    }

    override fun onResume() {
        super.onResume()
        todoManager.readFromStorage()
        displayTodoItems()
    }

    private fun onAddButtonClick() {
        val newItem = inputEditText.text.toString().trim()
        inputEditText.setText("")
        if (newItem.isNotEmpty()) {
            todoManager.addTask(newItem)
            displayTodoItems()
        }
    }

    private fun displayTodoItems() {
        val itemText = StringBuffer()
        val items = todoManager.getTasks()
        val lineSeparator = System.getProperty("line.separator")
        for ((index, item) in items.withIndex()) {
            itemText.append(index + 1).append(".").append(item).append(lineSeparator)
        }
        itemsTextView.text = itemText.toString()
    }

    private fun onClearButtonClick() {
        todoManager.clearTasks()
        displayTodoItems()
    }
}
