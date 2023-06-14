import json
from transformers import pipeline
from pymongo import MongoClient

client = MongoClient('mongodb://localhost:27017/')

db = client.tweetsFilteredV4
collection = db.tweetsFilteredV4

sentiments = pipeline("sentiment-analysis", model="nlptown/bert-base-multilingual-uncased-sentiment")

documents = collection.find()

for document in documents:
    text = document["text"]
    result = sentiments(text)
    label = result[0]["label"]

    collection.update_one(
        {"id": document["id"]},
        {"$set": {"sentiment": label}}
    )
