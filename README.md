# ChatApp
Introduction:

chat application for organisational communication                                                 
The aim of project was to design a hierarchial chat application to be used within organisations. 
A two-level of hierarchy: manager-employee is used.
Real-time chat application that can communicate over various networks using Socket programming.

Requirements:

1) The server should have a static IP address.
2) The router should be port forwarded.
3) The server should have a domain name for public access. We have used no-ip doc client for providing domain name.

How to Run:

1) Run all the three servers.
   pre-requisite: All the requirements mentioned above.
2) Run the executable chat application on the client side.
3) You are ready to go!

Video link for project demonstration:

Youtube video Link: https://youtu.be/wNbAUfXl0lg           

Explanation of all the classes in the project:


	login_win:

The user enters the credentials i.e. username, password and position in hierarchy (manager or employee)
1) If the connection is validated by server (login_server) then it opens a new window based on the position of the client
2) if credentials are invalid then it prints warning for user

	client_frame and client_frame_manage:

The window opened by login_win after successful credentials validation
1) All the details are autofilled
2) User can perform chat operations here
3) special features:
   a) chat alongwith emojis
   b) viewing online users in the current network
   c) Personal chat with any online user or group chat by default
   d) View announcements posted by manager


	chat_pers:

This is the window which will be opened when you want to chat personally with any online user.
1) The name of the two users will be visible on the top
2) If the participants exceed time limit then their window will be closed automatically with a warning.



	login_server:

Server program to let the user login using correct credentials or he can register also
   a)Login:
	1)It gives warning for invalid credentials.
	2)It debars you to login if you are already logged in from another window

   b)Register:
	1)It gives warning for duplicate entries



	server_frame:

Used to serve both employee and manager chat requests



	server_pers:

Used to server personal chat requests between two clients



	Database:

We are using mysql database
	


	tables:

1) tbluser: registered users (name, password, position)
2) activuser: currently active users(online users)
