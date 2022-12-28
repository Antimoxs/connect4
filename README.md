# connect4
A simple, dynamic connect4 library in java.

This is used pretty much straight forward:

1. Create implementations for the following callbacks:

`IPlayerTurn#onPlayerTurn()`

`IGameEnd#onGameEnd()` 

`IGameRender#onGameRender()`

(`ILogOutput#onLogMessage()` *Optional*)

2. Make a new Connect4 instance.
3. Start the game!
