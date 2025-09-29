
let currentEditId = null;

// Load all employees
 /*function loadEmployees() {
    fetch(contextPath + "/api/employees")
    .then(res => res.json())
    .then(list => {
        console.log("Fetched employees:", list);
        const tbody = document.getElementById("employeeTableBody");
        tbody.innerHTML = "";

        list.forEach(emp => {
            let row = document.createElement("tr");
			
            let currId = emp.id
            
            let idCell = document.createElement("td");
            idCell.textContent = emp.id;
            row.appendChild(idCell);

            let nameCell = document.createElement("td");
            nameCell.textContent = emp.name;
            row.appendChild(nameCell);

            let deptCell = document.createElement("td");
            deptCell.textContent = emp.department;
            row.appendChild(deptCell);

            let salaryCell = document.createElement("td");
            salaryCell.textContent = emp.salary;
            row.appendChild(salaryCell);

            let actionCell = document.createElement("td");
            //actionCell.innerHTML = `<button onclick="showEditModal(${currId})">Edit</button>
              //                      <button onclick="deleteEmployee(${currId})">Delete</button>`;
            
              let editBtn = document.createElement("button");
			editBtn.textContent = "Edit";
			editBtn.addEventListener("click", () => showEditModal(currId));

			let deleteBtn = document.createElement("button");
			deleteBtn.textContent = "Delete";
			deleteBtn.addEventListener("click", () => deleteEmployee(currId));

			actionCell.appendChild(editBtn);
			actionCell.appendChild(deleteBtn)
              
            row.appendChild(actionCell);

            tbody.appendChild(row);
        });
    })
    .catch(err => console.error("Fetch error:", err));
} */

function loadEmployees() {
    fetch(contextPath + "/api/employees")
        .then(res => {
            if (!res.ok) {
                throw new Error("Failed to fetch employees: " + res.status);
            }
            return res.json();
        })
        .then(list => {
            const tbody = document.getElementById("employeeTableBody");
            tbody.innerHTML = ""; // clear previous rows

            if (!list || list.length === 0) {
                // Show "No employees found" row
                let row = document.createElement("tr");
                row.innerHTML = `<td colspan="5" style="text-align:center;">No employees found</td>`;
                tbody.appendChild(row);
                return;
            }

            list.forEach(emp => {
                let row = document.createElement("tr");

                row.innerHTML = `
                    <td>${emp.id}</td>
                    <td>${emp.name}</td>
                    <td>${emp.department}</td>
                    <td>${emp.salary}</td>
                    <td>
                        <button onclick="showEditModal(${emp.id})">Edit</button>
                        <button onclick="deleteEmployee(${emp.id})">Delete</button>
                    </td>
                `;

                tbody.appendChild(row);
            });
        })
        .catch(err => {
            console.error("Fetch error:", err);
            alert("Could not load employees. Please check console.");
        });
}




// Edit modal
function showEditModal(id) {
	 if (!id) {
	        alert("No employee selected for edit!");
	        return;
	    }
    currentEditId = id;

    fetch(contextPath + "/api/employees/" + id)
        .then(res => {
            if (!res.ok) {
                throw new Error("Failed to fetch employee: " + res.status);
            }
            return res.json();
        })
        .then(emp => {
            document.getElementById("editName").value = emp.name;
            document.getElementById("editDepartment").value = emp.department;
            document.getElementById("editSalary").value = emp.salary;
            document.getElementById("modalOverlay").style.display = "block";
            document.getElementById("editModal").style.display = "block";
        })
        .catch(err => {
            console.error("Fetch error:", err);
            alert("Cannot fetch employee details. Check console.");
        });
}


function closeModal() {
    document.getElementById("modalOverlay").style.display = "none";
    document.getElementById("editModal").style.display = "none";
}

// Save updates
function saveEdit() {
    if (!currentEditId) {
        alert("No employee selected for editing.");
        return;
    }
    const updatedEmp = {
        name: document.getElementById("editName").value,
        department: document.getElementById("editDepartment").value,
        salary: parseFloat(document.getElementById("editSalary").value)
    };
  
   fetch(contextPath + "/api/employees/" + currentEditId, {
        method: "PUT",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(updatedEmp)
    })
    .then(res => {
        if (!res.ok) throw new Error("Failed to update employe: " + res.status);
        return res.json();
    })
    .then(data => {
        if(data.success) {
            alert("Updated successfully!");
            closeModal();
            loadEmployees();
        } else {
            alert("Update failed: " + (data.error || "Unknown error"));
        }
    })
    .catch(err => console.error("Update fetch error:", err));
}


// Delete employee
function deleteEmployee(id) {
  if(!confirm("Delete this employee?")) return;

    fetch(contextPath + "/api/employees/" + id, { method: "DELETE" })
    .then(res => res.json())
    .then(data => {
        if(data.success) {
            alert("Deleted successfully!");
            loadEmployees();
        } else {
            alert("Delete failed: " + (data.error || "Unknown error"));
        }
    })
    .catch(err => console.error("Delete fetch error:", err));
}

window.onload = loadEmployees;