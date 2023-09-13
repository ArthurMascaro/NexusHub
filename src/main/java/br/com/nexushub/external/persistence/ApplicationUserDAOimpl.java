package br.com.nexushub.external.persistence;

import br.com.nexushub.usecases.account.gateway.ApplicationUserDAO;
import br.com.nexushub.usecases.account.model.ApplicationUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ApplicationUserDAOimpl implements ApplicationUserDAO {

    @Value("${queries.sql.application-user.insert.application-user}")
    private String registerNewUserQuery;

    @Value("${queries.sql.application-user.select.application-user-by-id}")
    private String findUserByIdQuery;

    @Value("${queries.sql.application-user.select.application-user-by-username}")
    private String findUserByUsernameQuery;

    private JdbcTemplate jdbcTemplate;

    private final PasswordEncoder passwordEncoder;

    public ApplicationUserDAOimpl(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ApplicationUser registerNewUser(ApplicationUser user) {
       UUID userId = UUID.randomUUID();

        jdbcTemplate.update(registerNewUserQuery, rs-> {
            rs.setObject(1, userId);
            rs.setString(2, user.getName());
            rs.setString(3, user.getUsername());
            rs.setString(4, passwordEncoder.encode(user.getPassword()));
            rs.setBoolean(5, true);
            rs.setBoolean(6, true);
            rs.setBoolean(7, true);
            rs.setBoolean(8, true);
        });

        return user.newInstanceWithId(userId);
    }

    @Override
    public Optional<ApplicationUser> findUserById(UUID userId) {
        try {
            ApplicationUser user = jdbcTemplate.queryForObject(findUserByIdQuery,
                    this::mapperApplicationUserFromRs, userId);

            if (Objects.isNull(user)) {
                throw new IllegalStateException();
            }

            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<ApplicationUser> findUserByUsername(String username) {
        try {
            ApplicationUser user = jdbcTemplate.queryForObject(findUserByUsernameQuery,
                    this::mapperApplicationUserFromRs, username);

            if (Objects.isNull(user)) {
                throw new IllegalStateException();
            }

            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private ApplicationUser mapperApplicationUserFromRs(ResultSet rs, int rowNum) throws SQLException {
        UUID id = (UUID) rs.getObject("id");
        String name = rs.getString("name");
        String username = rs.getString("username");
        String password = rs.getString("password");
        boolean isAccountNonExpired = rs.getBoolean("is_account_non_expired");
        boolean isAccountNonLocked = rs.getBoolean("is_account_nonLocked");
        boolean isCredentialsNonExpired = rs.getBoolean("is_credentials_non_expired");
        boolean isEnabled = rs.getBoolean("is_enabled");

        return ApplicationUser.createWithAllArguments(id, name, username, password, isAccountNonExpired,
                isAccountNonLocked, isCredentialsNonExpired, isEnabled);
    }
}
