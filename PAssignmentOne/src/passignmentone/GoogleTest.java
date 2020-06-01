package passignmentone;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import javax.swing.JTable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GoogleTest {

	private ArrayList<Google> testArrayList = new ArrayList<Google>();

	
	
	@BeforeEach
	void setUp() throws Exception {
		
	}

	@Test
	void dateSearchtest() {
		testArrayList.add(new Google("2020-06-02",200.20,300.35, 100.40, 150.30, 150.30, 1000000 ));
		testArrayList.add(new Google("2020-05-02",300.20,400.35, 200.40, 250.30, 250.30, 2000000 ));
		testArrayList.add(new Google("2020-06-02",130.20,17000.35, 100.40, 120.30, 120.30, 3000000 ));
		
		assertEquals(testArrayList.get(0).getVolume(), 1000000);
	}
	
	

}
