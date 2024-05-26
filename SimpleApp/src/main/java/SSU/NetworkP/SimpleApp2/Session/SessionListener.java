package SSU.NetworkP.SimpleApp2.Session;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;


@WebListener
public class SessionListener implements HttpSessionListener {
    private static final Logger logger = LoggerFactory.getLogger(SessionListener.class);
    private static final AtomicInteger sessionCount = new AtomicInteger(0);

    private final String FILE_PATH = "./session_count3.log";

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        sessionCount.incrementAndGet();
        HttpSession session = event.getSession();
        logger.error("\n\tSESSION CREATED : {}, TOTAL ACCESSER : {}", session.getId(), getTotalSessionCount());
        writeSessionCountToFile();
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        sessionCount.decrementAndGet();
    }

    public static int getTotalSessionCount() {
        return sessionCount.get();
    }

    private void writeSessionCountToFile() {
//        String FILE_PATH = "./test.txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, false))) {
            writer.println(new Date() + " - Total sessions: " + getTotalSessionCount());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
