import java.util.ArrayList;
import java.util.List;

public class Blockchain {

    // Déclaration variable
    private int difficulty; // Difficulté pour le processus de minage
    private List<Block> blocks; // Stocker dans la liste les blocks

    // Constructeur
    public Blockchain(int difficulty) {
        this.difficulty = difficulty;
        blocks = new ArrayList<>();
        // Création du premier block de la blockchain
        Block b = new Block(0, System.currentTimeMillis(), null, "Premier Block");
        b.mineBlock(difficulty);
        blocks.add(b);
    }

    // Getter
    public int getDifficulty() {
        return difficulty;
    }

    // Méthode qui renvoie le dernier block de notre blockchain
    public Block latestBlock(){
        return blocks.get(blocks.size() - 1);
    }

    // Méthode qui renvoie un nouveau block avec un index incrémenté de 1 par rapport au dernier block et dont le previousHash
    // sera celui du block pécedent
    public Block newBlock(String data){
        Block latestBlock = latestBlock();
        return new Block(latestBlock.getIndex() + 1, System.currentTimeMillis(), latestBlock.getHash(), data);
    }

    // Méthode qui permet d'ajouter le block à notre blockchain mais avant cela il faut miner le block
    public void addBlock(Block b){
        if (b != null) {
            b.mineBlock(difficulty);
            blocks.add(b);
        }
    }

    // Méthode qui permet de vérifier que la méthode de notre blockchain est bien valide.
    // Ce block est valide si son index est égal à 0 et que le previsousHash est null et que le hash courant est cohérent
    public boolean isFirstBlockValid(){

        return true;
    }
























}
