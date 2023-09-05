package by.pvt.newmultiproject.api.enums;

public enum TypeStuff {
    FOOD("Еда"),
    INSTRUMENTS("Стройматериалы"),
    COSMETICS("Косметика");

    private String role;

    TypeStuff(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return  role;
    }
}
