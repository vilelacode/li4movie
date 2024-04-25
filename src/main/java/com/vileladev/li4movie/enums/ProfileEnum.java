package com.vileladev.li4movie.enums;

public enum ProfileEnum {
    ADMIN("ADMIN"),
    USER("USER");

    private String nome;

    ProfileEnum(String nome) {
        this.nome = nome;
    }

    public static ProfileEnum getProfileEnum(String nome) {
        for (ProfileEnum profileEnum : ProfileEnum.values()) {
            if (profileEnum.name().contains(nome.toUpperCase())) {
                return profileEnum;
            }
         }
        return null;
    }

    public static String getProfileName(String perfil) {
        if(perfil.toLowerCase().contains("adm")) {
            return "ADMIN";
        } else if (perfil.toLowerCase().contains("usuar")) {
            return "USER";
        }
        throw new IllegalArgumentException("Perfil não encontrado");
    }


}
