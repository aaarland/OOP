/**
 * Boardable interface
 * Only required method is isVisible
 * @author aarl2934
 *
 */
public interface Boardable {
	/**
	 * isVisible returns a true or false 
	 * value based on if the Boardable element
	 * should be visible
	 * @return true or false.
	 */
	public boolean isVisible();
	/**
	 * share checks when the Boardable element shares the same tile as the this Boardable element 
	 * @param elem 
	 * @return true when the Boardables can share space
	 */
	public boolean share(Boardable elem);
	/**
	 * Each Boardable element need their own toString()
	 * @return the elements toString, i.e "?", ".", "*"
	 */
	public String toString();
}
