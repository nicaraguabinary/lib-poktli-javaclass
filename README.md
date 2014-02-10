==========================
poktli
==========================

Poktli ("humo" o "aroma" en Nahuatl) es una librería para la carga y guardado de archivos compilados de JAVA (.class). Puede ser utilizada como base para el desarrollo de decompiladores, (des)ofuscadores, inyectores de código y otras herramientas para proyectos JAVA.

==========================
Licencia
==========================
GPLv2, ver archivo LICENSE

==========================
Origen
==========================
Esta librería fue inicialmente desarrollada por Marcos Ortega a partir del documento de especificaciones de archivos CLASS publicado por Oracle:  http://docs.oracle.com/javase/specs/jvms/se5.0/html/ClassFile.doc.html

==========================
Utilidad
==========================
Esta implementación intenta ser una alternativa a "javap", apuntando a tener disponibles versiones en C, Java y otros lenguajes de interés. De tal forma que los desarrolladores podamos desarrollar software con la funcionalidad de cargar, manipular y guardar archivos ".class".

Posibles implementaciones prácticas de esta librería incluyen el desarrollo del siguiente tipo de software:

a) para compilación de código (producir class)

b) para decompilación de binarios (interpretar class)

c) para ofuscación en binarios (manipular class)

d) desofuscación de binarios (interpretar class)

e) inyección o extracción de opcodes, miembros, métodos o invocaciones a métodos (manipular class)

f) plugins, builders o asistentes que faciliten el proceso de integrar librerías de terceros a un proyecto Java.

g) otros...

Esperamos algún día las especificaciones de los binarios DEX (Android Dalvik) y Blackberry Micro sean publicados oficialmente y puedan ser integrados a esta librería.

==========================
Binarios de prueba
==========================

ClassSnifSnif.jar es una binario que permite probar la carga y guardado de archivos class. Los siguientes son ejemplos de uso desde consola:

Prueba simple y silenciosa:

$ java -jar ClassSnifSnif.jar -class ./miArchivoCompilado.class 

Prueba imprimiendo el contenido del archivo CLASS:

$ java -jar ClassSnifSnif.jar -v -class ./miArchivoCompilado.class 

Prueba de carga y guardado de archivo CLASS:

$ java -jar ClassSnifSnif.jar -test -class ./miArchivoCompilado.class 



