import React, { useEffect, useState } from "react";
import axios from "axios";
import "./App.css";

function App() {
    const [accounts, setAccounts] = useState([]);

    useEffect(() => {
        axios.get("http://localhost:8080/api/accounts")
            .then((response) => setAccounts(response.data))
            .catch((error) => console.error("Lỗi khi lấy dữ liệu:", error));
    }, []);

    return (
        <div className="container">
            <h1>List Account</h1>
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Họ và Tên</th>
                    <th>Mật Khẩu</th>
                    <th>Tên Đăng Nhập</th>
                    <th>Department ID</th>
                </tr>
                </thead>
                <tbody>
                {accounts.map((account) => (
                    <tr key={account.id}>
                        <td>{account.id}</td>
                        <td>{account.full_name}</td>
                        <td>{account.password}</td>
                        <td>{account.username}</td>
                        <td>{account.department_id || "N/A"}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}

export default App;
