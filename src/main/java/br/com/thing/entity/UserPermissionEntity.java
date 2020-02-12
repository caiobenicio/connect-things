package br.com.thing.entity;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.thing.security.UserPermissionKey;

@Entity
@Table(name = "tb_user_permission")
public class UserPermissionEntity extends BaseEntity<UserPermissionKey> {

    private static final long serialVersionUID = 201602010251L;

}
