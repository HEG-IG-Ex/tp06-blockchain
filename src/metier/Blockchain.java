package metier;

import domaine.Block;
import outils.InvalidIdException;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    private Block head;
    private List<Block> allBlocks = new ArrayList<>();

    /**
     * Constructeur de la blockchain
     * a) Le block reçu contient déjà le timestamp et les data, prev est à null
     * b) Définir l'id
     * c) Mettre à jour head
     * @param genesis - premier block de la blockchain.
     */
    public Blockchain(Block genesis) {
        genesis.setId(0);
        allBlocks.add(genesis);
        this.head = genesis;
    }

    /**
     * Ajouter un block à la blockchain
     * a) Vérifier que l'id reçu est le même que l'id de head
     * b) si pas le même, lancer InvalidIdException
     * c) Définir les attributs manquants dans le block (id, idPrev, prev)
     * d) Mettre à jour head
     * @param id identifiant du block précédent au block à ajouter
     * @param block instance de Block qui doit être ajoutée à la blockchain
     * @return le block modifié
     */
    public Block insert(int id, Block block) throws InvalidIdException {
       if(id != head.getId()){ throw new InvalidIdException(); }
       block.setPrev(head);
       block.setIdPrev(id);
       block.setId(++id);
       head = block;
       allBlocks.add(block);
       return block;
    }

    /**
     * Rechercher un block de la chaîne
     * a) objectif: retourner le block dont l’id est la valeur reçue en paramètre
     * @param id identifiant du block
     * @return le block trouvé
     */
    public Block search(int id) {

        Block current = head;
        while(current != null){
            if(current.getId() == id){
                return current;
            }else {
                current = current.getPrev();
            }
        }
        return null;
    }

    /**
     * Retourner un block de la chaîne en ordre constant O(1)
     * a) Objectif: retourner tousLesBlocks.get(i) en ordre O(1)
     * b) Par conséquent, on a besoin d'avoir un accès direct à tousLesBlocks
     * c) déclarer un attribut tousLesBlocks, et le remplir avec chaque block
     * d) et donc ici, dans get ==> Retourner tousLesBlocks.get(i) en ordre O(1)
     * @param id identifiant du block
     * @return le block trouvé
     */
    public Block get(int id) {
        return allBlocks.get(id);
    }

    /**
     * Algorithme pour calculer un id d'un block
     * @param block instance Block pour laquelle il faut calculer l'identifiant
     * @return l'identifiant calculé pour le bloc
     */
    private int hashBlockId(Block block) {
        return block.getHashBlock();
    }

    /**
     * a) Version 1 : Retourner un String contenant l'id de tous les blocks
     * b) Version 2 : Avoir une std permettant de les retourner dans l’ordre (block0, block1, block2, …)
     */
    @Override
    public String toString() {
        Block b = this.head;
        String output = "";
        System.out.print("Blockchain: ");


        // Version 2 - Traverse through the blockchain
        while (b != null) {
            // Print the data at current block
            output =  b.toString() + " <= " + output;
            // Go to prev block
            b = b.getPrev();
        }

        return (output.substring(0, output.length() - 4));
    }
}