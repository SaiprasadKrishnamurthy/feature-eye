# README #

# What this is about? #
This is an APP that mines/pulls feature files defined in Gherkin language from one or more GIT repositories of your organisation
 and presents this from a database that can then be easily searched/reported/collaborated upon.
 There are quite a few useful functions available to the user to easily create a Feature file which can then be validated based on
 pluggable rules defined by your company and can then published to various channels.
 
 The default channel that is supported is **Slack**
 
 
# Prerequisites #
## To build and deploy the source ##
* Java 8
* Maven 3
* Tomcat 8+
* MongoDB 3.2.1+

# Modules #
| Module        | Description           | Tech Notes  |
| ------------- |:-------------:| -----:|
| api      | general api shared by all the modules (just the model and domain definitions) | Java/Maven module exported as a jar artifact|
| loaders      | mines and loads the feature files from GIT into the MongoDB | Scala/Maven module eported as a jar artifact. Uses Akka Actors for better scalability|
| rest      | contains the business logic of exposing the underlying mongodb as useful business functions via RestAPI | Java/Maven module exported as a jar artifact. Uses Spring Boot Microservices framework|
| ui      | contains the user interfaces no business logic. It talks to the rest api to access a business functionality. | Java/Maven module exported as a war artifact. Uses JSF framework|

# High Level Architecture #
Architecture

![Architecture](architecture.gif?raw=true "High level architecture")

# A few screenshots #
![Screenshot](one.png?raw=true "Screenshot of UI")
![Screenshot](two.png?raw=true "Screenshot of UI")
![Screenshot](three.png?raw=true "Screenshot of UI")
![Screenshot](four.png?raw=true "Screenshot of UI")
![Screenshot](five.png?raw=true "Screenshot of UI")
![Screenshot](six.png?raw=true "Screenshot of UI")
![Screenshot](seven.png?raw=true "Screenshot of UI")
![Screenshot](eight.png?raw=true "Screenshot of UI")







