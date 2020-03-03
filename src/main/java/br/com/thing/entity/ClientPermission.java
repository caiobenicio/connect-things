package br.com.thing.entity;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
<<<<<<< HEAD:src/main/java/br/com/thing/entity/UserPermission.java
@Table(name = "user_permission")
public class UserPermission extends BaseEntity<UserPermissionKey> {
=======
@Table(name = "client_permission")
public class ClientPermission extends BaseEntity<ClientPermissionKey> {
>>>>>>> 27e4d0c87bd3c8a5f6938831f643c1b3711f7351:src/main/java/br/com/thing/entity/ClientPermission.java

    private static final long serialVersionUID = 201602010251L;

}
