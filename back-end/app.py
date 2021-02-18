from flask import Flask, request, session
from flask_sqlalchemy import SQLAlchemy
import re

app = Flask(__name__)
app.secret_key = b'Ziya the legend'

app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql://cs458:cs458@localhost:3306/cs458'
db = SQLAlchemy(app)

from models import *  # noqa: E402
db.create_all()


@app.before_request
def check_logged_in():
    if request.endpoint in ['signup', 'login']:
        return
    if 'sign_in_email' not in session:
        return {"error": "NOT_SIGNED_IN"}, 401


@app.route('/api', methods=['POST'])
def index():
    user = User.query.filter_by(email=session['sign_in_email']).first()
    return user.as_dict()


@app.route('/api/signup', methods=["POST"])
def signup():
    request_data = request.get_json()
    try:
        email = request_data['email']
        password = request_data['password']
        receive_offers = request_data['receive_offers']
    except (KeyError, TypeError):
        return {"error": "DATA_MISSING"}, 400
    regex = r"^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@" \
            r"[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$"
    prog = re.compile(regex)
    if not prog.match(email):
        return {"error": "EMAIL_INVALID"}, 400

    if User.query.filter_by(email=email).first() is not None:
        return {"error": 'ACCOUNT_EXISTS'}, 400

    user = User(email, password, receive_offers)
    db.session.add(user)
    db.session.commit()

    session['sign_in_email'] = email
    return {}, 200


@app.route('/api/set_plan', methods=["POST"])
def set_plan():
    request_data = request.get_json()
    try:
        plan = request_data['plan']
    except (KeyError, TypeError):
        return {"error": "DATA_MISSING"}, 400

    user = User.query.filter_by(email=session['sign_in_email']).first()
    user.plan = plan
    db.session.commit()
    return {}, 200


@app.route('/api/login', methods=["POST"])
def login():
    request_data = request.get_json()
    try:
        email = request_data['email']
        password = request_data['password'].encode()
    except (KeyError, TypeError):
        return {"error": "DATA_MISSING"}, 400

    user = User.query.filter_by(email=email).first()
    if user is None:
        return {"error": "NO_ACCOUNT_WITH_EMAIL"}, 400

    if bcrypt.hashpw(password, user.password.encode()) != user.password.encode():
        return {"error": "INCORRECT_PASSWORD"}, 400
    session['sign_in_email'] = email
    return {}, 200


@app.route('/api/logout', methods=["POST"])
def logout():
    session.clear()
    return {}


if __name__ == "__main__":
    app.run(host="0.0.0.0", debug=True, port=5000)
