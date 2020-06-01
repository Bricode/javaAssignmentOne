package passignmentone;

/**
 * @author Blake Chalmers
 *
 */
public class Google implements Comparable<Google>{

	/**
	 *  Declaring the parameters needed to store a google object
	 */
	private String date;
	private Double open;
	private Double high;
	private Double low;
	private Double close;
	private Double adjClose;
	private long volume;
	
	/**
	 * The main method of the Google class, This receives and declares all 
	 * the parameters and declares them as local variables
	 * 
	 */
	public Google(String date, Double open, Double high, Double low, Double close, Double adjClose, long volume) {
		this.date = date;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.adjClose = adjClose;
		this.volume = volume;
	}
	/**
	 * These are the methods to generate the get and set methods for the google class
	 * @return
	 */
	public String getDate() {
		return date;
	}
	/**
	 * These are the methods to generate the get and set methods for the google class
	 * @return
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * These are the methods to generate the get and set methods for the google class
	 * @return
	 */
	public Double getOpen() {
		return open;
	}	
	/**
	 * These are the methods to generate the get and set methods for the google class
	 * @return
	 */
	public void setOpen(Double open) {
		this.open = open;
	}	
	/**
	 * These are the methods to generate the get and set methods for the google class
	 * @return
	 */
	public Double getHigh() {
		return high;
	}
	/**
	 * These are the methods to generate the get and set methods for the google class
	 * @return
	 */
	public void setHigh(Double high) {
		this.high = high;
	}	
	/**
	 * These are the methods to generate the get and set methods for the google class
	 * @return
	 */
	public Double getLow() {
		return low;
	}
	/**
	 * These are the methods to generate the get and set methods for the google class
	 * @return
	 */
	public void setLow(Double low) {
		this.low = low;
	}
	/**
	 * These are the methods to generate the get and set methods for the google class
	 * @return
	 */
	public Double getClose() {
		return close;
	}	
	/**
	 * These are the methods to generate the get and set methods for the google class
	 * @return
	 */
	public void setClose(Double close) {
		this.close = close;
	}	
	/**
	 * These are the methods to generate the get and set methods for the google class
	 * @return
	 */
	public Double getAdjClose() {
		return adjClose;
	}	
	/**
	 * These are the methods to generate the get and set methods for the google class
	 * @return
	 */
	public void setAdjClose(Double adjClose) {
		this.adjClose = adjClose;
	}	
	/**
	 * These are the methods to generate the get and set methods for the google class
	 * @return
	 */
	public long getVolume() {
		return volume;
	}	
	/**
	 * These are the methods to generate the get and set methods for the google class
	 * @return
	 */
	public void setVolume(long volume) {
		this.volume = volume;
	}
	/**
	 * This is the toString override method, it prints out the date of a record
	 */
	@Override
	public String toString() {		
		return getDate().toString();
	}
	/**
	 * The override method for the comparator class.
	 */
	@Override
	public int compareTo(Google o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
