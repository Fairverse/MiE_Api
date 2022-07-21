package com.fairverse.mie_api.gameuser;

import com.fairverse.mie_api.security.PasswordEncoder;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
        Optional<GameUser> user1Optional = gameUserRepository.findGameUserByEmail(body.get("email"));
        if (user1Optional.isPresent()) {
            GameUser gameUser = user1Optional.get();
            if (passwordEncoder.matches(body.get("password"), gameUser.getPassword())) {
                return gameUser.toStringC();
            } else {
                throw new IllegalStateException("Parola hatalı!");
            }
        } else {
            throw new IllegalStateException("Bu email adresi ile kullanıcı bulunamadı!");
        }
    }

    public String register(GameUser gameUser) {
        Optional<GameUser> gameUserOptional = gameUserRepository.findGameUserByEmail(gameUser.getEmail());
        if (gameUserOptional.isPresent()) {
            throw new IllegalStateException("Bu mail daha önce alınmış!");
        }
        gameUserOptional = gameUserRepository.findGameUserByUsername(gameUser.getUsername());
        if (gameUserOptional.isPresent()) {
            throw new IllegalStateException("Bu username daha önce alınmış!");
        }
        gameUser.setPassword(passwordEncoder.encode(gameUser.getPassword()));
        gameUserRepository.save(gameUser);
        return "Kullanıcı kayıt edildi";
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return gameUserRepository.findGameUserByEmail(email).orElseThrow(() -> new
                UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }
}
