const taskInput = document.getElementById('taskInput');
const addButton = document.getElementById('addButton');
const taskList = document.getElementById('taskList');

addButton.addEventListener('click', () => {
    const newTask = taskInput.value;
    if (newTask) {
        const li = document.createElement('li');
        li.textContent = newTask;
        taskList.appendChild(li);
        taskInput.value = '';
    }
});
