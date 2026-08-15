
import {useState} from "react";


function Login({setIsLoggedIn}) {
    const [email,setEmail] = useState("");
    const [password,setPassword] = useState("");
    const handleLogin = async () => {
        try {
            const response = await fetch(`${import.meta.env.VITE_API_URL}/api/auth/login`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    email: email,
                    password: password,
                }),
            });

            const data = await response.json();

            console.log("Login response:", data);

            if (!response.ok) {
                alert("Login failed");
                return;
            }

            alert("Login successful!");
            localStorage.setItem("token", data.token);
            setIsLoggedIn(true);
            console.log("Token:", data);
        } catch (error) {
            console.error("Login error:", error);
            alert("Could not connect to backend");
        }
    };

    return (
        <div className="auth-page">
            <h1 className="login-title">welcome back to kharidisi</h1>


<p className="page-subtitle">LOGIN to continue</p>
            <input
                type="email"
                placeholder="Email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
            />

            <br />

            <input
                type="password"
                placeholder="Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
            />

            <br />

            <button onClick={handleLogin}>Login</button>
        </div>
    );
}

export default Login;
