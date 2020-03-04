package br.com.thing.entity;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "client_permission")
public class ClientPermission extends BaseEntity<ClientPermissionKey> {
    private static final long serialVersionUID = 201602010251L;

}
