This is API for my todos application.

If you want developing it locally first clone this repository.

Then run docker container by command:

    docker-compose up

Or you can start app in development profile simply by running it in your IDE.

***Both methods will start app on localhost with port 8080***

First you need to authorize yourself
Add new user on this endpoint:

    /api/auth/signup
With following JSON:

    {
        "username": ...,
        "password": ...
    }
***You will get access token, use it in headers to all other endpoints call***

Endpoints:

/api/auth/login - request with the same JSON as in signup

/api/todos/{username} - username from authentication endpoints... returns todos list for user

/api/todos/{username}/{id} - returns exact todo

/api/todos - POST method - adding new todo with JSON format:

    {
        "username": YOU MUST USE AUTHENTICATED USERNAME
        "description": ADD SOME TEXT TO YOUR TODO
    }

/api/todos/{username}/{id} - PUT method - changing todo to finished or unfinished

/api/todos/{username}/{id} - DELETE method - deletes todo



