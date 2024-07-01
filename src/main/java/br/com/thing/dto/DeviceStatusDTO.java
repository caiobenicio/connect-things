package br.com.thing.dto;

public class DeviceStatusDTO {
    private Long device;
    private Long user;
    private Long board;
    private Long pin;
    private int action;

    public Long getDevice() {
        return device;
    }
    public void setDevice(Long device) {
        this.device = device;
    }
    public Long getUser() {
        return user;
    }
    public void setUser(Long user) {
        this.user = user;
    }
    public Long getBoard() {
        return board;
    }
    public void setBoard(Long board) {
        this.board = board;
    }
    public Long getPin() {
        return pin;
    }
    public void setPin(Long pin) {
        this.pin = pin;
    }
    public int getAction() {
        return action;
    }
    public void setAction(int action) {
        this.action = action;
    }
}
