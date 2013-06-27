KinoSense
=========

Motion Statement - Enabling Smart Phones to Sense by themselves.

Description
------
KinoSense is primarily a framework which enables Smart Phones to use high level trigger to fire high level actions based on rules which could be hosted on the cloud.

Read the terminologies mentioned below to understand the key building blocks.

Terminologies
===========

We need to understand the following terminologies

* Triggers
* Actions
* Rules
* Profiles

Triggers
----
Triggers are Events. Triggers can be both low level or high level. An Accelerometer event can be a low level trigger, mentioning user is turning his device around various axis. A User flipping the phone facedown can be a high level triggers. A High Level Trigger indicates an intension, in this case it can mean the user wants to put his/her phone on Silent. Triggers are to be used alone or in conjuction with other Triggers and Actions needs to be fired when triggers fulfill a set of rules

Actions
----
Actions can be low level or high level. Phone going on Silent mode can be low level action. Phone going in a "Do Not Distrub and Auto Reply" mode is a high level action. This high level action is composed of phone going in a silent mode as well as disconnecting an incoming call and sending the callee an predefined SMS mentioning - "In a Meeting, Will call you back".

Rules
----
Rules is nothing but fulfillment of set of Triggers (and may be some context). If rules are fulfilled appropriate actions should be fired. The Simplest Rule can be "if (Trigger) then (Action)". A more complex one could be "if (Trigger 1 and (Trigger 2  or Trigger 3) ) then (Action 1 and Action 2)".
Rules can also use certain context along with Triggers, This will progressively elaborated as Project moves on.

Profiles
----
Profiles are templates which are in simply stated set of rules. A User can create various profiles or clone some well known profiles. This way user does not have to manage individual rules

Goals
=====

KinoSense is meant to be 
* Primarily a Framework
* Secondary a Product

The idea is to develop the framework and demostrate how it works by building some showcase products. Then it is up to the community to build on top of the framework. Also this is an Open Source Projects, feature submissions from users will be merged into the project after an evaluation.

Phase 1
=====
Phase 1 is all client side and no server side. The set of rules will be hard coded and user cannot change it. This would include following 
* Development of the Framework
* Development of Key Triggers and Actions
* Defining Hard Coded Rules
* Testing the Framework and Showcase Product on various Android devices

Phase 2
======
Phase 2 will have some Server Side component. In this the rules will be pushed from the server to the client.
Will be Progressively elaborated as Project is near end of Phase 2

Phase 3
=====
More context inferred from Server Side (or Cloud). Profiles coming into play. Ability to code the following rule

If (Keynote starts){
   Put all phones on Silent Mode
}

The thought process behind this is still in development. But the idea is to build a rule set which goes beyond single device and cloud context.

if (Bob does X){
   Jane's Phone Y
}



Project Members
----
* Rohit Ghatol
* Saurabh Gangarde
* Kumar Gaurav
* Sagar J Pawar
* More coming
