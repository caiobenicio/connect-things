package br.com.thing.entity;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_permission")
public class UserPermission extends BaseEntity<UserPermissionKey> {

    private static final long serialVersionUID = 201602010251L;

}
