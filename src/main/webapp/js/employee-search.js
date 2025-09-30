function searchEmployee(event) {
    event.preventDefault();

    const id = document.getElementById("id").value;

    fetch(contextPath + "/api/employees/"+ id)
        .then(res => {
            if (!res.ok) {
                throw new Error("Employee not found, status: " + res.status);
            }
            return res.json();
        })
        .then(emp => {
            const table = document.getElementById("resultTable");
            const tbody = document.getElementById("resultBody");

            tbody.innerHTML = "";
            let row = document.createElement("tr");

            row.innerHTML = `
                <td>${emp.id}</td>
                <td>${emp.name}</td>
                <td>${emp.department}</td>
                <td>${emp.salary}</td>
            `;

            tbody.appendChild(row);
            table.style.display = "table"; 
        })
        .catch(err => {
            alert("Employee not found!");
            document.getElementById("resultTable").style.display = "none";
            console.error("Search error:", err);
        });
}
