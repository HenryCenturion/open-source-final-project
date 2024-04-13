## Tabla de Contenido
</div>
<br>
<br>

</div>

<div style="margin-left: 80px;">

- 5.1. [Software Configuration Management](#51-software-configuration-management) <br>
  - 5.1.1. [Software Development Environment Configuration](#511-software-development-environment-configuration) <br>
  - 5.1.2. [Source Code Management](#512-source-code-management) <br>
  - 5.1.3. [Source Code Style Guide & Conventions](#513-source-code-style-guide--conventions) <br>
  - 5.1.4. [Software Deployment Configuration](#514-software-deployment-configuration) <br>
- 5.2. [Landing Page, Services & Applications Implementation](#52-landing-page-services--applications-implementation) <br>
  - 5.2.1. [Sprint 1](#521-sprint-1) <br>
    - 5.2.1.1. [Sprint Planning 1](#5211-sprint-planning-1) <br>
    - 5.2.1.2. [Sprint Backlog 1](#5212-sprint-backlog-1) <br>
    - 5.2.1.3. [Development Evidence for Sprint Review](#5213-development-evidence-for-sprint-review) <br>
    - 5.2.1.4. [Testing Suite Evidence for Sprint Review](#5214-testing-suite-evidence-for-sprint-review) <br>
    - 5.2.1.5. [Execution Evidence for Sprint Review](#5215-execution-evidence-for-sprint-review) <br>
    - 5.2.1.6. [Services Documentation Evidence for Sprint Review](#5216-services-documentation-evidence-for-sprint-review) <br>
    - 5.2.1.7. [Software Deployment Evidence for Sprint Review](#5217-software-deployment-evidence-for-sprint-review) <br>
    - 5.2.1.8. [Team Collaboration Insights during Sprint](#5218-team-collaboration-insights-during-sprint) <br>
- 5.3. [Validation Interviews](#53-validation-interviews) <br>
  - 5.3.1. [Diseño de Entrevistas](#531-diseño-de-entrevistas) <br>
  - 5.3.2. [Registro de Entrevistas](#532-registro-de-entrevistas) <br>
  - 5.3.3. [Evaluaciones según heurísticas](#533-evaluaciones-según-heurísticas) <br>
- 5.4. [Video About-the-Product](#54-video-about-the-product) <br>
 
</div>

<br>
<br>
<br>
<br>
<br>

  ## 5.1. Software Configuration Management

  ### 5.1.1. Software Development Environment Configuration

  - **Project Management:**
  - Trello es una plataforma que facilita la colaboración y gestión de proyectos, permitiendo alcanzar niveles más altos de productividad. Se adapta tanto a entornos de oficinas en edificios corporativos como al trabajo remoto.

- **Requierements Management:**
  - Utilizaremos Pivotal Tracker como herramienta para gestionar de manera eficaz los user stories y las epics, garantizando que todos los miembros del equipo puedan acceder a ellos de forma sencilla e intuitiva.

- **Product UX/UI Design:**
  - Figma será la plataforma que emplearemos para llevar a cabo la creación de wireframes y la página de aterrizaje de nuestro proyecto, de manera fácil, eficaz y con gran eficiencia. Esto nos permitirá trabajar de manera más fluida y efectiva en el diseño y desarrollo del proyecto.

- **Software Development:**
  - **IDE:** JetBrain
  - **HTML:** es la sigla que representa HyperText Markup Language, el cual es el lenguaje de marcado empleado en la construcción de páginas web.
  - **CSS:** En castellano, “Cascading Style Sheets”, o CSS, es un lenguaje de diseño gráfico que se utiliza para dar formato y diseñar presentaciones de documentos estructurados que están escritos en HTML.

### 5.1.2. Source Code Management

  - Utilizamos GitHub como plataforma y sistema de control de versiones.
  - En la parte del GitFlow, estamos utilizando las ramas "main" y el "develop", siendo la primera la rama padre de todas, y la segunda la rama hija de la anterior mencionada.
  - Además, hemos agregado 7 ramas externas, dependientes de la rama "develop", el cual se ditribuyen en:
    
      - document/caratula
      - document/capitulo-I
      - document/capitulo-II
      - document/capitulo-III
      - document/capitulo-IV
      - document/capitulo-V
      - feature/landing-page
      
   - Estas han sido creadas con el proposito de dividir el trabajo en los 5 capitulos junto con la caratula y el landing page de acuerdo a las historias de usuario creadas.
   - Las ramas externas creadas son hijas de la rama "develop", por lo que, al culminar con el trabajo en la etapa de desarrollo, estas se uniran mediante el Pull Request para asi tener todo el informe en un solo documento.

<p align="center">
  <img src="https://raw.githubusercontent.com//HenryCenturion//open-source-final-project//document/caratula//images//main.png" alt="UPC">
</p>

  - Link del GitHub: github.com/HenryCenturion/open-source-final-project

  ### 5.1.3. Source Code Style Guide & Conventions

Las directrices para el formato del código fuente y las convenciones de codificación se establecen siguiendo las reglas específicas para el lenguaje HTML y el estilo de hoja de estilos que corresponde al CSS. Las principales pautas que consideraremos se presentarán de la siguiente manera:

- **Declaración del tipo de documento HTML:**
  - `<!DOCTYPE html>`

- **Inclusión del idioma en la etiqueta html:**
  - `<html lang="en">`

- **Inclusión de las etiquetas esenciales dentro de la etiqueta html:**
  - `<head>` y `<body>`

- **Etiqueta Head:**
  
  - Dentro de la sección `<head>` de una página web, que no es visible para el usuario al visitarla, es común incluir diversas etiquetas, como metadatos (`<meta>`), enlaces para archivos y fuentes, entre otros elementos adicionales fundamentales incorporar a esta estructura principal que determina el título de la página web, que se define con `<title>`.

- **Etiqueta Body:**
  
    - La etiqueta `<body>`, que es la parte visual de la página web, engloba todos los elementos que el usuario puede ver y experimentar. En esta sección, se utilizan las etiquetas `<header>`, `<main>` y `<footer>` para estructurar el contenido de la siguiente manera:
    - La etiqueta `<header>` se emplea para la barra de navegación superior de la página.
    - Etiqueta `<main>`: Contiene el contenido central e informativo de la página.
    - Etiqueta `<footer>`: Se utiliza al final de la página para proporcionar información adicional o como soporte técnico y enlaces a redes sociales, entre otros.
    
- **Cierre de Etiquetas:**

  - Cada etiqueta se cierra de manera apropiada, como por ejemplo `<title></title>`.
  - En algunos casos, como `<meta charset="UTF-8">`, la etiqueta no requiere un cierre explícito.
  - Etiquetas como `<ul>`, `<section>`, `<input>`, `<label>`, pueden incluir atributos como class e id.

- **Hoja de Estilo - Styles:**

  - En “styles.css”, se define el estilo que se aplicará a “index.html”.
  - Se utilizan propiedades y valores predefinidos dentro de {} para modificar el aspecto de los elementos.

- **Diseño Web Responsivo:**

  - Esencial para que el contenido sea legible en computadoras, tabletas y teléfonos.
  - Se logra con @media screen para diferentes anchos de pantalla.
  
### 5.1.4. Software Deployment Configuration

La implementación de un software abarca los pasos, procesos y actividades necesarios para poner a disposición de los usuarios que visiten nuestro sitio web una actualización o un sistema. La configuración para desplegar la solución de manera satisfactoria para cada uno de nuestros productos digitales se llevará a cabo a través de un servidor (Git), junto con el uso de GitHub. Esto implica que cada archivo .html y .css será subido a un repositorio, permitiendo la configuración de cualquier parte del código de nuestra página web. Posteriormente, se ejecutará en un servidor de prueba para verificar el correcto funcionamiento y detectar posibles errores en el código. El proceso de implementación de un software consta de tres fases principales: preparación, pruebas y despliegue.

  ## 5.2. Landing Page, Services & Applications Implementation

  

  ### 5.2.1. Sprint 1
  
  #### 5.2.1.1. Sprint Planning 1

  | **Sprint #** | Sprint 1 |
|---------------|----------|
| **Sprint Planning Background** |  |
| Date         | 2024-04-13 |
| Time          | 12:16 AM |
| Location     | Reunion virtual mediante videoconferencia. |
| Prepared by | Mendez Lopez, Sebastian Alonso |
| Attendees (to planning meeting) | Mendez Lopez, Sebastian Alonso / Atencio Castillo, John Alexis / Centurion Quintana, Henry Manuel / Mamani Silva, Francis Daniel / Frisancho Levano, Sebastian Mathias  |
| **Sprint n - 1 Review Summary** | No hay resumen del sprint anterior, debido a que este es el primer sprint que se elabora. |
| **Sprint n - 1 Retrospective Summary** | No hay retrospectiva del sprint anterior, debido a que este es el primer sprint que se elabora. |
| **Sprint Goal & User Stories ** | |
| Sprint n Goal | La implementacion correcta de la primera version del landing page. Consideramos que, en una escala de medicion, hemos cumplido un 8. |
| Sprint n Velocity | 5 |
| Sum of Story Points | 5 |

  #### 5.2.1.2. Sprint Backlog 1
  
   | **ID** | **Title**                        | **ID of Assignment** | **Title of Assignment**                                     | **Description**                                                                                   | **Estimation (Hours)** | **Assigned by**       | **Status**      |
|-------------------------------|-----------------------------------|-------------------------------------|------------------------------------------------|---------------------------------------------------------------------------------------------------|------------------------|----------------------|-----------------|
| US23                          | Conocer acerca de la aplicacion web    | T01                                 | Crear seccion "Acerca de"       | Desarrollar el codigo en html, css y JS de la seecion "Acerca de"     | 1                      | Sebastián Méndez     | Done       |
| US24                            |   Conocer los planes de la aplicacion web                                | T02                                 | Crear seccion "Planes"       | Desarrollar el codigo en html, css y JS de la seecion "Planes"                             | 1                      | Henry Centurion     | Done       |
| US25                          |      Ver informacion de los desarrolladores                               | T03                                 | Crear seccion "Us"              |    Desarrollar el codigo en html, css y JS de la seecion "Us"                | 1                      | John Atencio     | Done       |
| US26                          | Leer preguntas frecuentes            | T04                                 | Crear seccion "Preguntas"             | Desarrollar el codigo en html, css y JS de la seecion "Preguntas"                                                           | 1                      | Francis Mamani     | Done           |
| US27                              |    Contactarse con el soporte                           | T05                                 | Crear seccion "Contact"     |Desarrollar el codigo en html, css y JS de la seecion "Contact"                           | 1                      | Sebastián Frisancho     | Done     |

  #### 5.2.1.3. Development Evidence for Sprint Review
  | Repository                       | Branch    | Commit Id                                 | Commit Message       | Commit Message Body | Commited on (Date) |
  |-------------------------------|-----------------------------------|-------------------------------------|------------------------------------------------|---------------------------------------------------------------------------------------------------|------------------------|
  | github.com/HenryCenturion/open-source-final-project                       | feature/landing-page    | ebdbd1e                                 | Initial commit       | Empty | 13/04/2024 |
  | github.com/HenryCenturion/open-source-final-project                       | feature/landing-page    | 326f262                                 | feat: Implement landing-page plan section       | Add plan section in landing-page for visitor visual | 13/04/2024 |
  | github.com/HenryCenturion/open-source-final-project                       | feature/landing-page    | 66e031b                                 | feat: Update index.html       | Implemented the members section | 13/04/2024 |
  | github.com/HenryCenturion/open-source-final-project                       | feature/landing-page    | bced3d2                                 | feat: Update images folder       | Add arrow | 13/04/2024 |
  

  #### 5.2.1.4. Testing Suite Evidence for Sprint Review

 - No se realizo testing, debido a que es la primera implementacion del landing page.

  #### 5.2.1.5. Execution Evidence for Sprint Review

 - Hemos aprovechado el sprint para trabajar de manera colaborativa, cumplir con nuestros objetivos, y mejorar tanto nuestros productos como nuestros procesos de trabajo.

 - Vista del landing page:

Header:
       
  <p align="center">
  <img src="https://raw.githubusercontent.com//HenryCenturion//open-source-final-project//document/caratula//images//header.png" alt="UPC">
</p>
        Mid:
     <br>
     <br>
  <p align="center">
  <img src="https://raw.githubusercontent.com//HenryCenturion//open-source-final-project//document/caratula//images//mid.png" alt="UPC">
</p>
        Plans:
     <br>
     <br>
  <p align="center">
  <img src="https://raw.githubusercontent.com//HenryCenturion//open-source-final-project//document/caratula//images//plans.png" alt="UPC">
</p>
        Us:
     <br>
     <br>
  <p align="center">
  <img src="https://raw.githubusercontent.com//HenryCenturion//open-source-final-project//document/caratula//images//us.png" alt="UPC">
</p>
        Preguntas:
     <br>
     <br>
  <p align="center">
  <img src="https://raw.githubusercontent.com//HenryCenturion//open-source-final-project//document/caratula//images//questions.png" alt="UPC">
</p>
      Footer:
     <br>
     <br>
  <p align="center">
  <img src="https://raw.githubusercontent.com//HenryCenturion//open-source-final-project//document/caratula//images//footer.png" alt="UPC">
</p>
  
         
- Link del video: https://drive.google.com/drive/folders/1OJ-TDtUbmeLt5Tx_Ce6XCEpjLG8vTKXu?usp=drive_link

#### 5.2.1.6. Services Documentation Evidence for Sprint Review

 - No se realizo services, debido a que el landing page no presenta servicios.

#### 5.2.1.7. Software Deployment Evidence for Sprint Review

  - No se utilizo ningun despliegue, debido a que el landing page no presenta evidencia de deployment por ser estatico.

#### 5.2.1.8. Team Collaboration Insights during Sprint

- **Primera Seccion (header)**: Se implemento la distribucion del header y su transparencia con el fondo. (Sebastian Mendez)
- **Segunda Seccion**: Se realizaron los layouts de la pagina. (Henry Centurion)
- **Tercera Seccion**: Se implementaron imagenes y titulos respectivos en cada seccion. (John Atencio)
- **Cuarta Seccion**: Se implementaron contenidos e informacion que poseen los cuerpos, reemplazando los Lorem Ipsum. (Francis Mamani)
- **Footer**: Se realizo el pie de pagina junto a las ideas que esta posee. (Sebastian Frisancho)

## 5.3. Validation Interviews



  ### 5.3.1. Diseño de Entrevistas


  
  ### 5.3.2. Registro de Entrevistas

  

  ### 5.3.3. Evaluaciones según heurísticas

  

  ## 5.4. Video About-the-Product
