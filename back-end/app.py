from flask import Flask, request, redirect, render_template, url_for, session
from flask_mysqldb import MySQL, MySQLdb
import bcrypt

app = Flask(__name__)
app.secret_key = b'Ziya the legend'
app.config['MYSQL_HOST'] = 'localhost'
app.config['MYSQL_USER'] = 'root'
app.config['MYSQL_PASSWORD'] = 'root'
app.config['MYSQL_DB'] = 'testdatabase'
app.config['MYSQL_CURSORCLASS'] = 'DictCursor'
mysql = MySQL(app)


@app.route('/', methods=['POST'])
def index():
    if session.get("email") is None:
        return '', 401
    return session['email']


@app.route('/signup', methods=["POST"])
def signup():
    request_data = request.get_json()
    name = request_data['name']
    surname = request_data['surname']
    email = request_data['email']
    password = request_data['password']
    hashed_password = bcrypt.hashpw(password.encode('utf-8'), bcrypt.gensalt())

    cursor = mysql.connection.cursor()
    cursor.execute("INSERT INTO users (name,surname,email,password) VALUES(%s,%s,%s,%s)",
                   (name, surname, email, hashed_password,))
    mysql.connection.commit()
    session['email'] = email
    return "Signup is successful"


@app.route('/login', methods=["GET", "POST"])
def login():
    if request.method == "POST":
        request_data = request.get_json()
        email = request_data['email']
        password = request_data['password'].encode('utf-8')

        cursor = mysql.connection.cursor(MySQLdb.cursors.DictCursor)
        cursor.execute("SELECT * FROM users WHERE VALUES email=%", email)


@app.route('/logout', methods=["POST"])
def logout():
    session.clear()
    return 'You logged out :)'


if __name__ == "__main__":
    app.run(host="0.0.0.0", debug=True, port=5000)
