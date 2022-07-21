package com.fairverse.mie_api.gameuser;

import com.fairverse.mie_api.security.PasswordEncoder;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GameUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final GameUserRepository gameUserRepository;
    private final PasswordEncoder passwordEncoder;

    public List<GameUser> getUsers() {
        return gameUserRepository.findAll();
    }

    public String getUser(Map<String, String> body) {
        if (body.containsKey("mail") && body.containsKey("password")) {
            Optional<GameUser> user1Optional = gameUserRepository.findGameUserByMail(body.get("mail"));
            if (user1Optional.isPresent()) {
                GameUser gameUser = user1Optional.get();
                String hashedPassword = hashPassword(body.get("password"));
                if (gameUser.getPassword().equals(hashedPassword)) {
                    return gameUser.toStringC();
                } else {
                    throw new IllegalStateException("Parola hatalı!");
                }
            } else {
                throw new IllegalStateException("Bu email adresi ile kullanıcı bulunamadı!");
            }
        } else {
            throw new IllegalStateException("RequestBody missing fields!");
        }
    }

    public String register(GameUser gameUser) {
        Optional<GameUser> gameUserOptional = gameUserRepository.findGameUserByMail(gameUser.getEmail());
        if (gameUserOptional.isPresent()) {
            throw new IllegalStateException("Bu mail daha önce alınmış!");
        }
        gameUserOptional = gameUserRepository.findGameUserByUsername(gameUser.getUsername());
        if (gameUserOptional.isPresent()) {
            throw new IllegalStateException("Bu username daha önce alınmış!");
        }
        gameUser.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(gameUser.getPassword()));
        gameUserRepository.save(gameUser);
        return "Kullanıcı kayıt edildi";
    }

    private String hashPassword(String password) {
        try {
            byte[] encodedhash = MessageDigest.getInstance("SHA-256").digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder(2 * encodedhash.length);
            for (byte b : encodedhash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append("0");
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return gameUserRepository.findGameUserByMail(email).orElseThrow(() -> new
                UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }
}
