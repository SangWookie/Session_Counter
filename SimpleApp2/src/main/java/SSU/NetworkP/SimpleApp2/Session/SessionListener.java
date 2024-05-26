package SSU.NetworkP.SimpleApp2.Session;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;


@WebListener
public class SessionListener implements HttpSessionListener {
    private static Logger logger = LoggerFactory.getLogger(SessionListener.class);
    private static final AtomicInteger sessionCount = new AtomicInteger(0);

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
        String filePath = "./session_count.txt"; // 파일 경로를 적절히 수정하세요
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write("Total sessions: " + getTotalSessionCount() + "\n");
        } catch (IOException e) {
            logger.error("Error writing session count to file", e);
        }
    }
}
