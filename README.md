# Week_12A_Task
Repository for week 12A task.
# Description:
- In this task you will have to create a simple login application. The GUI is a simple HTML page where you can write in a username and a password (as a password type field) and you can push a button where you can send the written data.
If the username and password are correct, redirect to a profile page where the application greets the user, show something like this: 'Welcome <username> ! Have a nice day !'(of course <username> should be replaced with the username typed in the login page).
If the username and password are incorrect, show a message on the login page like 'Bad username or password ! Permission denied' . If user tries to login while she/he is already logged in, warn the user about it and let her/him log out putting a Logout button on the page. Put this button also onto the profile page.
If user wants to reach the profile page while she/he is not logged in, rediect her/him to the login page and warn her/him it's necessary to be logged in.
- user interface has to be described in HTML
- choose a predefined username and password and let login only with these username and password, otherwise warn the message above Bad username or password ! Permission denied'  and redirect to login page
- pages should have a form (tag) where it is necessary
- method type of forms is POST
- the progress is managed on server side using javax.servlet.http.HttpServlet
- user data has to be stored in session (javax.servlet.http.HttpSession)
