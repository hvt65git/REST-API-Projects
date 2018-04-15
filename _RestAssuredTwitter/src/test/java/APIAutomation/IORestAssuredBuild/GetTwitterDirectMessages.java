package APIAutomation.IORestAssuredBuild;
/*
 * 
https://developer.twitter.com/en/docs/direct-messages/sending-and-receiving/api-reference/list-events

Sending and receiving events

GET direct_messages/events/list
Returns all Direct Message events (both sent and received) within the last 30 days. Sorted in reverse-chronological order.

Resource URL
https://api.twitter.com/1.1/direct_messages/events/list.json

Resource Information
Response formats	JSON
Requires authentication?	Yes (user context only)
Rate limited?	Yes
Requests / 15-min window (user auth)	15/user and 750/app
Parameters
count (optional)	Max number of events to be returned. 20 default. 50 max.
cursor (optional)	For paging through result sets greater than 1 page, use the “next_cursor” property from the previous request.
Example request using Twurl
twurl -X GET /1.1/direct_messages/events/list.json
Example Response
Events are returned in the events array. A next_cursor property will be returned if there are more events to be retrieved.

Note

To determine if there are more event to retrieve, always look for the presence of a next_cursor. In rare cases the events array may be empty.

{
  "next_cursor": "AB345dkfC",
  "events": [
    { "id": "110", "created_timestamp": "5300", ... },
    { "id": "109", "created_timestamp": "5200", ... },
    { "id": "108", "created_timestamp": "5200", ... },
    { "id": "107", "created_timestamp": "5200", ... },
    { "id": "106", "created_timestamp": "5100", ... },
    { "id": "105", "created_timestamp": "5100", ... },
    ...
  ]
}
Note

User objects are not hydrated. ht comment - aka not all fields are loaded for the user objects that have been instantiated
aka User objects are lazy loaded.
 * 
 */


public class GetTwitterDirectMessages {

}

//bad news - postman was not allowed to get them
//{
//"errors": [
//           {
//               "code": 93,
//               "message": "This application is not allowed to access or delete your direct messages."
//           }
//       ]
//   }
//probably have to use twurl
