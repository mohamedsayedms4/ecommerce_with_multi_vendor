package org.example.remotly.repository;

import org.example.remotly.model.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for {@link VerificationCode} entity.
 * Provides CRUD operations and custom queries related to verification codes.
 *
 * Includes a method to find a verification code entry by email address.
 *
 * Example usage:
 * {@code VerificationCode code = verificationCodeRepository.findByEmail("example@example.com");}
 *
 * @author Mohamed Sayed
 * @since 2025-07-27
 */
@Repository
public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {

    /**
     * Finds a {@link VerificationCode} by the provided email.
     *
     * @param email the email associated with the verification code
     * @return the VerificationCode object if found, or null if not found
     */
    VerificationCode findByEmail(String email);
}
