// Firebase configuration
const firebaseConfig = {
    apiKey: "your-api-key",
    authDomain: "your-auth-domain",
    projectId: "your-project-id",
    storageBucket: "your-storage-bucket",
    messagingSenderId: "your-sender-id",
    appId: "your-app-id"
};

// Initialize Firebase
firebase.initializeApp(firebaseConfig);

// Initialize Firestore
const db = firebase.firestore();

// Step 4: Add Task to Firestore
document.getElementById('task-form').addEventListener('submit', function(e) {
    e.preventDefault();
    
    // Get task value
    const taskInput = document.getElementById('task-input');
    const task = taskInput.value;

    // Add task to Firestore
    db.collection('tasks').add({
        task: task,
        timestamp: firebase.firestore.FieldValue.serverTimestamp()
    })
    .then(() => {
        console.log('Task added');
        taskInput.value = ''; // Clear the input field
    })
    .catch(error => {
        console.error('Error adding task: ', error);
    });
});

// Step 5: Display Tasks in Real-Time
const taskList = document.getElementById('task-list');

// Real-time listener
db.collection('tasks').orderBy('timestamp').onSnapshot(snapshot => {
    taskList.innerHTML = ''; // Clear the list before updating
    snapshot.forEach(doc => {
        const task = doc.data().task;
        const li = document.createElement('li');
        li.textContent = task;
        const deleteButton = document.createElement('button');
        deleteButton.textContent = 'Delete';
        deleteButton.classList.add('delete');
        deleteButton.setAttribute('data-id', doc.id);
        li.appendChild(deleteButton);
        taskList.appendChild(li);
    });
});

// Step 6: Delete Task from Firestore
taskList.addEventListener('click', function(e) {
    if (e.target.classList.contains('delete')) {
        const id = e.target.getAttribute('data-id');
        db.collection('tasks').doc(id).delete()
        .then(() => {
            console.log('Task deleted');
        })
        .catch(error => {
            console.error('Error deleting task: ', error);
        });
    }
});
