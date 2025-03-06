<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit User</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Update User Detail</h2>
        
        <form action="UserUpdateServlet" method="post">
            <input type="hidden" name="id" value="${userId} ">

            <div class="mb-3">
                <label class="form-label">First Name</label>
                <input type="text" class="form-control" name="fname" 
                       value="${fname}" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Last Name</label>
                <input type="text" class="form-control" name="lname" 
                       value="${lname}" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Email</label>
                <input type="email" class="form-control" name="email" 
                       value="${email}" required>
            </div>
            
            <div class="mb-3">
                <label class="form-label">Phone</label>
                <input type="text" class="form-control" name="phone" 
                 value="${phone}" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Addresses</label>
                <div id="address-container">
                    <% 
                    String addresses = (String) request.getAttribute("addresses");
                    if (addresses != null && !addresses.isEmpty()) {
                        String[] addressArray = addresses.split(", ");
                        for (String address : addressArray) { 
                    %>
                        <div class="input-group mb-2">
                            <input type="text" class="form-control" name="addresses"  required>
                            <button type="button" class="btn btn-danger" onclick="removeAddress(this)">Delete</button>
                        </div>
                    <% 
                        } 
                    } 
                    %>
                </div>
                <button type="button" class="btn btn-success mt-2" onclick="addAddress()">Add Address</button>
            </div>

            <button type="submit" class="btn btn-primary">Update User</button>
            <a href="UserButton.jsp" class="btn btn-secondary">Cancel</a>
        </form>
    </div>

    <script>
    function addAddress() {
        let container = document.getElementById("address-container");
        let div = document.createElement("div");
        div.classList.add("input-group", "mb-2");
        div.innerHTML = `
            <input type="text" class="form-control" name="address_line" required>
            <button type="button" class="btn btn-danger" onclick="removeAddress(this)">Delete</button>
        `;
        container.appendChild(div);
    }

    function removeAddress(button) {
        button.parentElement.remove();
    }
    </script>
</body>
</html>
