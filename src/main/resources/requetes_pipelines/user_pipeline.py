from pymongo import MongoClient

# Requires the PyMongo package.
# https://api.mongodb.com/python/current

client = MongoClient('mongodb://localhost:27017/')
result = client['tweetsFilteredV5']['tweetsFilteredV5'].aggregate([
    {
        '$project': {
            '_id': 0, 
            'id': '$user.id_str', 
            'name': '$user.name', 
            'pseudo': '$user.screen_name', 
            'favorite_count': '$user.favourites_count', 
            'follower_count': '$user.followers_count', 
            'friends_count': '$user.friends_count', 
            'tweet_id': '$id_str'
        }
    }, {
        '$out': {
            'db': 'tweetsFilteredV5',
            'coll': 'user_tweet'
        }
    }
])