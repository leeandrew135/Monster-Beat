# Monster-Beat

### CS2212 Educational Game

---

### CS 2212 Team 34

---

Our software is a game that uses multiple choice questions to test to knowledge of the user.
The questions bank is able to be edited to ask questions on any subject.

---

### Running the Program

Run the executable file MonsterBeat.jar.
The executable file MonsterBeat.jar must be in the root folder (/MonsterBeat/).
The .jar file will not execute the program unless it is in this root folder.
MonsterBeat/src/Main.java is the file that runs the program.

---

### Libraries used for this Program:

java.util._;
java.io._;
java.awt*;
javax.swing.*;
javax.sound.\*;
javax.imageio.ImageIO;

---

### Compiling the Program

The executable file MonsterBeat.jar must be in the root folder (/MonsterBeat/).
The .jar file will not execute the program unless it is in this root folder.
MonsterBeat/src/Main.java is the file that runs the program.

### In order to compile the program:

1. Change the working directory to /MonsterBeat/src/ and
   compile /MonsterBeat/src/frontend/ and /MonsterBeat/src/backend/
   From the working directory /MonsterBeat/src/ , this can be done with the commands:
   javac frontend/_.java
   javac backend/_.java

2. Change the working directory to the root directory (/MonsterBeat/) and
   compile the file /MonsterBeat/src/Main.java/
   This can be done with the following command:
   javac -cp src src/Main.java

3. Create the executable .jar (Java ARchive) file to run the program.
   From the working directory set as the root folder (/MonsterBeat/),
   this can be done with the command:
   jar cfm MonsterBeat.jar Manifest.txt -C src/ .

** NOTE **
The Manifest.txt is necessary to compile the .jar file.
The txt file contains one line as follows:

### Main-Class: Main

The line must contain the newline character at the end of the line.

Assuming that all has been done correctly,
the file /MonsterBeat/MonsterBeat.jar/ should be in the root folder,
and can be executed to run the program.

---

### Accounts

User accounts can be registered with an email. Attempting to login with an email that has
not been registered will automatically redirect the user to the Create Account Page. The
Create Account Page can also be accessed by the Create Account button on the Login
screen. There is special login that can be used by entering "admin" into the Login
screen.

---

References to Resources Used

Sound effects: https://mixkit.co/
Character images: https://craftpix.net/product/chibi-monsters-2d-asset-pack/

---
