package cn.jianchengwang.todo.core.java8.streams.example;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

public class Example3 {

    // 交易员
    @Data
    @AllArgsConstructor
    static class Trader {
        // 姓名
        private String name;
        // 城市
        private String city;
    }

    // 交易
    @Data
    @AllArgsConstructor
    static class Transaction {
        private Trader trader;
        // 交易年份
        private int    year;
        // 交易额
        private int    value;
    }

    /**
     *  * (1) 找出2011年发生的所有交易，并按交易额排序（从低到高）。
     *  * (2) 交易员都在哪些不同的城市工作过？
     *  * (3) 查找所有来自于剑桥的交易员，并按姓名排序。
     *  * (4) 返回所有交易员的姓名字符串，按字母顺序排序。
     *  * (5) 有没有交易员是在米兰工作的？
     *  * (6) 打印生活在剑桥的交易员的所有交易额。
     *  * (7) 所有交易中，最高的交易额是多少？
     *  * (8) 找到交易额最小的交易。
     * @param args
     */
    public static void main(String[] args) {

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan  = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        // (1) 找出2011年发生的所有交易，并按交易额排序（从低到高）。
        transactions.stream()
                .filter(i -> i.getYear() == 2011).sorted(Comparator.comparing(Transaction::getValue))
                .forEach(i -> System.out.println(i));

        // (2) 交易员都在哪些不同的城市工作过？
        transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .collect(Collectors.toList())
                .forEach(i -> System.out.println(i));

        // (3) 查找所有来自于剑桥的交易员，并按姓名排序。
        transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted((a, b) -> a.getName().compareTo(b.getName()))
                .collect(Collectors.toList())
                .forEach(i -> System.out.println(i));

        // (4) 返回所有交易员的姓名字符串，按字母顺序排序。
        transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .distinct()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList())
                .forEach(i -> System.out.println(i));

        // (5) 有没有交易员是在米兰工作的？
        transactions.stream()
                .map(Transaction::getTrader)
                .anyMatch(trader -> trader.getCity().equals("Milan"));

        // (6) 打印生活在剑桥的交易员的所有交易额。
        Integer sumTransaction = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .reduce(0, Integer::sum);
        System.out.println(sumTransaction);

        // (7) 所有交易中，最高的交易额是多少？
        OptionalInt maxTransaction = transactions.stream()
                .mapToInt(Transaction::getValue)
                .max();
        System.out.println(maxTransaction.isPresent()?maxTransaction.getAsInt():"not found");

        // (8) 找到交易额最小的交易。
        Optional<Transaction> minTransaction = transactions.stream()
                .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);
        System.out.println(minTransaction.isPresent()?minTransaction:"not found");

    }
}
