package br.com.thing.dto;

import br.com.thing.entity.Device;

public class DevicePortDTO {
    private Long boardId;
    private String boardName;
    private Long portId;
    private String portName;

    private Device device;

    public DevicePortDTO() {
    }

    /**
     * @return Long return the boardId
     */
    public Long getBoardId() {
        return boardId;
    }

    /**
     * @param boardId the boardId to set
     */
    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    /**
     * @return String return the boardName
     */
    public String getBoardName() {
        return boardName;
    }

    /**
     * @param boardName the boardName to set
     */
    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    /**
     * @return Long return the portId
     */
    public Long getPortId() {
        return portId;
    }

    /**
     * @param portId the portId to set
     */
    public void setPortId(Long portId) {
        this.portId = portId;
    }

    /**
     * @return String return the portName
     */
    public String getPortName() {
        return portName;
    }

    /**
     * @param portName the portName to set
     */
    public void setPortName(String portName) {
        this.portName = portName;
    }


    /**
     * @return Device return the device
     */
    public Device getDevice() {
        return device;
    }

    /**
     * @param device the device to set
     */
    public void setDevice(Device device) {
        this.device = device;
    }

}
