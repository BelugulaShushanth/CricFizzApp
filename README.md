<a name="readme-top"></a>


![cricfizz-logo](https://github.com/BelugulaShushanth/CricFizzApp/assets/66299354/5c10b7aa-6f6d-493c-bea8-1a2dff258967)
<!-- PROJECT LOGO -->
<br />



<!-- ABOUT THE PROJECT -->
## About Cricfizz
<ol>
  <li>This is a live cricket scores application built by leveraging the <b>CricBuzz</b> API.</li>
  <li>This is developed using the <b>Microservices Architecture</b> and implemented standard microservices patterns like <b>Config Server, Service Discovery, Load Balancing, Dynamic Scalling, API Gateway, Circuit Breaker, Resiliency</b></li>
  <li>In this app a user can check the <b>live/recent/upcoming</b> cricket match scores</li>
  <li>This application also provides a feature called <b>alerts</b> where a user can subscribe to get alerts for a particular match</li>
  <li>A user can login using <b>Google OAuth</b> or by standard login form to subscribe to alerts</li>
  <li>A user can schedule a <b>alert interval</b> by choosing the time period also</li>
  <li>A user can <b>activate/deactivate</b> the alerts whenever he wants</li>
</ol>



## Technology Used


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

1. When the user opens the page the first thing a user will see by default is the Live Cricket Matches which are divided into 4 parts<br>
       a) International: This is where international matches are shown<br>
       b) League: This is where all the league matches like IPL, BPL, and BBL are shown<br>
       c) Domestic: This is where all the domestic matches like the Ranji Trophy, and County Championship are shown<br>
       d) Women: This is where all the matches related to women are shown<br>
   By default, International matches are shown but a user can select another type of match also<br>
   By default, only 6 matches are shown a user can click on the More Matches button to see more matches
   
![image](https://github.com/BelugulaShushanth/CricFizzApp/assets/66299354/3aa1b371-aafe-4b10-a715-157c02368705)


3. The Navbar contains Live, Recent, and Upcoming navigation buttons by default Live matches are shown. If a user clicks on the Recent button this is how it will look
![image](https://github.com/BelugulaShushanth/CricFizzApp/assets/66299354/5c4e0b44-2b5a-42b9-94cc-b73d455a2c37)


4. If a user clicks on the Upcoming Nav button this is how it will look
![image](https://github.com/BelugulaShushanth/CricFizzApp/assets/66299354/75d192dc-014f-41ba-ad68-26102c13ebe4)


5. A user can click on a particular match card to see more match details like commentary and scorecard
   When a user clicks on the match card by default the commentary is shown. This is how it looks
![image](https://github.com/BelugulaShushanth/CricFizzApp/assets/66299354/9887e8a7-7302-4640-bf41-c302aa65d346)


6. When a user clicks on score card this is how it will looks
![image](https://github.com/BelugulaShushanth/CricFizzApp/assets/66299354/acf16660-d386-4fa8-8b35-ae3bf949fffa)
![image](https://github.com/BelugulaShushanth/CricFizzApp/assets/66299354/ae058d2b-f3ee-446f-b224-33560500c936)



