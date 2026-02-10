# 📱 TURMA Android

<div align="center">

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-0095D5?style=for-the-badge&logo=kotlin&logoColor=white)
![MockAPI](https://img.shields.io/badge/MockAPI-FF6B6B?style=for-the-badge&logo=fastapi&logoColor=white)

**Aplicación Android para gestionar proyectos y tareas en tiempo real**

[Características](#-características) • [Instalación](#-instalación) • [Uso](#-uso) • [API](#-api-endpoints)

![Version](https://img.shields.io/badge/version-1.0.0-blue)
![License](https://img.shields.io/badge/license-MIT-green)
![API](https://img.shields.io/badge/API-24%2B-brightgreen)

</div>

---

## 📋 Descripción

TaskFlow es una aplicación Android moderna que permite crear y gestionar proyectos y tareas en tiempo real. Utiliza MockAPI como backend para proporcionar sincronización instantánea y persistencia de datos en la nube.

## ✨ Características

- 🎯 **Gestión de Proyectos** - Crea, edita y elimina proyectos
- ✅ **Control de Tareas** - Administra tareas asociadas a cada proyecto
- 🔄 **Tiempo Real** - Sincronización automática con la API
- 🎨 **Material Design 3** - Interfaz moderna y adaptable
- 📱 **Responsive** - Compatible con diferentes tamaños de pantalla
- 🌙 **Modo Oscuro** - Soporte para tema claro y oscuro
- 🚀 **Rendimiento Optimizado** - Arquitectura MVVM con Coroutines



## 🛠️ Stack Tecnológico

| Categoría | Tecnología |
|-----------|------------|
| **Lenguaje** | Kotlin |
| **Arquitectura** | MVVM (Model-View-ViewModel) |
| **UI** | Jetpack Compose / XML + ViewBinding |
| **Networking** | Retrofit 2, OkHttp |
| **Asincronía** | Kotlin Coroutines, Flow |
| **Inyección de Dependencias** | Hilt / Koin |
| **Serialización** | Gson / Moshi |
| **Backend** | MockAPI |


## 🚀 Instalación

### 1. Clonar el repositorio
```bash
git clone https://github.com/tu-usuario/taskflow-android.git
cd taskflow-android
```

### 2. Configurar MockAPI

1. Ve a [MockAPI.io](https://mockapi.io) y crea una cuenta gratuita
2. Crea un nuevo proyecto
3. Crea los siguientes recursos:


### 3. Configurar las variables de entorno

Crea un archivo `local.properties` en la raíz del proyecto y agrega:
```properties
BASE_URL=https://[tu-id-unico].mockapi.io/api/v1/
```

> **Nota:** Reemplaza `[tu-id-unico]` con el ID de tu proyecto en MockAPI

### 4. Sincronizar y compilar
```bash
./gradlew build
```

O desde Android Studio: **Build > Make Project**

### 5. Ejecutar la aplicación

Conecta un dispositivo Android o inicia un emulador y presiona **Run** ▶️

## 💡 Uso

### Crear un Proyecto

1. En la pantalla principal, toca el botón flotante **+**
2. Ingresa el nombre y descripción del proyecto
3. Selecciona un color (opcional)
4. Presiona **Guardar**

### Gestionar Tareas

1. Selecciona un proyecto de la lista
2. Toca **+** para crear una nueva tarea
3. Completa el título, descripción y prioridad
4. Presiona **Crear**

### Marcar Tareas como Completadas

- Toca el checkbox junto a cualquier tarea
- El estado se sincroniza automáticamente con el servidor

### Eliminar Proyectos o Tareas

- Desliza el elemento hacia la izquierda
- O mantén presionado y selecciona **Eliminar**


## 🔌 API Endpoints

### Proyectos

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/projects` | Obtener todos los proyectos |
| `GET` | `/projects/:id` | Obtener proyecto por ID |
| `POST` | `/projects` | Crear nuevo proyecto |
| `PUT` | `/projects/:id` | Actualizar proyecto |
| `DELETE` | `/projects/:id` | Eliminar proyecto |

### Tareas

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/tasks` | Obtener todas las tareas |
| `GET` | `/tasks?projectId=:id` | Obtener tareas por proyecto |
| `POST` | `/tasks` | Crear nueva tarea |
| `PUT` | `/tasks/:id` | Actualizar tarea |
| `DELETE` | `/tasks/:id` | Eliminar tarea |

## 🧪 Testing

Ejecutar los tests unitarios:
```bash
./gradlew test
```

Ejecutar los tests de instrumentación:
```bash
./gradlew connectedAndroidTest
```

## 🤝 Contribuciones

¡Las contribuciones son bienvenidas! Si quieres mejorar este proyecto:

1. Haz un Fork del repositorio
2. Crea una rama para tu feature (`git checkout -b feature/nueva-funcionalidad`)
3. Commit tus cambios (`git commit -am 'Agrega nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Abre un Pull Request

Por favor, asegúrate de:
- Seguir el estilo de código del proyecto
- Agregar tests para nuevas funcionalidades
- Actualizar la documentación si es necesario

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo [LICENSE](LICENSE) para más detalles.
```
MIT License

Copyright (c) 2024 Tu Nombre

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction...
```

## 👨‍💻 Autor

**José María Martín Fernández**

- 🐙 GitHub: [@joseemariaa04](https://github.com/joseemariaa04)
- 💼 LinkedIn: [Tu Perfil](https://linkedin.com/in/tu-perfil)
- 📧 Email: josemariamartin2004@gmail.com
- 🌐 Portfolio: [tuportfolio.com](https://tuportfolio.com)

---

<div align="center">

**⭐ Si este proyecto te fue útil, considera darle una estrella ⭐**

Hecho con ❤️ y ☕

</div>
