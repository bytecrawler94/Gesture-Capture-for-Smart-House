# from flask import Flask
# from flask import request
# from werkzeug.utils import secure_filename
# import os.path
# from flask import jsonify

# app = Flask(__name__)


# @app.route("/", methods=['POST'])
# def upload():
#     file = request.files['image']
#     filename = secure_filename(file.filename)

#     path = 'C:\\user_created_gestures'

#     updatedfilename = os.path.join(path, filename)
#     file.save(updatedfilename)
#     resp = jsonify(success=True)
#     return resp


# if __name__ == "__main__":
#     app.run(host="0.0.0.0")

# from flask import Flask, request
# import os
# app = Flask(__name__)

# @app.route("/", methods = ['GET','POST'])
# def hi():
#     return "Hi"
# @app.route("/upload_video", methods = ['GET','POST'])
# def upload_video():
#     if request.files:

#         video = request.files['video']

#         print(video)

#         video.save(os.path.join(r"C:\\Users\\", video.filename))
#     return "video recieved"
# if __name__ == "__main__":
#     app.run(host = '0.0.0.0')

from flask import Flask
from flask import request
from werkzeug.utils import secure_filename
import os.path
from flask import jsonify

app = Flask(__name__)

@app.route("/", methods=['POST'])
def upload():
    file = request.files['image']
    filename = secure_filename(file.filename)

    path = 'C:\\Users\\avina\\Desktop\\temp_server'

    updatedfilename = os.path.join(path, filename)
    file.save(updatedfilename)
    resp = jsonify(success=True)
    return resp

if __name__ == "__main__":
    app.run(host="0.0.0.0")
