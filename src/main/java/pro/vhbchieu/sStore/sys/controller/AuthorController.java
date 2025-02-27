package pro.vhbchieu.sStore.sys.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pro.vhbchieu.sStore.sys.domain.dto.Auth.*;
import pro.vhbchieu.sStore.sys.service.AuthorService;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping("/register")
    public void register(@Valid @RequestBody AccountRequest request) {
        authorService.register(request);
    }

    @PostMapping("/login")
    public TokenResponse login(@Valid @RequestBody LoginDto request) {
        return authorService.login(request);
    }

    @GetMapping("/checkToken/{token}")
    public TokenInfo checkToken(@PathVariable String token) {
        return authorService.checkToken(token);
    }

}
