package exceptions;
/**
 * Activité 2, ValeurImpossibleException.java.
 *
 * @author Mohamadou Mansour
 * package-info.java: toutes les infos sur le package
 *
 * class ValeurImpossibleException .
 */
public class ValeurImpossibleException  extends Exception {
   /**
     * Constructeur par défaut de ValeurImpossibleException.
     */
   public ValeurImpossibleException() {
      super("Saisie impossible car la valur ne fais pas partie de l'alphabet");
   }
   /**
     * Constructeur de ValeurImpossibleException avec une valeur spécifique.
     *
     * @param valueOf la valeur qui a causé l'exception
     */
   public ValeurImpossibleException(final String valueOf) {
      super(valueOf);
   }
}
