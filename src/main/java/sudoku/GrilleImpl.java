package sudoku;
import java.util.Set;

import exceptions.ElementInterditException;
import exceptions.HorsBornesException;
import exceptions.ValeurImpossibleException;
import exceptions.ValeurInitialeModificationException;

import java.util.HashSet;
import java.util.Iterator;

/**
* Implémentation de l'interface Grille.
*/
public class GrilleImpl implements Grille {
    private final int dimension;
    private boolean[][] lignesInitiales;
    private final Set<ElementDeGrille> alphabet;
    private ElementDeGrille[][] grille;

    /**
     * @param elementDeGrilles.
     * @param grilleTab
     */
    public GrilleImpl(final ElementDeGrille[] elementDeGrilles, final ElementDeGrille[][] grilleTab) {
        dimension = elementDeGrilles.length;
        this.alphabet = new HashSet<>();
        grille = grilleTab;
            for (int i = 0; i < elementDeGrilles.length; i++) {
            this.alphabet.add(elementDeGrilles[i]);
        }
        // on conserve les valeurs initiales
        setInitialesPositions();
    }
    
    @Override
    public Set<ElementDeGrille> getElements() {
        return this.alphabet;
    }

    @Override
    public int getDimension() {
       return this.dimension;
    }

    @Override
    public void setValue(final int x, final int y, final ElementDeGrille value) 
        throws HorsBornesException, ValeurImpossibleException, ElementInterditException, ValeurInitialeModificationException {
    	   if (x < 0 || x >= dimension || y < 0 || y >= dimension) {
    	        throw new HorsBornesException();
    	    }
    	if (isValeurInitiale(x, y)) {
            throw new ValeurInitialeModificationException();
        }
        if (!isJouable(x, y, value) || isValeurInitiale(x, y)) {
            throw new ValeurImpossibleException(value);}
        
       

        if (value != null && !isAlphabet(value)) {
            throw new ElementInterditException();
        }
        this.grille[x][y] = value;
    }

    @Override
    public ElementDeGrille getValue(final int x, final int y) throws HorsBornesException {
        if ((x > this.dimension) || (x < 0) || (y > this.dimension) || (y < 0)) {
            throw new HorsBornesException();
        }
        return this.grille[x][y];
    }

    @Override
    public boolean isComplete() {
        for (int i = 0; i < this.dimension; i++) {
            for (int j = 0; j < this.dimension; j++) {
                if (this.grille[i][j] == null) {   
                    return false;
                }
            }
        }
        return true;
    }

   @Override
public boolean isPossible(int x, int y, ElementDeGrille value) throws HorsBornesException, ValeurImpossibleException {
     if (x < 0 || x >= dimension || y < 0 || y >= dimension) {
    	        throw new HorsBornesException();
    	    }
    if (value == null && !isValeurInitiale(x, y)) {
        return true;
    }

    if (!isJouable(x, y, value) || isValeurInitiale(x, y)) {
        throw new ValeurImpossibleException(value);
    }

   

    return true;
}

    @Override
    public boolean isValeurInitiale(final int x, final int y) {
       return lignesInitiales[x][y];
    }

    private void setInitialesPositions() {
        lignesInitiales = new boolean[dimension][dimension];
        for (int i = 0; i < this.dimension; i++) {
            for (int j = 0; j < this.dimension; j++) {
                if (this.grille[i][j] != null) {
                    lignesInitiales[i][j] = true;
                } else {
                    lignesInitiales[i][j] = false;
                }
            }
        }
    }


    public boolean isAlphabet(final ElementDeGrille element) {
        Iterator<ElementDeGrille> iterator = this.alphabet.iterator();
        while (iterator.hasNext()) {
            if(iterator.next().equals(element)) {
                return true;
            }
        }
        return false;
    }

    public boolean isJouable(final int x, final int y, final ElementDeGrille element) {
        // Parcours de la ligne
        for (int i = 0; i < this.dimension; i++) {
            if (i != x && this.grille[i][y] != null && this.grille[i][y].equals(element)) {
                return false;
            }
        }

        // Parcours de la colonne
        for (int i = 0; i < this.dimension; i++) {
            if (i != y && this.grille[x][i] != null && this.grille[x][i].equals(element)) {
                return false;
            }
        }

        // Parcours de la sous-grille
        int dimensionCase = (int) Math.sqrt(this.dimension);
        int sousGrilleXDebut = x - (x % dimensionCase);
        int sousGrilleXFin = sousGrilleXDebut + dimensionCase;
        int sousGrilleYDebut = y - (y % dimensionCase);
        int sousGrilleYFin = sousGrilleYDebut + dimensionCase;

        for (int i = sousGrilleXDebut; i < sousGrilleXFin; i++) {
            for (int j = sousGrilleYDebut; j < sousGrilleYFin; j++) {
                if (i != x && j != y && this.grille[i][j] != null && this.grille[i][j].equals(element)) {
                    return false;
                }
            }
        }
        return true;
    }

}
