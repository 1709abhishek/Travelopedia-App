package com.travelopedia.fun.customer_service;

import com.travelopedia.fun.customer_service.accounts.models.Account;
import com.travelopedia.fun.customer_service.accounts.repository.AccountsRepository;
import com.travelopedia.fun.customer_service.accounts.service.*;
import com.travelopedia.fun.customer_service.accounts.security.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link AccountsService} class.
 * This class tests the methods and behaviors of the {@link AccountsService} class related to
 * account management, authentication, and authorization, ensuring correct functionality and interaction
 * with mocked dependencies such as repositories, authentication manager, and security utilities.
 */
@ExtendWith(MockitoExtension.class)
public class AccountsServiceTest {

    /**
     * Mocked repository for interacting with account data.
     */
    @Mock
    private AccountsRepository accountsRepository;

    /**
     * Mocked authentication manager for simulating the login process.
     */
    @Mock
    private AuthenticationManager authenticationManager;

    /**
     * Mocked password encoder for simulating password encryption.
     */
    @Mock
    private PasswordEncoder passwordEncoder;

    /**
     * Mocked user details service for managing user-specific details.
     */
    @Mock
    private UserDetailsServiceImpl userDetailsService;

    /**
     * Mocked JWT utility for generating and verifying JWT tokens.
     */
    @Mock
    private JwtUtil jwtUtil;

    /**
     * The instance of the service being tested, which is injected with the mocked dependencies.
     */
    @InjectMocks
    private AccountsService accountsService;

    /**
     * A sample account used in the tests to simulate a new user.
     */
    private Account account;

    /**
     * An existing account used to simulate account update or retrieval.
     */
    private Account existingAccount;

    /**
     * Sets up the test environment by initializing the {@link Account} objects before each test.
     * This method is executed before each test to ensure a clean state.
     */
    @BeforeEach
    void setUp() {
        account = new Account();
        account.setFirstName("Alisha");
        account.setLastName("Walunj");
        account.setEmail("alisha@example.com");
        account.setName("Alisha Walunj");
        account.setPassword("password123");

        existingAccount = new Account();
        existingAccount.setName("Abhishek Jain");
        existingAccount.setEmail("abhishek@example.com");
    }

    /**
     * Tests the {@link AccountsService#loginAccount(Account)} method for failure scenarios.
     * This test simulates an invalid login attempt by mocking the authentication manager to throw an exception.
     */
    @Test
    public void testLoginAccount_Failure() {
        when(authenticationManager.authenticate(any())).thenThrow(new RuntimeException("Invalid credentials"));
        Exception exception = assertThrows(RuntimeException.class, () -> {
            accountsService.loginAccount(account);
        });
        assertEquals("Invalid credentials", exception.getMessage());
    }

    /**
     * Tests the {@link AccountsService#logoutAccount(String)} method.
     * This test verifies that logging out successfully clears the authentication context from the {@link SecurityContextHolder}.
     */
    @Test
    public void testLogoutAccount() {
        accountsService.logoutAccount("jwtToken");
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    /**
     * Tests the {@link AccountsService#isUserLoggedIn()} method when the user is logged in.
     * This test mocks a successful authentication and checks if the method correctly identifies the logged-in state.
     */
    @Test
    public void testIsUserLoggedIn_True() {
        Authentication mockAuthentication = mock(Authentication.class);
        when(mockAuthentication.isAuthenticated()).thenReturn(true);
        SecurityContext mockSecurityContext = mock(SecurityContext.class);
        when(mockSecurityContext.getAuthentication()).thenReturn(mockAuthentication);
        SecurityContextHolder.setContext(mockSecurityContext);
        boolean isLoggedIn = accountsService.isUserLoggedIn();
        assertTrue(isLoggedIn);
    }

    /**
     * Tests the {@link AccountsService#isUserLoggedIn()} method when no user is logged in.
     * This test ensures that the method returns false when there is no authentication context set.
     */
    @Test
    public void testIsUserLoggedIn_False() {
        assertFalse(accountsService.isUserLoggedIn());
    }

    /**
     * Tests the {@link AccountsService#saveUserDetails(String, String, String)} method for saving user details.
     * This test simulates saving a new account and verifies that the save method is invoked with the correct account data.
     */
    @Test
    public void testSaveUserDetails() {
        when(accountsRepository.save(any(Account.class))).thenReturn(account);
        accountsService.saveUserDetails("Algorithm", "Deep", "deep@example.com");
        verify(accountsRepository, times(1)).save(any(Account.class));
        verify(accountsRepository).save(argThat(savedAccount ->
                savedAccount.getName().equals("Deep") &&
                        savedAccount.getEmail().equals("deep@example.com")
        ));
    }

    /**
     * Tests the {@link AccountsService#saveOrUpdateAccount(Account)} method when the account already exists.
     * This test checks that the service updates the existing account rather than creating a new one.
     */
    @Test
    public void saveOrUpdateUserAccount_AccountExists() {
        when(accountsRepository.findByEmail(account.getEmail())).thenReturn(existingAccount);
        when(accountsRepository.save(any(Account.class))).thenReturn(account);
        accountsService.saveOrUpdateAccount(account);
        verify(accountsRepository, times(1)).findByEmail(account.getEmail());
        verify(accountsRepository, times(1)).save(existingAccount);
        assertEquals("Alisha Walunj", existingAccount.getName(), "Account name should be updated.");
    }

    /**
     * Tests the {@link AccountsService#saveOrUpdateAccount(Account)} method when the account does not exist.
     * This test simulates the creation of a new account and verifies that the save method is called with the correct account data.
     */
    @Test
    public void saveOrUpdateUserAccount_AccountDoesNotExist() {
        when(accountsRepository.findByEmail(account.getEmail())).thenReturn(null);
        accountsService.saveOrUpdateAccount(account);
        verify(accountsRepository, times(1)).findByEmail(account.getEmail());
        verify(accountsRepository, times(1)).save(account);
    }
}
