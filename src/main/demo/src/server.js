require("dotenv").config();
const express = require("express");
const mysql = require("mysql");
const cors = require("cors");

const app = express();
app.use(cors());

// Kết nối MySQL
const db = mysql.createConnection({
    host: "localhost",
    user: 'root',
    password: '',
    database: 'testingsystem_account',
});

db.connect((err) => {
    if (err) {
        console.error("Lỗi kết nối MySQL:", err);
        return;
    }
    console.log("Đã kết nối MySQL!");
});

// API lấy danh sách tài khoản
app.get("/api/accounts", (req, res) => {
    const sql = "SELECT * FROM account";
    db.query(sql, (err, results) => {
        if (err) {
            res.status(500).send(err);
            return;
        }
        res.json(results);
    });
});

// Chạy server
const PORT = process.env.PORT || 5000;
app.listen(PORT, () => console.log(`Server chạy tại http://localhost:${PORT}`));
