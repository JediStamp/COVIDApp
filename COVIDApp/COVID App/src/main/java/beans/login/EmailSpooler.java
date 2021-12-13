package beans.login;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import beans.app.Email;

public class EmailSpooler {

	ExecutorService executor;

    public EmailSpooler() {
        // unlimited number of threads
//        executor = Executors.newCachedThreadPool();
        // limit of 5 threads
        executor = Executors.newFixedThreadPool(5);

    }

    // Receives the email to be processed from a requester
    public void enqueue(Email email) {
        System.out.printf("Creating a worker for recording %s\n", email);
        Runnable worker = new Worker(email);
        executor.execute(worker);
    }

    public void shutdown() {
        executor.shutdown();
        System.out.println("\nThread pool is not accepting new threads.\n");
        while (!executor.isTerminated()) {

        }
        System.out.printf("\nFinished all threads\n");
    }


}
