## Tabla de Contenido
</div>
<br>
<br>

</div>

<div style="margin-left: 80px;">

  - 3.1. [To-Be Scenario Mapping](#31-to-be-scenario-mapping) <br>
  - 3.2. [User Stories](#32-user-stories)<br>
  - 3.3. [Impact Mapping](#33-impact-mapping) <br>
  - 3.4. [Product Backlog](#34-product-backlog) <br>
  
</div>

<br>
<br>
<br>
<br>
<br>


## 3.1. To-Be Scenario Mapping




## 3.2. User Stories

Las historias de usuario representan una manera de transformar la comunicación casual de los clientes del producto en un requisito de software que debe tenerse en cuenta durante el desarrollo del sistema. Una historia de usuario bien elaborada comunica al desarrollador qué funcionalidad están construyendo, su propósito y el beneficio que proporciona al usuario. Para el proyecto TuPastpo, hemos preparado un conjunto de historias de usuario para la creación de la landign page, la aplicación web y algunas historias técnicas.

| Epic/User Story ID | Título | Descripción | Criterios de aceptación | Relacionado con (Epic ID) |
| ------------------ | ------ | ----------- | ----------------------- | ------------------------- |
|EP01|Gestión de Usuarios|Desarrollar funcionalidades para la gestión de usuarios, incluyendo registro, inicio de sesión, recuperación de contraseña y perfil de usuario.|No aplica|No aplica|
|EP02|Exploración de Canchas|Desarrollar funcionalidades para que los usuarios puedan explorar y buscar canchas disponibles en diferentes ubicaciones.|No Aplica|No Aplica|
|EP03|Reservas de Canchas:|Desarrollar funcionalidades para que los usuarios puedan realizar reservas de canchas de forma rápida y sencilla.|No Aplica|No Aplica|
|EP04|Gestión de Pagos|Desarrollar funcionalidades para procesar pagos de forma segura y confiable dentro de la plataforma.|No Aplica|No Aplica|
|EP05|Notificaciones y Recordatorios|Desarrollar funcionalidades para enviar notificaciones y recordatorios a los usuarios sobre sus reservas de canchas.|No Aplica|No Aplica|
|EP06|Calificaciones y Comentarios:|Desarrollar funcionalidades para que los usuarios puedan calificar y dejar comentarios sobre las canchas reservadas.|No Aplica|No Aplica|
|EP07|Integración de Redes Sociales:|Desarrollar funcionalidades para que los usuarios puedan compartir sus reservas y experiencias en redes sociales.|No Aplica|No Aplica|
|EP08|Soporte al Cliente|Desarrollar funcionalidades para proporcionar soporte y atención al cliente a los usuarios de la plataforma.|No Aplica|No Aplica|
|EP09|Gestión de Reservas Múltiples|Desarrollar funcionalidades para que los usuarios puedan gestionar múltiples reservas de canchas simultáneamente.|No Aplica|No Aplica|
|EP10|Optimización para Dispositivos Móviles| Desarrollar una versión móvil optimizada de la plataforma para una experiencia de usuario fluida en dispositivos móviles.|No Aplica|No Aplica|
|EP11|Análisis y Mejora Continua|Desarrollar funcionalidades para recopilar datos y realizar análisis con el fin de mejorar la plataforma de manera continua.|No Aplica|No Aplica|
|US01|Registro de Usuarios|Como usuario nuevo, quiero poder registrarme en la plataforma TuPasto para acceder a todas las funcionalidades.|**Escenario 1: Registro exitoso** **Dado** que soy un nuevo usuario Cuando accedo a la página de registro y completo el formulario con mi información Entonces debería recibir un correo electrónico de confirmación para activar mi cuenta.|EP01|
|US02|Inicio de session|Como usuario, quiero poder iniciar sesión en mi cuenta para gestionar mis reservas|**Escenario 1: Incio de sesion correcto** **Dado** que soy un usuario con una cuenta **Cuando** accedo a la pagina de uniciar session y ingreso mis datos **Entonces** deberia porder ingresar correctamente a mi cuenta permitiendome usar los servicios.  **Escenario 2: Incio de sesion Incorrecto** **Dado** que soy un usuario tratando de ingresar a los servicios **Cuando** accedo a la pagina de uniciar session y ingreso mis datos y ocurre un error **Entonces** deberia mostrarme un mensaje dejando en claro que existe un error en el inicio de sesion|EP01|
|US03|Búsqueda de Canchas por Ubicación|Como usuario, quiero poder buscar canchas por ubicación para encontrar instalaciones cercanas.|**Escenario 1: Búsqueda exitosa** **Dado** que el usuario ha ingresado una ubicación válida en el campo de búsqueda **Cuando** hace clic en el botón de búsqueda **Entonces** se muestran las canchas disponibles en esa ubicación. **Escenario 2: No se encontraron cnachas** **Dado** que el usuario ha ingresado una ubicación válida en el campo de búsqueda **Cuando** hace clic en el botón de búsqueda **Entonces** mostrara que no se encontraron canchas en esa ubicación.|EP02|
|US04|Selección de Fecha y Hora para Reserva|Como usuario, quiero poder seleccionar una fecha y hora para mi reserva de cancha.|**Escenario 1: Selección de fecha y hora exitosa** **Dado** que el usuario está en la página de detalles de la cancha y ha seleccionado una fecha y hora disponibles **Cuando** hace clic en el botón "Reservar" **Entonces** la reserva se confirma y el usuario recibe una confirmación por correo electrónico.  **Escenario 2: Selección de fecha y hora Ocupadas** **Dado** que el usuario está en la página de detalles de la cancha y ha seleccionado una fecha y hora que no estan disponibles **Cuando** hace clic en el botón "Reservar" **Entonces** aparece un error en pantalla indicando que el horario esta ocupado.|EP03|
|US05|Proceso de Pago Seguro|Como usuario, quiero poder pagar por mi reserva de cancha de forma segura utilizando diferentes métodos de pago.|**Escenario 1: Pago exitoso con tarjeta de crédito o debito** **Dado** que el usuario ha seleccionado la opción de pago con tarjeta de crédito **Cuando** ingresa los detalles de su tarjeta y completa el pago **Entonces** recibe un recibo de confirmación por correo electrónico y la reserva se registra en su cuenta.|EP04|
|US06|Notificaciones de Reserva|Como usuario, quiero recibir notificaciones por correo electrónico o mensajes de texto sobre el estado de mi reserva.|**Escenario 1: Recepción de recordatorio de reserva** **Dado** que el usuario ha realizado una reserva y ha proporcionado su dirección de correo electrónico o número de teléfono móvil **Cuando** se acerca la fecha y hora de la reserva **Entonces** el usuario recibe un correo electrónico o mensaje de texto recordatorio con los detalles de la reserva.|EP05|
|US06|Notificaciones de Confirmacion de Reserva|Como Usuario quiero porder recibir notificaciones por correo electronico o mensajes de texto Confiramandome mi reserva|**Escenario 1: Recepción confirmacion de reserva** **Dado** que el usuario ha realizado una reserva y ha proporcionado su dirección de correo electrónico o número de teléfono móvil **Cuando** Realiza la reserva **Entonces** el usuario recibe un correo electrónico o mensaje de texto Confirmando la reserva y los detalles de esta misma|EP05|
|US07|Calificación de Experiencia de Cancha|Como usuario, quiero poder calificar la calidad de la cancha y el servicio proporcionado por el proveedor.|**Escenario 1: Calificación de la experiencia** **Dado** que el usuario ha completado su partido en la cancha reservada **Cuando** recibe el correo electrónico de calificación de la experiencia **Entonces** accede al enlace proporcionado, otorga una calificación y deja comentarios si lo desea.|EP06|
|US08|Compartir Reserva en Redes Sociales|Como usuario, quiero poder compartir mi reserva de cancha en redes sociales como Facebook o Twitter.|**Escenario 1: Compartir reserva en Facebook** **Dado** que el usuario ha completado una reserva exitosa **Cuando** selecciona la opción de compartir en Facebook y agrega un mensaje personalizado **Entonces** el enlace compartido aparece en su feed de noticias con el mensaje adjunto.|EP07|
|US10|Contactar al Equipo de Soporte|Como usuario, quiero poder contactar al equipo de soporte en caso de problemas o consultas relacionadas con mi reserva.|**Escenario 1: Envío de consulta al equipo de soporte** **Dado** que el usuario tiene una consulta o problema con su reserva **Cuando** completa el formulario de contacto y envía el mensaje **Entonces** recibe una confirmación de que su consulta ha sido recibida y puede esperar una respuesta dentro de las 24 horas.|EP08|
|US11|Vista de Todas las Reservas Activas|Como usuario, quiero poder ver todas mis reservas activas en un solo lugar.|**Escenario 1: Visualización de todas las reservas activas** **Dado** que el usuario ha iniciado sesión en su cuenta **Cuando** accede a la sección de reservas en su perfil **Entonces** ve una lista de todas las reservas activas que ha realizado.|EP09|
|US12|Interfaz Intuitiva en Dispositivos Móviles|Como usuario, quiero una interfaz intuitiva y fácil de usar en la versión móvil de la plataforma.|**Escenario 1: Navegación fluida en dispositivos móviles** **Dado* que el usuario accede a la plataforma desde un dispositivo móvil **Cuando** navega por las diferentes secciones y funcionalidades **Entonces** experimenta una navegación fluida y sin problemas.|EP10|
|US13|Entrevistas a usuario |Como susuari, quiero poder exresar mis comentarios sobre la aplicacion cada cierto tiempo, y asi escuchen mi opinion|**Escenario 1: Encuesta de satisfaccion al usuario** **Dado**  que el usuario desea poder expresar sus opiniones respecto a los servicios ofrecidos **Cuando** ejecute una reserva, se le mostrara si desea participar en una pequeña encuesta **Entonces** se recopilara datos respecto a su opinion sobre nuestros servicios, permitiendonos mejorar con el tiempo en base a las necesidades del usuario|EP11|
|US14|Recuperación de Contraseña|Como usuario, quiero poder recuperar mi contraseña en caso de olvido o pérdida.|**Escenario 1: Recuperación exitosa de contraseña** **Dado que** el usuario ha olvidado su contraseña y desea recuperarla **Cuando** accede a la página de recuperación de contraseña e ingresa su correo electrónico **Entonces** recibe un correo electrónico con un enlace de restablecimiento de contraseña y puede establecer una nueva contraseña.|EP01|
|US15|Edición de Perfil de Usuario|Como usuario, quiero poder editar la información de mi perfil en cualquier momento.|**Escenario 1: Edición exitosa del perfil de usuario** **Dado que** el usuario desea actualizar la información en su perfil **Cuando** accede a la función de edición de perfil y realiza los cambios deseados **Entonces** guarda los cambios y la información de su perfil se actualiza correctamente.|EP01|
|US16|Visualización de Horarios Disponibles|Como usuario, quiero poder ver los horarios disponibles para cada cancha antes de realizar una reserva.|**Escenario 1: Visualización de horarios disponibles** **Dado que** el usuario está interesado en reservar una cancha específica **Cuando** accede a la página de detalles de la cancha **Entonces** ve un calendario con horarios disponibles y puede identificar fácilmente los horarios libres.|EP02|
|US17|Cancelación de Reserva|Como usuario, quiero poder cancelar una reserva existente si surge un cambio en mis planes.|**Escenario 1: Cancelación exitosa de reserva** **Dado que** el usuario necesita cancelar una reserva existente **Cuando** accede a la página de detalles de la reserva y confirma la cancelación **Entonces** la reserva se cancela correctamente y se notifica al usuario y al proveedor.|EP03|
|US18|Reserva de Canchas por Horas Adicionales|Como usuario, quiero poder extender mi reserva de cancha si mi partido se alarga más de lo previsto.|**Escenario 1: Extensión exitosa de reserva** **Dado que** el usuario se da cuenta de que su partido durará más tiempo de lo previsto **Cuando** accede al proceso de reserva y selecciona horas adicionales **Entonces** confirma la extensión y la reserva se actualiza con las horas adicionales.|EP03|
|US19|Recibo de Confirmación de Pago|Como usuario, quiero recibir un recibo de confirmación por correo electrónico después de realizar un pago por mi reserva.|**Escenario 1: Recepción del recibo de confirmación de pago** **Dado que** el usuario ha completado con éxito el proceso de pago **Cuando** se completa la transacción **Entonces** recibe un recibo de confirmación por correo electrónico con todos los detalles de la reserva.|EP04|
|US20|Reembolso por Cancelación de Reserva|Como usuario, quiero recibir un reembolso automático si mi reserva se cancela debido a circunstancias imprevistas.|**Escenario 1: Reembolso automático por cancelación de reserva** **Dado que** la reserva ha sido cancelada dentro del período de tiempo especificado **Cuando** se procesa la cancelación y el reembolso **Entonces** el usuario recibe una notificación por correo electrónico y el reembolso se refleja en su cuenta dentro del plazo especificado.|EP04|
|US21|Comentarios Anónimos de Usuarios|Como usuario, quiero poder dejar comentarios sobre una cancha de forma anónima para compartir mi experiencia.|**Escenario 1: Dejar un comentario anónimo sobre una cancha** **Dado que** el usuario desea compartir su experiencia con una cancha específica **Cuando** escribe su comentario y selecciona la opción de enviarlo de forma anónima **Entonces** el comentario se publica sin revelar la identidad del usuario.|EP06|
|US22|Actualización de Información de Canchas|Como administrador del sistema, quiero poder actualizar la información de las canchas para mantenerla precisa y actualizada.|**Escenario 1: Actualización exitosa de información de canchas** **Dado que** el administrador necesita actualizar la información de una cancha **Cuando** accede a la interfaz de administración y realiza los cambios necesarios **Entonces** los cambios se aplican correctamente y se reflejan en la plataforma para los usuarios.|EP07|
|US23|Publicación de Noticias y Eventos|Como administrador del sistema, quiero poder publicar noticias y eventos relacionados con las actividades deportivas en la plataforma.|**Escenario 1: Publicación exitosa de noticias y eventos** **Dado que** el administrador desea compartir noticias o eventos relevantes **Cuando** accede a la sección de administración de contenido y publica la información **Entonces** la noticia o evento se muestra correctamente en la plataforma para que los usuarios puedan verla.|EP07|
|US24|Iniciar Sesión con Cuenta de Redes Sociales|Como usuario, quiero poder iniciar sesión en la plataforma utilizando mis cuentas de redes sociales para una experiencia de inicio de sesión más rápida y conveniente.|**Escenario 1: Inicio de sesión exitoso con cuenta de redes sociales** **Dado que** el usuario desea iniciar sesión en la plataforma **Cuando** elige la opción de iniciar sesión con una cuenta de redes sociales y completa el proceso de autorización **Entonces** se autentica su sesión y se le redirige de vuelta a la plataforma.|EP09|
|US25|Optimización de la Velocidad de Carga de la Plataforma|Como usuario, quiero que la plataforma cargue rápidamente para poder acceder a la información y realizar reservas sin demoras.|**Escenario 1: Verificación de la velocidad de carga optimizada** **Dado que** se han realizado mejoras de rendimiento en la plataforma **Cuando** los usuarios acceden a la plataforma desde diferentes dispositivos y conexiones de internet **Entonces** se verifica que la velocidad de carga cumple con los estándares establecidos y se realizan ajustes adicionales si es necesario.|EP10|





## 3.3. Impact Mapping






## 3.4. Product Backlog

