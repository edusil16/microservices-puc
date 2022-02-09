package br.com.boaentrega.modelo.dominio;


import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

@Getter
public enum Perfil {

    ADMIN("1"),
    FORNECEDOR("2"),
    CLIENTE("3"),
    MOTORISTA("4");
    
    private static final Map<String, Perfil> perfilValor = new HashMap<>();

    private String perfil;

    Perfil(String perfil) {
        this.perfil = perfil;
    }
    
    static {
        for (Perfil perfil : Perfil.values()) {
            perfilValor.put(perfil.getPerfil(), perfil);
        }
    }
    
    public static Perfil obterPerfilPorValor(String perfil) {
        return perfilValor.get(perfil);
    }
    
}
