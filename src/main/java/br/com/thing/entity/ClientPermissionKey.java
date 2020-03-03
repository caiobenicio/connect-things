package br.com.thing.entity;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import br.com.thing.utils.BaseKey;

@Embeddable
public class ClientPermissionKey extends BaseKey {

    private static final long serialVersionUID = 201602010536L;

    @Column(name = "permission_id", length = 11, nullable = false)
    private Long permissionId;
    
    @Column(name = "client_id", length = 11, nullable = false)
    private Long clientId;

    
    public ClientPermissionKey() {
    }

    public ClientPermissionKey(Long permissionId, Long clientId) {
		super();
		this.permissionId = permissionId;
		this.clientId = clientId;
	}

    
	public Long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((this.permissionId == null) ? 0 : this.permissionId.hashCode());
        result = prime * result + ((this.clientId == null) ? 0 : this.clientId.hashCode());

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

        ClientPermissionKey other = (ClientPermissionKey) obj;

        if (this.permissionId == null) {
            if (other.permissionId != null) {
                return false;
            }
        } else if (!this.permissionId.equals(other.permissionId)) {
            return false;
        }

        if (this.clientId == null) {
            if (other.clientId != null) {
                return false;
            }
        } else if (!this.clientId.equals(other.clientId)) {
            return false;
        }

        return true;
    }

}