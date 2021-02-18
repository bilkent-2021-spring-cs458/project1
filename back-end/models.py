import bcrypt
from app import db


class User(db.Model):
    email = db.Column(db.String(60), primary_key=True)
    password = db.Column(db.String(64))
    name = db.Column(db.String(50))
    surname = db.Column(db.String(50))
    plan = db.Column(db.Integer)

    def __init__(self, name, surname, email, password):
        self.name = name
        self.surname = surname
        self.email = email
        self.password = bcrypt.hashpw(password.encode(), bcrypt.gensalt())

    def as_dict(self):
        return {
            "name": self.name,
            "surname": self.surname,
            "email": self.email,
            "plan": self.plan
        }
