from pymongo import MongoClient

# Requires the PyMongo package.
# https://api.mongodb.com/python/current

client = MongoClient('mongodb://localhost:27017/')
result = client['tweetsDL']['tweetsDL'].aggregate([
    {
        '$match': {
            'retweeted_status.id_str': {
                '$ne': None
            }
        }
    }, {
        '$project': {
            '_id': 0, 
            'tweet_id': '$id_str', 
            'retweet_id': '$retweeted_status.id_str'
        }
    }, {
        '$out': {
            'db': 'tweets',
            'coll': 'retweet_tweet'
        }
    }
])