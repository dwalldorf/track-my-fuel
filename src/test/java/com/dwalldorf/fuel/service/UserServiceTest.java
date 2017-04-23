package com.dwalldorf.fuel.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import com.dwalldorf.fuel.BaseTest;
import com.dwalldorf.fuel.exception.ResourceConflictException;
import com.dwalldorf.fuel.form.user.RegisterForm;
import com.dwalldorf.fuel.model.HasUser;
import com.dwalldorf.fuel.model.User;
import com.dwalldorf.fuel.repository.UserRepository;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceTest extends BaseTest {

    @Mock
    private SessionService mockSessionService;

    @Mock
    private PasswordEncoder mockPasswordEncoder;

    @Mock
    private UserRepository mockUserRepository;

    private UserService userService;

    @Override
    protected void setUp() {
        this.userService = new UserService(mockSessionService, mockPasswordEncoder, mockUserRepository);
    }

    @Test
    public void testRegister_EncodesPassword() {
        final String password = "password";
        RegisterForm registerForm = new RegisterForm()
                .setPassword(password);

        userService.register(registerForm);

        verify(mockPasswordEncoder).encode(eq(password));
    }

    @Test
    public void testRegister_SavesUser() {
        RegisterForm registerForm = new RegisterForm()
                .setUsername("username")
                .setEmail("mail@host.tld")
                .setPassword("password");

        userService.register(registerForm);

        verify(mockUserRepository).save(any(User.class));
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByUsername_ThrowsUsernameNotFound() {
        final String username = "username";
        when(mockUserRepository.findByUsername(username)).thenReturn(null);

        userService.loadUserByUsername(username);
    }

    @Test
    public void testLoadUserByUsername() {
        final String username = "john";
        final String email = "john@host.tld";
        final String password = "ac7f27cb2be83415cad48f2257c00287c9d8e91fe4da9058a52f9319055dfeb3f6b403bf5c6e2a59";

        User mockDbUser = new User()
                .setUsername(username)
                .setEmail(email)
                .setPassword(password);
        when(mockUserRepository.findByUsername(eq(username))).thenReturn(mockDbUser);

        UserDetails userDetails = userService.loadUserByUsername(username);

        assertEquals(username, userDetails.getUsername());
        assertEquals(password, userDetails.getPassword());
        assertEquals(1, userDetails.getAuthorities().size());
    }

    @Test(expected = ResourceConflictException.class)
    public void testVerifyOwner_ThrowsResourceConflictException() {
        final Long currentUserId = 123L;
        final Long objectUserId = 321L;

        HasUser mockObject = mock(HasUser.class);
        when(mockObject.getUser()).thenReturn(new User().setId(objectUserId));
        when(mockSessionService.getCurrentUserId()).thenReturn(currentUserId);

        userService.verifyOwner(mockObject);
    }

    @Test
    public void testVerifyOwner() {
        final Long currentUserId = 123L;
        final Long objectUserId = currentUserId;

        HasUser mockObject = mock(HasUser.class);
        when(mockObject.getUser()).thenReturn(new User().setId(objectUserId));
        when(mockSessionService.getCurrentUserId()).thenReturn(currentUserId);

        userService.verifyOwner(mockObject);
    }
}