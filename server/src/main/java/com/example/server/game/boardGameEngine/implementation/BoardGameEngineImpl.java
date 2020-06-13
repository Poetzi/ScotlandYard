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
import com.example.server.messages.TurnMessage;
import com.example.server.messages.UpdatePlayersPosition;

public class BoardGameEngineImpl {

    private Player[] players = new Player[2];
    //private TurnMessage[] turns = new TurnMessage[2];
    private int numberOfPlayers = 0;
    private int maxRounds = 24;
    private int actualRound;
    private GameBoard gameBoard = new GameBoardImpl();
    private int numberOfFields;
    private LobbyImpl lobby;
    private int mrXId;

    private Connection con0;
    private Connection con1;

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

      //Taxi Transitions
      gameBoard.addFieldWithTransition(1,2,"taxi");
        gameBoard.addFieldWithTransition(2,1,"taxi");
        gameBoard.addFieldWithTransition(1,3,"taxi");
        gameBoard.addFieldWithTransition(3,1,"taxi");
        gameBoard.addFieldWithTransition(3,4,"taxi");
        gameBoard.addFieldWithTransition(4,3,"taxi");
        gameBoard.addFieldWithTransition(1,5,"taxi");
        gameBoard.addFieldWithTransition(5,1,"taxi");
        gameBoard.addFieldWithTransition(3,7,"taxi");
        gameBoard.addFieldWithTransition(7,3,"taxi");
        gameBoard.addFieldWithTransition(4,8,"taxi");
        gameBoard.addFieldWithTransition(8,4,"taxi");

        gameBoard.addFieldWithTransition(6,5,"taxi");
        gameBoard.addFieldWithTransition(5,6,"taxi");
        gameBoard.addFieldWithTransition(5,7,"taxi");
        gameBoard.addFieldWithTransition(7,5,"taxi");
        gameBoard.addFieldWithTransition(7,8,"taxi");
        gameBoard.addFieldWithTransition(8,7,"taxi");
        gameBoard.addFieldWithTransition(5,11,"taxi");
        gameBoard.addFieldWithTransition(11,5,"taxi");
        gameBoard.addFieldWithTransition(8,9,"taxi");
        gameBoard.addFieldWithTransition(9,8,"taxi");

        gameBoard.addFieldWithTransition(1,2,"taxi");
        gameBoard.addFieldWithTransition(1,2,"taxi");
        gameBoard.addFieldWithTransition(11,17,"taxi");
        gameBoard.addFieldWithTransition(9,20,"taxi");
        gameBoard.addFieldWithTransition(20,9,"taxi");
        gameBoard.addFieldWithTransition(20,21,"taxi");
        gameBoard.addFieldWithTransition(17,18,"taxi");
        gameBoard.addFieldWithTransition(18,17,"taxi");
        gameBoard.addFieldWithTransition(15,25,"taxi");
        gameBoard.addFieldWithTransition(14,26,"taxi");

        gameBoard.addFieldWithTransition(25,24,"taxi");
        gameBoard.addFieldWithTransition(24,25,"taxi");
        gameBoard.addFieldWithTransition(24,18,"taxi");
        gameBoard.addFieldWithTransition(18,24,"taxi");
        gameBoard.addFieldWithTransition(18,19,"taxi");
        gameBoard.addFieldWithTransition(19,18,"taxi");
        gameBoard.addFieldWithTransition(19,21,"taxi");
        gameBoard.addFieldWithTransition(21,19,"taxi");
        gameBoard.addFieldWithTransition(26,27,"taxi");
        gameBoard.addFieldWithTransition(27,26,"taxi");
        gameBoard.addFieldWithTransition(25,32,"taxi");
        gameBoard.addFieldWithTransition(32,25,"taxi");
        gameBoard.addFieldWithTransition(19,22,"taxi");
        gameBoard.addFieldWithTransition(22,19,"taxi");

        gameBoard.addFieldWithTransition(33,23,"taxi");
        gameBoard.addFieldWithTransition(23,33,"taxi");
        gameBoard.addFieldWithTransition(29,30,"taxi");
        gameBoard.addFieldWithTransition(30,29,"taxi");
        gameBoard.addFieldWithTransition(28,29,"taxi");
        gameBoard.addFieldWithTransition(29,28,"taxi");
        gameBoard.addFieldWithTransition(29,41,"taxi");
        gameBoard.addFieldWithTransition(41,29,"taxi");
        gameBoard.addFieldWithTransition(30,31,"taxi");
        gameBoard.addFieldWithTransition(31,30,"taxi");

        gameBoard.addFieldWithTransition(41,31,"taxi");
        gameBoard.addFieldWithTransition(31,41,"taxi");
        gameBoard.addFieldWithTransition(34,35,"taxi");
        gameBoard.addFieldWithTransition(35,34,"taxi");
        gameBoard.addFieldWithTransition(35,36,"taxi");
        gameBoard.addFieldWithTransition(36,35,"taxi");
        gameBoard.addFieldWithTransition(41,42,"taxi");
        gameBoard.addFieldWithTransition(42,41,"taxi");
        gameBoard.addFieldWithTransition(31,40,"taxi");
        gameBoard.addFieldWithTransition(40,31,"taxi");
        gameBoard.addFieldWithTransition(34,39,"taxi");
        gameBoard.addFieldWithTransition(39,34,"taxi");
        gameBoard.addFieldWithTransition(36,37,"taxi");
        gameBoard.addFieldWithTransition(37,36,"taxi");

        gameBoard.addFieldWithTransition(42,40,"taxi");
        gameBoard.addFieldWithTransition(40,42,"taxi");
        gameBoard.addFieldWithTransition(40,39,"taxi");
        gameBoard.addFieldWithTransition(39,40,"taxi");
        gameBoard.addFieldWithTransition(38,37,"taxi");
        gameBoard.addFieldWithTransition(37,38,"taxi");
        gameBoard.addFieldWithTransition(42,46,"taxi");
        gameBoard.addFieldWithTransition(46,42,"taxi");
        gameBoard.addFieldWithTransition(40,45,"taxi");
        gameBoard.addFieldWithTransition(45,40,"taxi");
        gameBoard.addFieldWithTransition(39,43,"taxi");
        gameBoard.addFieldWithTransition(43,39,"taxi");
        gameBoard.addFieldWithTransition(43,44,"taxi");
        gameBoard.addFieldWithTransition(44,43,"taxi");

        gameBoard.addFieldWithTransition(46,45,"taxi");
        gameBoard.addFieldWithTransition(45,46,"taxi");
        gameBoard.addFieldWithTransition(45,44,"taxi");
        gameBoard.addFieldWithTransition(44,45,"taxi");

        //Bus Transitions
        gameBoard.addFieldWithTransition(2,12,"bus");
        gameBoard.addFieldWithTransition(12,2,"bus");

        gameBoard.addFieldWithTransition(1,11,"bus");
        gameBoard.addFieldWithTransition(11,1,"bus");

        gameBoard.addFieldWithTransition(7,10,"bus");
        gameBoard.addFieldWithTransition(10,7,"bus");

        gameBoard.addFieldWithTransition(10,19,"bus");
        gameBoard.addFieldWithTransition(19,10,"bus");

        gameBoard.addFieldWithTransition(13,15,"bus");
        gameBoard.addFieldWithTransition(15,13,"bus");

        gameBoard.addFieldWithTransition(18,23,"bus");
        gameBoard.addFieldWithTransition(23,18,"bus");

        gameBoard.addFieldWithTransition(27,30,"bus");
        gameBoard.addFieldWithTransition(30,27,"bus");

        gameBoard.addFieldWithTransition(32,34,"bus");
        gameBoard.addFieldWithTransition(34,32,"bus");

        gameBoard.addFieldWithTransition(13,12,"bus");
        gameBoard.addFieldWithTransition(12,13,"bus");

        gameBoard.addFieldWithTransition(12,11,"bus");
        gameBoard.addFieldWithTransition(11,12,"bus");

        gameBoard.addFieldWithTransition(11,10,"bus");
        gameBoard.addFieldWithTransition(10,11,"bus");

        gameBoard.addFieldWithTransition(10,9,"bus");
        gameBoard.addFieldWithTransition(9,10,"bus");

        gameBoard.addFieldWithTransition(26,24,"bus");
        gameBoard.addFieldWithTransition(24,26,"bus");

        gameBoard.addFieldWithTransition(28,27,"bus");
        gameBoard.addFieldWithTransition(27,28,"bus");

        gameBoard.addFieldWithTransition(27,32,"bus");
        gameBoard.addFieldWithTransition(32,27,"bus");

        gameBoard.addFieldWithTransition(32,33,"bus");
        gameBoard.addFieldWithTransition(33,32,"bus");

        gameBoard.addFieldWithTransition(23,22,"bus");
        gameBoard.addFieldWithTransition(22,23,"bus");

        gameBoard.addFieldWithTransition(31,34,"bus");
        gameBoard.addFieldWithTransition(34,31,"bus");

        gameBoard.addFieldWithTransition(39,38,"bus");
        gameBoard.addFieldWithTransition(38,39,"bus");

        //U-Bahn Transitions
        gameBoard.addFieldWithTransition(2,16,"ubahn");
        gameBoard.addFieldWithTransition(16,2,"ubahn");
        gameBoard.addFieldWithTransition(16,33,"ubahn");
        gameBoard.addFieldWithTransition(33,16,"ubahn");
        gameBoard.addFieldWithTransition(33,38,"ubahn");
        gameBoard.addFieldWithTransition(38,33,"ubahn");

        gameBoard.addFieldWithTransition(19,16,"ubahn");
        gameBoard.addFieldWithTransition(16,19,"ubahn");
        gameBoard.addFieldWithTransition(14,16,"ubahn");
        gameBoard.addFieldWithTransition(16,14,"ubahn");

        gameBoard.addFieldWithTransition(22,33,"ubahn");
        gameBoard.addFieldWithTransition(33,22,"ubahn");
        gameBoard.addFieldWithTransition(33,28,"ubahn");
        gameBoard.addFieldWithTransition(28,33,"ubahn");

        gameBoard.addFieldWithTransition(23,38,"ubahn");
        gameBoard.addFieldWithTransition(38,23,"ubahn");


    }


    public void sendStartingPositions() {


        // toDo send initial position of the players to clients
        gameBoard.setPositionOfPlayer(0, 2);
        gameBoard.setPositionOfPlayer(1, 21);



        updatePositionOffaPlayer(0,2);
        updatePositionOffaPlayer(1,21);
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

    public void updatePositionOffaPlayer(int pId, int toField)
    {
        gameBoard.setPositionOfPlayer(pId,toField);


        UpdatePlayersPosition msg = new UpdatePlayersPosition();
        msg.setPlayerId(pId);
        msg.setToField(toField);

        con0.sendTCP(msg);
        con1.sendTCP(msg);
    }


    public boolean checkWinningCondition() {
        int misterX = mrXId;

        if (maxRounds == actualRound) {
            System.out.println("Mister X won");
            return false;
        }

        for (int i = 0; i < numberOfPlayers; i++) {
            /*if(players.get(misterX) == players.get(i).currentPosition){ //Missing field from Player CLass
                System.out.println("Mister X lost");
                return true;
            }
             */
        }
        return false;
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
}
