# AidaScript

AidaScript is a simple procedural (for now) programming language that can be used for simple programming and input macros. The program for writing and running AidaScript programs is called the AidaScript Standard IDE. It's very much a work in progress.

![AidaScript screenshot](https://raw.githubusercontent.com/0x416c616e/AidaScript/master/aidascript.png)

# Dependencies/Requirements

This is made for 64-bit Windows 10/11. I might add support for other operating systems in the future though.

You must have Java 15 (or higher) installed in order to run the program.

# Instructions

1. Clone the repo
2. Double click run.vbs
3. In the AutoInput editor window, you can write a script and then run it with the run button. You can also stop the script with the "halt macro" button.
4. If you want to find out the (x, y) location of a certain spot, move the AutoInput screen over where you want to click, then click "get coords" to get the current mouse coordinates. 

# Syntax

Here's an example of how to write AidaScript:

    # this is a comment
    click 400 500
    wait 300
    click 600 300
    wait 1000
    click 100 700
    wait 2000
    rightclick 300 400
    wait 3000
    move 361 532


click is for left click, rightclick is for right clicking. wait means how long to wait in milliseconds. The arguments provided to click or rightclick are the x, y coordinates of where you want to click or move the mouse. To find the x, y location of something, click the "get coords" button. 

To run a script, click run macro. If you want to quit a script before it's done running, click halt macro. It's good to put at least one long wait line in your script so that you have the ability to move the move and halt the script if you want to. 

Note: the above code example only shows the old features. There are many more advanced features now.

# New Features

This documentation will have to be updated at some point.

# Unfinished features

If you click on a button and it does nothing, that's because it isn't finished yet. 