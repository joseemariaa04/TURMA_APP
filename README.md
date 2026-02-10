📱 TaskFlow - Gestor de Proyectos y Tareas
<div align="center">
Mostrar imagen
Mostrar imagen
Mostrar imagen
Gestiona tus proyectos y tareas en tiempo real con una interfaz moderna y fluida
📥 Descargar APK • 📖 Documentación • 🚀 Empezar
</div>

✨ Características

🎯 Creación de Proyectos - Organiza tu trabajo en proyectos independientes
✅ Gestión de Tareas - Añade, edita y completa tareas en tiempo real
🔄 Sincronización en Tiempo Real - Todos los cambios se reflejan instantáneamente
🎨 Interfaz Material Design - Diseño moderno y fácil de usar
📊 Seguimiento de Progreso - Visualiza el estado de tus proyectos
🌐 API REST - Integración con MockAPI para almacenamiento en la nube

📸 Screenshots
<div align="center">
  <img src="screenshots/home.png" width="250" />
  <img src="screenshots/projects.png" width="250" />
  <img src="screenshots/tasks.png" width="250" />
</div>
🛠️ Tecnologías Utilizadas

Lenguaje: Kotlin
Arquitectura: MVVM (Model-View-ViewModel)
Networking: Retrofit + OkHttp
Asincronía: Coroutines + Flow
Inyección de Dependencias: Hilt/Dagger
UI: Jetpack Compose / XML con ViewBinding
Backend: MockAPI (https://mockapi.io)

📋 Requisitos Previos

Android Studio Arctic Fox o superior
SDK de Android 24 o superior (Android 7.0+)
Conexión a Internet
Cuenta en MockAPI (gratuita)

🚀 Instalación
Clonar el Repositorio
bashgit clone https://github.com/tu-usuario/taskflow-android.git
cd taskflow-android
Configurar MockAPI

Crea una cuenta en MockAPI.io
Crea un nuevo proyecto
Crea los siguientes endpoints:

/projects con los campos: id, name, description, createdAt
/tasks con los campos: id, projectId, title, completed, createdAt


Copia tu URL de API y pégala en local.properties:

propertiesBASE_URL=https://[tu-id].mockapi.io/api/v1/
Compilar y Ejecutar
bash./gradlew assembleDebug
```

O simplemente abre el proyecto en Android Studio y presiona ▶️ Run.

## 💡 Uso

1. **Crear un Proyecto**
   - Toca el botón flotante `+`
   - Ingresa el nombre y descripción
   - Presiona "Crear"

2. **Añadir Tareas**
   - Selecciona un proyecto
   - Toca `+` para crear una nueva tarea
   - Completa los detalles y guarda

3. **Marcar como Completada**
   - Toca el checkbox junto a cualquier tarea
   - El estado se sincroniza automáticamente

## 📁 Estructura del Proyecto
```
app/
├── data/
│   ├── api/          # Servicios de Retrofit
│   ├── model/        # Modelos de datos
│   └── repository/   # Capa de repositorio
├── di/               # Módulos de inyección de dependencias
├── ui/
│   ├── projects/     # Pantalla de proyectos
│   ├── tasks/        # Pantalla de tareas
│   └── components/   # Componentes reutilizables
└── util/             # Utilidades y extensiones
🔌 Endpoints de la API
MétodoEndpointDescripciónGET/projectsObtener todos los proyectosPOST/projectsCrear nuevo proyectoPUT/projects/:idActualizar proyectoDELETE/projects/:idEliminar proyectoGET/tasksObtener todas las tareasPOST/tasksCrear nueva tareaPUT/tasks/:idActualizar tareaDELETE/tasks/:idEliminar tarea
🤝 Contribuir
Las contribuciones son bienvenidas. Para cambios importantes:

Haz fork del proyecto
Crea una rama para tu feature (git checkout -b feature/AmazingFeature)
Commit tus cambios (git commit -m 'Add some AmazingFeature')
Push a la rama (git push origin feature/AmazingFeature)
Abre un Pull Request

📝 Licencia
Este proyecto está bajo la Licencia MIT. Ver el archivo LICENSE para más detalles.
👨‍💻 Autor
Tu Nombre

GitHub: @tu-usuario
LinkedIn: Tu Perfil

🙏 Agradecimientos

MockAPI por proporcionar una API REST gratuita
Material Design por las guías de diseño
La comunidad de Android por el constante apoyo


<div align="center">
⭐ Si te gusta este proyecto, dale una estrella en GitHub ⭐
</div>
