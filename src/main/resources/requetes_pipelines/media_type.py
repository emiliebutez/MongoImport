from pymongo import MongoClient

# Requires the PyMongo package.
# https://api.mongodb.com/python/current

client = MongoClient('mongodb://localhost:27017/')
result = client['tweetsDL']['tweetsDL'].aggregate([
    {
        '$unwind': {
            'path': '$entities.media', 
            'preserveNullAndEmptyArrays': False
        }
    }, {
        '$project': {
            '_id': 0, 
            'media_type': '$entities.media.type', 
            'tweet_id': '$id_str'
        }
    }, {
        '$out': {
            'db': 'tweets',
            'coll': 'mediaType_tweet'
        }
    }
])