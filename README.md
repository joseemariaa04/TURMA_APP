📱 TURMA

TURMA es una app de Android para gestionar tus proyectos y tareas en tiempo real. Todo se sincroniza usando MockAPI, así que puedes crear, actualizar y eliminar proyectos y tareas sin complicaciones.

✨ Qué hace

Proyectos: Crea y organiza tus proyectos de manera sencilla.

Tareas: Añade tareas a tus proyectos, márcalas como hechas o bórralas cuando ya no las necesites.

Sincronización en tiempo real: Todo lo que cambies se guarda automáticamente en MockAPI y se ve al instante.

Interfaz simple y limpia: Pensada para que navegar por la app sea rápido y fácil.

🛠 Tecnologías usadas

Kotlin / Java

MockAPI para guardar los datos

Arquitectura MVVM

Librerías: Retrofit, Gson, LiveData, RecyclerView

📸 Cómo se ve

(Aquí puedes poner capturas de pantalla de la app, una del listado de proyectos y otra de las tareas dentro de un proyecto)

🚀 Cómo probarla

Clona este repositorio:

git clone https://github.com/tu-usuario/turma.git


Abre el proyecto en Android Studio.

Asegúrate de tener conexión a internet (MockAPI necesita estar activo).

Ejecuta la app en un emulador o en tu celular.

🔧 Configuración de MockAPI

Ve a MockAPI
 y crea un proyecto.

Crea endpoints para Proyectos y Tareas.

Copia la URL base de tu proyecto y reemplázala en la app:

const val BASE_URL = "https://tu-mockapi-url.mockapi.io/"

💡 Cómo usar TURMA

Abre la app.

Crea un proyecto nuevo.

Añade tareas dentro de ese proyecto.

Marca tareas como completadas o bórralas si ya no sirven.

Todo se guarda automáticamente y se refleja en tiempo real.

🤝 Contribuciones

Si quieres mejorar la app, puedes:

Hacer un fork del repo.

Crear tu rama para cambios:

git checkout -b feature/nueva-funcionalidad


Hacer commit de tus cambios y enviar un pull request.

📝 Licencia

MIT. Haz lo que quieras con el código, solo menciona al autor 😉
