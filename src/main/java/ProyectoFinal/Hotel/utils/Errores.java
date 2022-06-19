package ProyectoFinal.Hotel.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Errores {

    /**
     * Atributo de la clase Errores.
     */
    public static Logger logger=Logger.getLogger(Errores.class.getName());

    /**
     * Crea un log de tipo INFO y muestra su información.
     * @param s
     */
    public static void logInfo(String s){
        save();
        logger.setLevel(Level.INFO);
        logger.log(Level.INFO,s);
    }

    /**
     * Crea un log de tipo WARNING y muestra su información.
     * @param s
     */
    public static void logWarning(String s){
        save();
        logger.setLevel(Level.WARNING);
        logger.log(Level.INFO,s);
    }

    /**
     * Coge las propiedades del archivo para usar un log.
     */
    private static void save(){
        try{
            InputStream config=Errores.class.getResourceAsStream("Errores.properties");
            LogManager.getLogManager().readConfiguration(config);
        }catch(SecurityException| IOException |NullPointerException e){
            Utils.print("Sistema de errores no iniciado");
        }
        logger=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    }
}
