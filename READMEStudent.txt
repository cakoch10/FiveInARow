Name: Caleb Koch
Netid: cak247

Instructions for interacting with the GUI:
1) Run Main.java
2) Select the two opponents (only select one player as X and one player as O)
	- Xs are colored red by default on the board
	- Os are blue
3) Click next
4) Play the game (or wait for the AI to finish)
5) Observe end result
	- If the game ends with a winner, their winning 5-in-a-row is highlighted yellow
	- If the game ends in a draw, the entire board is highlighted yellow

Any known bugs or shortcomings in your submission

1) The coloring of the buttons on the first window doesn't always reflect the player selected
 - For example if you click on the button "Human as X", it will be highlighted, but if you
 click it again, it will be de-highlighted; however, if you click next you will play as X
2) If you click next without selecting players, error occurs
3) Can't see AI move
4) Two smart AIs don't draw (tie) when depth is 3, but draw when depth is 2 or 4
 - Instead O beats X when depth is 3 which seems counterintuitive since X goes first
 - But X wins when depth is 5
 

Any additional design or implementation notes that you think are interesting
The initial project code was well documented and the javadoc was helpful.


Feedback to help us improve the assignment for the future

Post FAQ questions earlier. Also, TA office hours are often unhelpful because there are so many people and the TA shift often ends before he/she can get to everyone.
It would be nice to know what we should expect when two smart AIs play each other (i.e. when they should tie, when one should beat the other)