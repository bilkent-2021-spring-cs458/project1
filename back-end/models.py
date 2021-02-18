import bcrypt
from app import db


class User(db.Model):
    email = db.Column(db.String(50), primary_key=True)
    password = db.Column(db.String(64))
    plan = db.Column(db.Integer)
    receive_offers = db.Column(db.Boolean)

    def __init__(self, email, password, receive_offers):
        self.email = email
        self.password = bcrypt.hashpw(password.encode(), bcrypt.gensalt())
        self.receive_offers = receive_offers

    def as_dict(self):
        return {
            "email": self.email,
            "plan": self.plan,
            "receive_offers": self.receive_offers
        }
