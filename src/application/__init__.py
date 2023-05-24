# Mini Flask example app.
from flask import Flask, render_template, request
import os
app = Flask(__name__)


@app.route("/")
def root():
    return app.send_static_file('photo.jpg')

@app.route('/camera', methods=['GET'])
def get_camera():
    return render_template('camera.html')


@app.route('/geo', methods=['GET'])
def get_geolocation():
    return render_template('location.html')

@app.route('/service-worker.js')
def sw():
    return app.send_static_file('service-worker.js')

@app.route('/send')
def send():
    photo = request.args.get('photo')

    data = "RESPONDEDDATA"

    return render_template('sender.html', data=data)

if __name__ == "__main__":
    if os.environ.get('FLASK_DEBUG'):
        app.run(host='0.0.0.0', debug=True, port=5000)
    else:
        app.run()
