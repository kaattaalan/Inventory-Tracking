package com.rm.inventorytracking;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Local server port'unu kullanarak sayfaya request atıyoruz.
 * Welcome mesajını gösterip göstermediğini denetliyoruz.
 * Welcome mesajı, giriş yapan kullanıcıyı karşılayan ekranda gösteriliyor.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void returnDefaultHomepageMessage() throws Exception {
        assertThat(this.testRestTemplate.getForObject("http://localhost:"+port,String.class))
                .contains("Welcome");
    }
}
