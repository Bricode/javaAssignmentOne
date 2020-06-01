package passignmentone;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import javax.swing.JTable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Blake
 * This testing doc is incomplete, as I ran out of time to reconfigure my utilities class to allow for the methods to be tested
 */
class GoogleTest {

	private ArrayList<Google> testArrayList = new ArrayList<Google>();	
	
	@BeforeEach
	void setUp() throws Exception {
		
	}

	@Test
	void getVolume() {
		testArrayList.add(new Google("2020-06-02",200.20,300.35, 100.40, 150.30, 150.30, 1000000 ));
		testArrayList.add(new Google("2020-05-02",300.20,400.35, 200.40, 250.30, 250.30, 2000000 ));
		testArrayList.add(new Google("2020-06-02",130.20,17000.35, 100.40, 120.30, 120.30, 3000000 ));		
		assertEquals(testArrayList.get(0).getVolume(), 1000000);
	}
	@Test
	void getDate() {
		testArrayList.add(new Google("2020-06-02",130.20,17000.35, 100.40, 120.30, 120.30, 3000000 ));	
		testArrayList.add(new Google("2020-05-02",300.20,400.35, 200.40, 250.30, 250.30, 2000000 ));
		assertEquals(testArrayList.get(0).getDate(), "2020-06-02");
	}
	@Test
	void getHigh() {
		testArrayList.add(new Google("2020-06-02",130.20,17000.35, 100.40, 120.30, 120.30, 3000000 ));	
		testArrayList.add(new Google("2020-05-02",300.20,400.35, 200.40, 250.30, 250.30, 2000000 ));
		assertEquals(testArrayList.get(0).getHigh(), 17000.35);
	}
	@Test
	void getLow() {
		testArrayList.add(new Google("2020-06-02",130.20,17000.35, 100.40, 120.30, 120.30, 3000000 ));	
		testArrayList.add(new Google("2020-05-02",300.20,400.35, 200.40, 250.30, 250.30, 2000000 ));
		assertEquals(testArrayList.get(0).getLow(), 100.40);
	}
	@Test
	void getOpen() {
		testArrayList.add(new Google("2020-06-02",130.20,17000.35, 100.40, 120.30, 120.30, 3000000 ));	
		testArrayList.add(new Google("2020-05-02",300.20,400.35, 200.40, 250.30, 250.30, 2000000 ));
		assertEquals(testArrayList.get(1).getOpen(), 300.20);
	}
	@Test
	void getClose() {
		testArrayList.add(new Google("2020-06-02",130.20,17000.35, 100.40, 120.30, 120.30, 3000000 ));	
		testArrayList.add(new Google("2020-05-02",300.20,400.35, 200.40, 250.30, 250.30, 2000000 ));
		assertEquals(testArrayList.get(1).getClose(), 250.30);
	}
	@Test
	void getAdjClose() {
		testArrayList.add(new Google("2020-06-02",130.20,17000.35, 100.40, 120.30, 120.30, 3000000 ));	
		testArrayList.add(new Google("2020-05-02",300.20,400.35, 200.40, 250.30, 250.30, 2000000 ));
		assertEquals(testArrayList.get(0).getAdjClose(), 120.30);
	}
	

}
