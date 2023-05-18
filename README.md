# **PL:** Program **Flashcards** umożliwia użytkownikowi zarządzanie zestawem fiszek.
## Użytkownik może dodawać, usuwać, importować i eksportować fiszki, zadawać pytania z losowo wybranych fiszek, resetować statystyki oraz wyświetlać logi.

### Jesli na starcie zostły okręślony argument rozruch `-import` to porgram rozpoczyna się od wczytania fiszek z pliku. Następnie użytkownik może wykonywać różne akcje, podając odpowiednie komendy.

#### Dostępne komendy:

**add** - dodaje nową fiszkę do zestawu. Użytkownik wprowadza pytanie i definicję fiszki, które zostają zapisane w pamięci programu.

**remove** - usuwa wybraną fiszkę ze zestawu. Użytkownik podaje nazwę fiszki, która ma zostać usunięta.

**import** - importuje fiszki z pliku. Użytkownik podaje nazwę pliku, z którego mają zostać wczytane fiszki.

**export** - eksportuje fiszki do pliku. Użytkownik podaje nazwę pliku, do którego mają zostać zapisane fiszki.

**ask** - zadaje pytania z losowo wybranych fiszek. Użytkownik podaje liczbę pytań, które chce otrzymać.

**exit** - jeśli zostanie okręslony argument `-export` , program zapisuje fiszki do pliku i następnie wyłacza program.

**log** - zapisuje logi programu do pliku. Użytkownik podaje nazwę pliku, do którego mają zostać zapisane logi.

**hardest card** - wyświetla informacje o najtrudniejszych fiszkach. Pokazuje fiszki, które użytkownik najczęściej źle odpowiadał.

**reset stats** - resetuje statystyki najtrudniejszych fiszek.

# **ENG:** Program **Flashcards** allows the user to manage a set of flashcards.
## Users can add, remove, import, and export flashcards, ask questions from randomly selected flashcards, reset statistics, and display logs.

### If the program is started with the `-import` argument, the program begins by loading flashcards from a file. Then the user can perform various actions by entering the appropriate commands.

#### Available commands:

- **add**: adds a new flashcard to the set. The user enters the question and definition of the flashcard, which are saved in the program's memory.

- **remove**: removes a selected flashcard from the set. The user specifies the name of the flashcard to be removed.

- **import**: imports flashcards from a file. The user specifies the file name from which the flashcards should be loaded.

- **export**: exports flashcards to a file. The user specifies the file name to which the flashcards should be saved.

- **ask**: asks questions from randomly selected flashcards. The user specifies the number of questions they want to receive.

- **exit**: if the `-export` argument is specified, the program saves the flashcards to a file and then exits the program.

- **log**: saves program logs to a file. The user specifies the file name to which the logs should be saved.

- **hardest card**: displays information about the hardest flashcards. It shows the flashcards that the user most frequently answered incorrectly.

- **reset stats**: resets the statistics of the hardest flashcards.
