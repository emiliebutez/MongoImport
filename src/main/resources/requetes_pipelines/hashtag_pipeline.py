from pymongo import MongoClient

# Requires the PyMongo package.
# https://api.mongodb.com/python/current

client = MongoClient('mongodb://localhost:27017/')
result = client['tweetsFilteredV5']['tweetsFilteredV5'].aggregate([
    {
        '$unwind': {
            'path': '$entities.hashtags', 
            'preserveNullAndEmptyArrays': False
        }
    }, {
        '$project': {
            '_id': 0, 
            'hashtagText': '$entities.hashtags.text', 
            'tweet_id': '$id_str'
        }
    }, {
        '$out': {
            'db': 'tweetsFilteredV5',
            'coll': 'hasgtags_tweet'
        }
    }
])