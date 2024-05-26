package SSU.NetworkP.SimpleApp2.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private Integer totalRequestCounter = 0;
    @Value("${server.number}")
    private String serverNum;

    @Value("${file.path.request.count}")
    private String FILE_PATH;

    @Value("#{new Integer('${delay.ms}')}")
    private Integer delay;

    @GetMapping(name = "/")
    public ResponseEntity<String> mainPage(HttpSession session) throws InterruptedException {
        this.totalRequestCounter++;
        TimeUnit.MILLISECONDS.sleep(delay);
        writeCounterToFile();
        return ResponseEntity.ok("This is Server #" + serverNum);
    }

    private void writeCounterToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            String logEntry = new Date() + " - Total Request Counter: " + this.totalRequestCounter;
            writer.println(logEntry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}