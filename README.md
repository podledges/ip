# PodleGPT

> Your personal command-line assistant.

PodleGPT is a command-line application built in Java to help manage tasks, track progress, and keep your daily activities organized.

## Prerequisites
1. Ensure you have Java 17 or above installed in your Computer.
   **Mac users:** Ensure you have the precise JDK version prescribed [here]([https://se-education.org/guides/tutorials/javaInstallationMac.html]).
   
## Quick start
### Installation
1. Clone the project into the folder you want to use as the root folder using the following command:
   ```bash
   git clone [https://github.com/podledges/ip.git](https://github.com/podledges/ip.git)
2. `cd` into the folder you put the jar file in, and use the `java -jar PodleGPT.jar` command to run the application

### Alternative for Installation
1. Download the latest `.jar` file from the Releases page (to be implemented).
2. Copy the file to the folder you want to use as the home folder.

### Running PodleGPT
[Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar PodleGPT.jar` command to run the application[cite: 9].

A welcome message should appear in a few seconds (Example given below)


## Features 

### Adding a ToDo Task: `todo`, `add`
Creates a basic task in the tracker.
* **Format:** `todo <TASK_NAME>` or `add <TASK_NAME>`
* **Example:** `todo read book`

### Adding a Deadline Task: `deadline`
Creates a task with a specific deadline. Needs a name and a time separated by `/`.
* **Format:** `deadline <TASK_NAME> /<TIME>`
* **Example:** `deadline submit assignment /by Friday 2359`

### Adding an Event Task: `event`
Creates an event with a start and end time. Needs a name and two times separated by `/`.
* **Format:** `event <EVENT_NAME> /<START_TIME> /<END_TIME>`
* **Example:** `event project meeting /from 2pm /to 4pm`

### Listing all Tasks: `list`
List all current tasks along with their completion status.
* **Format:** `list`

### Marking/Unmarking Tasks: `mark`, `unmark`
Marks a task as completed or incomplete. PodleGPT supports marking or unmarking multiple tasks at once!
* **Format:** `mark <INDEX> [INDEX...]` or `unmark <INDEX> [INDEX...]`
* **Example:** `mark 1` or `mark 1, 2, 3`

### Deleting Tasks: `delete`
Removes a task from your list permanently.
* **Format:** `delete <INDEX>`
* **Example:** `delete 2`

### Quitting the Program: `bye`, `byebye`
Saves your data and safely exits the application.
* **Format:** `bye` or `byebye`

### Command summary 
The following table summarizes the available commands:

| Action | Format | Example |
| :--- | :--- | :--- |
| **ADD TODO** | `todo <TASK_NAME>` | `todo read book` |
| **ADD DEADLINE**| `deadline <TASK_NAME> /<TIME>` | `deadline submit assignment /by Friday 2359` |
| **ADD EVENT** | `event <NAME> /<TIME1> /<TIME2>`| `event project meeting /from 2pm /to 4pm` |
| **LIST** | `list` | `list` |
| **MARK** | `mark <INDEX1>, [INDEX2]...` | `mark 1, 2, 3` |
| **UNMARK** | `unmark <INDEX1>, [INDEX2]...`| `unmark 1` |
| **DELETE** | `delete <INDEX>` | `delete 2` |
| **QUIT** | `bye` or `byebye` | `bye` |

## Showcase

Welcome Message:

```text
                                  .:.:-...    ..   .      .   .      .    .      .+-++.
                                ::-..              .                        ...     ..:-**.
                            .:..::....-= ..-    ....::...       .==  .....  .....-.... ...:.....-=..
                             -.    .-+. :.........:..:  .  ...   ..      ..:........  -....... . .::
                  = .=... ..:.  ::=-:=:   ..::-==:    ..  =  =%*===:.::..:-:.::..=-=--.   .:......:::=
                    -===-..--..   --. ..:   ::%=@@: .   .-% +:  .== =+:=-   .......::........-:=-....=
               *:+:--+++#+=..--.  .:@%: ..@@@@+*:.:-.:++:.. **.#.  .:.  ...:=+++*=:   -#** .@:...    ..-=-
           ...-#=:.:-----. =-+::**##* :#@@@@%%@@==-:..::. .# . @@**#@.-.*+++:      .:     . -@*:..    .--:=%
       -@@=----. .:-=+--+..++*:    .#@#.   .%%@-    ====*** *#*:   *@ +:-:###@@%#*++-        ..:+***:. .  :
        @*---+:..:.+#   . =:    **@:.@@@@@@@@@@@@@.      .*%=: =.   -:...:       .=%*++-  =:#:.---::     .**
         ..    *-+##. -- .::=@@@** :.       ++*.*==@*%@@@@@@%@+:... +  .+%+++##**   %@@=::   .-:....    *..
    = =*=  ..@:-@@:.@*..*#. .-    :-#@@@@+.=::+@@@@@* %%* :=@:  .== @  ..+#.    =%@+%#-.**@=-#%##+::-=:  +.+=
   @.  :#@@@@. =-.+   @@@@ .#==:##:.+@#*#@%*++%%#@@@@@  -: @-+-. .. - * @@#*@%%-*+@#:.        .=. ..*-  -=  +..
  @-@@ **@@@@#=  @@=:@%%%@@- *%@@@@*=@. -@@@@@@@#@@= +  %*@#==#=---**@ @@+-      #=  .%@@@#: #@@ ....  @@  @   -+
  :=  :#==.    .. @%#---*@@@@#*=:: :=*@=@@@@-- -:+.. ...  -@@-.@:@@@=:    #@@@@@@@@       -@@  -@+.. .  @@:.@
   =- =.  :  + +@@# .@%%=@@%@@**--=%@@@@@@@*@@@#:  *=...@=   #  ...   +-.  #%@@  @@# .      @@  = @@.   @*+ ...#  :
  =-  +*=. ..=*@@@ -.+....:#%=:         .+@@@@@@%+-.*+  :%#*=:@.*++-=#+- :*@*      -#%@*  .=-  @@   @  %@= + -- :..:=*:.
  .*. @@+----@#  ..=#+@@@@@+      .:.-#-    @@@#-.  -:@@@-.@*@@@%%%@%*.-=..  ..#=.     -++  +#  @   @= %:.   ::..:.   .*
   .  =#*+-@@%.   #@@*    .-#@+.      -+%     --.  @@---@@#      ...::@*  :.=@@@*-:==*==---. *+:@#** +% *.     @@@@@..  ::
  #.:.+:==###..+@@   .++:. =#=  ..*@@@*=%@@#**@+-@@@@*@@=  .::-=      @@        .:.    +==.:+ =@@@@@@+#= #     @@: *- -.
  =#-+..+ :@@@.%% +%@@--:   :+*++++     ..:#@@+@*. --*##*@@@*+* @@@%- *:#@*-#%%.  .++=.-......-.+@==+@.  .*  ..%*@@ =. .::
   @*=::: #@@@-   * @@..  := .=+==##*%**=#@*..  .@@@++++@+:- -%      .*- *#%:  +*-.  .----@=.   . **+@*    .@@@-.+@=:..*--
    *-..=:---  *@@+@@*::.=..-==*... +@@@+-  ::*@%   :-   +@@@*+@@@@@+  #%:+-+::    -- .    . :. ..@@@=%@@#:@@.. @+ .-= -
     @-   .@@@*+=:+:*@    -=-.*@@@   *@*. #:++..=   *@::*  * .  .    ::-@ =- ..:.. . @.:..:. ..-*-++#%*#@   .@@-+  -  :
     -@:==@##  =%. .*-  :#+ :=@:  :%+@+.:#. ##-:.  :@@@@@@@#   %-==  =%.++.=.==# +..:.= .. .. .+.-==@#@@     ##. : ** :
      @@@@@%%@@@@*    @@# . .-=### :  :  :.#%%+@-=@@# .. :-=@@=@+-- @@@@#@:+.@@=. =. = .::.=. .- @@@%   .=  +%%.@@@@@@@
         @              @-.-+%**  #:-++.*#@@@@@@@@@ @.--+%@@@@@@-  %@@%@#+*#+@@-=-:..=:-..:::.   :@@@#=@#@@@@@@#@
                          @-..  ::* .@#@@@@%@@@##+=-*   @@@@@   @@@@%-:-=@.:#==..- .+-- ..#..:##+
                           @#=%@++=*@@+%@@@###%#@@%+@@@@@%%.  @+***+*@@@%::= --..  .:.:-     +*
                            @#--%%%+@%@@@*==##@%:=+*:.  ::+@+%-+-**=@=.#@@+%#@@@:*@=::  %%=*%#
                              @@..:-*++#%%=-===*%*+=*%@@@*@#: %=#**- =*=+.:*@@@ =   . .+##*=.
                               @@@#*@@@@@%%@**#::*==*+==%+--@-#:-:::*%@%@#==.  @+:-.+:%*
                                                  *@%%%%@@*=+ = ---+*%@@-+=%%%%%=.
                                           ____   ___  ____  _     _____ ____
                                          |  _ \ / _ \|  _ \| |   | ____/ ___|
                                          | |_) | | | | | | | |   |  _| \___ \
                                          |  __/| |_| | |_| | |___| |___ ___) |
                                          |_|    \___/|____/|_____|_____|____/

HELLO! I am Podles! made by Podles!
What SHALL Podles do for you?! ( ??-)-?
```
