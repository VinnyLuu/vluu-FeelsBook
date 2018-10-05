# vluu-FeelsBook
Assignment 1 for CMPUT 301. An app that allows users to create a history of emotions.

Acknowledgements: I had casual discussions with Justin Mah and Anthony Lam (CMPUT 301 Lec B1) about this assignment.

Resources Used: 
To save my abstract emotion class to disk using Json I followed the instrctions from this link alongside the code shown during the I/O and Persistence with GSON lab: 
https://www.javacodegeeks.com/2012/04/json-with-gson-and-abstract-classes.html
Author: Oleg Varaksin 
Created: April 24, 2012
Date Accessed: Oct 2, 2018
https://github.com/joshua2ua/lonelyTwitter
Author: Joshua Carles Campbell https://github.com/joshua2ua
Using GSON was shown by our TA in the Thursday 5-8 ETLC 301 Lab: Shaiful Chowdhury. Also gotten help for the exception of the comment string being too long from this lab.

To pass my emotions to different activities through intents, I made my Emotion class implement Serializable:
https://developer.android.com/reference/java/io/Serializable
Date Accessed: September 31, 2018

To sort my emotion history by date, I created my own comparator class following this link:
https://stackoverflow.com/a/2784576
Author: Michael Myers https://stackoverflow.com/users/13531/michael-myers
Lasted Edited: June 2, 2016
Date Accessed: Oct 2, 2018

To implement button clickers listeners without creating several anonymous onclick functions for each button, I followed this link: https://stackoverflow.com/a/25905313
Author: Pragnesh Ghoda シ https://stackoverflow.com/users/3535925/pragnesh-ghoda-%e3%82%b7
Lasted Edited: March 31, 2017 
Date Accessed: September 29, 2018

Help on using onActivityResult and adding intent methods in the activities was from: 
Android Programming, The Big Nerd Ranch Guide (2nd Edition)
Author: Bill Phillips, Chris Steward, Brain Hardy & Kristin Marsicano

I had got help on conversion of strings to date with this link:
https://stackoverflow.com/a/4216767
Author: BalusC https://stackoverflow.com/users/157882/balusc
Lasted Edited: April 10, 2010

Formatting of adding comment via popup window was made following this video:
https://www.youtube.com/watch?v=fn5OlqQuOCk
Channel Name: Filip Vujovic https://www.youtube.com/channel/UC51giEqIrRD1rFmCLoraHLw
Uploaded: May 7, 2015
Accessed: September 30, 2018

General ideas and help from CMPUT301: Saga of Student Picker:
https://www.youtube.com/playlist?list=PL240uJOh_Vb4PtMZ0f7N8ACYkCLv0673O
Author: Abram Hindle https://www.youtube.com/channel/UCTLkh9KmeYXQBR59wJxq1eg
Last updaated: September 14, 2014
Accessed: September 22, 2018
