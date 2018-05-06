package _GET_;
/*
 * 
 * 
https://developer.twitter.com/en/docs/tweets/timelines/api-reference/get-statuses-mentions_timeline.html
Get Tweet timelines

Overview 
Guides 
API Reference 
API Reference contents  
GET statuses/home_timeline 
GET statuses/mentions_timeline 
GET statuses/user_timeline 
GET statuses/mentions_timeline

Returns the 20 most recent mentions (Tweets containing a users’s @screen_name) for the authenticating user.
The timeline returned is the equivalent of the one seen when you view your mentions on twitter.com.
This method can only return up to 800 tweets.
See Working with Timelines for instructions on traversing timelines.
Resource URL - note, Postman calls this the request URL
https://api.twitter.com/1.1/statuses/mentions_timeline.json

 * 
 * 
 * 
 */

//Programming Assignment
//for my @ishmail2015 account
//get all mentions and print out this JSON data
//"user": {
//    "id": 922643294223589376,
//    "id_str": "922643294223589376",
//    "name": "3rd Eye of Deborah",
//    "screen_name": "ConsecratedMind",
//    "location": "Realms",
//    "description": "Just when I though I had 'arrived' witnessing visions and miracles from God, I had an 'out-of-body experience' October 21st 2015",
//    "url": "https://t.co/VHiNeJepzs",
//...}
//
//
public class GetTwitterMentions {

}

//postman Get Request URL (uri endpoint)
//output from postman
/*
 * [
    {
        "created_at": "Mon Feb 12 05:01:57 +0000 2018",
        "id": 962914649862795264,
        "id_str": "962914649862795264",
        "text": "@ishmail2015 Subscribe :-)  I'm still setting up https://t.co/LSCBfjO8DU",
        "truncated": false,
        "entities": {
            "hashtags": [],
            "symbols": [],
            "user_mentions": [
                {
                    "screen_name": "ishmail2015",
                    "name": "El Cosmic Lupo Solo",
                    "id": 3556936994,
                    "id_str": "3556936994",
                    "indices": [
                        0,
                        12
                    ]
                }
            ],
            "urls": [
                {
                    "url": "https://t.co/LSCBfjO8DU",
                    "expanded_url": "https://www.youtube.com/channel/UCmBRLoEbLBUod55EUrCtmCQ/videos?view_as=subscriber",
                    "display_url": "youtube.com/channel/UCmBRL…",
                    "indices": [
                        49,
                        72
                    ]
                }
            ]
        },
        "source": "<a href=\"http://twitter.com\" rel=\"nofollow\">Twitter Web Client</a>",
        "in_reply_to_status_id": 962801342694678528,
        "in_reply_to_status_id_str": "962801342694678528",
        "in_reply_to_user_id": 3556936994,
        "in_reply_to_user_id_str": "3556936994",
        "in_reply_to_screen_name": "ishmail2015",
        "user": {
            "id": 922643294223589376,
            "id_str": "922643294223589376",
            "name": "3rd Eye of Deborah",
            "screen_name": "ConsecratedMind",
            "location": "Realms",
            "description": "Just when I though I had 'arrived' witnessing visions and miracles from God, I had an 'out-of-body experience' October 21st 2015",
            "url": "https://t.co/VHiNeJepzs",
            "entities": {
                "url": {
                    "urls": [
                        {
                            "url": "https://t.co/VHiNeJepzs",
                            "expanded_url": "https://www.youtube.com/channel/UCmBRLoEbLBUod55EUrCtmCQ",
                            "display_url": "youtube.com/channel/UCmBRL…",
                            "indices": [
                                0,
                                23
                            ]
                        }
                    ]
                },
                "description": {
                    "urls": []
                }
            },
            "protected": false,
            "followers_count": 13,
            "friends_count": 29,
            "listed_count": 0,
            "created_at": "Tue Oct 24 01:57:58 +0000 2017",
            "favourites_count": 118,
            "utc_offset": null,
            "time_zone": null,
            "geo_enabled": false,
            "verified": false,
            "statuses_count": 77,
            "lang": "en",
            "contributors_enabled": false,
            "is_translator": false,
            "is_translation_enabled": false,
            "profile_background_color": "000000",
            "profile_background_image_url": "http://abs.twimg.com/images/themes/theme1/bg.png",
            "profile_background_image_url_https": "https://abs.twimg.com/images/themes/theme1/bg.png",
            "profile_background_tile": false,
            "profile_image_url": "http://pbs.twimg.com/profile_images/953040763893567488/ZJ0B9wUN_normal.jpg",
            "profile_image_url_https": "https://pbs.twimg.com/profile_images/953040763893567488/ZJ0B9wUN_normal.jpg",
            "profile_banner_url": "https://pbs.twimg.com/profile_banners/922643294223589376/1521696010",
            "profile_link_color": "1B95E0",
            "profile_sidebar_border_color": "000000",
            "profile_sidebar_fill_color": "000000",
            "profile_text_color": "000000",
            "profile_use_background_image": false,
            "has_extended_profile": false,
            "default_profile": false,
            "default_profile_image": false,
            "following": true,
            "follow_request_sent": false,
            "notifications": false,
            "translator_type": "none"
        },
        "geo": null,
        "coordinates": null,
        "place": null,
        "contributors": null,
        "is_quote_status": false,
        "retweet_count": 0,
        "favorite_count": 1,
        "favorited": true,
        "retweeted": false,
        "possibly_sensitive": false,
        "lang": "en"
    },
    {
        "created_at": "Mon Feb 12 04:58:50 +0000 2018",
        "id": 962913867067285504,
        "id_str": "962913867067285504",
        "text": "@ishmail2015 We sure can!  That's what my channel is going to be about.  Out-of-body experience through God's word.… https://t.co/5WSGuU7rhb",
        "truncated": true,
        "entities": {
            "hashtags": [],
            "symbols": [],
            "user_mentions": [
                {
                    "screen_name": "ishmail2015",
                    "name": "Cosmico Lupo Solo",
                    "id": 3556936994,
                    "id_str": "3556936994",
                    "indices": [
                        0,
                        12
                    ]
                }
            ],
            "urls": [
                {
                    "url": "https://t.co/5WSGuU7rhb",
                    "expanded_url": "https://twitter.com/i/web/status/962913867067285504",
                    "display_url": "twitter.com/i/web/status/9…",
                    "indices": [
                        117,
                        140
                    ]
                }
            ]
        },
        "source": "<a href=\"http://twitter.com\" rel=\"nofollow\">Twitter Web Client</a>",
        "in_reply_to_status_id": 962801342694678528,
        "in_reply_to_status_id_str": "962801342694678528",
        "in_reply_to_user_id": 3556936994,
        "in_reply_to_user_id_str": "3556936994",
        "in_reply_to_screen_name": "ishmail2015",
        "user": {
            "id": 922643294223589376,
            "id_str": "922643294223589376",
            "name": "3rd Eye of Deborah",
            "screen_name": "ConsecratedMind",
            "location": "Realms",
            "description": "Just when I though I had 'arrived' witnessing visions and miracles from God, I had an 'out-of-body experience' October 21st 2015",
            "url": "https://t.co/VHiNeJepzs",
            "entities": {
                "url": {
                    "urls": [
                        {
                            "url": "https://t.co/VHiNeJepzs",
                            "expanded_url": "https://www.youtube.com/channel/UCmBRLoEbLBUod55EUrCtmCQ",
                            "display_url": "youtube.com/channel/UCmBRL…",
                            "indices": [
                                0,
                                23
                            ]
                        }
                    ]
                },
                "description": {
                    "urls": []
                }
            },
            "protected": false,
            "followers_count": 13,
            "friends_count": 29,
            "listed_count": 0,
            "created_at": "Tue Oct 24 01:57:58 +0000 2017",
            "favourites_count": 118,
            "utc_offset": null,
            "time_zone": null,
            "geo_enabled": false,
            "verified": false,
            "statuses_count": 77,
            "lang": "en",
            "contributors_enabled": false,
            "is_translator": false,
            "is_translation_enabled": false,
            "profile_background_color": "000000",
            "profile_background_image_url": "http://abs.twimg.com/images/themes/theme1/bg.png",
            "profile_background_image_url_https": "https://abs.twimg.com/images/themes/theme1/bg.png",
            "profile_background_tile": false,
            "profile_image_url": "http://pbs.twimg.com/profile_images/953040763893567488/ZJ0B9wUN_normal.jpg",
            "profile_image_url_https": "https://pbs.twimg.com/profile_images/953040763893567488/ZJ0B9wUN_normal.jpg",
            "profile_banner_url": "https://pbs.twimg.com/profile_banners/922643294223589376/1521696010",
            "profile_link_color": "1B95E0",
            "profile_sidebar_border_color": "000000",
            "profile_sidebar_fill_color": "000000",
            "profile_text_color": "000000",
            "profile_use_background_image": false,
            "has_extended_profile": false,
            "default_profile": false,
            "default_profile_image": false,
            "following": true,
            "follow_request_sent": false,
            "notifications": false,
            "translator_type": "none"
        },
        "geo": null,
        "coordinates": null,
        "place": null,
        "contributors": null,
        "is_quote_status": false,
        "retweet_count": 0,
        "favorite_count": 0,
        "favorited": false,
        "retweeted": false,
        "lang": "en"
    },
    {
        "created_at": "Fri Jan 05 22:30:49 +0000 2018",
        "id": 949407868343783424,
        "id_str": "949407868343783424",
        "text": "@ishmail2015 Lupo 😂😂😂",
        "truncated": false,
        "entities": {
            "hashtags": [],
            "symbols": [],
            "user_mentions": [
                {
                    "screen_name": "ishmail2015",
                    "name": "El Cosmic Lupo Solo",
                    "id": 3556936994,
                    "id_str": "3556936994",
                    "indices": [
                        0,
                        12
                    ]
                }
            ],
            "urls": []
        },
        "source": "<a href=\"http://twitter.com/download/iphone\" rel=\"nofollow\">Twitter for iPhone</a>",
        "in_reply_to_status_id": 949392052382449664,
        "in_reply_to_status_id_str": "949392052382449664",
        "in_reply_to_user_id": 3556936994,
        "in_reply_to_user_id_str": "3556936994",
        "in_reply_to_screen_name": "ishmail2015",
        "user": {
            "id": 583018272,
            "id_str": "583018272",
            "name": "Maelle ♛",
            "screen_name": "JustMaelle",
            "location": "Milan | Italy",
            "description": "Glamour #Model, Alternative #CamGirl and Freelance Designer. #Bisexual. ᵀᴴᴱ’ᴼᴿᴵᴳᴵᴻᴬᴸ",
            "url": "https://t.co/olm1wLHNO0",
            "entities": {
                "url": {
                    "urls": [
                        {
                            "url": "https://t.co/olm1wLHNO0",
                            "expanded_url": "http://maelle.cammodels.com",
                            "display_url": "maelle.cammodels.com",
                            "indices": [
                                0,
                                23
                            ]
                        }
                    ]
                },
                "description": {
                    "urls": []
                }
            },
            "protected": false,
            "followers_count": 46743,
            "friends_count": 137,
            "listed_count": 317,
            "created_at": "Thu May 17 16:45:40 +0000 2012",
            "favourites_count": 71142,
            "utc_offset": 7200,
            "time_zone": "Rome",
            "geo_enabled": false,
            "verified": false,
            "statuses_count": 25856,
            "lang": "it",
            "contributors_enabled": false,
            "is_translator": false,
            "is_translation_enabled": false,
            "profile_background_color": "3F3F3F",
            "profile_background_image_url": "http://pbs.twimg.com/profile_background_images/669545206/f5f0af9d74363869ee1c2ea235fc0a6f.jpeg",
            "profile_background_image_url_https": "https://pbs.twimg.com/profile_background_images/669545206/f5f0af9d74363869ee1c2ea235fc0a6f.jpeg",
            "profile_background_tile": false,
            "profile_image_url": "http://pbs.twimg.com/profile_images/789617933605535744/53yRAZBo_normal.jpg",
            "profile_image_url_https": "https://pbs.twimg.com/profile_images/789617933605535744/53yRAZBo_normal.jpg",
            "profile_banner_url": "https://pbs.twimg.com/profile_banners/583018272/1509415238",
            "profile_link_color": "AC2769",
            "profile_sidebar_border_color": "FFFFFF",
            "profile_sidebar_fill_color": "EFEFEF",
            "profile_text_color": "333333",
            "profile_use_background_image": true,
            "has_extended_profile": true,
            "default_profile": false,
            "default_profile_image": false,
            "following": true,
            "follow_request_sent": false,
            "notifications": false,
            "translator_type": "none"
        },
        "geo": null,
        "coordinates": null,
        "place": null,
        "contributors": null,
        "is_quote_status": false,
        "retweet_count": 0,
        "favorite_count": 0,
        "favorited": false,
        "retweeted": false,
        "lang": "tl"
    },
    {
        "created_at": "Tue Jan 02 09:14:08 +0000 2018",
        "id": 948120211588304897,
        "id_str": "948120211588304897",
        "text": "@ishmail2015 @realstreamate @StreamateModels ❤️❤️❤️",
        "truncated": false,
        "entities": {
            "hashtags": [],
            "symbols": [],
            "user_mentions": [
                {
                    "screen_name": "ishmail2015",
                    "name": "Cosmico Lupo Solo",
                    "id": 3556936994,
                    "id_str": "3556936994",
                    "indices": [
                        0,
                        12
                    ]
                },
                {
                    "screen_name": "realstreamate",
                    "name": "Streamate",
                    "id": 4922254453,
                    "id_str": "4922254453",
                    "indices": [
                        13,
                        27
                    ]
                },
                {
                    "screen_name": "StreamateModels",
                    "name": "💙 Streamate Models 💙",
                    "id": 133458946,
                    "id_str": "133458946",
                    "indices": [
                        28,
                        44
                    ]
                }
            ],
            "urls": []
        },
        "source": "<a href=\"http://twitter.com/download/iphone\" rel=\"nofollow\">Twitter for iPhone</a>",
        "in_reply_to_status_id": 947908951500132352,
        "in_reply_to_status_id_str": "947908951500132352",
        "in_reply_to_user_id": 3556936994,
        "in_reply_to_user_id_str": "3556936994",
        "in_reply_to_screen_name": "ishmail2015",
        "user": {
            "id": 1517816730,
            "id_str": "1517816730",
            "name": "Tatti_D",
            "screen_name": "Tatti_D",
            "location": "Wishlist at",
            "description": "Cammodel (18+) Join my site https://t.co/MDpDLvNcOB \nFollow my Snapchat txxx103\nGet my videos at https://t.co/U0Z7I69pQn",
            "url": "https://t.co/qZCsI83ytO",
            "entities": {
                "url": {
                    "urls": [
                        {
                            "url": "https://t.co/qZCsI83ytO",
                            "expanded_url": "https://www.amazon.com/gp/registry/wishlist/?ie=UTF8&cid=A1ZTTR87UKC4S8",
                            "display_url": "amazon.com/gp/registry/wi…",
                            "indices": [
                                0,
                                23
                            ]
                        }
                    ]
                },
                "description": {
                    "urls": [
                        {
                            "url": "https://t.co/MDpDLvNcOB",
                            "expanded_url": "http://tatti.cammodels.com",
                            "display_url": "tatti.cammodels.com",
                            "indices": [
                                28,
                                51
                            ]
                        },
                        {
                            "url": "https://t.co/U0Z7I69pQn",
                            "expanded_url": "https://www.manyvids.com/Profile/477189/Tatti_D/",
                            "display_url": "manyvids.com/Profile/477189…",
                            "indices": [
                                97,
                                120
                            ]
                        }
                    ]
                }
            },
            "protected": false,
            "followers_count": 32620,
            "friends_count": 445,
            "listed_count": 108,
            "created_at": "Fri Jun 14 23:16:36 +0000 2013",
            "favourites_count": 1947,
            "utc_offset": -14400,
            "time_zone": "Eastern Time (US & Canada)",
            "geo_enabled": false,
            "verified": false,
            "statuses_count": 1224,
            "lang": "en",
            "contributors_enabled": false,
            "is_translator": false,
            "is_translation_enabled": false,
            "profile_background_color": "EBEBEB",
            "profile_background_image_url": "http://abs.twimg.com/images/themes/theme7/bg.gif",
            "profile_background_image_url_https": "https://abs.twimg.com/images/themes/theme7/bg.gif",
            "profile_background_tile": false,
            "profile_image_url": "http://pbs.twimg.com/profile_images/590604121096486913/pigE4iNg_normal.jpg",
            "profile_image_url_https": "https://pbs.twimg.com/profile_images/590604121096486913/pigE4iNg_normal.jpg",
            "profile_banner_url": "https://pbs.twimg.com/profile_banners/1517816730/1413484165",
            "profile_link_color": "990000",
            "profile_sidebar_border_color": "DFDFDF",
            "profile_sidebar_fill_color": "F3F3F3",
            "profile_text_color": "333333",
            "profile_use_background_image": true,
            "has_extended_profile": true,
            "default_profile": false,
            "default_profile_image": false,
            "following": true,
            "follow_request_sent": false,
            "notifications": false,
            "translator_type": "none"
        },
        "geo": null,
        "coordinates": null,
        "place": null,
        "contributors": null,
        "is_quote_status": false,
        "retweet_count": 0,
        "favorite_count": 0,
        "favorited": false,
        "retweeted": false,
        "lang": "und"
    },
    {
        "created_at": "Sat Sep 09 22:43:37 +0000 2017",
        "id": 906649322317639680,
        "id_str": "906649322317639680",
        "text": "@ishmail2015 I knew you'd like that one Papi",
        "truncated": false,
        "entities": {
            "hashtags": [],
            "symbols": [],
            "user_mentions": [
                {
                    "screen_name": "ishmail2015",
                    "name": "Cosmico Lupo Solo",
                    "id": 3556936994,
                    "id_str": "3556936994",
                    "indices": [
                        0,
                        12
                    ]
                }
            ],
            "urls": []
        },
        "source": "<a href=\"http://twitter.com/download/iphone\" rel=\"nofollow\">Twitter for iPhone</a>",
        "in_reply_to_status_id": 906633175828332544,
        "in_reply_to_status_id_str": "906633175828332544",
        "in_reply_to_user_id": 3556936994,
        "in_reply_to_user_id_str": "3556936994",
        "in_reply_to_screen_name": "ishmail2015",
        "user": {
            "id": 1517816730,
            "id_str": "1517816730",
            "name": "Tatti_D",
            "screen_name": "Tatti_D",
            "location": "Wishlist at",
            "description": "Cammodel (18+) Join my site https://t.co/MDpDLvNcOB \nFollow my Snapchat txxx103\nGet my videos at https://t.co/U0Z7I69pQn",
            "url": "https://t.co/qZCsI83ytO",
            "entities": {
                "url": {
                    "urls": [
                        {
                            "url": "https://t.co/qZCsI83ytO",
                            "expanded_url": "https://www.amazon.com/gp/registry/wishlist/?ie=UTF8&cid=A1ZTTR87UKC4S8",
                            "display_url": "amazon.com/gp/registry/wi…",
                            "indices": [
                                0,
                                23
                            ]
                        }
                    ]
                },
                "description": {
                    "urls": [
                        {
                            "url": "https://t.co/MDpDLvNcOB",
                            "expanded_url": "http://tatti.cammodels.com",
                            "display_url": "tatti.cammodels.com",
                            "indices": [
                                28,
                                51
                            ]
                        },
                        {
                            "url": "https://t.co/U0Z7I69pQn",
                            "expanded_url": "https://www.manyvids.com/Profile/477189/Tatti_D/",
                            "display_url": "manyvids.com/Profile/477189…",
                            "indices": [
                                97,
                                120
                            ]
                        }
                    ]
                }
            },
            "protected": false,
            "followers_count": 32620,
            "friends_count": 445,
            "listed_count": 108,
            "created_at": "Fri Jun 14 23:16:36 +0000 2013",
            "favourites_count": 1947,
            "utc_offset": -14400,
            "time_zone": "Eastern Time (US & Canada)",
            "geo_enabled": false,
            "verified": false,
            "statuses_count": 1224,
            "lang": "en",
            "contributors_enabled": false,
            "is_translator": false,
            "is_translation_enabled": false,
            "profile_background_color": "EBEBEB",
            "profile_background_image_url": "http://abs.twimg.com/images/themes/theme7/bg.gif",
            "profile_background_image_url_https": "https://abs.twimg.com/images/themes/theme7/bg.gif",
            "profile_background_tile": false,
            "profile_image_url": "http://pbs.twimg.com/profile_images/590604121096486913/pigE4iNg_normal.jpg",
            "profile_image_url_https": "https://pbs.twimg.com/profile_images/590604121096486913/pigE4iNg_normal.jpg",
            "profile_banner_url": "https://pbs.twimg.com/profile_banners/1517816730/1413484165",
            "profile_link_color": "990000",
            "profile_sidebar_border_color": "DFDFDF",
            "profile_sidebar_fill_color": "F3F3F3",
            "profile_text_color": "333333",
            "profile_use_background_image": true,
            "has_extended_profile": true,
            "default_profile": false,
            "default_profile_image": false,
            "following": true,
            "follow_request_sent": false,
            "notifications": false,
            "translator_type": "none"
        },
        "geo": null,
        "coordinates": null,
        "place": null,
        "contributors": null,
        "is_quote_status": false,
        "retweet_count": 0,
        "favorite_count": 1,
        "favorited": true,
        "retweeted": false,
        "lang": "en"
    },
    {
        "created_at": "Sun Mar 05 00:47:01 +0000 2017",
        "id": 838189070547312642,
        "id_str": "838189070547312642",
        "text": "@ishmail2015 Awwwwww😍😍😍😍",
        "truncated": false,
        "entities": {
            "hashtags": [],
            "symbols": [],
            "user_mentions": [
                {
                    "screen_name": "ishmail2015",
                    "name": "Cosmico Lupo Solo",
                    "id": 3556936994,
                    "id_str": "3556936994",
                    "indices": [
                        0,
                        12
                    ]
                }
            ],
            "urls": []
        },
        "source": "<a href=\"http://twitter.com/download/iphone\" rel=\"nofollow\">Twitter for iPhone</a>",
        "in_reply_to_status_id": 838138135833788416,
        "in_reply_to_status_id_str": "838138135833788416",
        "in_reply_to_user_id": 3556936994,
        "in_reply_to_user_id_str": "3556936994",
        "in_reply_to_screen_name": "ishmail2015",
        "user": {
            "id": 1517816730,
            "id_str": "1517816730",
            "name": "Tatti_D",
            "screen_name": "Tatti_D",
            "location": "Wishlist at",
            "description": "Cammodel (18+) Join my site https://t.co/MDpDLvNcOB \nFollow my Snapchat txxx103\nGet my videos at https://t.co/U0Z7I69pQn",
            "url": "https://t.co/qZCsI83ytO",
            "entities": {
                "url": {
                    "urls": [
                        {
                            "url": "https://t.co/qZCsI83ytO",
                            "expanded_url": "https://www.amazon.com/gp/registry/wishlist/?ie=UTF8&cid=A1ZTTR87UKC4S8",
                            "display_url": "amazon.com/gp/registry/wi…",
                            "indices": [
                                0,
                                23
                            ]
                        }
                    ]
                },
                "description": {
                    "urls": [
                        {
                            "url": "https://t.co/MDpDLvNcOB",
                            "expanded_url": "http://tatti.cammodels.com",
                            "display_url": "tatti.cammodels.com",
                            "indices": [
                                28,
                                51
                            ]
                        },
                        {
                            "url": "https://t.co/U0Z7I69pQn",
                            "expanded_url": "https://www.manyvids.com/Profile/477189/Tatti_D/",
                            "display_url": "manyvids.com/Profile/477189…",
                            "indices": [
                                97,
                                120
                            ]
                        }
                    ]
                }
            },
            "protected": false,
            "followers_count": 32620,
            "friends_count": 445,
            "listed_count": 108,
            "created_at": "Fri Jun 14 23:16:36 +0000 2013",
            "favourites_count": 1947,
            "utc_offset": -14400,
            "time_zone": "Eastern Time (US & Canada)",
            "geo_enabled": false,
            "verified": false,
            "statuses_count": 1224,
            "lang": "en",
            "contributors_enabled": false,
            "is_translator": false,
            "is_translation_enabled": false,
            "profile_background_color": "EBEBEB",
            "profile_background_image_url": "http://abs.twimg.com/images/themes/theme7/bg.gif",
            "profile_background_image_url_https": "https://abs.twimg.com/images/themes/theme7/bg.gif",
            "profile_background_tile": false,
            "profile_image_url": "http://pbs.twimg.com/profile_images/590604121096486913/pigE4iNg_normal.jpg",
            "profile_image_url_https": "https://pbs.twimg.com/profile_images/590604121096486913/pigE4iNg_normal.jpg",
            "profile_banner_url": "https://pbs.twimg.com/profile_banners/1517816730/1413484165",
            "profile_link_color": "990000",
            "profile_sidebar_border_color": "DFDFDF",
            "profile_sidebar_fill_color": "F3F3F3",
            "profile_text_color": "333333",
            "profile_use_background_image": true,
            "has_extended_profile": true,
            "default_profile": false,
            "default_profile_image": false,
            "following": true,
            "follow_request_sent": false,
            "notifications": false,
            "translator_type": "none"
        },
        "geo": null,
        "coordinates": null,
        "place": null,
        "contributors": null,
        "is_quote_status": false,
        "retweet_count": 0,
        "favorite_count": 1,
        "favorited": true,
        "retweeted": false,
        "lang": "und"
    },
    {
        "created_at": "Tue Feb 28 02:51:23 +0000 2017",
        "id": 836408426880249860,
        "id_str": "836408426880249860",
        "text": "@ishmail2015 https://t.co/tVvzgqJVEC",
        "truncated": false,
        "entities": {
            "hashtags": [],
            "symbols": [],
            "user_mentions": [
                {
                    "screen_name": "ishmail2015",
                    "name": "Cosmico Lupo Solo",
                    "id": 3556936994,
                    "id_str": "3556936994",
                    "indices": [
                        0,
                        12
                    ]
                }
            ],
            "urls": [
                {
                    "url": "https://t.co/tVvzgqJVEC",
                    "expanded_url": "http://www.drjoedispenza.com/blog/consciousness/what-does-the-spike-in-the-schumann-resonance-mean/",
                    "display_url": "drjoedispenza.com/blog/conscious…",
                    "indices": [
                        13,
                        36
                    ]
                }
            ]
        },
        "source": "<a href=\"http://twitter.com\" rel=\"nofollow\">Twitter Web Client</a>",
        "in_reply_to_status_id": 836387651548655616,
        "in_reply_to_status_id_str": "836387651548655616",
        "in_reply_to_user_id": 3556936994,
        "in_reply_to_user_id_str": "3556936994",
        "in_reply_to_screen_name": "ishmail2015",
        "user": {
            "id": 4590354219,
            "id_str": "4590354219",
            "name": "LikeLikeLicka",
            "screen_name": "ChampagneMexxi",
            "location": "",
            "description": "VIP Access to your girl, @MexxicanBeauty",
            "url": "https://t.co/ifweK9vzcG",
            "entities": {
                "url": {
                    "urls": [
                        {
                            "url": "https://t.co/ifweK9vzcG",
                            "expanded_url": "http://mexxicanbeauty.manyvids.com",
                            "display_url": "mexxicanbeauty.manyvids.com",
                            "indices": [
                                0,
                                23
                            ]
                        }
                    ]
                },
                "description": {
                    "urls": []
                }
            },
            "protected": true,
            "followers_count": 27,
            "friends_count": 29,
            "listed_count": 1,
            "created_at": "Thu Dec 24 15:15:31 +0000 2015",
            "favourites_count": 199,
            "utc_offset": -25200,
            "time_zone": "Pacific Time (US & Canada)",
            "geo_enabled": false,
            "verified": false,
            "statuses_count": 183,
            "lang": "en",
            "contributors_enabled": false,
            "is_translator": false,
            "is_translation_enabled": false,
            "profile_background_color": "000000",
            "profile_background_image_url": "http://abs.twimg.com/images/themes/theme1/bg.png",
            "profile_background_image_url_https": "https://abs.twimg.com/images/themes/theme1/bg.png",
            "profile_background_tile": false,
            "profile_image_url": "http://pbs.twimg.com/profile_images/827958184224526336/RRejSWsN_normal.jpg",
            "profile_image_url_https": "https://pbs.twimg.com/profile_images/827958184224526336/RRejSWsN_normal.jpg",
            "profile_banner_url": "https://pbs.twimg.com/profile_banners/4590354219/1486234330",
            "profile_link_color": "000000",
            "profile_sidebar_border_color": "000000",
            "profile_sidebar_fill_color": "000000",
            "profile_text_color": "000000",
            "profile_use_background_image": false,
            "has_extended_profile": true,
            "default_profile": false,
            "default_profile_image": false,
            "following": true,
            "follow_request_sent": false,
            "notifications": false,
            "translator_type": "none"
        },
        "geo": null,
        "coordinates": null,
        "place": null,
        "contributors": null,
        "is_quote_status": false,
        "retweet_count": 0,
        "favorite_count": 0,
        "favorited": false,
        "retweeted": false,
        "possibly_sensitive": false,
        "lang": "und"
    },
    {
        "created_at": "Sat Feb 25 01:21:46 +0000 2017",
        "id": 835298709231517700,
        "id_str": "835298709231517700",
        "text": "@ishmail2015 vamos!!",
        "truncated": false,
        "entities": {
            "hashtags": [],
            "symbols": [],
            "user_mentions": [
                {
                    "screen_name": "ishmail2015",
                    "name": "Cosmico Lupo Solo",
                    "id": 3556936994,
                    "id_str": "3556936994",
                    "indices": [
                        0,
                        12
                    ]
                }
            ],
            "urls": []
        },
        "source": "<a href=\"http://twitter.com\" rel=\"nofollow\">Twitter Web Client</a>",
        "in_reply_to_status_id": 834629252418981890,
        "in_reply_to_status_id_str": "834629252418981890",
        "in_reply_to_user_id": 3556936994,
        "in_reply_to_user_id_str": "3556936994",
        "in_reply_to_screen_name": "ishmail2015",
        "user": {
            "id": 4590354219,
            "id_str": "4590354219",
            "name": "LikeLikeLicka",
            "screen_name": "ChampagneMexxi",
            "location": "",
            "description": "VIP Access to your girl, @MexxicanBeauty",
            "url": "https://t.co/ifweK9vzcG",
            "entities": {
                "url": {
                    "urls": [
                        {
                            "url": "https://t.co/ifweK9vzcG",
                            "expanded_url": "http://mexxicanbeauty.manyvids.com",
                            "display_url": "mexxicanbeauty.manyvids.com",
                            "indices": [
                                0,
                                23
                            ]
                        }
                    ]
                },
                "description": {
                    "urls": []
                }
            },
            "protected": true,
            "followers_count": 27,
            "friends_count": 29,
            "listed_count": 1,
            "created_at": "Thu Dec 24 15:15:31 +0000 2015",
            "favourites_count": 199,
            "utc_offset": -25200,
            "time_zone": "Pacific Time (US & Canada)",
            "geo_enabled": false,
            "verified": false,
            "statuses_count": 183,
            "lang": "en",
            "contributors_enabled": false,
            "is_translator": false,
            "is_translation_enabled": false,
            "profile_background_color": "000000",
            "profile_background_image_url": "http://abs.twimg.com/images/themes/theme1/bg.png",
            "profile_background_image_url_https": "https://abs.twimg.com/images/themes/theme1/bg.png",
            "profile_background_tile": false,
            "profile_image_url": "http://pbs.twimg.com/profile_images/827958184224526336/RRejSWsN_normal.jpg",
            "profile_image_url_https": "https://pbs.twimg.com/profile_images/827958184224526336/RRejSWsN_normal.jpg",
            "profile_banner_url": "https://pbs.twimg.com/profile_banners/4590354219/1486234330",
            "profile_link_color": "000000",
            "profile_sidebar_border_color": "000000",
            "profile_sidebar_fill_color": "000000",
            "profile_text_color": "000000",
            "profile_use_background_image": false,
            "has_extended_profile": true,
            "default_profile": false,
            "default_profile_image": false,
            "following": true,
            "follow_request_sent": false,
            "notifications": false,
            "translator_type": "none"
        },
        "geo": null,
        "coordinates": null,
        "place": null,
        "contributors": null,
        "is_quote_status": false,
        "retweet_count": 0,
        "favorite_count": 1,
        "favorited": true,
        "retweeted": false,
        "lang": "es"
    },
    {
        "created_at": "Mon Jan 23 22:54:13 +0000 2017",
        "id": 823665167909224449,
        "id_str": "823665167909224449",
        "text": "@ishmail2015 it is what it is Papi!",
        "truncated": false,
        "entities": {
            "hashtags": [],
            "symbols": [],
            "user_mentions": [
                {
                    "screen_name": "ishmail2015",
                    "name": "Cosmico Lupo Solo",
                    "id": 3556936994,
                    "id_str": "3556936994",
                    "indices": [
                        0,
                        12
                    ]
                }
            ],
            "urls": []
        },
        "source": "<a href=\"http://twitter.com/download/iphone\" rel=\"nofollow\">Twitter for iPhone</a>",
        "in_reply_to_status_id": 823655706276442113,
        "in_reply_to_status_id_str": "823655706276442113",
        "in_reply_to_user_id": 3556936994,
        "in_reply_to_user_id_str": "3556936994",
        "in_reply_to_screen_name": "ishmail2015",
        "user": {
            "id": 1517816730,
            "id_str": "1517816730",
            "name": "Tatti_D",
            "screen_name": "Tatti_D",
            "location": "Wishlist at",
            "description": "Cammodel (18+) Join my site https://t.co/MDpDLvNcOB \nFollow my Snapchat txxx103\nGet my videos at https://t.co/U0Z7I69pQn",
            "url": "https://t.co/qZCsI83ytO",
            "entities": {
                "url": {
                    "urls": [
                        {
                            "url": "https://t.co/qZCsI83ytO",
                            "expanded_url": "https://www.amazon.com/gp/registry/wishlist/?ie=UTF8&cid=A1ZTTR87UKC4S8",
                            "display_url": "amazon.com/gp/registry/wi…",
                            "indices": [
                                0,
                                23
                            ]
                        }
                    ]
                },
                "description": {
                    "urls": [
                        {
                            "url": "https://t.co/MDpDLvNcOB",
                            "expanded_url": "http://tatti.cammodels.com",
                            "display_url": "tatti.cammodels.com",
                            "indices": [
                                28,
                                51
                            ]
                        },
                        {
                            "url": "https://t.co/U0Z7I69pQn",
                            "expanded_url": "https://www.manyvids.com/Profile/477189/Tatti_D/",
                            "display_url": "manyvids.com/Profile/477189…",
                            "indices": [
                                97,
                                120
                            ]
                        }
                    ]
                }
            },
            "protected": false,
            "followers_count": 32620,
            "friends_count": 445,
            "listed_count": 108,
            "created_at": "Fri Jun 14 23:16:36 +0000 2013",
            "favourites_count": 1947,
            "utc_offset": -14400,
            "time_zone": "Eastern Time (US & Canada)",
            "geo_enabled": false,
            "verified": false,
            "statuses_count": 1224,
            "lang": "en",
            "contributors_enabled": false,
            "is_translator": false,
            "is_translation_enabled": false,
            "profile_background_color": "EBEBEB",
            "profile_background_image_url": "http://abs.twimg.com/images/themes/theme7/bg.gif",
            "profile_background_image_url_https": "https://abs.twimg.com/images/themes/theme7/bg.gif",
            "profile_background_tile": false,
            "profile_image_url": "http://pbs.twimg.com/profile_images/590604121096486913/pigE4iNg_normal.jpg",
            "profile_image_url_https": "https://pbs.twimg.com/profile_images/590604121096486913/pigE4iNg_normal.jpg",
            "profile_banner_url": "https://pbs.twimg.com/profile_banners/1517816730/1413484165",
            "profile_link_color": "990000",
            "profile_sidebar_border_color": "DFDFDF",
            "profile_sidebar_fill_color": "F3F3F3",
            "profile_text_color": "333333",
            "profile_use_background_image": true,
            "has_extended_profile": true,
            "default_profile": false,
            "default_profile_image": false,
            "following": true,
            "follow_request_sent": false,
            "notifications": false,
            "translator_type": "none"
        },
        "geo": null,
        "coordinates": null,
        "place": null,
        "contributors": null,
        "is_quote_status": false,
        "retweet_count": 0,
        "favorite_count": 0,
        "favorited": false,
        "retweeted": false,
        "lang": "en"
    },
    {
        "created_at": "Sun Nov 01 04:47:42 +0000 2015",
        "id": 660679585558896640,
        "id_str": "660679585558896640",
        "text": "@ishmail2015 Tweet this:\n\n@Brooklyn_Chase #DogfartGOTM",
        "truncated": false,
        "entities": {
            "hashtags": [
                {
                    "text": "DogfartGOTM",
                    "indices": [
                        42,
                        54
                    ]
                }
            ],
            "symbols": [],
            "user_mentions": [
                {
                    "screen_name": "ishmail2015",
                    "name": "Cosmico Lupo Solo",
                    "id": 3556936994,
                    "id_str": "3556936994",
                    "indices": [
                        0,
                        12
                    ]
                },
                {
                    "screen_name": "Brooklyn_Chase",
                    "name": "Brooklyn Chase™",
                    "id": 628566074,
                    "id_str": "628566074",
                    "indices": [
                        26,
                        41
                    ]
                }
            ],
            "urls": []
        },
        "source": "<a href=\"http://twitter.com\" rel=\"nofollow\">Twitter Web Client</a>",
        "in_reply_to_status_id": 660670874647818240,
        "in_reply_to_status_id_str": "660670874647818240",
        "in_reply_to_user_id": 3556936994,
        "in_reply_to_user_id_str": "3556936994",
        "in_reply_to_screen_name": "ishmail2015",
        "user": {
            "id": 2704715982,
            "id_str": "2704715982",
            "name": "Ben, Just Ben",
            "screen_name": "ItsBenJustBen",
            "location": "Chicagoland",
            "description": "18+ / Over-educated porn fan. Master of the single-use hashtag. Avatar depicts Dionysus & Ariadne, for the classicists in our midst.",
            "url": "https://t.co/BynglBiEmF",
            "entities": {
                "url": {
                    "urls": [
                        {
                            "url": "https://t.co/BynglBiEmF",
                            "expanded_url": "https://switter.at/@ItsBenJustBen",
                            "display_url": "switter.at/@ItsBenJustBen",
                            "indices": [
                                0,
                                23
                            ]
                        }
                    ]
                },
                "description": {
                    "urls": []
                }
            },
            "protected": false,
            "followers_count": 1263,
            "friends_count": 109,
            "listed_count": 4,
            "created_at": "Sun Aug 03 19:23:21 +0000 2014",
            "favourites_count": 620,
            "utc_offset": -18000,
            "time_zone": "Central Time (US & Canada)",
            "geo_enabled": false,
            "verified": false,
            "statuses_count": 5869,
            "lang": "en",
            "contributors_enabled": false,
            "is_translator": false,
            "is_translation_enabled": false,
            "profile_background_color": "F0F0F0",
            "profile_background_image_url": "http://abs.twimg.com/images/themes/theme1/bg.png",
            "profile_background_image_url_https": "https://abs.twimg.com/images/themes/theme1/bg.png",
            "profile_background_tile": false,
            "profile_image_url": "http://pbs.twimg.com/profile_images/640272323048812544/apTBDtE5_normal.jpg",
            "profile_image_url_https": "https://pbs.twimg.com/profile_images/640272323048812544/apTBDtE5_normal.jpg",
            "profile_banner_url": "https://pbs.twimg.com/profile_banners/2704715982/1473096453",
            "profile_link_color": "0A0A2A",
            "profile_sidebar_border_color": "000000",
            "profile_sidebar_fill_color": "000000",
            "profile_text_color": "000000",
            "profile_use_background_image": false,
            "has_extended_profile": true,
            "default_profile": false,
            "default_profile_image": false,
            "following": false,
            "follow_request_sent": false,
            "notifications": false,
            "translator_type": "none"
        },
        "geo": null,
        "coordinates": null,
        "place": null,
        "contributors": null,
        "is_quote_status": false,
        "retweet_count": 0,
        "favorite_count": 0,
        "favorited": false,
        "retweeted": false,
        "lang": "en"
    },
    {
        "created_at": "Tue Oct 06 05:09:09 +0000 2015",
        "id": 651262896719196160,
        "id_str": "651262896719196160",
        "text": "@ishmail2015 @DangeRussWilson Russell doesn't respond to idiots!  Get a life!",
        "truncated": false,
        "entities": {
            "hashtags": [],
            "symbols": [],
            "user_mentions": [
                {
                    "screen_name": "ishmail2015",
                    "name": "Cosmico Lupo Solo",
                    "id": 3556936994,
                    "id_str": "3556936994",
                    "indices": [
                        0,
                        12
                    ]
                },
                {
                    "screen_name": "DangeRussWilson",
                    "name": "Russell Wilson",
                    "id": 512613427,
                    "id_str": "512613427",
                    "indices": [
                        13,
                        29
                    ]
                }
            ],
            "urls": []
        },
        "source": "<a href=\"http://twitter.com/download/iphone\" rel=\"nofollow\">Twitter for iPhone</a>",
        "in_reply_to_status_id": 651262430039814144,
        "in_reply_to_status_id_str": "651262430039814144",
        "in_reply_to_user_id": 3556936994,
        "in_reply_to_user_id_str": "3556936994",
        "in_reply_to_screen_name": "ishmail2015",
        "user": {
            "id": 1625592774,
            "id_str": "1625592774",
            "name": "CottonConnie",
            "screen_name": "CottonConniee",
            "location": "",
            "description": "",
            "url": null,
            "entities": {
                "description": {
                    "urls": []
                }
            },
            "protected": false,
            "followers_count": 256,
            "friends_count": 171,
            "listed_count": 31,
            "created_at": "Sat Jul 27 14:41:11 +0000 2013",
            "favourites_count": 29894,
            "utc_offset": null,
            "time_zone": null,
            "geo_enabled": true,
            "verified": false,
            "statuses_count": 36622,
            "lang": "en",
            "contributors_enabled": false,
            "is_translator": false,
            "is_translation_enabled": false,
            "profile_background_color": "C0DEED",
            "profile_background_image_url": "http://abs.twimg.com/images/themes/theme1/bg.png",
            "profile_background_image_url_https": "https://abs.twimg.com/images/themes/theme1/bg.png",
            "profile_background_tile": false,
            "profile_image_url": "http://pbs.twimg.com/profile_images/378800000196978523/e8f9de6a1d9e51db55bc185523b091d3_normal.jpeg",
            "profile_image_url_https": "https://pbs.twimg.com/profile_images/378800000196978523/e8f9de6a1d9e51db55bc185523b091d3_normal.jpeg",
            "profile_link_color": "1DA1F2",
            "profile_sidebar_border_color": "C0DEED",
            "profile_sidebar_fill_color": "DDEEF6",
            "profile_text_color": "333333",
            "profile_use_background_image": true,
            "has_extended_profile": false,
            "default_profile": true,
            "default_profile_image": false,
            "following": false,
            "follow_request_sent": false,
            "notifications": false,
            "translator_type": "none"
        },
        "geo": null,
        "coordinates": null,
        "place": null,
        "contributors": null,
        "is_quote_status": false,
        "retweet_count": 0,
        "favorite_count": 0,
        "favorited": false,
        "retweeted": false,
        "lang": "en"
    }
]
 */
