<a name="readme-top"></a>


![cricfizz-logo](https://github.com/BelugulaShushanth/CricFizzApp/assets/66299354/5c10b7aa-6f6d-493c-bea8-1a2dff258967)
<!-- PROJECT LOGO -->
<br />



<!-- ABOUT THE PROJECT -->
## About Cricfizz
<ol>
  <li>This is a live cricket scores application built by leveraging the <b>CricBuzz</b> API.</li>
  <li>This is developed using the <b>Microservices Architecture</b> and implemented standard microservices patterns like <b>Config Server, Service Discovery, Load Balancing, Dynamic Scaling, API Gateway, Circuit 
   Breaker, Resiliency</b></li>
  <li>In this app a user can check the <b>live/recent/upcoming</b> cricket match scores</li>
  <li>This application also provides a feature called <b>alerts</b> where a user can subscribe to get alerts for a particular match</li>
  <li>A user can login using <b>Google OAuth</b> or by standard login form to subscribe to alerts</li>
  <li>A user can schedule a <b>alert interval</b> by choosing the time period also</li>
  <li>A user can <b>activate/deactivate</b> the alerts whenever he wants</li>
</ol>



## Technologies Used


<ul>
<li>Java, J2EE, Core Java, Java 8 Features</li>
<li>Spring Core, Spring MVC, Spring Data JPA using Hibernate</li>
<li>Spring Security to authenticate a user using Google OAuth and standard Form Login</li>
<li>Spring Boot</li>
<li>Spring Cloud to implement standard microservices patterns</li>
<li>Appache Cassandra DB as a highly available and distributed Database</li>
<li>H2 Database to store in memory data</li>
<li>Thymleaf to render the UI</li>
<li>Apache Kafka as a alerts streaming service</li>
<li>Docker to containerze the micrsoservies</li>
</ul>


## Cricfizz Architecture and System Design
![image](https://github.com/BelugulaShushanth/CricFizzApp/assets/66299354/778aab9b-6257-42f6-a259-bbf87bc78bd0)



## Project Description and User Interface

1. When the user opens the page the first thing a user will see by default is the **Live** Cricket Matches which are divided into 4 parts<br>
       a) **International**: This is where international matches are shown<br>
       b) **League**: This is where all the league matches like IPL, BPL, and BBL are shown<br>
       c) **Domestic**: This is where all the domestic matches like the Ranji Trophy, and County Championship are shown<br>
       d) **Women**: This is where all the matches related to women are shown<br>
       
   By default, **International** matches are shown but a user can select another type of match also<br>
   By default, only **6 matches** are shown a user can click on the **More Matches** button to see more matches
   
![image](https://github.com/BelugulaShushanth/CricFizzApp/assets/66299354/3aa1b371-aafe-4b10-a715-157c02368705)



2. The Navbar contains **Live, Recent, and Upcoming** navigation buttons by default **Live** matches are shown. If a user clicks on the **Recent** button this is how it will look
   
![image](https://github.com/BelugulaShushanth/CricFizzApp/assets/66299354/5c4e0b44-2b5a-42b9-94cc-b73d455a2c37)



3. If a user clicks on the **Upcoming** Nav button this is how it will look

![image](https://github.com/BelugulaShushanth/CricFizzApp/assets/66299354/75d192dc-014f-41ba-ad68-26102c13ebe4)



4. A user can click on a particular **match card** to see more match details like **commentary** and **Scorecard**
   When a user clicks on the match card by **default the commentary** is shown. This is how it looks

![image](https://github.com/BelugulaShushanth/CricFizzApp/assets/66299354/9887e8a7-7302-4640-bf41-c302aa65d346)



5. When a user clicks on **Scorecard** this is how it will look

![image](https://github.com/BelugulaShushanth/CricFizzApp/assets/66299354/acf16660-d386-4fa8-8b35-ae3bf949fffa)
![image](https://github.com/BelugulaShushanth/CricFizzApp/assets/66299354/ae058d2b-f3ee-446f-b224-33560500c936)



6. There is also an important feature called **Alerts** in Cricfizz<br>
     -> A user can **subscribe** to **Score Alerts** to a particular match<br>
     -> A user can also choose the **time interval** for a Score Alerts <br>
     -> A user needs to log in in order to subscribe to alerts<br>
     -> Checkout the snapshot below
   
   ![Screenshot 2023-10-30 161815](https://github.com/BelugulaShushanth/CricFizzApp/assets/66299354/3cf27254-96af-4aa3-a274-7e8921cf276b)




7. A user can log in using **Google OAuth** or by using **Standard** form login<br>
    a) Login using Google OAuth Login<br>
       -> Click on login on the top right side of the page<br>
       -> Click on the Login with Google button.
   
      ![image](https://github.com/BelugulaShushanth/CricFizzApp/assets/66299354/01254652-6bfe-4b92-9ecf-0c73418aaedf)

      ![image](https://github.com/BelugulaShushanth/CricFizzApp/assets/66299354/4bf9673e-12d9-4767-b11b-557a48af33b5)

   b) Standard Login<br>
       -> To log in using standard login a user should have an account first<br>
       -> To **create an account** a user should sign up first<br>
       -> Go to login -> click on **signup**<br>
       ->Enter the mail ID and Click on **send OTP** and an OTP will be sent to the given mail ID.
   
   ![image](https://github.com/BelugulaShushanth/CricFizzApp/assets/66299354/d7f32a5b-d805-440d-8bed-812ffd2ed8fe)

       -> Enter the **OTP** Received to the mail ID and click on **verify your account**
   
      ![image](https://github.com/BelugulaShushanth/CricFizzApp/assets/66299354/f7a02034-9baa-4f52-824a-ed171e01418d)

       -> After a user Mail ID is verified you will be redirected to create an Account page which looks as below.<br>
           Fill out the details and click on Create Account.
   
      ![image](https://github.com/BelugulaShushanth/CricFizzApp/assets/66299354/e198f82f-f655-404e-91db-43440c271a48)
   

9. After a user logs in using OAuth or Standard Login a user will see the Alerts tab as below<br>
   In the alerts page, a user can select the below fields<br>
   a) **Match Type**: A user can select live or upcoming from dropdown<br>
   b) **Series Name**: According to the Match Type selected the Series Name will be populated dynamically. A user can select a series name from dropdown<br>
   c) **Match Name**: According to the Series Name selected the matches belonging to that series will be populated. A user can select any match from the dropdown<br>
   d) **Alert Type**: A user can choose an alert type like score, wicket, match-ended<br>
   e) **Time Period**: A user can select a time period from the drop-down which means the user will get alerts for every time period selected
      check the snapshot below
   
    ![image](https://github.com/BelugulaShushanth/CricFizzApp/assets/66299354/f97a6522-a28c-42db-8dff-d197528b1e96)

11. A user can click on the subscribe alert after selecting all the inputs.<br>
    A user will get a popup in UI that the Alert is scheduled as below
    
   ![image](https://github.com/BelugulaShushanth/CricFizzApp/assets/66299354/2d461648-91dc-41e1-8b4a-87b6f96106e6)
