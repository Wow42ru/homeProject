import java.util.*;

public class MessageTask {
    public static void countEachPriority(List<Message> messageList) {
        int[] counter = new int[MessagePriority.values().length];

        for (Message m : messageList) {
            counter[m.getPriority().ordinal()]++;
        }
        System.out.println("Распределение сообщений по приорететам:");
        for (int i = 0; i < counter.length; i++) {
            System.out.println(counter[i] + " " + MessagePriority.getPriority(i));

        }
    }

    public static void countEachCode(List<Message> messageList) {

        int[] counter = new int[10];

        for (Message m : messageList) {
            counter[m.getCode()]++;
        }
        System.out.println("Распределение сообщений по коду:");
        for (int i = 0; i < counter.length; i++) {
            System.out.println(counter[i] + " сообщений с кодом " + i);

        }
    }

    private static void uniqueMessageCount(List<Message> messageList) {
        Comparator comparator = new PriorityComporator().thenComparing(new CodComparator());

        TreeSet<Message> messageTreeSet = new TreeSet<>(comparator);
        messageTreeSet.addAll(messageList);

        System.out.println("Всего " + messageTreeSet.size() + " уникальных сообщений");
    }

    public static List<Message> uniqueMessagesInOriginalOrder(List<Message> messageList) {

        Comparator comparator = new PriorityComporator().thenComparing(new CodComparator());

        LinkedHashSet<Message> messageLinkedHashSet = new LinkedHashSet<>();
        messageLinkedHashSet.addAll(messageList);
        messageList.clear();
        messageList.addAll(messageLinkedHashSet);

        return messageList;
    }

    public static void removeEach(List<Message> messageList, MessagePriority priority) {//   LOW, MEDIUM, HIGH, URGENT; Priority
        System.out.println(" Массив содержит элементов: " + messageList.size());
        System.out.println(messageList);
        for (int i = 0; i < messageList.size(); i++) {
            if (messageList.get(i).getPriority().equals(priority)) {
                messageList.remove(i);
                i = -1;
            }
        }
        System.out.println(messageList);
        System.out.println(" После выполнения метода массив содержит элементов: " + messageList.size());
    }

    public static void removeOther(List<Message> messageList, MessagePriority priority) {
        System.out.println(" Массив содержит элементов: " + messageList.size());
        System.out.println(messageList);
        for (int i = 0; i < messageList.size(); i++) {
            if (!messageList.get(i).getPriority().equals(priority)) {
                messageList.remove(i);
                i = -1;
            }
        }
        System.out.println(messageList);
        System.out.println(" После выполнения метода массив содержит элементов: " + messageList.size());
    }

    public static void main(String[] args) {
        // вызов методов
        List<Message> messageList = MessageGenerator.generate(30);
        System.out.println(messageList);
        MessageTask.countEachPriority(messageList);
        MessageTask.countEachCode(messageList);
        MessageTask.uniqueMessageCount(messageList);
        System.out.println(MessageTask.uniqueMessagesInOriginalOrder(messageList));
        //   MessageTask.removeEach(messageList, MessagePriority.getPriority(3));
        MessageTask.removeOther(messageList, MessagePriority.getPriority(3));

    }


}

class CodComparator implements Comparator<Message> {

    @Override
    public int compare(Message o1, Message o2) {
        return Integer.compare(o1.getCode(), o2.getCode());
    }
}

class PriorityComporator implements Comparator<Message> {

    @Override
    public int compare(Message o1, Message o2) {
        return Integer.compare(o1.getPriority().ordinal(), o2.getPriority().ordinal());
    }
}