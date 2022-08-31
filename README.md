# p2p-p2server-Lan-Chat-Application
# Run project
First must run the server 
Then run the login window

![Screenshot 2022-08-24 163514](https://user-images.githubusercontent.com/19889053/187736151-f0964227-071b-4d27-b25e-0adf0eac4ea7.png)
![Screenshot 2022-08-24 164001](https://user-images.githubusercontent.com/19889053/187736160-58037407-2e93-419c-b90f-eabcac7a1d12.png)
![Screenshot 2022-08-24 164922](https://user-images.githubusercontent.com/19889053/187736162-7b9dc789-815d-44a8-ab52-a6626a9e6b89.png)
   
   
   
Enter the name or id of user and the port number because it’s the same pc
 same Ip The default is 9000 The go to main window to start chat.

![ss](https://user-images.githubusercontent.com/19889053/187736705-59bdb3cf-c2c8-4a8f-8ac8-b18316b326a3.png)

 

# Initialize method in main window
Initialize Chat clint object that runs in thread
Then run client server in timer of 5 seconds to send my ID to the server every 5 seconds. 
Client server 
We define the port and the IP of the server in the datagram packet, for send the user ID to server
Server
Server receive msg that contains the ID and get IP and the port from the packet, then save the information of this ID with time Login Time  into a hash Table if hash Table does not have this ID before , then send the info of all IDs that in hashTable to all clint in hashTable, else update the login time.
Then get the now time and decrease by 6 seconds, then for each key in hash Table check if the login time is before the time above calculated, if was true will remove that key from hash Table then resend the hash Table to all users to update 
e.g.. if ahmad is terminate

after 6 second ahmad will disappear from compo Box
because he is not sending his name msg “ hay I’m here” in past 6 second




# Chat client 
In this step, will receive msg from server or client,
And From server to update hash Table of client and from client to show msg  










# The format of the messaging (our Protocol).
•	we send to the server from Client server,  send only ID or a name
and the server will know the IP and port from UDP packet  
•	the server send the msg  to client in hash Table in this format
      "u/: name :  ip :  Port”
      When finish sending to client send  “f/:” so that client know all items     was sent to update combo box
•	the chat client thread split the msg based on  “:”
•	if the str[0]=u/ store info in hash list
•	else its =f/ update the user lest
•	else its normal msg from client


