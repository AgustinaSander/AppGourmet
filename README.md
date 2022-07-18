# AppGourmet
El siguiente proyecto fue realizado como practica del Programa de Pasantias de la empresa <code>Hexacta</code> en un plazo de 2 meses.<br>
Consistio en el desarrollo de un sistema que permita gestionar recetarios, recetas, usuarios y sus preferencias (vegano, vegetariano..).

## Indice
1. <a href="#acerca-de-la-implementacion">Acerca de la Implementacion</a>
2. <a href="#enunciado">Enunciado</a>
3. <a href="#imagenes">Imagenes de las Interfaces</a>
4. <a href="#etapas-del-proyecto">Etapas del Proyecto</a>

## Acerca de la Implementacion
Se trata de un proyecto web realizado con Java con los IDEs Eclipse y Visual Studio Code. <br>
Para el backend se utilizo el framework <code>Spring</code> y se tuvieron en cuenta los principios de la Programacion Orientada a Objetos, Patrones MVC, DAO y Singleton. Se realizo una API Rest y se utilizo <code>Postman</code> para verificar su funcionamiento. <br>
En el frontend se utilizo <code>Angular</code> junto con Bootstrap y Sass y se consumio la API generada.
<br> Se realizaron test unitarios con <code>JUnit 4 y Mockito</code> de las principales funcionalidades.

## Enunciado
Se cuenta con recetarios que poseen recetas. De estas se conocen los nombres, los ingredientes que poseen y las cantidades de cada uno. 
Cada ingrediente tiene un nombre, cantidad de calorias y grupo alimenticio al que pertenece.
<br>
Los usuarios podran crearse distintos perfiles con preferencias alimenticias. Estos podran suscribirse a los recetarios y si se agrega una receta que cumpla con sus preferencias se les notificara.
<br>
Existen rankings donde se agregan recetarios y participan las recetas de los mismos. Las recetas tienen puntajes segun su presencia en los rankings.

## Imagenes
<div align="center">
<img src="https://user-images.githubusercontent.com/75265449/179575393-bd98c43e-c5eb-4599-95ca-5bb1e921d313.png" alt="interfaz-principal" width="60%">
</div>
<br><br>
<div align="center" display="flex">
<img src="https://user-images.githubusercontent.com/75265449/179576291-98cc2fb4-74a4-48fb-8dec-b6ad05999257.png" width="40%" alt="alta-receta">
<img src="https://user-images.githubusercontent.com/75265449/179576450-308513d1-8ed2-46e3-aa46-4e8b76b05ff6.png" width="40%" alt="descripcion-receta">
</div>
<br><br>
<div align="center">
<img src="https://user-images.githubusercontent.com/75265449/179576592-5cbb72bf-a8e1-47cb-b459-9d5d9feb4b69.png"  width="60%" alt="recetas">
</div>

## Etapas del proyecto
El proyecto se dividio en 6 entregas.<br>
1 - A partir del enunciado, se comenzo a plantear y desarrollar el modelo de negocios con las clases, metodos y asociaciones. Se realizaron test unitarios de los siguientes casos: 
- Cantidad de comidas para un recetario
- Cantidad de ingredientes para una comida
- Cálculo correcto de calorías para una comida
- Presencia de un determinado alimento en una comida
- Presencia de un determinado grupo alimenticio en una comida
<br>
2 - Siguiendo con el desarrollo se realizaron los test unitarios validando si una comida es apta o no para un cierto usuario.<br>
3 - Se mockeo el envio de notificaciones con Mockito, se implementaron los rankings y se generaron los siguientes test unitarios:<br>
Al agregar una receta a un recetario, 
- Los usuarios suscritos reciben la notificación cuando la comida cumple con el perfil indicado
- Los usuarios suscritos no reciben la notificación cuando la comida no cumple con el perfil indicado
- El ranking incrementa la cantidad de puntos acumulados para la comida<br>
4 - Se agrego la persistencia de los recetarios en una base de datos relacional utilizando Hibernate y H2Database. Unicamente se persistieron recetarios, recetas y comidas. Se realizaron test unitarios del conjunto de operaciones CRUD.<br>
5 - Se genero una API Rest que expone el listado de libros de recetas, listado de recetas y de recetas de un libro especifico.<br>
6 - Se genero la aplicacion web con Angular para exponer la informacion brindada por la API.
