from pymongo import MongoClient

# Requires the PyMongo package.
# https://api.mongodb.com/python/current

client = MongoClient('mongodb://localhost:27017/')
result = client['tweetsFilteredV5']['tweetsFilteredV5'].aggregate([
    {
        '$project': {
            'id_str': '$id_str', 
            'lang': '$lang', 
            'text': '$text', 
            'in_reply_to_status_id_str': '$in_reply_to_status_id_str', 
            'in_reply_to_user_id_str': '$in_reply_to_user_id_str', 
            'is_quote_status': '$is_quote_status', 
            'quote_count': '$quote_count', 
            'reply_count': '$reply_count', 
            'retweet_count': '$retweet_count', 
            'favorite_count': '$favorite_count',
            'sentiment': '$sentiment',
            'date': '$date',
            'hour': '$hour'
        }
    }, {
        '$out': {
            'db': 'tweetsFilteredV5',
            'coll': 'main_tweet'
        }
    }
])