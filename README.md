# Proyecto foro hub - David Castro(Estudiante de alura latam)

Hola! muchas gracias por visitar este repositorio, en esta ocasion les traigo un proyecto de backend de un foro, en el cual existen diferentes endpoints en los cuales podremos hacer diversas operaciones.
Este es un proyecto realizado para el programa ONE de Oracle en cooperacion con Alura Latam.

Documentación de Endpoints

# Controlador de Autenticación

-Iniciar Sesión
El endpoint POST /autenticar/ingresar permite a los usuarios autenticarse en el sistema proporcionando su nombre de usuario y contraseña. Si la autenticación es exitosa, se devuelve un token JWT válido que puede ser utilizado para acceder a recursos protegidos en la API.

-Registrar Nuevo Usuario
El endpoint POST /autenticar/registrar permite registrar un nuevo usuario en el sistema. Se deben proporcionar un nombre de usuario y una contraseña válidos para crear una cuenta nueva. Una vez registrado, el endpoint devuelve un token JWT para la autenticación del nuevo usuario.

# Controlador de Tópicos

-Publicar Nuevo Tópico
El endpoint POST /topicos permite a los usuarios publicar un nuevo tópico en el foro. Se deben enviar datos como el ID del usuario, el curso relacionado, el título y el contenido del tópico. Una vez publicado, el endpoint devuelve los detalles del tópico creado, incluyendo su identificador único.

-Obtener Todos los Tópicos
El endpoint GET /topicos recupera todos los tópicos disponibles en el sistema, proporcionando una lista paginada ordenada por fecha de creación descendente. Este endpoint es útil para listar todos los temas activos en el foro.

-Obtener Tópico por ID
El endpoint GET /topicos/{id} permite recuperar un tópico específico identificado por su ID único. Proporciona detalles detallados del tópico, incluyendo el ID del usuario que lo creó, el curso asociado, el título del tópico, su contenido y la fecha de creación.

-Actualizar Tópico
El endpoint PUT /topicos/{id} permite actualizar los detalles de un tópico existente identificado por su ID. Los datos actualizados incluyen el curso, el título y el contenido del tópico. Una vez actualizado, el endpoint devuelve los detalles actualizados del tópico. Si no se encuentra el topico se devuelve el error 404.

-Eliminar Tópico
El endpoint DELETE /topicos/{id} elimina lógicamente un tópico existente identificado por su ID. Esta acción marca el tópico como inactivo en el sistema, pero conserva los datos históricos. Si el tópico no se encuentra, el endpoint devuelve un error 404.


Muchas gracias y saludos cordiales!
