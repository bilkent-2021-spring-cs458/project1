from flask import Flask, request, session
from flask_cors import CORS
from flask_sqlalchemy import SQLAlchemy
import re

app = Flask(__name__)
app.secret_key = b'Ziya the legend'

app.config['CORS_SUPPORTS_CREDENTIALS'] = True
cors = CORS(app, supports_credentials=True)

app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql://cs458:cs458@localhost:3306/cs458'
db = SQLAlchemy(app)

from models import *  # noqa: E402
db.create_all()


@app.route('/', methods=['POST'])
def index():
    if 'email' in session:
        user = User.query.filter_by(email=session['email']).first()
        return user.as_dict()
    return {"error": "Not signed in"}, 401


@app.route('/signup', methods=["POST"])
def signup():
    request_data = request.get_json()
    try:
        name = request_data['name']
        surname = request_data['surname']
        email = request_data['email']
        password = request_data['password']
    except (KeyError, TypeError):
        return {"error": "Some data missing"}, 400
    regex = r"^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@" \
            r"[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$"
    prog = re.compile(regex)
    if not prog.match(email):
        return {"error": "Email format invalid"}, 400

    if User.query.filter_by(email=email).first() is not None:
        return {"error": 'User already signed up'}, 400

    user = User(name, surname, email, password)
    db.session.add(user)
    db.session.commit()

    session['email'] = email
    return "{}", 200


@app.route('/login', methods=["POST"])
def login():
    request_data = request.get_json()
    try:
        email = request_data['email']
        password = request_data['password'].encode()
    except (KeyError, TypeError):
        return {"error": "Some data missing"}, 400

    user = User.query.filter_by(email=email).first()
    if user is None:
        return {"error": "No such user"}, 400

    if bcrypt.hashpw(password, user.password.encode()) == user.password.encode():
        session['email'] = email
        return '{}', 200
    else:
        return {"error": "No such user"}, 400


@app.route('/logout', methods=["POST"])
def logout():
    if session.get("email") is None:
        return {"error": "Not signed in"}, 401
    session.clear()
    return '{}'


if __name__ == "__main__":
    app.run(host="0.0.0.0", debug=True, port=5000)
