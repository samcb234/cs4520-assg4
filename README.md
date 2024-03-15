github: https://github.com/samcb234/cs4520-assg4

This project can be run by opening it in Android Developer and running it locally.
The login fragment doesn't use MVVM architecture, because it simply verifies two strings.
The ProductListFragment does use MVVM. The main activity initializes the db, and then the
View initializes its own viewModel and passes it the db. The view model makes and handles
the API calls and passes any new or updated information into the database, which it monitors
for updates, and presents this live data for the view to observe.