package midianet.friend.adm.domain;

public enum QuestionType {
    STARTWITH, ENDWITH, CONTAINS, EQUALS;

    public String toString() {
        switch (this) {
            case STARTWITH:
                return "Começa com";
            case ENDWITH:
                return "Termina com";
            case CONTAINS:
                return "Contêm";
            default:
                return "Exatamente";
        }
    }

}