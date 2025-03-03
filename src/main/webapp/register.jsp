<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Page</title>
    <style>
        /* Global Styles */
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f0f4f8;
            margin: 0;
            padding: 0;
        }
        h1 {
            text-align: center;
            color: #333;
            font-size: 2em;
        }

        /* Container */
        .container {
            max-width: 600px;
            margin: 50px auto;
            background-color: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            box-sizing: border-box;
        }

        /* Form Styles */
        form {
            display: flex;
            flex-direction: column;
        }

        label {
            font-size: 1.1em;
            color: #555;
            margin-bottom: 8px;
        }

        input[type="text"],
        input[type="email"],
        input[type="number"],
        input[type="password"] {
            padding: 12px;
            font-size: 1em;
            border: 1px solid #ccc;
            border-radius: 4px;
            margin-bottom: 20px;
            width: 100%;
            box-sizing: border-box;
            transition: border-color 0.3s ease;
        }

        input[type="text"]:focus,
        input[type="email"]:focus,
        input[type="number"]:focus,
        input[type="password"]:focus {
            border-color: #007bff;
            outline: none;
        }

        .some-class {
            display: flex;
            justify-content: space-between;
            margin-bottom: 20px;
        }

        .some-class label {
            font-size: 1em;
            color: #333;
            margin-right: 15px;
        }

        button {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 12px 20px;
            font-size: 1.2em;
            cursor: pointer;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #0056b3;
        }

        /* Address Input Fields */
        .address-inputs {
            display: flex;
            flex-direction: column;
            margin-bottom: 20px;
        }

        .address-inputs .address-field {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
        }

        .address-inputs input {
            padding: 12px;
            font-size: 1em;
            border: 1px solid #ccc;
            border-radius: 4px;
            margin-right: 10px;
            width: calc(100% - 40px); /* Adjust for the button width */
            box-sizing: border-box;
            transition: border-color 0.3s ease;
        }

        .address-button, .remove-button {
            background-color: #28a745;
            color: white;
            border: none;
            padding: 10px 20px;
            font-size: 1em;
            cursor: pointer;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .address-button:hover, .remove-button:hover {
            background-color: #218838;
        }

        .remove-button {
            background-color: #dc3545; /* Red for removal */
        }

        /* Back link styles */
        p {
            text-align: center;
            margin-top: 20px;
        }

        p a {
            color: #007bff;
            text-decoration: none;
            font-size: 1.1em;
        }

        p a:hover {
            text-decoration: underline;
        }

        /* Responsive design */
        @media (max-width: 600px) {
            .container {
                padding: 20px;
            }

            h1 {
                font-size: 1.8em;
            }

            label {
                font-size: 1em;
            }

            button {
                font-size: 1em;
            }
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Registration Page</h1>

    <!-- Registration Form -->
    <div id="register-form">
        <form action="NewRegisterUser" method="post">
            <label for="fname">First name:</label>
            <input type="text" id="fname" name="fname" required>

            <label for="lname">Last Name:</label>
            <input type="text" id="lname" name="lname" required>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>

            <label for="phone">Phone:</label>
            <input type="number" id="phone" name="phone" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

            <label for="address">Address:</label>
            <div class="address-inputs" id="address-list">
                <div class="address-field">
                    <input type="text" name="address_line" placeholder="Enter address line" required>
                    <button type="button" class="remove-button" onclick="removeAddress(this)">Remove</button>
                </div>
            </div>
            <button type="button" class="address-button" onclick="addAddress()">Add Another Line</button>

            <div class="some-class">
                <label for="username">Choose your role:</label>
                <label><input type="radio" name="option" value="Admin"> Admin</label>
                <label><input type="radio" name="option" value="User"> User</label>
            </div>

            <button type="submit">Register</button>
        </form>
    </div>

    <p><a href="Index.html">Back to Home</a></p>
</div>

<script>
    // Function to show the register for

    // 

    // Function to add another address input field
    function addAddress() {
        const addressFieldDiv = document.createElement('div');
        addressFieldDiv.className = 'address-field';

        const newAddressInput = document.createElement('input');
        newAddressInput.type = 'text';
        newAddressInput.name = 'address_line';
        newAddressInput.placeholder = 'Enter address line';
        newAddressInput.required = true;

        const removeButton = document.createElement('button');
        removeButton.type = 'button';
        removeButton.className = 'remove-button';
        removeButton.textContent = 'Remove';
        removeButton.onclick = function() {
            removeAddress(removeButton);
        };

        addressFieldDiv.appendChild(newAddressInput);
        addressFieldDiv.appendChild(removeButton);

        const addressList = document.getElementById('address-list');
        addressList.appendChild(addressFieldDiv);
    }

    // Function to remove an address input field
    function removeAddress(button) {
        const addressFieldDiv = button.parentElement;
        addressFieldDiv.remove();
    }
</script>

</body>
</html>