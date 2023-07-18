# Wordle Bot

## Description
Wordle Bot is a Java-based project designed to solve Wordle puzzles automatically. It utilizes the Selenium framework to interact with the official Wordle webpage and solve the daily puzzle.

[Wordle](https://www.nytimes.com/games/wordle/index.html) is a popular word-guessing game where players attempt to guess a five-letter target word within six attempts. The game provides feedback by indicating the correct letters in the guessed word, making it an engaging and challenging puzzle.

This project aims to automate the solving process by leveraging Selenium's capabilities to interact with web elements and simulate user input on the Wordle page. With Wordle Bot, you can quickly obtain the solution to the daily Wordle puzzle without manual input or guesswork.

Please note that the program is configured to work with the [Wordle page](https://www.nytimes.com/games/wordle/index.html) as it is at 18/07/2023. If the page is changed in the future the bot might not work anymore and will need to be updated.

Wordle Bot achieves a high level of accuracy in solving Wordle puzzles when using the provided dictionary of words that can be guessed and can be the word of the day. After many simulations, the program consistently guesses the correct word in at most six guesses with an accuracy rate of 97%. This high accuracy rate ensures reliable and efficient solving of Wordle puzzles when utilizing the specified dictionary.

Please note that the accuracy rate mentioned is based on simulations and testing using the provided dictionary. The accuracy may vary if a different dictionary or word list is used, or if the Wordle game's dictionary changes over time.

If you encounter any issues or have questions, please refer to the project's documentation or reach out to the project contributors for assistance.

## Features
- Automates the solving of Wordle puzzles
- Interacts with the official Wordle webpage
- Utilizes Selenium framework for web interaction
- Provides the solution to the daily Wordle puzzle
- Reduces the need for manual input and guesswork

## Installation

### Prerequisites
- Java Development Kit (JDK) 19 or higher
- Gradle build tool

### Instructions
1. Clone the repository:
   ```shell
   git clone https://github.com/Babu-on-GitHub/wordle_bot
   ```

2. Navigate to the project directory:
   ```shell
   cd wordle_bot
   ```

3. Build the project using Gradle:
   ```shell
   gradle build
   ```

4. Run the project:
   ```shell
   gradle run
   ```

## Usage
1. Make sure the Wordle Bot project is running.
2. The program will automatically open a web browser and navigate to the official Wordle webpage.
3. Wait for the program to locate and click on the "Play today's puzzle" button to start the game.
4. Observe Wordle Bot's progress as it interacts with the page and makes guesses automatically.
5. Once Wordle Bot successfully solves the puzzle, the solution will be displayed.

Please note that the program handles the entire process of opening the browser, navigating to the correct pages, and solving the puzzle on its own. Simply run the project and let Wordle Bot handle the rest.

If you encounter any issues or have questions, please refer to the project's documentation or reach out to the project contributors for assistance.

## Contributing
Contributions to the Wordle Bot project are welcome and encouraged. If you have any ideas, suggestions, or bug reports, please submit them as issues or pull requests on the project's GitHub repository.

## License
This project is licensed under the MIT License.

## Acknowledgments
- The Wordle game and concept belong to the original creators.

We acknowledge and appreciate the original creators of Wordle for developing the game and introducing it to the community. Wordle Bot is an independent project that utilizes the game's mechanics and dictionary for the purpose of automating the solving process.

  
- Selenium for providing a powerful web automation framework.
- The Java and Gradle communities for their contributions to the development ecosystem.
- The Wordle dictionary used in this project, which contains the contents of the dictionary as of January 27th, 2022.
   You can find the dictionary contents [here](https://gist.github.com/scholtes/94f3c0303ba6a7768b47583aff36654d).
   We appreciate the availability and use of the Wordle dictionary in this project, as it helps enhance the accuracy and effectiveness of solving Wordle puzzles.

## Disclaimer
This project is developed independently and is not endorsed, sponsored, or affiliated with the owners of the Wordle game or any related entities. The use of Wordle Bot is at the user's discretion and responsibility. Please use this project responsibly, adhere to the terms and conditions of the official Wordle game, and respect the intellectual property rights of the game's creators.

If you have any questions or concerns regarding the usage of Wordle Bot, please consult the official Wordle game resources or reach out to the project contributors for guidance.
