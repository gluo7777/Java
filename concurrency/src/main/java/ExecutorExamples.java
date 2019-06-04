import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.concurrent.Executors.*;

// reference: https://www.baeldung.com/java-executor-service-tutorial
public class ExecutorExamples {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // helper
        ExecutorService executor1 = newFixedThreadPool(10);

        // full
        ExecutorService executor2 =
                new ThreadPoolExecutor(1, 10, 0L, TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<Runnable>());

        // async void
        executor1.execute(() -> System.out.println("Yo!!"));

        // async result
        Future<String> msg1 = executor1.submit(() -> "123");
        System.out.println(msg1.get());

        // first success
        System.out.println(executor1.invokeAny(buildCallables()));

        // all
        buildRunnables().forEach(executor1::execute);
        List<Future<String>> futures = executor1.invokeAll(buildCallables());

        // shutting down
        // not immediate, stop taking new work, only shutdown after all threads finished
//        executor1.shutdown();
        // tries to terminate immediately and returns list of non completed
//        buildRunnables(10000).forEach(executor1::execute);
//        List<Runnable> notCompleted = executor1.shutdownNow();
//        System.out.printf("%d tasks failed to complete on time.", notCompleted.size());
        // try shutdown, then wait before terminate
        System.out.println("How many tasks can I complete in 10 seconds?");
        buildRunnables(10000).forEach(executor1::execute);
        executor1.shutdown();
        try {
            if(!executor1.awaitTermination(3, TimeUnit.SECONDS)){
                executor1.shutdownNow();
            }
        }catch (InterruptedException e){
            System.err.println(e);
            executor1.shutdownNow();
        }
    }

    private static void asyncTasks(ExecutorService executor1) {
        List<Runnable> callables = buildRunnables();
        callables.stream().forEach(executor1::execute);
    }
    private static List<Runnable> buildRunnables(){return buildRunnables(1000);}
    private static List<Runnable> buildRunnables(int sleepBase) {
        return IntStream.range(0, 10).mapToObj(i -> (Runnable) () -> {
            try {
                TimeUnit.MILLISECONDS.sleep((int) (Math.random() * sleepBase + 1));
                System.out.println("Completed task " + i);
            } catch (InterruptedException e) {
                System.err.println(e);
                System.out.println("Failed to complete task " + i);
            }
        }).collect(Collectors.toList());
    }

    private static void callables(ExecutorService executor1) {
        List<Callable<String>> callables = buildCallables();
        try {
            executor1.invokeAll(callables);
        } catch (InterruptedException e) {
            System.err.println(e);
            System.err.printf("Failed to execute %d tasks\n", callables.size());
        }
    }

    private static List<Callable<String>> buildCallables() {
        return IntStream.range(0, 10).mapToObj(ExecutorExamples::buildCallable).collect(Collectors.toList());
    }

    private static Callable<String> buildCallable(int i) {
        return () -> {
            try {
                TimeUnit.MILLISECONDS.sleep((int) (Math.random() * 1000 + 1));
                return "Task " + i + " completed successfully";
            } catch (InterruptedException e) {
                System.err.println(e);
                return "Failed to complete task " + i;
            }
        };
    }
}
