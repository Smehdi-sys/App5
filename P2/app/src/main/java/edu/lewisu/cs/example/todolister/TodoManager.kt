package edu.lewisu.cs.example.todolister


import android.content.Context
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.PrintWriter
import java.util.*

const val TODO_FOLDER_NAME = "dfdsfdfdsfdsf"
const val TODO_FILENAME = "Sohm.txt"

class TodoManager(var appContext: Context) {
    private var taskItems: MutableList<String> = mutableListOf()

    init {
        // Create the folder if it doesn't exist
        val folder = File(appContext.filesDir, TODO_FOLDER_NAME)
        if (!folder.exists()) {
            folder.mkdir()
        }
    }

    fun addTask(task: String) {
        taskItems.add(task)
    }

    fun getTasks(): List<String> {
        return Collections.unmodifiableList(taskItems)
    }

    fun clearTasks() {
        taskItems.clear()
    }

    fun saveToStorage() {
        val filePath = File(appContext.filesDir, TODO_FOLDER_NAME + File.separator + TODO_FILENAME)
        val outputStream = FileOutputStream(filePath)
        writeListToStream(outputStream)
    }

    fun readFromStorage() {
        try {
            val filePath = File(appContext.filesDir, TODO_FOLDER_NAME + File.separator + TODO_FILENAME)
            val inputStream = FileInputStream(filePath)
            val reader = inputStream.bufferedReader()
            taskItems.clear()
            reader.forEachLine { taskItems.add(it) }
        } catch (ex: FileNotFoundException) {
            // Handle the exception if the file is not found
        }
    }

    private fun writeListToStream(outputStream: FileOutputStream) {
        val writer = PrintWriter(outputStream)
        for (task in taskItems) {
            writer.println(task)
        }
        writer.close()
    }
}