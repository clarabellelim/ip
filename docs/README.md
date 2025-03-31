# GhostTask - Manage Your Haunting Tasks 👻

> “I will always be haunting you...” – GhostTask 👻

GhostTask helps you manage your eerie, haunting tasks with a spooky twist. With this tool, you can:
- **Track your tasks with a hauntingly fun UI** using JavaFX.
- **Manage your tasks** through commands like add, mark, unmark, delete, and more.
- **Quickly search** for tasks by date or keyword.

### How to Use GhostTask 👻:
1. Download the latest version from [here](https://github.com/clarabellelim/ip).
2. Run the **GhostTask JAR file** from your terminal or through the provided GUI.
3. Add tasks like **Todos**, **Deadlines**, and **Events**.
4. Let GhostTask manage your haunted list of tasks with an easy-to-use interface.

#### Features 👻:
- **Task Management**: Add, mark, unmark, delete, and list tasks.
- **Deadline & Event Tracking**: Manage haunted tasks with deadlines and event timeframes.
- **Search Functionality**: Filter and search for specific haunted tasks by date or keyword.
- **Graphical User Interface (GUI)**: In addition to the command-line interface, GhostTask now comes with a spooky JavaFX-based GUI for easier task management.

### Key Features 👻:
- **Add Task**: You can add **Todos**, **Deadlines**, or **Events**.
- **Mark/Unmark Task**: Mark a task as done (or unmark it) to track progress.
- **Delete Task**: Easily remove tasks from your list.
- **Find Tasks**: Search tasks by keyword or specific haunting date.
- **Task List**: View your tasks in a spookily formatted list.

#### How to Run 👻:
1. Download the latest release or clone the repository.
2. **Run the JAR file** with the following command (assuming you have Java installed):
    ```bash
    java -jar ghosttask.jar
    ```
3. Alternatively, run the Java application directly if you have the project set up in an IDE:
    ```java
    public class Main {
        public static void main(String[] args) {
            // Launching the GhostTask application
            Application.launch(MainApp.class, args);
        }
    }
    ```

### Example Commands 👻:
Here’s how you can interact with the command-line interface:

- **Adding a Todo**:
    ```bash
    todo Buy spooky decorations
    ```

- **Adding a Deadline**:
    ```bash
    deadline Submit haunted report /by 2025-04-07
    ```

- **Adding an Event**:
    ```bash
    event Ghost party /from 2025-04-10 18:00 /to 2025-04-10 23:00
    ```

- **Marking a task as done**:
    ```bash
    mark 1
    ```

- **Unmarking a task**:
    ```bash
    unmark 1
    ```

- **Deleting a task**:
    ```bash
    delete 2
    ```

- **Searching for a task by keyword**:
    ```bash
    find haunting
    ```

- **Listing all tasks**:
    ```bash
    list
    ```

### Tasks to do 👻:
- [ ] Integrate reminders (coming soon 🕐)
- [x] Add deadline functionality
- [x] Implement task search by keyword
- [x] Display tasks in a user-friendly format (with GUI)

> GhostTask will help keep your haunting tasks organized and under control, making your to-do list less spooky. 👻

### Java Usage:
If you're a Java developer, you can use GhostTask to practice your Java skills. Here's the entry point to the application:

```java
public class Main {
    public static void main(String[] args) {
        // Launching the GhostTask application
        Application.launch(MainApp.class, args);
    }
}
```

This will launch the GhostTask GUI, where you can interact with your tasks visually.

---

**Don't let your tasks haunt you** 👻! GhostTask is here to help you keep them under control, whether you're using the command line or the GUI. It's free 🏷️ to use and works on all systems with Java support.
