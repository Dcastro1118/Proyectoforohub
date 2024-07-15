package com.proyecto.forohub.modelos;
import com.proyecto.forohub.dto.topicos.DatosActualizarTopico;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

    @Table(name = "topicos")
    @Entity(name = "topicos")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(of = "id")
    public class Topico {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "uid_usuario")
        private Usuario usuario;

        @Column(name = "nombre_curso")
        private String curso;
        private String titulo;
        private String mensaje;
        private LocalDateTime fechaDeCreacion;
        private Boolean status = Boolean.TRUE;


        public void updateTopicData(DatosActualizarTopico datosActualizarTopico) {
            if (datosActualizarTopico.titulo() != null) {
                this.titulo = datosActualizarTopico.titulo();
            }
            if (datosActualizarTopico.mensaje() != null) {
                this.mensaje = datosActualizarTopico.mensaje();
            }
        }

        public void disableTopic() {
            this.status = false;
        }
    }

