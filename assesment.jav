// ================
//VERSION: 2021-01-02
// Name of Candidate: Aria Askaryar
//QUESTION 1. Why does this code not work? How do you fix it?
//----------------
/*
Reasoning: 
This code didnt work because The code is trying to remove elements from the 
input List<Integer> called data that are greater than 10. The problem is that 
you cannot modify a List while iterating over it using a for loop, 
as this will result in a 'ConcurrentModificationException' so i replaced the for 
loop with a while loop. To fix this, I used an iterator and its remove() method 
to safely remove elements from the list during iteration.
*/ 
//my solution

//I imported nessesary dependencies and packages 
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

public class Example {
    public List<Integer> removeBigNumbers(List<Integer> data) {
        //changed for loop into while loop
        Iterator<Integer> iterator = data.iterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            if (i > 10) {
                iterator.remove();
            }
        }
        return data;
    }
    //Test class 1
    public static void main(String[] args) {
        // Create a new Example object
        Example example = new Example();

        // Create a list of integers to test the removeBigNumbers method
        List<Integer> numbers = new ArrayList<>();
        numbers.add(5);
        numbers.add(15);
        numbers.add(7);
        numbers.add(12);

        // Call the removeBigNumbers method and print the result
        System.out.println(example.removeBigNumbers(numbers));
    }
}
// Output in compiler is [5, 7]

//---------------------------------------------------------------------------------------------------------------------------------------
// QUESTION 2: Programming Task
// ----------------
// Write a class that implements the following interface, assuming that all methods
// are used with approximately the same frequency.
//-------------------

//my soultion 
import java.util.HashMap;
import java.util.Map;

public class ItemStoreImpl implements ItemStore {

    private Map<String, Item> itemMap;

    public ItemStoreImpl() {
        itemMap = new HashMap<>();
    }

    @Override
    public void put(Item item) {
        itemMap.put(item.getID(), item);
    }

    @Override
    public Item get(String id) {
        return itemMap.get(id);
    }

    @Override
    public void dropAllByTag(String tag) {
        itemMap.values().removeIf(item -> item.getTag().equals(tag));
    }

    @Override
    public int size() {
        return itemMap.size();
    }

    static class ItemImpl implements Item {

        private String id;
        private String tag;

        public ItemImpl(String id, String tag) {
            this.id = id;
            this.tag = tag;
        }

        @Override
        public String getID() {
            return id;
        }

        @Override
        public String getTag() {
            return tag;
        }
    }
}

//test case
public class Example {
    public static void main(String[] args) {
        ItemStore store = new ItemStoreImpl();
        Item item1 = new ItemStoreImpl.ItemImpl("1", "tag1");
        Item item2 = new ItemStoreImpl.ItemImpl("2", "tag2");
        Item item3 = new ItemStoreImpl.ItemImpl("3", "tag1");

        store.put(item1);
        store.put(item2);
        store.put(item3);

        System.out.println(store.size()); // prints 3

        store.dropAllByTag("tag1");

        System.out.println(store.size()); // prints 1
    }
}
//--------------------------------------------------------------------------
// QUESTION 3: Memory Management
// ----------------
// The `SmallMemoryMessageTest` class below passes on our development machines,
// but the client reports that it fails on their 64MB VM.
// 1. Run on a 64M VM and copy the failing stack trace.
// 2. Modify `main()` to work on a 64MB VM.
// 3. Ensure there are no more than one performance warnings.
// To set 64Mb VM, run using `java -Xmx64M SmallMemoryMessageTest`.
// ```
public class SmallMemoryMessageTest {
    private static final int MAX_MESSAGES = 5000;
    private static final int MIN_MESSAGES = 2000;

    public static void main(String[] args) {
        MessageProcessor processor = Util.createMessageProcessor();
        MessageArchiver archiver = Util.createMessageArchiver();
        List<Message> messages = new ArrayList<>();

        for (int i = 0; i < Util.EXPECTED_TOTAL; i++) {
            Message msg = Util.random();
            processor.processMessage(msg);
            messages.add(msg);

            if (messages.size() >= MAX_MESSAGES) {
                archiver.archiveMessages(messages, m -> m.getSubject().startsWith("A"));
                messages.clear();
            }
        }

        if (!messages.isEmpty()) {
            archiver.archiveMessages(messages, m -> m.getSubject().startsWith("A"));
            messages.clear();
        }

        /*
         * DO NOT CHANGE ANYTHING BELOW THIS LINE.
         * PROGRAM MUST EXIT SUCCESSFULLY
         */
        Util.validate();
    }
}

//----------------------------------------------------------------------------------
// QUESTION 4: Debugging
// ----------------
// ```

/*
 The issue with the given code is that the rating instance variable is not initialized
 and its value is null. In the rating() method, the code tries to return the value of
 this uninitialized Integer object, which leads to a NullPointerException.
 To fix the issue, we need to initialize the rating instance variable with some value, 
 for example, we can assign a default value of zero to it. 
 */
package com.moesol.hr.bugs;
public class Bug1 {
    private Integer rating;
    //this is needed so its not null
    public Bug1() {
        rating = 0;
    }
    
    public int rating() {
        return rating;
    }
    
    public static void main(String[] args) {
        System.out.println("rating:" + new Bug1().rating());
    }
}
//----------------------------------------------------------------------------
//QUESTION 5: Wrong Result
// ----------------
// The following program produces inconsistent results. It should always output
// this:
// ```
// counter is 20000

//my solution 
public class WrongAnswer {
    private int counter = 0;
    public static void main(String[] args) {
        new WrongAnswer().run(); 
    }
    private void run() {
        try {
            Thread t1 = new Thread(this::incrementToOnHundred);
            Thread t2 = new Thread(this::incrementToOnHundred);
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            System.out.println("counter is " + counter);
        } catch (InterruptedException e) {
            System.err.println("fatal error, unexpected interrupt exception");
            System.exit(2);  
        }
    }
    private void incrementToOnHundred() {
        for (int i = 0; i < 10_000;i++) {
            doSomeFakeWork(); 
        }  
    }
    // need synchronized
    private synchronized void doSomeFakeWork() {
        counter++;   
    }
}

//output: counter is 20000 //every time

//--------------------------------------------------------
//Bonus

//In Python3
import base64

key = b"MOEBIUS"
encrypted = base64.b64decode("DiArJTs0JzgjJDYgOj0+Y2U7JiBzPiApNCwxczknIGIrOj04PGUzPDAgOSYqLGhfWW1vZWJpdXNtb2ViaXVzbW9lYml1c21vZWJpdXNtb2ViaXVzY2hibEN1c21vZWJpe3RqYWViaXVzbWFlYml1c21vZWhucnltb2VicwoPYhB/Yml1c21hT2JpdXNtb38dFXoMd29lYhYJe2IQZWJnb31nEBltFn9zbW9/YmYJc3dvZWxue2ljaGtIaXV9amhreGl6D211ZWJpe3xkE2ViaXJpamVlbRV1eW11ZWJue31qYWViZGhpInV4b0N1aRITah1zcn13dX9saXVzbWhlaG5yeW1vZWJjdXRjaGpsbnUMEWdqHW57dHdoa2VDdWltYBlic3Vpd3V/eGl1c21vbx0Vegxnb2ViaXV+cG8qYnR4c21gbB5pdXNtaE9iaXJ9Y2hlYm5vaXdoZWJpdXNnb2oeaX9zbW9lYmdyfGNoa2JpdXRHb2ViaXVzbW9lYml1c21vZWJpdXljYW9iaXVzbW9lYmlvWQ==")

key_length = len(key)
decrypted = bytearray()
for i in range(len(encrypted)):
    decrypted.append(encrypted[i] ^ key[i % key_length])

print(decrypted.decode('ascii'))

