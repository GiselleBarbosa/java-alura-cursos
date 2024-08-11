import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class GeradorDeArquivo {
    public void criarArquivoJson(Endereco endereco) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter registro = new FileWriter(endereco.cep() + ".json");
        registro.write(gson.toJson(endereco));
        registro.close();
    }
}
