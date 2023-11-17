from flask import Flask, request, jsonify
import joblib
import pandas as pd
import logging
from logging.handlers import RotatingFileHandler

app = Flask(__name__)

# Configure logging
logging.basicConfig(level=logging.INFO)
logger = logging.getLogger("ModelServer")
handler = RotatingFileHandler('model_server.log', maxBytes=10000, backupCount=1)
handler.setLevel(logging.INFO)
formatter = logging.Formatter('%(asctime)s - %(name)s - %(levelname)s - %(message)s')
handler.setFormatter(formatter)
logger.addHandler(handler)
app.logger.addHandler(handler)

# Load model
model = joblib.load('isolation_forest.pkl')

# Define the order of features as expected by the model
FEATURE_ORDER = ['Time'] + [f'V{i}' for i in range(1, 29)] + ['Amount']


@app.route('/predict', methods=['POST'])
def predict():
    data = request.json

    # Log input data
    logger.info("Received input data: %s", data)

    # Ensure the DataFrame has the columns in the correct order
    features = pd.DataFrame([data])[FEATURE_ORDER]

    # Check if the DataFrame has the correct number of columns
    if len(features.columns) != len(FEATURE_ORDER):
        logger.error("Received incorrect number of features.")
        return jsonify({'error': 'Incorrect number of features'}), 400

    # Make prediction
    prediction = model.predict(features)

    # Log prediction result
    logger.info("Prediction result: %s", prediction.tolist())

    response = jsonify({'prediction': prediction.tolist()})

    # Log response
    logger.info("Response: %s", response.get_data(as_text=True))

    return response


if __name__ == '__main__':
    # Ensure Flask is not running in silent mode
    app.run(host='0.0.0.0', port=5000, debug=True)
