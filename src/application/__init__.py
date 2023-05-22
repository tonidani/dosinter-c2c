# Mini Flask example app.
from flask import Flask, render_template

app = Flask(__name__)


@app.route("/")
def root():
    return "Hello, world."

@app.route('/camera', methods=['GET'])
def get_camera():
    return render_template('camera.html')

@app.route('/geo', methods=['GET'])
def get_geolocation():
    return render_template('location.html')


if __name__ == "__main__":
    app.run()
