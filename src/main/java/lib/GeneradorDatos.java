package lib;

import net.datafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Generador de datos falsos para pruebas con locale español.
 * <p>
 * </p>
 * <h3>Ejemplo rápido</h3>
 * <pre>
 * GeneradorDatos gen = new GeneradorDatos();
 * String nombre = gen.generarNombreCompleto();
 * String password = gen.generarPassword();
 * </pre>
 */
public final class GeneradorDatos {

    /* ================= Constantes internas ================= */

    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final String ALFANUMERICO = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final String SIMBOLOS = "!@#$%^&*-_+?";
    private static final String LETRAS_DNI = "TRWAGMYFPDXBNJZSQVHLCKE";

    /* =============== Dependencias e instancias ============== */

    private final Faker faker;
    private final Random random;

    /* ===================== Constructores ==================== */

    /**
     * Constructor con locale español por defecto.
     */
    public GeneradorDatos() {
        this.faker = new Faker(new Locale("es", "ES"));
        this.random = ThreadLocalRandom.current();
    }

    /**
     * Constructor parametrizado por locale.
     */
    public GeneradorDatos(Locale locale) {
        this.faker = new Faker(locale);
        this.random = ThreadLocalRandom.current();
    }

    /* =================== Métodos de nombres ================= */

    public String generarNombreCompleto() {
        return faker.name().fullName();
    }

    public String generarNombre() {
        return faker.name().firstName();
    }

    public String generarApellido() {
        return faker.name().lastName();
    }

    /* =================== Fechas y tiempo ==================== */

    public String generarFechaNacimiento(int minEdad, int maxEdad) {
        LocalDate fecha = faker.timeAndDate().birthday(minEdad, maxEdad);
        return fecha.format(FORMATO_FECHA);
    }

    public String generarFechaAleatoria(int inicioYear, int finYear) {
        LocalDate inicio = LocalDate.of(inicioYear, 1, 1);
        LocalDate fin = LocalDate.of(finYear, 12, 31);
        long dias = Math.abs(fin.toEpochDay() - inicio.toEpochDay());
        LocalDate fecha = inicio.plusDays(random.nextLong(dias + 1));
        return fecha.format(FORMATO_FECHA);
    }

    /* =================== Números y dígitos ================== */

    public int generarNumeroEntero(int min, int max) {
        return faker.number().numberBetween(min, max + 1);
    }

    public double generarNumeroDecimal(double min, double max) {
        return faker.number().randomDouble(2, (long) min, (long) max);
    }

    public String generarDNI() {
        String numero = String.format("%08d", random.nextInt(100_000_000));
        char letra = LETRAS_DNI.charAt(Integer.parseInt(numero) % 23);
        return numero + letra;
    }

    /* =================== Datos de contacto ================== */

    public String generarNumeroTelefono() {
        return faker.phoneNumber().phoneNumber();
    }

    public String generarDireccion() {
        return faker.address().fullAddress();
    }

    public String generarCorreoElectronico() {
        return faker.internet().emailAddress();
    }

    public String generarNumeroMovil() {
        return faker.phoneNumber().cellPhone();
    }

    public String generarNombreEmpresa() {
        return faker.company().name();
    }


    /* ==================== Nuevas utilidades ================= */

    /**
     * Genera una cadena alfanumérica de longitud fija.
     */
    public String generarAlfanumerico(int longitud) {
        return random.ints(longitud, 0, ALFANUMERICO.length())
                .mapToObj(ALFANUMERICO::charAt)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    /**
     * Genera una contraseña de 12 caracteres que incluye símbolos por defecto.
     */
    public String generarPassword() {
        return generarPassword(12, true);
    }

    /**
     * Genera una contraseña personalizada.
     *
     * @param longitud        Longitud total deseada (mín 4)
     * @param incluirSimbolos si se añaden símbolos especiales
     */
    public String generarPassword(int longitud, boolean incluirSimbolos) {
        if (longitud < 4) throw new IllegalArgumentException("La longitud mínima es 4");
        String base = ALFANUMERICO + (incluirSimbolos ? SIMBOLOS : "");
        return random.ints(longitud, 0, base.length())
                .mapToObj(base::charAt)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    /**
     * Genera un nombre de usuario combinando nombre + tres dígitos.
     */
    public String generarUsername() {
        return (generarNombre().toLowerCase() + generarNumeroEntero(100, 999));
    }

    /**
     * Genera un IBAN válido para España (ES + 22 dígitos). No realiza checksum real,
     * suficiente para casos de prueba.
     */
    public String generarIBAN() {
        String numeros = String.format("%020d", random.nextLong(1_000_000_000_000_000_000L));
        return "ES" + numeros;
    }

    /**
     * Genera un número de tarjeta de crédito (no real) usando Faker.
     */
    public String generarTarjetaCredito() {
        return faker.finance().creditCard();
    }

    /* =================== Colecciones rápidas ================ */

    public List<String> generarListaNombres(int cantidad) {
        return IntStream.range(0, cantidad)
                .mapToObj(i -> generarNombreCompleto())
                .collect(Collectors.toList());
    }

    public List<String> generarListaCorreos(int cantidad) {
        return IntStream.range(0, cantidad)
                .mapToObj(i -> generarCorreoElectronico())
                .collect(Collectors.toList());
    }

    /* =================== Utilidades de RegEx ================= */

    /**
     * Genera una cadena que cumple con la expresión regular proporcionada.
     * Ejemplo: gen.generarPorRegex("[A-Z]{3}-\\d{4}");
     */
    public String generarPorRegex(String regex) {
        return faker.regexify(regex);
    }
}
