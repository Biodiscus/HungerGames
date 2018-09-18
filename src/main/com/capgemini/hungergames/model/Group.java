package main.com.capgemini.hungergames.model;

public enum Group {
    DISTRICT, CAREERS, NONE;

    @Override
    public String toString() {
        String upper = this.name().substring(0, 1);
        String lower = this.name().substring(1).toLowerCase();

        return upper + lower;
    }
}
