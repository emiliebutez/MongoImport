import json
from transformers import pipeline
from pymongo import MongoClient

client = MongoClient('mongodb://localhost:27017/')

db = client.tweetsDL
collection = db.tweetsDL

sentiments = pipeline("sentiment-analysis", model="nlptown/bert-base-multilingual-uncased-sentiment")

documents = collection.find({"sentiment": {"$eq": None}})
index = 0
total_documents = collection.count_documents({})

for document in documents:
    text = document["text"]
    result = sentiments(text)
    label_set = set(result[0]["label"])
    label = ""

    if label_set.intersection({"1", "2"}):
        label = "Négatif"
    elif label_set.intersection({"4", "5"}):
        label = "Positif"
    else:
        label = "Neutre"

    collection.update_one(
        {"id": document["id"]},
        {"$set": {"sentiment": label}}
    )
    index += 1
    print(f"Document treated: {index}/{total_documents}")

print("All documents have been processed.")
