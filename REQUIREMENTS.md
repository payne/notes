# Shall & Should requirements

Shall requirements must be done.  e.g. you shall breath.
Should requirements are nice to have.  e.g. you should exercise.


1. Shall allow an unauthenticated user to read the home page and about page.
1. Shall require github OAuth2 authentication to view all other pages.
1. Shall only allow github usernames that are on an authorized list to perform create, update, and delete functions.
1. X: Shall hold the list of authorized usernames in a simple text file src/main/resources/authorized-usernames.txt.   This file has one entry per line.  Entries are username, one or more blanks, then a comma separated list of actions categories permitted (create,update,delete).
1. Shall allow a new URL to be entered into the database.  The user shall be able to pick tags that are in the new URL from a list of tags that are already in the database.
1. Should allow new tags to be entered into the database.
1. Shall allow an tag to be deleted from the database.
1. Shall allow a URL’s title, description, and list of tags to be updated.
1. Shall allow a user to view a list of the URLs in the database.
1. Shall allow a user to view a list of tags in the database.
1. Shall allow a user to click on a URL to view that URL’s title, description, list of tags in the URL, and other information. Call this a URL view.
1. Shall allow a user to click on an tag to view that tag’s name, and list of URLs in which they have acted.  Call this an tag view.
1. Shall create a bookmarkable URL for an tag view.
1. Shall create a bookmarkable URL for a URL view.
1. Should allow a user to click on a genre and see a list of URLs in that genre.
1. Should allow a user to view a list of genres.
1. Should allow many genres to be assigned to one URL.
1. Should allow a user to view all tags that star in URLs of a particular genre.
1. Should allow a user to choose one or more genres and see a list of URLs in those genres.
1. Should allow a user to choose one or more genres and see a list of tags that have worked in those genres.
1. Should allow for bookmarkable URL search that looks in a URL’s title or description.  Resulting in the display of a list of URLs.
1. Should allow for bookmarkable tag search that looks in the names of tags.  Resulting in the display of a list of tags.
1. Should welcome back a user and tell them how long it's been since their last visit.  This should be done using a cookie.
1. Should allow users to put URLs into a borrowing basket.  This should be done using HTTP sessions.




