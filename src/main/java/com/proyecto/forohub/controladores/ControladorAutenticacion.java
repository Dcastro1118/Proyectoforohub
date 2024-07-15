package com.proyecto.forohub.controladores;

import com.proyecto.forohub.dto.DatosJWT;
import com.proyecto.forohub.dto.usuarios.DatosAuthUsuario;
import com.proyecto.forohub.modelos.Usuario;
import com.proyecto.forohub.servicios.ServicioAutenticacion;
import com.proyecto.forohub.servicios.ServicioToken;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticar")
public class ControladorAutenticacion {

    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ServicioToken servicioToken;
    @Autowired
    private ServicioAutenticacion servicioAutenticacion;

    // Inciar Sesion
    @PostMapping("/ingresar")
    public ResponseEntity login(@Valid @RequestBody DatosAuthUsuario authUserData) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(authUserData.username(), authUserData.password());

        var authUser = authManager.authenticate(authToken);
        var jwt = servicioToken.generateToken((Usuario) authUser.getPrincipal());

        Object responseBody = new Object() {
            public final int httpStatus = HttpStatus.OK.value();
            public final DatosJWT jwtData = new DatosJWT(jwt);
        };

        return ResponseEntity.ok().body(responseBody);
    }
    @PostMapping("/registrar")
    public ResponseEntity registrar(@Valid @RequestBody DatosAuthUsuario nuevoUsuario) {
        // Aquí deberías validar y procesar la creación del usuario
        Usuario usuario = new Usuario();
        usuario.setUsername(nuevoUsuario.username());
        usuario.setPassword(passwordEncoder.encode(nuevoUsuario.password())); // Asegúrate de encriptar la contraseña antes de guardarla

        // Guardar el usuario en la base de datos
        Usuario usuarioGuardado = servicioAutenticacion.guardarUsuario(usuario);

        // Puedes generar un token JWT automáticamente al crear el usuario si lo necesitas
        String jwt = servicioToken.generateToken(usuarioGuardado);

        Object responseBody = new Object() {
            public final int httpStatus = HttpStatus.CREATED.value();
            public final DatosJWT jwtData = new DatosJWT(jwt);
        };

        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }
}
