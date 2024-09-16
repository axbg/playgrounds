package app.service;

import org.springframework.stereotype.Service;

@Service
public class BaseService {
    public void test() {
        this.privateTest();
    }

    private void privateTest() {
        System.out.println("testing");
    }
}
