package edu.umb.cs.cs680.hw08;


import static org.junit.Assert.*;

import java.util.HashMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ObserverPatternTest {

	private StockEventObservable stockObservable;
	private PieChartObserver pieChartObserver;
	private TableObserver tableObserver;
	private ThreeDObserver threeDObserver;
	private HashMap<String, Float> stocksMap, stocksMapForPieChart, stocksMapForTable, stocksMapFor3D,
			bondsMapForPieChart, bondsMapForTable, bondsMapFor3D;
	private BondEventObservable bondObservable;
	private HashMap<String, Float> testMap, bondsMap;
	private Observable observable;

	@Before
	public void setUp() {
		testMap = new HashMap<String, Float>();
		testMap.put("1", 110.5f);
		testMap.put("2", 150.2f);
		testMap.put("3", 200.1f);

		pieChartObserver = new PieChartObserver();
		tableObserver = new TableObserver();
		threeDObserver = new ThreeDObserver();

		bondObservable = new BondEventObservable();
		bondObservable.addObserver(pieChartObserver);
		bondObservable.addObserver(tableObserver);
		bondObservable.addObserver(threeDObserver);

		stockObservable = new StockEventObservable();
		stockObservable.addObserver(pieChartObserver);
		stockObservable.addObserver(tableObserver);
		stockObservable.addObserver(threeDObserver);

	}

	@Test
	public void notifyObserversTestForStockEvent() {
		stocksMap = stockObservable.getStocks();
		System.out.println(
				"********************** StockEvent for Pie Chart, Table, 3D Observers : **********************");
		stockObservable.changeQuote("2", 300.5f);
		stocksMapForPieChart = pieChartObserver.getPieChartForStocks();
		stocksMapForTable = tableObserver.getTableStocks();
		stocksMapFor3D = threeDObserver.getThreeDStocks();

		assertEquals(stocksMapForPieChart, stocksMap);
		assertEquals(stocksMapForTable, stocksMap);
		assertEquals(stocksMapFor3D, stocksMap);
	}

	@Test
	public void notifyObserversTestForBondEvent() {
		bondsMap = bondObservable.getBondMap();
		System.out.println(
				"********************** BondEvent for Pie Chart, Table, 3D Observers : **********************");
		bondObservable.changeQuote("2", 300.5f);
		bondsMapForPieChart = pieChartObserver.getPieChartForBonds();
		bondsMapForTable = tableObserver.getTableBonds();
		bondsMapFor3D = threeDObserver.getThreeDBonds();

		assertEquals(bondsMapForPieChart, bondsMap);
		assertEquals(bondsMapForTable, bondsMap);
		assertEquals(bondsMapFor3D, bondsMap);
	}

	@Test
	public void notifyObserversTestForNullArgsInBonds() {
		HashMap<String, Float> expectedBondMap = bondObservable.getBondMap();

		bondObservable.notifyObservers();
		HashMap<String, Float> actualBondMap = bondObservable.getBondMap();

		assertEquals(actualBondMap, expectedBondMap);
	}

	@Test
	public void notifyObserversTestForNullArgsInStocks() {
		HashMap<String, Float> expectedStockMap = stockObservable.getStocks();

		stockObservable.notifyObservers();
		HashMap<String, Float> actualStockMap = stockObservable.getStocks();

		assertEquals(actualStockMap, expectedStockMap);
	}

	@Test
	public void notifyObserversTestForNullObservableObjects() {
		observable = new Observable();
		Boolean actualObservable = observable.hasChanged();

		observable.notifyObservers();
		Boolean expectedObservable = observable.hasChanged();

		assertEquals(actualObservable, expectedObservable);
	}

	@Test
	public void testNull() {
		observable = new Observable();
		Boolean actualObservable = observable.hasChanged();

		observable.notifyObservers(null);
		Boolean expectedObservable = observable.hasChanged();

		assertEquals(actualObservable, expectedObservable);

	}

	@After
	public void tearDown() {
		observable = null;
		stocksMap = null;
		bondsMap = null;
		testMap = null;

		tableObserver = null;
		pieChartObserver = null;
		threeDObserver = null;

		bondObservable = null;
		bondsMapFor3D = null;
		bondsMapForPieChart = null;
		bondsMapForTable = null;

		stockObservable = null;
		stocksMapFor3D = null;
		stocksMapForPieChart = null;
		stocksMapForTable = null;
	}

}
