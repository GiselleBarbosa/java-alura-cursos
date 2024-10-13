package br.com.alura.service;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.domain.Abrigo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.http.HttpResponse;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AbrigoServiceTest {

    private ClientHttpConfiguration client = mock(ClientHttpConfiguration.class);
    private AbrigoService abrigoService = new AbrigoService(client);
    private HttpResponse<String> response = mock(HttpResponse.class);
    private Abrigo abrigo = new Abrigo("Teste", "Rua das Flores", "abrigo@gmail.com", "11988887777");

    @Test
    @DisplayName("Deve verificar quando existem abrigos cadastrados.")
    public void deveVerificarQuandoHaAbrigo() throws IOException, InterruptedException {
        abrigo.setId(0L);
        String expectedAbrigosCadastrados = "Abrigos cadastrados:";
        String expectedIdENome = "0 - Teste";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        ObjectMapper objectMapper = new ObjectMapper();
        String abrigoJson = objectMapper.writeValueAsString(abrigo);
        when(response.body()).thenReturn("[" + abrigoJson + "]");
        when(client.realizarRequisicoesGet(anyString())).thenReturn(response);

        abrigoService.listarAbrigosCadastrados();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actualAbrigosCadastrados = lines[0];
        String actualIdENome = lines[1];

        Assertions.assertEquals(expectedAbrigosCadastrados, actualAbrigosCadastrados);
        Assertions.assertEquals(expectedIdENome, actualIdENome);
    }

    @Test
    @DisplayName("Deve verificar quando não existem abrigos cadastrados.")
    public void deveVerificarQuandoNaoHaAbrigo() throws IOException, InterruptedException {
        abrigo.setId(0L);
        String expected = "Não existem abrigos cadastrados!";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        when(response.body()).thenReturn("[]");
        when(client.realizarRequisicoesGet(anyString())).thenReturn(response);

        abrigoService.listarAbrigosCadastrados();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[0];

        Assertions.assertEquals(expected, actual);
    }
}