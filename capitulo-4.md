## Tabla de contenidos
<br>
<br>

- 4.1. [Style Guidelines](#41-style-guidelines) <br>
  - 4.1.1. [General Style Guidelines](#411-general-style-guidelines) <br>
  - 4.1.2. [Web Style Guidelines](#412-web-style-guidelines)<br>
- 4.2. [Information Architecture](#42-information-architecture)<br>
  - 4.2.1. [Organization Systems](#421-organization-systems)<br>
  - 4.2.2. [Labeling Systems](#422-labeling-systems)<br>
  - 4.2.3. [SEO Tags and Meta Tags](#423-seo-tags-and-meta-tags)<br>
  - 4.2.4. [Searching Systems](#424-searching-systems)<br>
  - 4.2.5. [Navigation Systems](#425-navigation-systems)<br>
- 4.3. [Landing Page UI Design](#43-landing-page-ui-design)<br>
  - 4.3.1. [Landing Page Wireframe](#431-landing-page-wireframe)<br>
  - 4.3.2. [Landing Page Mock-up](#432-landing-page-mock-up)<br>
- 4.4. [Web Applications UX/UI Design](#44-web-applications-uxui-design)<br>
  - 4.4.1. [Web Applications Wireframes](#441-web-applications-wireframes) <br>
  - 4.4.2. [Web Applications Wireflow Diagrams](#442-web-applications-wireflow-diagrams)<br>
  - 4.4.3. [Web Applications Mock-ups](#443-web-applications-mock-ups) <br>
  - 4.4.4. [Web Applications User Flow Diagrams](#444-web-applications-user-flow-diagrams)<br>
- 4.5. [Web Applications Prototyping](#45-web-applications-prototyping)<br>
- 4.6. [Domain-Driven Software Architecture](#46-domain-driven-software-architecture)<br>
  - 4.6.1. [Software Architecture Context Diagram](#461-software-architecture-context-diagram)<br>
  - 4.6.2. [Software Architecture Container Diagrams](#462-software-architecture-container-diagrams)<br>
  - 4.6.3. [Software Architecture Components Diagrams](#463-software-architecture-components-diagrams)<br>
- 4.7. [Software Object-Oriented Design](#47-software-object-oriented-design)<br>
  - 4.7.1. [Class Diagrams](#471-class-diagrams)<br>
  - 4.7.2. [Class Dictionary](#472-class-dictionary)<br>
- 4.8. [Database Design](#48-database-design)<br>
  - 4.8.1. [Database Diagram](#481-database-diagram)<br>

</div>

<br>
<br>
<br>
<br>
<br>

## 4.1. Style Guidelines

### 4.1.1. General Style Guidelines

**D’Taquito:** Es una aplicación web que brinda apoyo en el proceso de reserva de canchas de fútbol y mesas de billar en Lima Metropolitana. Con ello, los amantes del fútbol y billar pueden encontrar y reservar canchas de forma conveniente y segura, todo desde la comodidad de su navegador web.

- Nuestra audiencia abarca todo el público que desee conseguir un espacio deportivo, ya sea de futbol o billar, para recrearse. Además de otorgar a los dueños de estos espacios un entorno web para una mayor visibilidad de su negocio.

**Mensaje:** Nuestro producto es seguro. A través de nuestra aplicación web podrás adquirir un servicio de alquiler de canchas de futbol y/o mesas de billar, como también un sistema automatizado de reservas para dueños de estos servicios.

**Lenguaje:** Se usa una voz activa, sencilla y clara. 

**Tono:** Informativo y atractivo. 

**Contenido:** Imágenes y colores relacionados a nuestro producto.

**Información:** Imágenes de las canchas y mesas, con su respectiva descripción. Además, tendrás la facilidad de ver los horarios disponibles para las reservas correspondientes. Y en caso de ser dueño, un resumen de reservas diarias como la información de las personas que solicitaron el servicio.

**Tranquilidad:** Comodidad y seguridad que nuestros productos son para personas que deseen un espacio cercano para hacer deporte, como también para personas que deseen automatizar su sistema de reservas y ampliar su visibilidad.


### 4.1.2. Web Style Guidelines

#### 1. Branding
- **Logo:**
El logo de D'taquito debe destacar en todas las páginas web  y se debe colocar en la esquina superior izquierda de la página.  
- **Colores:**
Los colores principales de la marca son el verde y el blanco. El verde debe utilizarse para resaltar elementos importantes y botones de llamada a la acción. Mientras que el blanco se utiliza para el fondo de la página y para el texto principal.
- **Tipografía:**
La tipografía principal es Roboto, con un peso de 400 para el texto principal y 700 para los encabezados. Se permite el uso de fuentes sans-serif alternativas para mejorar la legibilidad en diferentes dispositivos.
#### 2. Layout
- **Responsive Design:**
La página web debe ser completamente responsive, adaptándose a diferentes tamaños de pantalla, desde dispositivos móviles hasta escritorios.
- **Navegación:**
 La barra de navegación debe ser clara y fácil de usar, con opciones de menú desplegables para organizar el contenido. Debe incluir enlaces a las secciones principales de la aplicación, como búsqueda de canchas, reservas, perfil del usuario, etc.
#### 3. Interacción
- **Botones:**
Los botones deben tener un aspecto destacado y ser fácilmente distinguibles como elementos interactivos. Deben utilizar el color verde de la marca para resaltar su importancia.
Se debe utilizar una animación de cambio de color al pasar el cursor sobre los botones para indicar interactividad.
#### 4. Iconografía
- **Íconos:**
Se debe utilizar una colección coherente de íconos en todo el sitio web para mejorar la usabilidad y la comprensión del contenido. Los íconos deben ser claros y reconocibles, utilizando el color verde de la marca cuando sea apropiado.
#### 5. Multimedia
- **Imágenes:**
Se deben utilizar imágenes de alta calidad que complementen el contenido y refuercen la identidad de la marca. Se debe prestar atención a la accesibilidad, asegurándose de que todas las imágenes tengan texto alternativo descriptivo.


## 4.2. Information Architecture

### 4.2.1. Organization Systems

<div style="text-align: justify;">

En D'taquito, aplicaremos diferentes sistemas de organización para gestionar la información de manera eficiente y proporcionar una experiencia de usuario intuitiva. A continuación, se detallan los sistemas de organización que utilizaremos y en qué casos se aplicarán:
#### Organización Visual del Contenido:
1. **Jerárquica:**
   - Se aplicará la organización jerárquica en la página de inicio y en otras páginas principales para destacar la información más importante, como las características clave de la aplicación y los beneficios para los usuarios.
   - Los encabezados, títulos y botones de llamada a la acción se diseñarán para ser más grandes y prominentes, atrayendo la atención del usuario de manera efectiva.
2. **Secuencial:**
   - Se utilizará la organización secuencial en el proceso de reserva de canchas o mesas de billar, guiando al usuario paso a paso a través del proceso para completar su reserva con éxito.
   - Cada paso del proceso se presentará de forma clara y concisa, con indicaciones claras sobre qué información se requiere en cada etapa.
3. **Matricial:**
   - Se aplicará la organización matricial en la página de búsqueda de canchas o mesas de billar, permitiendo a los usuarios filtrar los resultados según diferentes criterios, como ubicación, disponibilidad y características específicas de las instalaciones.
   - Los usuarios podrán seleccionar múltiples criterios de búsqueda para refinar sus resultados y encontrar fácilmente lo que están buscando.
#### Esquemas de Categorización de Contenido:
1. **Alfabético:**
   - Se utilizará la categorización alfabética para organizar la lista de canchas de fútbol o mesas de billar disponibles en función de su nombre o ubicación.
   - Esto facilitará a los usuarios encontrar rápidamente la cancha o mesa que están buscando, especialmente si conocen el nombre exacto.


</div>

### 4.2.2. Labeling Systems

<div style="text-align: justify;">

En D'taquito, nos esforzamos por utilizar etiquetas claras y concisas para representar los conjuntos de información y las asociaciones entre ellas. Nuestro objetivo es garantizar la simplicidad y evitar la confusión para nuestros visitantes y usuarios. A continuación, se detallan las etiquetas que utilizaremos y cómo se asociarán entre sí:
1. **Conjuntos de Información:**
   - **Canchas de Fútbol / Mesas de Billar:**
     - Utilizaremos la etiqueta "Canchas de Fútbol" o "Mesas de Billar" para representar los diferentes conjuntos de información relacionados con las instalaciones deportivas disponibles.
   - **Reservas:**
     - La etiqueta "Reservas" se utilizará para representar la información relacionada con las reservas de canchas de fútbol o mesas de billar realizadas por los usuarios.
   - **Perfil de Usuario:**
     - Utilizaremos la etiqueta "Perfil de Usuario" para representar la información asociada con los perfiles individuales de los usuarios, que incluyen detalles como nombre, foto de perfil, información de contacto, etc.
2. **Asociaciones entre Etiquetas:**
   - **Categorías y Subcategorías:**
     - Las etiquetas de categoría se asociarán con las etiquetas de subcategoría para organizar la información de manera jerárquica. Por ejemplo, "Canchas de Fútbol" puede tener subcategorías como "Canchas al Aire Libre" y "Canchas Cubiertas".
   - **Filtros y Opciones de Búsqueda:**
     - Utilizaremos etiquetas descriptivas para representar las opciones de filtro y búsqueda disponibles para los usuarios. Por ejemplo, "Ubicación", "Disponibilidad", "Tipo de Instalación", etc.
   - **Acciones y Funcionalidades:**
     - Asociaremos etiquetas claras y concisas con las diferentes acciones y funcionalidades disponibles en la aplicación, como "Reservar", "Cancelar", "Editar Perfil", "Crear Evento", etc.
   - **Feedback y Estado:**
     - Utilizaremos etiquetas visuales para representar el estado de las reservas o acciones realizadas por los usuarios, como "Confirmado", "Pendiente", "Cancelado", etc.


</div>

### 4.2.3. SEO Tags and Meta Tags

Por supuesto, aquí tienes una propuesta para los SEO Tags y Meta Tags que se utilizarán en las principales páginas de la experiencia tanto a nivel del sitio web estático como en la aplicación web:
### Landing Page:
- **Title:** 
  - D'taquito: Reserva tu cancha o mesa de billar de manera rápida y segura 
- **Meta Tags:**
  - **Description:** 
    - Canchas y mesas de billar cerca de ti a solo un clic de reserva
  - **Keywords:** 
    - Canchas de fútbol, Mesas de billar, Reservas deportivas, Organización de partidos, Deporte local, Juegos deportivos.
  - **Author:** 
    - D'taquito developers

### Web Application:
- **Title:** 
  - D'taquito: Reserva tu cancha o mesa de billar de manera rápida y segura
- **Meta Tags:**
  - **Description:** 
    - Canchas y mesas de billar cerca de ti a solo un clic de reserva
  - **Keywords:** 
    - Reservas deportivas, Organización de partidos, Canchas de fútbol, Mesas de billar, Deporte local, Juegos deportivos.
  - **Author:** 
    - D'taquito developers


### 4.2.4. Searching Systems

<div style="text-align: justify;">

- Los usuarios pueden buscar canchas de fútbol y mesas de billar disponibles en su ubicación actual o en una ubicación específica. Esto puede implementarse utilizando la geolocalización del dispositivo del usuario o permitiendo que ingresen manualmente una ubicación.

- Los usuarios pueden refinar sus búsquedas utilizando filtros como la fecha y hora de reserva, el tipo de cancha (césped sintético, césped natural, sala de billar, etc.), la capacidad de la cancha o mesa (número de jugadores), la disponibilidad de servicios adicionales (vestuarios, duchas, estacionamiento, etc.).

- Después de realizar una búsqueda, los resultados se presentan de manera ordenada y fácilmente comprensible para que los usuarios puedan encontrar rápidamente lo que están buscando. Pueden ordenarse por relevancia, distancia, precio, valoraciones, etc.

- Los usuarios pueden ver una vista previa de los detalles de las canchas de fútbol o mesas de billar encontradas, incluyendo fotos, ubicación en el mapa, horarios disponibles, precios y reseñas de otros usuarios.

</div>

### 4.2.5. Navigation Systems

Con esta estructura de navegación, los usuarios pueden moverse fácilmente entre las diferentes secciones de la aplicación y realizar acciones como buscar locales, unirse a reservas comunitarias, realizar sus propias reservas y gestionar su perfil y configuración.

- **Locales:**
Esta sección muestra una lista de todos los espacios de juego disponibles, incluyendo canchas de fútbol y mesas de billar. Los usuarios pueden explorar diferentes locales, ver detalles como ubicación, fotos, servicios ofrecidos y horarios de disponibilidad. También podrían haber opciones de filtrado y ordenamiento para que los usuarios puedan encontrar rápidamente los locales que se ajusten a sus preferencias.

- **Sala Comunidad:**
En esta sección, los usuarios pueden unirse a reservas de otras personas o crear reservas públicas a las que otros puedan unirse. Pueden explorar las reservas existentes, ver detalles como la fecha, hora, ubicación y número de participantes, y unirse a las que les interesen. Los usuarios también pueden crear sus propias reservas públicas, estableciendo detalles como la fecha, hora, local y número de participantes.

- **Reserva:**
Aquí es donde los usuarios realizan el proceso de reserva para asegurar su espacio de juego. Pueden seleccionar la fecha y hora deseadas, elegir el local disponible de su preferencia, especificar el número de jugadores y cualquier otro detalle relevante para la reserva. También pueden revisar y confirmar los detalles de la reserva antes de finalizarla.

- **Configuración:**
Esta sección alberga las opciones de configuración del perfil del usuario. Los usuarios pueden acceder a su perfil donde pueden ver y editar su información personal, como nombre, dirección, número de contacto, etc. También pueden acceder a su cartera virtual para ver su saldo, agregar fondos o realizar pagos.

- **Suscripción:**
Permite a los usuarios administrar su suscripción a servicios premium o viceversa.

- **Historial:**
Muestra un registro de las reservas anteriores del usuario, permitiéndoles revisar sus actividades pasadas.


## 4.3. Landing Page UI Design

### 4.3.1. Landing Page Wireframe

<img src="https://raw.githubusercontent.com//HenryCenturion//open-source-final-project//document/caratula//images//Landing Page Wireframe.jpg" alt="UPC">

### 4.3.2. Landing Page Mock-up

<img src="https://raw.githubusercontent.com//HenryCenturion//open-source-final-project//document/caratula//images//Landing Page Mock-Up.jpg" alt="UPC">

## 4.4. Web Applications UX/UI Design

### 4.4.1. Web Applications Wireframes

<div style="text-align: justify;">

Link del figma para ver todos los wireframes: <br>
https://www.figma.com/file/9b5VMPb1CCHGYuWQkP2554/UX-Design?type=design&node-id=224%3A2303&mode=design&t=gMUKQghXe93oTI3D-1

</div>

<img src="https://raw.githubusercontent.com//HenryCenturion//open-source-final-project//document/caratula//images//wire1.png" alt="UPC">

<img src="https://raw.githubusercontent.com//HenryCenturion//open-source-final-project//document/caratula//images//wire2.png" alt="UPC">

### 4.4.2. Web Applications Wireflow Diagrams

### 4.4.3. Web Applications Mock-ups

<div style="text-align: justify;">

Link del figma para ver todos los mocks-up: <br>
https://www.figma.com/file/9b5VMPb1CCHGYuWQkP2554/UX-Design?type=design&node-id=224%3A2303&mode=design&t=gMUKQghXe93oTI3D-1

</div>

<img src="https://raw.githubusercontent.com//HenryCenturion//open-source-final-project//document/caratula//images//mock1.png" alt="UPC">

<img src="https://raw.githubusercontent.com//HenryCenturion//open-source-final-project//document/caratula//images//mock2.png" alt="UPC">

### 4.4.4. Web Applications User Flow Diagrams

<div style="text-align: justify;">

aaa

</div>

## 4.5. Web Applications Prototyping

<div style="text-align: justify;">

aaa


## 4.6. Domain-Driven Software Architecture

### 4.6.1. Software Architecture Context Diagram

<div style="text-align: justify;">

<img src="https://github.com/HenryCenturion/open-source-final-project/blob/13f33de46772af97f952ceb0a12e32f5903d019e/images/structurizr-86931-Contexto.png" alt="UPC">

</div>

### 4.6.2. Software Architecture Container Diagrams

<div style="text-align: justify;">

<img src="https://github.com/HenryCenturion/open-source-final-project/blob/13f33de46772af97f952ceb0a12e32f5903d019e/images/structurizr-86931-Contenedor%20(1).png" alt="UPC">

</div>


### 4.6.3. Software Architecture Components Diagrams

<div style="text-align: justify;">

<img src="https://github.com/HenryCenturion/open-source-final-project/blob/13f33de46772af97f952ceb0a12e32f5903d019e/images/structurizr-86931-API%20Rest%20Component%20Diagram.png" alt="UPC">

</div>

## 4.7. Software Object-Oriented Design

### 4.7.1. Class Diagrams

<div style="text-align: justify;">

<img src="https://github.com/HenryCenturion/open-source-final-project/blob/28df6078bec50dbc8c9144316016b58d3c7410f2/images/image.png" alt="UPC">

</div>

### 4.7.2. Class Dictionary

<div style="text-align: justify;">

</div>

## 4.8. Database Design

### 4.8.1. Database Diagram

<div style="text-align: justify;">
  
aaa
  
</div>
