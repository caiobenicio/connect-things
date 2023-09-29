package br.com.thing.entity;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import br.com.thing.utils.BaseKey;

@Embeddable
public class PortDeviceKey extends BaseKey {

    private static final long serialVersionUID = 201602010536L;

    @Column(name = "device_id", length = 11, nullable = false)
    private Long deviceId;
    
    @Column(name = "port_id", length = 11, nullable = false)
    private Long portId;

    
    public PortDeviceKey() {
    }

    public PortDeviceKey(Long deviceId, Long portId) {
		super();
		this.deviceId = deviceId;
		this.portId = portId;
	}

    
	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public Long getPortId() {
		return portId;
	}

	public void setPortId(Long portId) {
		this.portId = portId;
	}

	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((this.deviceId == null) ? 0 : this.deviceId.hashCode());
        result = prime * result + ((this.portId == null) ? 0 : this.portId.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!super.equals(obj)) {
            return false;
        }

        if (this.getClass() != obj.getClass()) {
            return false;
        }

        PortDeviceKey other = (PortDeviceKey) obj;

        if (this.deviceId == null) {
            if (other.deviceId != null) {
                return false;
            }
        } else if (!this.deviceId.equals(other.deviceId)) {
            return false;
        }

        if (this.portId == null) {
            if (other.portId != null) {
                return false;
            }
        } else if (!this.portId.equals(other.portId)) {
            return false;
        }

        return true;
    }

}