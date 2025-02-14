package app;

public class Card {

	private Suit suit;
    private Value value;
    
    public Card(){}
	
	public Card(Suit suit, Value value){
		this.suit = suit;
		this.value = value;
	}
	
	public String toString(){
		return this.suit.toString() + "-" + this.value.toString();
    }
    
    public Suit getSuit(){
		return this.suit;
	}
	
	public Value getValue(){
		return this.value;
	}
	
}