package belestiAssignment.kforce;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;

//Given a record of every transaction during a three month period, calculates the reward points earned for each customer per month and total.
public class CalculationHandler {

	//calculates reward point per transaction
	public static int calculatePoint(Double transactionCost) {
		if (transactionCost < 50)
			return 0;
		else if (transactionCost >= 50 && transactionCost <= 100)
			return 50;
		else
			return (int) (2 * transactionCost - 150);
	}

	//calculates reward points per month for a customer
	public static int calculatePointPerMonth(Integer customerId, int month,
			HashMap<Integer, HashMap<LocalDateTime, Double>> customerMap) {
		int point = 0;
		HashMap<LocalDateTime, Double> transactionMap = customerMap.get(customerId);
		for (LocalDateTime key : transactionMap.keySet()) {
			if (key.getMonth().getValue() == month)
				point = (int) (point + calculatePoint(transactionMap.get(key)));
		}
		return point;
	}

	//calculates total reward points for a customer
	public static int calculateTotalPoint(int customerId,
			HashMap<Integer, HashMap<LocalDateTime, Double>> customerMap) {
		int point = 0;
		HashMap<LocalDateTime, Double> transactionMap = customerMap.get(customerId);
		for (LocalDateTime key : transactionMap.keySet()) {
			point = (int) (point + calculatePoint(transactionMap.get(key)));
		}
		return point;
	}

	public static void main(String[] args) {
		LocalDateTime startDate = LocalDateTime.of(2019, Month.JUNE, 1, 19, 00, 00);
		
		HashMap<LocalDateTime, Double> transactionMap1 = new HashMap<LocalDateTime, Double>();
//		HashMap<LocalDateTime, Double> transactionMap2 = new HashMap<LocalDateTime, Double>();
//		HashMap<LocalDateTime, Double> transactionMap3 = new HashMap<LocalDateTime, Double>();

		HashMap<Integer, HashMap<LocalDateTime, Double>> customerMap1 = new HashMap<Integer, HashMap<LocalDateTime, Double>>();
//		HashMap<Integer, HashMap<LocalDateTime, Double>> customerMap2 = new HashMap<Integer, HashMap<LocalDateTime, Double>>();
//		HashMap<Integer, HashMap<LocalDateTime, Double>> customerMap3 = new HashMap<Integer, HashMap<LocalDateTime, Double>>();

		transactionMap1.put(startDate.plusDays(1), 40.0);
		transactionMap1.put(startDate.plusDays(3), 120.0);
		transactionMap1.put(startDate.plusDays(10), 200.0);
		transactionMap1.put(startDate.plusMonths(1), 40.0);
		transactionMap1.put(startDate.plusDays(3).plusMonths(1), 120.0);
		transactionMap1.put(startDate.plusDays(10).plusMonths(1), 200.0);
		transactionMap1.put(startDate.plusMonths(2), 40.0);
		transactionMap1.put(startDate.plusDays(3).plusMonths(2), 120.0);
		transactionMap1.put(startDate.plusDays(10).plusMonths(2), 200.0);

		customerMap1.put(1000, transactionMap1);

		System.out.println(calculatePoint(120.0));
		System.out.println(calculatePointPerMonth(1000, 8, customerMap1));
		System.out.println(calculateTotalPoint(1000, customerMap1));
	}

}
