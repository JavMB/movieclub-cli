package lib; // Ajusta tu paquete

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

// --- INICIO DE LA CLASE DE UTILIDAD ---
public class FileUtils {

    private static final int BITS_PER_BYTE = 8;

    private FileUtils() {
        // Constructor privado para que no se pueda instanciar
    }

    // --- MÉTODOS PARA TEXTO ---

    public static String leerArchivoComoString(File archivo) throws IOException {
        Objects.requireNonNull(archivo, "El archivo no puede ser null.");
        return Files.readString(archivo.toPath(), StandardCharsets.UTF_8);
    }

    public static void escribirStringAArchivo(File archivo, String contenido) throws IOException {
        Objects.requireNonNull(archivo, "El archivo no puede ser null.");
        Objects.requireNonNull(contenido, "El contenido no puede ser null.");
        Files.writeString(archivo.toPath(), contenido, StandardCharsets.UTF_8);
    }

    // --- MÉTODOS PARA BINARIO (BITS) ---

    public static boolean[] leerArchivoComoBits(File archivo) throws IOException {
        Objects.requireNonNull(archivo, "El archivo no puede ser null.");
        byte[] bytes = Files.readAllBytes(archivo.toPath());
        boolean[] bits = new boolean[bytes.length * BITS_PER_BYTE];
        for (int i = 0; i < bytes.length; i++) {
            for (int j = 0; j < BITS_PER_BYTE; j++) {
                bits[i * BITS_PER_BYTE + (BITS_PER_BYTE - 1 - j)] = ((bytes[i] >> j) & 1) == 1;
            }
        }
        return bits;
    }

    public static void escribirBitsAArchivo(File archivo, boolean[] bits) throws IOException {
        Objects.requireNonNull(archivo, "El archivo no puede ser null.");
        Objects.requireNonNull(bits, "El array de bits no puede ser null.");

        int numBytes = (bits.length + BITS_PER_BYTE - 1) / BITS_PER_BYTE;
        byte[] bytes = new byte[numBytes];

        for (int i = 0; i < bits.length; i++) {
            if (bits[i]) {
                bytes[i / BITS_PER_BYTE] |= (1 << (BITS_PER_BYTE - 1 - (i % BITS_PER_BYTE)));
            }
        }
        Files.write(archivo.toPath(), bytes);
    }
}
