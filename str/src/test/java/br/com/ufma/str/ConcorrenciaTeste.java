package br.com.ufma.str;

import br.com.ufma.str.dto.TransacaoDtoIn;
import br.com.ufma.str.service.ContaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class ConcorrenciaTeste {
    @Autowired
    private ContaService contaService;
    @Test
    public void testConcurrentTransactions() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Long conta1 = 1L;
        Long conta2 = 2L;
        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                try {
                    TransacaoDtoIn dto = new TransacaoDtoIn();
                    dto.setTipoTransacao(1L);
                    dto.setValorTransacao(new BigDecimal(100));
                    contaService.criarTransacao(dto, conta1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
    }
}
