import { useState } from "react";

function Register({ goToLogin }) {

    const [formData, setFormData] = useState({
        firstName: "",
        lastName: "",
        email: "",
        phoneNo: "",
        password: ""
    });

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    const handleRegister = async (e) => {
        e.preventDefault();

        try {
            const response= await fetch(`${import.meta.env.VITE_API_URL}/api/users/register`,
                {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(formData)
            });

            if (!response.ok) {
                throw new Error("Registration failed");
            }

            alert("Registration successful! Please login.");

            goToLogin();

        } catch (error) {
            console.error("Registration error:", error);
            alert("Registration failed. Please try again.");
        }
    };

    return (
        <div className="auth-page">
            <h1 className="register-title">welcome to KHARIDISI</h1>


            <p className="register-subtitle">please register to continue</p>

            <form onSubmit={handleRegister}>

                <input
                    type="text"
                    name="firstName"
                    placeholder="First Name"
                    value={formData.firstName}
                    onChange={handleChange}
                    required
                />

                <br />

                <input
                    type="text"
                    name="lastName"
                    placeholder="Last Name"
                    value={formData.lastName}
                    onChange={handleChange}
                    required
                />

                <br />

                <input
                    type="email"
                    name="email"
                    placeholder="Email"
                    value={formData.email}
                    onChange={handleChange}
                    required
                />

                <br />

                <input
                    type="text"
                    name="phoneNo"
                    placeholder="Phone Number"
                    value={formData.phoneNo}
                    onChange={handleChange}
                    required
                />

                <br />

                <input
                    type="password"
                    name="password"
                    placeholder="Password"
                    value={formData.password}
                    onChange={handleChange}
                    required
                />

                <br />

                <button type="submit">
                    Register
                </button>

            </form>

            <br />

            <button onClick={goToLogin}>
                Already have an account? Login
            </button>
        </div>
    );
}

export default Register;
