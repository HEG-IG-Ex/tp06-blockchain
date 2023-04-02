import domaine.Block;
import metier.Blockchain;
import outils.BlockGenerator;
import outils.InvalidIdException;

import java.util.List;

// inspirations :
//  https://www.geeksforgeeks.org/implementing-a-linked-list-in-java-using-class/
//  https://www.geeksforgeeks.org/reverse-a-linked-list/
public class Main {

    public static void main(String[] args) {
        List<Block> blocks = BlockGenerator.generateBlocks();

        // a) Créer une blockchain (avec le bloc 0)
        Blockchain bc = new Blockchain(blocks.get(0));
        // b) Insérer tous les blocks (1 à n-1) dans la blockchain
        for (int i = 0; i < blocks.size()-1; i++) {
            try{
                bc.insert(i,blocks.get(i+1));
            } catch(InvalidIdException iie){
                System.err.println(iie.getStackTrace());
            }
        }
        // c) Afficher la blockchain
        System.out.println(bc.toString());

        // d) Rechercher un bloc en utilisant search et get
        System.out.println(bc.search(2).toString());
        System.out.println(bc.get(0).toString());

    }
}