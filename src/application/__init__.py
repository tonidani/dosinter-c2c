# Mini Flask example app.
from flask import Flask, render_template, request, make_response, abort, g
import os
import datetime
import random
from functools import wraps

app = Flask(__name__)


def token_required(f):
    @wraps(f)
    def decorated(*args, **kwargs):
        token = request.args.get('token')

        if not token or token != 'super_haslo':
            return abort(403)

        return f(*args, **kwargs)

    return decorated

# List of words to choose from
words = ["apple", "banana", "cherry", "date", "elderberry", "fig", "grape", "honeydew"]

# Generate random text
def generate_random_text(length):
    text = ""
    for _ in range(length):
        word = random.choice(words)
        text += word + " "
    return text.strip()

# Generate and print random text
random_text = generate_random_text(10)

CACHE = {}

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
    now = datetime.datetime.now()
    formatted_datetime = now.strftime("%Y-%m-%d %H:%M:%S")
    data_from_phone = request.args.get('data')
    data = generate_random_text(10)
    response = make_response(render_template('sender.html', data=data))
    if 'special_id' not in request.cookies:
    # Generate a special ID or retrieve it from some source
        special_id = generate_special_id() 
        # Set the cookie with the special ID
        response.set_cookie('special_id', special_id)

    CACHE[request.cookies.get('special_id')] = f'{data_from_phone} | {formatted_datetime}'

    return response

@app.route('/send')
def redirect():
    now = datetime.datetime.now()
    formatted_datetime = now.strftime("%Y-%m-%d %H:%M:%S")
    data_from_phone = request.args.get('data')
    data = generate_random_text(10)
    response = make_response(render_template('sender.html', data=data))
    if 'special_id' not in request.cookies:
    # Generate a special ID or retrieve it from some source
        special_id = generate_special_id() 
        # Set the cookie with the special ID
        response.set_cookie('special_id', special_id)

    CACHE[request.cookies.get('special_id')] = f'{data_from_phone} | {formatted_datetime}'

    return response

@app.route('/cache')
@token_required
def cache():
   return CACHE

@app.route('/refresh_cache')
def refresh_cache():
   CACHE = {}
   return {}, 200

def generate_special_id():
    # Get user's IP address
    ip_address = request.remote_addr
    # Get user agent (web browser)
    user_agent = request.headers.get('User-Agent')
    # Combine IP address and user agent to generate special ID
    special_id = str(ip_address) + '_' + str(user_agent)
    
    return special_id



if __name__ == "__main__":
    if os.environ.get('FLASK_DEBUG'):
        app.run(host='0.0.0.0', debug=True, port=5000)
    else:
        app.run()
