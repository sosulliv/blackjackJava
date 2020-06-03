package app;

public class PlayerCard {

	private String player;
	private int cardNum;
    
    public PlayerCard(){}
    
	public PlayerCard(String player, int cardNum){
		this.player = player;
		this.cardNum = cardNum;
    }
    
    
    public String getPlayer() {
        return player;
    }

    public int getCardNum() {
        return cardNum;
    }
    
    @Override
    public String toString(){
		return this.player.toString() + "-" + this.cardNum;
    }

  
}