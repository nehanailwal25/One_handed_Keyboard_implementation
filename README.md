1.	Introduction:

An important step in approximately all the applications that we use nowadays is Text-Entry. Text Entry is a way of entering alphabets, Numbers, and special characters anywhere to convey any information. No matter what application we are using, in one way or the other we have to enter some text. Being such an important aspect in today’s mobile applications we need to give special attention to this. Now, as we are living in a fast-paced world everyone wants to complete all the tasks in as much less time as possible, the same applies to Text Entry as well. 

According to all the basic understanding, I got from discussing with people around me was that all the people were interested in typing the text comfortably. And, most people prefer typing with one hand as well (this was also an outcome of a very well-planned experiment performed by Amy K. Karlson, Benjamin B. Bederson on, Jose L. Contreras Vidal, 2006 in the paper Understanding Single-Handed Mobile Device Interaction) refer figure - 1.1. 


In this project, I am focusing on this Text-Entry keypad size problem only. My reason for choosing this challenge is that the text entry is such a crucial step for mostly all the applications in mobile devices and also we cannot just randomly pick a keyboard instead we have to be very specific about the device as the size has to be in a perfect proportion with the device also it has to be comfortable enough for the users. 

The current issue that I see with the available keyboard (QWERTY Keyboard) in mobile devices is that they are not that much handy when it comes to one-handed typing. We usually have to stretch the Thumb to reach the alphabets on the left side of the keyboard (considering a right-handed person). This kind of typing usually decreases the speed of the user while he is walking or even standing.

My main focus while designing a solution for this problem was on decreasing the size of the keyboard and making it enough efficient and easily accessible at the same time. I have also tried decreasing the number of keys as a more effective solution for it. For easy remembrance of keys to users, I have used 3 different color combinations in the text. By this, I am considering that the speed and accuracy both will be increased in comparison to the traditional keyboard.


2. Keyboard Implementation:

A Baseline Keyboard (Figure 2.1.1) and Implemented Keyboard (Figure 2.1.2) were used in the experiment process.
Baseline Keyboard is the normal QWERTY keyboard as we usually use in our mobiles and Implemented Keyboard was the new one made for the project. 
The implemented keyboard is made to be situated on the right-hand side of the device rather than being in the middle as the traditional keyboard. All the keys are easily accessible and clickable.
Implementation points in the new keyboard:
•	The traditional 26 keys for alphabets are replaced with just 13 keys in the new keyboard.
•	Alphabets are classified based on the frequency of occurrence in the English language, the most used ones are on the outermost side and the least occurring on the innermost side.
•	All the keys are color-coded according to their frequency, as:
-	RED: The most occurring alphabets
-	YELLOW: Moderately occurring alphabets
-	ORANGE: Least occurring alphabets
This color-coding concept was also used to make it easier for people to know where they have to tap next for any alphabet.
•	Typing any alphabet in the text area provided was being done by:
-	Clicking on that specific alphabet directly
-	Single tapping in the free area present in between the alphabets in the keys will enter the first alphabet into the text box.
-	Double tapping in the free area present in between the alphabets in the keys will enter the second alphabet into the text box.
•	Use of Backspace, Caps Lock, Enter, and Space key is the same as the traditional QWERTY Keypad (i.e. user will have to tap on the key as and when needed). 

