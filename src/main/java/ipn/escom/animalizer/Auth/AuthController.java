package ipn.escom.animalizer.Auth;

import ipn.escom.animalizer.models.request.LoginRequest;
import ipn.escom.animalizer.models.request.RegisterRequest;
import ipn.escom.animalizer.models.response.AuthResponse;
import ipn.escom.animalizer.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest request){
        return  ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("register")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid RegisterRequest request){
        return  ResponseEntity.ok(authService.register(request));
    }
}
