import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    @Disabled("Долгий тест (100 итераций по 200мс) — запускать вручную при необходимости")
    void main_shouldFinishWithin22Seconds() throws Exception {
        Main.main(new String[]{});

    }
}