package service;

import java.util.*;

/**
 * @author Ali Sharifi
 * <p>ali.sharifi.dev@gmail.com<p>
 */
public class CashDispenserService {
    // map: arzesh esmi + meghdar
    private NavigableMap<Integer, Integer> bills = new TreeMap<>(Collections.reverseOrder());

    public synchronized void load(Map<Integer, Integer> load) {
        load.forEach((val, count) -> bills.merge(val, count, Integer::sum));
        System.out.println("load completed");
    }

    // using greedy algorithm here
    public synchronized List<Integer> dispense(int amount) {
        if (amount <= 0) return null;
        Map<Integer, Integer> working = new TreeMap<>(Collections.reverseOrder());
        working.putAll(bills);
        List<Integer> result = new ArrayList<>();

        int remaining = amount;

        // key arzesh esmi

        for (int key : working.keySet()) {

            int take = Math.min(working.get(key), remaining / key);

            if (take > 0) {
                remaining = remaining - take * key;
                for (int i = 0; i < take; i++) {
                    result.add(key);
                }
            }
            if (remaining == 0) break;
        }
        if (remaining != 0) return null;

        bills.clear();
        bills.putAll(working);
        return result;
    }

    public synchronized int totalCash() {
        return bills.entrySet().stream().mapToInt(e -> e.getKey() * e.getValue()).sum();
    }
}
