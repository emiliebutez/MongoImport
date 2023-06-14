import json
from transformers import pipeline
sentiments = pipeline("sentiment-analysis", model="nlptown/bert-base-multilingual-uncased-sentiment")

text = input()
while text != "<<end of input>>":
    print(json.dumps(sentiments([text])))
    text = input()