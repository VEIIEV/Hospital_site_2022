package com.example.Services;

import com.example.Entity.VerificationToken;
import com.example.Repository.VerificationTokenRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.time.LocalDateTime;

@Service
public class VerificationTokenService {

    private static final BytesKeyGenerator DEFAULT_TOKEN_GENERATOR = KeyGenerators.secureRandom(15);
    private static final Charset US_ASCII = Charset.forName("US-ASCII");

    @Value(value = "${secure.token.validity}")
    private int tokenValidityInSeconds;

    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    public VerificationToken createSecureToken(){
        String tokenValue = new String(Base64.encodeBase64URLSafe(DEFAULT_TOKEN_GENERATOR.generateKey()), US_ASCII); // this is a sample, you can adapt as per your security need
        VerificationToken secureToken = new VerificationToken();
        secureToken.setToken(tokenValue);
        secureToken.setExpireAt(LocalDateTime.now().plusSeconds(getTokenValidityInSeconds()));
        //this.saveSecureToken(secureToken);
        return secureToken;
    }


    public void saveSecureToken(VerificationToken token) {
        verificationTokenRepository.save(token);
    }

    public VerificationToken findByToken(String token) {
        return verificationTokenRepository.findByToken(token);
    }

    public void removeToken(VerificationToken token) {
        verificationTokenRepository.delete(token);
    }

    public void removeTokenByToken(String token) {
        verificationTokenRepository.removeByToken(token);
    }

    public int getTokenValidityInSeconds() {
        return tokenValidityInSeconds;
    }
}