package com.tao;

public class Player {
    private static PlayerType playerType;
    public enum PlayerType{
        FOOTBALL,
        BASKETBALL,
        PING_PONG
    }

    public boolean isBasketBallPlayer(){
        return getPlayerType() == PlayerType.BASKETBALL;
    }

    public PlayerType getPlayerType(){
        return playerType;
    }

    public void setPlayerType(PlayerType playerType){
        this.playerType = playerType;
    }

    public static void main(String[] args) {
        Player player = new Player();
        player.setPlayerType(PlayerType.BASKETBALL);
        switch (playerType){
            case FOOTBALL :
                System.out.println("足球运动员");
                break;
            case PING_PONG:
                System.out.println("乒乓球运动员");
                break;
            case BASKETBALL:
                System.out.println("篮球运动员");
                break;
        }

    }
}
