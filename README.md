# SNESDownload
A rom hack Launcher with SNES games in mind, built for the end user with a customizable database.

# BACKGROUND

SNESDownload was initially made for Super Kekcroc World 3, as a launcher to help the end-user download hacks legally and everything else required for running a hack, from emulators to patching software, everything is completely customizable and more work is on the way to make it even more customizable to the end user, with a DLC marketplace planned and support for multiple games all in one executable.

# FEATURES

1. Fully featured setup, allowing for downloading of emulators for use with the hack.
2. Auto updating feature (WIP)
3. Support for MSU-1, DLC, and Voice Acting with more support coming soon.
4. Save Data Management allowing for quick deletion and export of save files.
5. User-Friendly interface that doesn't any work to get your hack up and running
6. Java based for near complete platform support
7. Support for consoles and android by just patching the rom but not playing it.
8. Complete customizablilty by the end user, allowing for nearly every aspect to be tweaked for your benefit as a hacker.
9. More features coming soon!

# Setting Up (Developer)

Setting up is very simple, but does require a way to compile the project. You can find a great way to compile java [here](http://www.skylit.com/javamethods/faqs/createjar.html) Now, at the very top of Main.java, you'll find this line:
```
public static String link = "https://www.dropbox.com/s/rncst247vr1nutl/database.ini?dl=1";
```
Replace the link seen here with your own database file (this WILL CHANGE at a later date, but for now it's safe to use this method of distribution. See the TODO list.)

Aaaaand you're done! (but not really)

Customizing the database is easy as pie, most of the stuff is explained what it is, and all of it completely changable by you! Here's just a small snippit of the format we're working with here
```
[HACKER]
Knucklesfan
[HACKNAME]
Super Kekcroc World 2: A Croc Throughout Time
[IMAGE]
https://www.dropbox.com/s/mgoy0glie6gwh9n/image%20%281%29.png?dl=1
[DESCRIPTION]
THE SECOND PART OF THE EPIC TRILOGY! Kekcroc is back and he means business. Enjoy 13 hand-crafted levels featuring loads of rocking around the croc!
```
(End tags are not needed unless it's an array, which we're getting into)
Not too bad, eh? Don't go looking at the code, it's not exactly what you'd call pretty... Anyways, This is the basic of how it works, but there's one other element  that really can't be identified but whatever, the array!
```
[EMULATORNAMES]
Snes9x 1.60
BSNES
Emulator1
emulator2
[END]
```
This is an array, Arrays are helpful in storing multiples of things, emulators, names, etc. Basically, put an [END] tag at the very end of something and it's ended.

and that's pretty much it! Everything else is explained in the database file, so go nuts with the customizing.

# Setting Up (Player)
Litterally run the program, that's it. The code does everything for you.  

# TODO
1. Implement a modular system, current system supports standalones but doesn't support multiple hacks at once.
2. Actually implement the save manager, currently just a placeholder (should be out by Kekcroc DASH!'s release this christmas)
3. Improve the codebase, as it's quite uh needy to say the least and a little slow.
4. Requests?
