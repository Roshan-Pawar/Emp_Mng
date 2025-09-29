function saveEmployee(event) {
    event.preventDefault(); // prevent form submit

    const employee = {
    	id: parseInt(document.getElementById("id").value),
        name: document.getElementById("name").value,
        department: document.getElementById("department").value,
        salary: parseFloat(document.getElementById("salary").value),
    };

    fetch(contextPath + "/api/employees", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(employee)
    })
    .then(res => res.json())
    .then(data => {
        if (data.success) {
            alert("Employee added successfully!");
            document.getElementById("employeeForm").reset();
        } else {
            alert("Error adding employee");
        }
    })
    .catch(err => {
        console.error("Fetch error:", err);
        alert("An unexpected error occurred. Check console for details.");
    });
}