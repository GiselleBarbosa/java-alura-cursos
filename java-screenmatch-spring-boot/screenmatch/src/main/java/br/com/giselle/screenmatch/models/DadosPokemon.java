package br.com.giselle.screenmatch.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosPokemon(
        @JsonAlias("name") String nome,
        @JsonAlias("abilities") List<Ability> habilidades,
        @JsonAlias("sprites") Sprites sprites
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Ability(
            @JsonAlias("is_hidden") boolean isHidden,
            @JsonAlias("slot") int slot,
            @JsonAlias("ability") AbilityDetalhes habilidadeDetalhes) {

        public record AbilityDetalhes(
                @JsonAlias("name") String nome,
                @JsonAlias("url") String url) {
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Sprites(
            @JsonAlias("front_default") String frenteDefault

    ) {}
}
