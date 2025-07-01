package lib;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * <b>StreamUtils</b> — utilidades simplificadas para trabajar con colecciones usando Streams.
 *
 * <h3>Resumen de funciones</h3>
 * <ul>
 *     <li><b>ordenarAsc / ordenarDesc:</b> ordena por un campo (ascendente o descendente).</li>
 *     <li><b>ordenarPorDosCampos:</b> ordena por dos campos (ej. primero fecha, luego título).</li>
 *     <li><b>filtrar:</b> devuelve solo los que cumplan una condición.</li>
 *     <li><b>mapear:</b> transforma la colección (por ejemplo, a títulos o nombres).</li>
 *     <li><b>primeroOrdenado:</b> devuelve el primer elemento ordenado (como un mínimo).</li>
 *     <li><b>algunoCumple:</b> ¿hay algún elemento que cumple una condición?</li>
 *     <li><b>todosCumplen:</b> ¿todos los elementos cumplen la condición?</li>
 * </ul>
 */
public final class StreamUtils {

    private StreamUtils() {
    }

    // ---------------------------------------------------------------------
    //  ORDENAR
    // ---------------------------------------------------------------------

    /**
     * Ordena una colección <b>ascendentemente</b> (de menor a mayor) según el campo indicado.
     *
     * @param coleccion colección a ordenar
     * @param getter    función que extrae el campo por el que ordenar (ej. Persona::getEdad)
     * @return una nueva lista ordenada
     */
    public static <T, U extends Comparable<? super U>> List<T> ordenarAsc(Collection<T> coleccion,
                                                                          Function<T, U> getter) {
        return coleccion.stream()
                .sorted(Comparator.comparing(getter))
                .toList();
    }

    /**
     * Ordena una colección <b>descendentemente</b> (de mayor a menor) según el campo indicado.
     *
     * @param coleccion colección a ordenar
     * @param getter    función que extrae el campo por el que ordenar
     * @return una nueva lista ordenada de mayor a menor
     */
    public static <T, U extends Comparable<? super U>> List<T> ordenarDesc(Collection<T> coleccion,
                                                                           Function<T, U> getter) {
        return coleccion.stream()
                .sorted(Comparator.comparing(getter).reversed())
                .toList();
    }

    /**
     * Ordena una colección por dos campos: primero por uno, y si hay empate, por otro.
     *
     * @param coleccion colección a ordenar
     * @param getter1   primer campo de ordenación
     * @param getter2   segundo campo de ordenación
     * @return una nueva lista ordenada
     */
    public static <T, U extends Comparable<? super U>, V extends Comparable<? super V>>
    List<T> ordenarPorDosCampos(Collection<T> coleccion,
                                Function<T, U> getter1,
                                Function<T, V> getter2) {
        return coleccion.stream()
                .sorted(Comparator.comparing(getter1).thenComparing(getter2))
                .toList();
    }

    // ---------------------------------------------------------------------
    //  FILTRAR & MAPEAR
    // ---------------------------------------------------------------------

    /**
     * Filtra una colección devolviendo solo los elementos que cumplan una condición.
     *
     * @param coleccion colección original
     * @param predicado condición a cumplir (ej. x -> x.getEdad() > 18)
     * @return lista filtrada
     */
    public static <T> List<T> filtrar(Collection<T> coleccion, Predicate<T> predicado) {
        return coleccion.stream()
                .filter(predicado)
                .toList();
    }

    /**
     * Filtra una colección heterogénea devolviendo solo los elementos que:
     * <ul>
     *     <li>Sean instancias del tipo especificado</li>
     *     <li>Y además cumplan el predicado dado</li>
     * </ul>
     *
     * <p>Ejemplo: obtener solo los libros de género "Fantasía"</p>
     * <pre>
     * List<Libro> librosFantasia = StreamUtils.filtrarPorTipo(
     *     publicaciones.values(),
     *     Libro.class,
     *     libro -> libro.getGenero().equals("Fantasía")
     * );
     * </pre>
     *
     * @param coleccion colección de entrada (puede contener objetos de distintos tipos)
     * @param tipo      clase que se desea filtrar (ej. {@code Libro.class})
     * @param predicado condición a cumplir por los elementos del tipo filtrado
     * @param <T>       tipo de los elementos que se desean obtener
     * @return lista con los elementos del tipo especificado que cumplan la condición
     */
    public static <T> List<T> filtrarPorTipo(Collection<?> coleccion, Class<T> tipo, Predicate<T> predicado) {
        return coleccion.stream()
                .filter(tipo::isInstance)
                .map(tipo::cast)
                .filter(predicado)
                .toList();
    }

    /**
     * Transforma una colección aplicando una función a cada elemento.
     *
     * @param coleccion colección original
     * @param mapper    función transformadora (ej. Persona::getNombre)
     * @return nueva lista con los resultados
     */
    public static <T, R> List<R> mapear(Collection<T> coleccion, Function<T, R> mapper) {
        return coleccion.stream()
                .map(mapper)
                .toList();
    }

    // ---------------------------------------------------------------------
    //  CONSULTAS RÁPIDAS
    // ---------------------------------------------------------------------

    /**
     * Devuelve el primer elemento tras ordenar por el campo indicado (mínimo).
     *
     * @param coleccion colección a revisar
     * @param getter    campo a usar como criterio
     * @return el más "pequeño" según ese campo, o vacío si la colección está vacía
     */
    public static <T, U extends Comparable<? super U>> Optional<T> primeroOrdenado(Collection<T> coleccion,
                                                                                   Function<T, U> getter) {
        return coleccion.stream()
                .min(Comparator.comparing(getter));
    }

    /**
     * Devuelve {@code true} si al menos un elemento cumple la condición dada.
     *
     * @param coleccion colección a revisar
     * @param predicado condición a verificar
     * @return true si alguno la cumple
     */
    public static <T> boolean algunoCumple(Collection<T> coleccion, Predicate<T> predicado) {
        return coleccion.stream().anyMatch(predicado);
    }

    /**
     * Devuelve {@code true} si todos los elementos cumplen la condición dada.
     *
     * @param coleccion colección a revisar
     * @param predicado condición a verificar
     * @return true si todos la cumplen
     */
    public static <T> boolean todosCumplen(Collection<T> coleccion, Predicate<T> predicado) {
        return coleccion.stream().allMatch(predicado);
    }
}
