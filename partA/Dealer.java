/** Driver program that creates a complete Deck
  * of 52 cards, shuffles the cards and deals one
  * card at a time until the deck is empty
  */
public class Dealer
{
    public static void main(String[] args)
    {
        Deck d = new Deck();
        System.out.println(d);

        d.shuffle();
        System.out.println(d);

        Card c;
        int count = 0;
        while (d.getCardCount() > 0)
        {
            c = d.deal();
            System.out.println("#" + (count+1)+" Dealt: " + c);
            count++;
        }   
    }
}
