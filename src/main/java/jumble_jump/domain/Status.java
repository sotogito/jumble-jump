package jumble_jump.domain;

public enum Status {
    END("그만하기", false)
    ;

    private String status;
    private boolean active;

    private static final String FORMAT = "[%s]";

    Status(String status, boolean active) {
        this.status = status;
    }

    public String getStatus(){
        return String.format(FORMAT, status);
    }

    public boolean isActive() {
        return active;
    }

}
