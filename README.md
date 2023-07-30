# Projet Poll

# Spécification pour la v1

## POLL Api

`POST /v1/polls/create`

Register a poll with json.

Example JSON :

```json
{
    "pollName":"name",
    "authorName":"anonymous",
    "pollOptions": [
        {
            "label":"option1"
        },
        {
            "label":"option2"
        },
        {
            "label":"option3"
        }
    ]
}
```

It return the created poll as JSON.

Example JSON :

```json
{
    "url": "qnc0Ho",
    "pollName": "name",
    "authorName": "anonymous",
    "pollOption": [
        {
            "optionId": 0,
            "label": "option1",
            "voteCount": 0
        },
        {
            "optionId": 1,
            "label": "option2",
            "voteCount": 0
        },
        {
            "optionId": 2,
            "label": "option3",
            "voteCount": 0
        }
    ]
}
```

`GET /v1/polls/{url}`

Find the poll with the specified url

```json
{
    "url": "qnc0Ho",
    "pollName": "name",
    "authorName": "anonymous",
    "pollOption": [
        {
            "optionId": 0,
            "label": "option1",
            "voteCount": 0
        },
        {
            "optionId": 1,
            "label": "option2",
            "voteCount": 0
        },
        {
            "optionId": 2,
            "label": "option3",
            "voteCount": 0
        }
    ]
}
```

`POST /v1/polls/vote/{url}/option/{optionID}`

Add 1 vote to the option `optionID` of the poll `url`

## Front

Composants :

PollForm, VoteForm, ShareLink, ResultGraph, ~~Navigation, Footer~~

Libraries :

VueJS, Vue Router, Vuetify, ChartJS, FetchAPI, (Pinia)

More specs :

Simple UI form Vuetify

no XSS and no SQL injection

# Features à venir

## Back

Unit testing

Autentification with a02 (google and id/pass)

Possible to restrict poll to 1 person (ip or session)

Poll can be closed after a given time

W*ebsocket to get poll update*

## Front

Unit testing && end to end testing

Autentification

Possible to find your polls in account/polls

Storybook

Nice discord implementation with ssr?

Better UI with possibility of deleting an option.

Form verification

Better organisation of components (and views)

Navbar with a logo and dark mode/light mode switch (with cookie)
