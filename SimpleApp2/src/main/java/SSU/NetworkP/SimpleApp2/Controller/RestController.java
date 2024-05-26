package SSU.NetworkP.SimpleApp2.Controller;

import SSU.NetworkP.SimpleApp2.Session.SessionListener;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.concurrent.TimeUnit;

@org.springframework.web.bind.annotation.RestController
public class RestController {


    @Value("${hello.guys}")
    private String guys;

    @GetMapping(name = "/")
    public ResponseEntity<String> mainPage(HttpSession session) throws InterruptedException {
        System.out.println(session.getId());
        TimeUnit.SECONDS.sleep(2);

        return ResponseEntity.ok("This is Server #2." + guys);
    }
}
