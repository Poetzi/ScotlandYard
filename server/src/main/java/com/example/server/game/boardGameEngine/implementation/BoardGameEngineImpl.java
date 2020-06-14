package com.example.server.game.boardGameEngine.implementation;

import com.esotericsoftware.kryonet.Connection;
import com.example.server.game.players.TravelLog;
import com.example.server.game.players.implementation.DetectiveImpl;
import com.example.server.game.players.implementation.MrXImpl;
import com.example.server.game.players.interfaces.Detective;
import com.example.server.game.players.interfaces.MrX;
import com.example.server.game.boardGameEngine.interfaces.BoardGameEngine;
import com.example.server.game.gameBoard.implementation.GameBoardImpl;
import com.example.server.game.gameBoard.interfaces.GameBoard;
import com.example.server.game.players.implementation.DetectiveImpl;
import com.example.server.game.players.implementation.MrXImpl;
import com.example.server.game.players.interfaces.Player;
import com.example.server.game.transitions.implementation.TransitionImpl;
import com.example.server.game.transitions.interfaces.Transition;
import com.example.server.lobby.implementation.LobbyImpl;
import com.example.server.lobby.interfaces.Lobby;
import com.example.server.messages.AskPlayerForTurn;
import com.example.server.messages.ToastMessage;
import com.example.server.messages.TurnMessage;
import com.example.server.messages.UpdatePlayersPosition;

public class BoardGameEngineImpl {

    private Player[] players = new Player[2];
    //private TurnMessage[] turns = new TurnMessage[2];
    private int numberOfPlayers = 0;
    private int maxRounds = 24;
    private int actualRound = 0;
    private GameBoard gameBoard = new GameBoardImpl();
    private int numberOfFields;
    private LobbyImpl lobby;
    private int mrXId;

    private boolean player0Turn = false;
    private boolean player1Turn = false;

    // MrX
    private Connection con0;
    // Detektiv
    private Connection con1;

    private boolean p0won = false;
    private boolean p1won = false;

    // Singleton
    private static BoardGameEngineImpl boardGameEngine;

    public int playerReady = 0;


    // private Konstruktor
    private BoardGameEngineImpl() {
    }

    // Singleton wird zurückgegeben
    public static BoardGameEngineImpl getInstance() {
        if (BoardGameEngineImpl.boardGameEngine == null) {
            BoardGameEngineImpl.boardGameEngine = new BoardGameEngineImpl();
        }
        return BoardGameEngineImpl.boardGameEngine;
    }


    public void addDetektiv(String name, int id) {
        players[id] = new DetectiveImpl(name, id);
        numberOfPlayers++;
    }


    public void addMrX(String name, int id) {
        players[id] = new MrXImpl(name, id);
        mrXId = id;
        numberOfPlayers++;
    }


    public void setupNewGame() {


        gameBoard.addFieldWithTransition(2, 3, "taxi");
        gameBoard.addFieldWithTransition(2, 7, "bus");
        gameBoard.addFieldWithTransition(2, 7, "ubahn");

        gameBoard.addFieldWithTransition(3, 1, "bus");
        gameBoard.addFieldWithTransition(3, 1, "taxi");
        gameBoard.addFieldWithTransition(3, 5, "taxi");

        gameBoard.addFieldWithTransition(5, 12, "taxi");

        gameBoard.addFieldWithTransition(12, 1, "bus");
        gameBoard.addFieldWithTransition(12, 16, "bus");

        gameBoard.addFieldWithTransition(1, 7, "bus");
        gameBoard.addFieldWithTransition(1, 13, "taxi");
        gameBoard.addFieldWithTransition(1, 12, "bus");

        gameBoard.addFieldWithTransition(7, 6, "ubahn");
        gameBoard.addFieldWithTransition(7, 8, "bus");

        gameBoard.addFieldWithTransition(8, 9, "taxi");

        gameBoard.addFieldWithTransition(6, 9, "taxi");
        gameBoard.addFieldWithTransition(6, 9, "bus");
        gameBoard.addFieldWithTransition(6, 13, "bus");
        gameBoard.addFieldWithTransition(6, 13, "taxi");
        gameBoard.addFieldWithTransition(6, 14, "ubahn");

        gameBoard.addFieldWithTransition(13, 15, "taxi");

        gameBoard.addFieldWithTransition(15, 16, "taxi");
        gameBoard.addFieldWithTransition(15, 14, "taxi");
        gameBoard.addFieldWithTransition(15, 19, "bus");

        gameBoard.addFieldWithTransition(14, 20, "ubahn");
        gameBoard.addFieldWithTransition(14, 25, "taxi");
        gameBoard.addFieldWithTransition(14, 25, "bus");

        gameBoard.addFieldWithTransition(16, 18, "taxi");

        gameBoard.addFieldWithTransition(25, 9, "taxi");
        gameBoard.addFieldWithTransition(25, 21, "taxi");
        gameBoard.addFieldWithTransition(25, 11, "bus");

        gameBoard.addFieldWithTransition(9, 10, "bus");

        gameBoard.addFieldWithTransition(10, 11, "taxi");

        gameBoard.addFieldWithTransition(11, 33, "taxi");
        gameBoard.addFieldWithTransition(11, 22, "taxi");

        gameBoard.addFieldWithTransition(33, 32, "bus");

        gameBoard.addFieldWithTransition(32, 23, "taxi");
        gameBoard.addFieldWithTransition(32, 22, "bus");
        gameBoard.addFieldWithTransition(32, 22, "ubahn");

        gameBoard.addFieldWithTransition(22, 21, "bus");
        gameBoard.addFieldWithTransition(22, 21, "ubahn");
        gameBoard.addFieldWithTransition(22, 24, "bus");

        gameBoard.addFieldWithTransition(21, 20, "bus");
        gameBoard.addFieldWithTransition(21, 20, "ubahn");
        gameBoard.addFieldWithTransition(21, 34, "bus");

        gameBoard.addFieldWithTransition(20, 19, "taxi");
        gameBoard.addFieldWithTransition(20, 19, "ubahn");
        gameBoard.addFieldWithTransition(20, 26, "ubahn");

        gameBoard.addFieldWithTransition(19, 18, "bus");
        gameBoard.addFieldWithTransition(19, 18, "ubahn");
        gameBoard.addFieldWithTransition(19, 27, "taxi");

        gameBoard.addFieldWithTransition(23, 44, "taxi");
        gameBoard.addFieldWithTransition(23, 24, "taxi");

        gameBoard.addFieldWithTransition(24, 45, "taxi");
        gameBoard.addFieldWithTransition(45, 44, "taxi");

        gameBoard.addFieldWithTransition(45, 34, "taxi");
        gameBoard.addFieldWithTransition(45, 30, "taxi");

        gameBoard.addFieldWithTransition(34, 29, "taxi");
        gameBoard.addFieldWithTransition(34, 26, "taxi");
        gameBoard.addFieldWithTransition(27, 26, "taxi");

        gameBoard.addFieldWithTransition(28, 29, "bus");
        gameBoard.addFieldWithTransition(30, 29, "taxi");

        gameBoard.addFieldWithTransition(30, 36, "ubahn");
        gameBoard.addFieldWithTransition(30, 31, "bus");
        gameBoard.addFieldWithTransition(31, 44, "taxi");
        gameBoard.addFieldWithTransition(31, 35, "bus");


        gameBoard.addFieldWithTransition(37, 29, "bus");

        gameBoard.addFieldWithTransition(37, 36, "taxi");
        gameBoard.addFieldWithTransition(37, 38, "taxi");
        gameBoard.addFieldWithTransition(38, 36, "taxi");
        gameBoard.addFieldWithTransition(36, 35, "ubahn");

        gameBoard.addFieldWithTransition(43, 35, "taxi");
        gameBoard.addFieldWithTransition(43, 35, "bus");

        gameBoard.addFieldWithTransition(43, 42, "taxi");

        gameBoard.addFieldWithTransition(41, 42, "bus");

        gameBoard.addFieldWithTransition(41, 40, "bus");

        gameBoard.addFieldWithTransition(38, 39, "bus");
        gameBoard.addFieldWithTransition(40, 39, "bus");


    }


    public void sendStartingPositions() {


        // toDo send initial position of the players to clients
        gameBoard.setPositionOfPlayer(0, 2);
        gameBoard.setPositionOfPlayer(1, 21);



        updatePositionOffaPlayer(0,2, "Taxi");
        updatePositionOffaPlayer(1,21, "Taxi");
        System.out.println("Initial Position von P0 und P1 gesendet");
    }

    public boolean checkDraw(TurnMessage msg)
    {
        return gameBoard.checkDraw(msg.getPlayerId(),msg.getToField(),msg.getCard());

    }


    public void askPlayer0forTurn()
    {
        AskPlayerForTurn msg = new AskPlayerForTurn();
        msg.setId(0);
        msg.setText("Bitte einen Zug angeben");
        con0.sendTCP(msg);
    }

    public void askPlayer1forTurn()
    {
        AskPlayerForTurn msg = new AskPlayerForTurn();
        msg.setId(1);
        msg.setText("Bitte einen Zug angeben");
        con1.sendTCP(msg);
    }

    public void updatePositionOffaPlayer(int pId, int toField, String card)
    {
        gameBoard.setPositionOfPlayer(pId,toField);


        UpdatePlayersPosition msg = new UpdatePlayersPosition();
        msg.setPlayerId(pId);
        msg.setToField(toField);
        ToastMessage toast = new ToastMessage(pId, pId+ " ist mit " +card + " gefahren!");
        if(pId == 0){
            con1.sendTCP(toast);
        }
        else{
            con0.sendTCP(toast);
        }

        con0.sendTCP(msg);
        con1.sendTCP(msg);
    }


    public void checkWinningCondition() {
        if(gameBoard.getPositionOfPlayer(0) == gameBoard.getPositionOfPlayer(1))
        {
            // Detektive hat gewonnen
            setP1won(true);
        }
        if (actualRound == maxRounds)
        {
            // MrX hat gewonnen
            setP0won(true);
        }
    }


    public void initLobby(LobbyImpl lobby) {
        this.lobby = lobby;
    }


    public LobbyImpl getLobby() {
        return lobby;
    }

    public void setLobby(LobbyImpl lobby) {
        this.lobby = lobby;
    }

    public Connection getCon0() {
        return con0;
    }

    public void setCon0(Connection con0) {
        this.con0 = con0;
    }

    public Connection getCon1() {
        return con1;
    }

    public void setCon1(Connection con1) {
        this.con1 = con1;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public boolean isPlayer0Turn() {
        return player0Turn;
    }

    public void setPlayer0Turn(boolean player0Turn) {
        this.player0Turn = player0Turn;
    }

    public boolean isPlayer1Turn() {
        return player1Turn;
    }

    public void setPlayer1Turn(boolean player1Turn) {
        this.player1Turn = player1Turn;
    }

    public int getActualRound() {
        return actualRound;
    }

    public void setActualRound(int actualRound) {
        this.actualRound = actualRound;
    }

    public void plus1ActualRound()
    {
        actualRound++;
    }

    public void setNextTurnforPlayer0()
    {
        player0Turn = true;
        player1Turn = false;
    }

    public void setNextTurnforPlayer1()
    {
        player1Turn = true;
        player0Turn = false;
    }


    public boolean isP0won() {
        return p0won;
    }

    public boolean isP1won() {
        return p1won;
    }

    public int getMaxRounds() {
        return maxRounds;
    }

    public void setP0won(boolean p0won) {
        this.p0won = p0won;
    }

    public void setP1won(boolean p1won) {
        this.p1won = p1won;
    }
}
