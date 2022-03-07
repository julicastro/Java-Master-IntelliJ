package com.forms.models.domain;

import java.util.Date;
import java.util.List;

// import javax.validation.Valid;
import javax.validation.constraints.Email;
// import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
// import javax.validation.constraints.Past;
// import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.forms.validation.IdentificadorRegex;
import com.forms.validation.Requerido;
// import org.springframework.format.annotation.DateTimeFormat;

public class Usuario {

    private String identificador; // no se valida xq no está en el form
    /*
     * @Pattern(regexp = "[0-9]{2}[.][\\d]{3}[.][\\d]{3}[-][a-zA-Z]{1}") // "\\d" =
     * del 0 al 9; [A-Z] si solo quiero minusculas
     */
    @IdentificadorRegex // si cambiamos el mensaje conviene hacerlo en properties
    private String idregular;

    // @NotEmpty(message = "El nombre no puede ser vacio")
    private String nombre;

    // @NotEmpty
    @Requerido
    private String apellido;

    @NotBlank
    @Size(min = 3, max = 8) // size solo para String. para numerios es @Min y @Max
    private String username;

    @NotEmpty
    private String password;

    @Requerido
    @Email
    private String email;

    @NotNull
    @Min(5)
    @Max(5000)
    private Integer cuenta;

    @NotEmpty // sirve para calcular size de lista
    private List <Role> roles;

    private Boolean habilitar;

    @NotEmpty
    private String genero;

    private String valorSecreto;

    @NotNull
    // @DateTimeFormat(pattern = "yyyy-MM-dd")
    // @Past// debe ser fecha en el pasado
    @Past // debe ser fecha en futuro
    private Date fechaNacimiento;

    // @Valid
    @NotNull
    private Pais pais;

    public String getNombre() {
        return this.nombre;
    }

    public String getValorSecreto() {
        return valorSecreto;
    }

    public void setValorSecreto(String valorSecreto) {
        this.valorSecreto = valorSecreto;
    }

    public Boolean getHabilitar() {
        return habilitar;
    }

    public void setHabilitar(Boolean habilitar) {
        this.habilitar = habilitar;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getCuenta() {
        return cuenta;
    }

    public void setCuenta(Integer cuenta) {
        this.cuenta = cuenta;
    }

    public String getIdregular() {
        return idregular;
    }

    public void setIdregular(String idregular) {
        this.idregular = idregular;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public List<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    

    public Boolean isHabilitar() {
        return this.habilitar;
    }

    public String getGenero() {
        return this.genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }



}
