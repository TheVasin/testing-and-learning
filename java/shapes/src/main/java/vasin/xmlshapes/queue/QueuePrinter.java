/**
 * This class take an object from queue and print it.
 * 
 * 2015, Alexander Vasin <alexander.v.vasin@gmail.com>
 */

package vasin.xmlshapes.queue;

import java.util.concurrent.BlockingQueue;

import vasin.xmlshapes.Errors;

public class QueuePrinter implements Runnable {
    private BlockingQueue<Object> queue;
    
    public QueuePrinter(BlockingQueue<Object> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Object element;
        while (true) {
            try {
                element = queue.take();
                if (element instanceof EndOfQueue) { // terminate if received special object marking the end of the queue
                    break;
                }
                System.out.println(element);
            } catch (InterruptedException e) {
                interrupted();
            }
        }
    }
    
    private void interrupted() {
        Errors.printErrorAndExit(Errors.MESSAGE_INTERRUPTED, Errors.ERROR_INTERRUPTED);
    }
}
