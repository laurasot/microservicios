package com.laurasoto.springboot.commons.usuarios.modelos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name="usuarios")
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 20)
    private String username;
    private Boolean enabled;
    private String nombre;
    private String apellido;
    @Column(unique = true, length = 100)
    private String email;
    @Column(length = 60)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="usuarios_roles",
            joinColumns = @JoinColumn(name="usuario_id"), inverseJoinColumns= @JoinColumn(name="rol_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id", "rol_id"})})
    private List<Rol> roles;
    private Integer intentos;

}
