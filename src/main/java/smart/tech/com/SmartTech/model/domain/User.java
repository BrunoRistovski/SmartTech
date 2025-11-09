package smart.tech.com.SmartTech.model.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import smart.tech.com.SmartTech.model.enumerations.Role;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonManagedReference
    @JoinColumn(nullable = false)
    @OneToOne( cascade = CascadeType.ALL)
    private ShoppingCart shoppingCart;

    @JsonBackReference
    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    @Column(nullable = false)
    private boolean isAccountNonExpired = true;
    @Column(nullable = false)
    private boolean isAccountNonLocked = true;
    @Column(nullable = false)
    private boolean isCredentialsNonExpired = true;
    @Column(nullable = false)
    private boolean isEnabled = true;

    public User(String username, String firstName, String lastName, LocalDateTime createdAt, String phoneNumber, String email, String password, Role role, ShoppingCart shoppingCart, List<Order> orders) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdAt = createdAt;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.role = role;
        this.shoppingCart = shoppingCart;
        this.orders = orders;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
