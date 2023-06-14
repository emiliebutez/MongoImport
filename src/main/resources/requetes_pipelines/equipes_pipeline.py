from pymongo import MongoClient

# Requires the PyMongo package.
# https://api.mongodb.com/python/current

client = MongoClient('mongodb://localhost:27017/')
result = client['tweetsDL']['tweetsDL'].aggregate([
    {
        '$unwind': {
            'path': '$equipes', 
            'preserveNullAndEmptyArrays': False
        }
    }, {
        '$project': {
            '_id': 0, 
            'equipe': '$equipes', 
            'tweet_id': '$id_str'
        }
    }, {
        '$out': {
            'db': 'tweets5',
            'coll': 'equipes_tweet'
        }
    }
])