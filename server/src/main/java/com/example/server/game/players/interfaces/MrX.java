package com.example.server.game.players.interfaces;

import com.example.server.game.players.TravelLog;
import com.example.server.messages.TravellogMessage;

public interface MrX extends Player {
    int getBlackTickets();

    int getDoubleMoveTickets();

    int getPositionOfRound(int round);

    String getTicketOfRound(int round);

    boolean isDoubleMoveAtRound(int round);

    void setHasCheated(int round);

    boolean getHasCheatedInRound(int round);

    void setBlackTickets(int ticketNumber);

    void setDoubleMoveTickets(int ticketNumber);

    void setPositionOfRound(int position, String ticket, int round, boolean isDoubleMove);

    void validateTicket(int round, String ticketType, int newPosition);

    void validateDoubleMoveTicket(int round, String ticketType, int newPosition);

    TravelLog getTravelLog(int round);

    void setTravelLog(TravelLog travelLog, int round);

    int getCheatTickets();

    void setCheatTickets(int cheatTickets);

    boolean isCaughtCheating();

    void setCaughtCheating(boolean caughtCheating, int round);

    int getVisibleFor();

    void setVisibleFor(int visibleFor);

    TravelLog[] getTravelLog();

    void setTravelLog(TravelLog[] travelLog);
}
