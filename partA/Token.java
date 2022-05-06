public abstract class Token 
{
    protected String tokenValue;

    /* Have the derived class implement this method
     * to display the value of the object 
     * using a 3 character-long String.
     * i.e. what you want shown as output on screen
     */
    public abstract String toString();

    public boolean match(Token other)
    {
        return this.tokenValue.equals(other.tokenValue);
    }
}
