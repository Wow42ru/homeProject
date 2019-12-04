package Lesson17.tasks.task2;

import com.sun.java.accessibility.util.Translator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TransactionsTask {
    public static void main(String[] args) {

        Account account1 = new Account("781057000", 2000);
        Account account2 = new Account("445259743", 1900);
        Account account3 = new Account("692343156", 30000);


        Transaction transaction1 = new Transaction("935b0b96-a93c-4ba3-b340-7e27918b77a1", 340, account1);
        Transaction transaction2 = new Transaction("6b724ed9-7718-4203-8e70-2728d09b7bf3", 560, account1);
        Transaction transaction3 = new Transaction("f0f44e9c-56cc-450e-bf92-3b0ff1497e49", 1000, account2);
        Transaction transaction4 = new Transaction("ffa44fa0-52c9-490d-aeee-d4d810cd37f0", 500, account2);
        Transaction transaction5 = new Transaction("5faef7f5-eca2-4a67-a228-fe4d8b59a2c8", 1600, account3);
        Transaction transaction6 = new Transaction("c38209a7-dbb7-47ee-b162-1953e29eab94", 2000, account3);
        Transaction transaction7 = new Transaction("c38209a7-dbb7-47ee-b162-1953e29eab94", 3400, account3);

        // дано:
        Stream<Transaction> transactionStream = Stream.of(transaction1, transaction2, transaction3,
                transaction4, transaction5, transaction6, transaction7);


        // результат: мапа, где String - номер (number) аккаунта, Long - сумма транзакций (sum) по аккаунту
        Map<String, Long> sumOfTransactionByEachAccount = transactionStream
                .collect(Collectors.groupingBy(p -> p.getAccount().getNumber(), Collectors.summingLong(Transaction::getSum)));// только сумма транзакций
        System.out.println(sumOfTransactionByEachAccount);



        Stream<Transaction> transactionStream2 = Stream.of(transaction1, transaction2, transaction3,
                transaction4, transaction5, transaction6, transaction7);

        Map<String, Long> sumOfTransactionByEachAccount2 = transactionStream2
                .collect(Collectors.groupingBy(p->p.getAccount().getNumber()
                        ,(Collectors.groupingBy(Transaction::getAccount,Collectors.summingLong(Transaction::getSum)))))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        s-> (Long) s.getValue()
                                .entrySet()
                                .stream()
                                .mapToLong(p -> p.getKey().getBalance()+p.getValue())
                                .findFirst()
                                .getAsLong()))// итоговый баланс
        ;


        System.out.println(sumOfTransactionByEachAccount2);//
    }
}
