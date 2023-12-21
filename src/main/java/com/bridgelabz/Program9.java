package com.bridgelabz;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Program9 {

    public static void main(String[] args) {
        // Create a ConcurrentLinkedQueue
        ConcurrentLinkedQueue<String> concurrentQueue = new ConcurrentLinkedQueue<>();

        // Create and start multiple producer threads
        Thread producerThread1 = new Thread(() -> produce(concurrentQueue, "Message 1"));
        Thread producerThread2 = new Thread(() -> produce(concurrentQueue, "Message 2"));

        producerThread1.start();
        producerThread2.start();

        // Create and start a consumer thread
        Thread consumerThread = new Thread(() -> consume(concurrentQueue));

        consumerThread.start();

        // Wait for all threads to finish
        try {
            producerThread1.join();
            producerThread2.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void produce(ConcurrentLinkedQueue<String> queue, String message) {
        // Add an element to the queue
        queue.offer(message);

        // Print the current thread and the updated queue
        System.out.println(Thread.currentThread().getName() + " produced: " + message);
    }

    private static void consume(ConcurrentLinkedQueue<String> queue) {
        // Simulate some processing time
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Poll an element from the queue (returns null if the queue is empty)
        String message = queue.poll();

        // Print the current thread and the consumed message
        System.out.println(Thread.currentThread().getName() + " consumed: " + message);
    }
}

