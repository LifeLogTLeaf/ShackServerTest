ShackServerTest
===============

Stores Simple Test .html File for test

If You have these Three Document in the Database, You can use this .html file for Test.

in tleaf_hashid Database
http://localhost:5984/tleaf_hashid/os1742%40gmail.com
{
   "_id": "os1742@gmail.com",
   "hash_id": "344bc889c8bb44dd6e4bb845d40007b9"
}

in tleaf_users Database
http://localhost:5984/tleaf_users/344bc889c8bb44dd6e4bb845d40007b9
{
   "_id": "344bc889c8bb44dd6e4bb845d40007b9",
   "nickname": "DevSusu",
   "email": "os1742@gmail.com",
   "password": "123456",
   "gender": "male",
   "age": "19"
}

in tleaf_apikey Database
http://localhost:5984/tleaf_apikey/6b22f647ef8f2f3278a1322d8b000210
{
   "_id": "6b22f647ef8f2f3278a1322d8b000210",
   "userId": "344bc889c8bb44dd6e4bb845d40007b9",
   "appId": "6b22f647ef8f2f3278a1322d8b000f81",
   "validFrom": "2014-10-24T16:41:32+09:00",
   "validTo": "2014-11-30T16:46:32+09:00",
   "valid": true
}
