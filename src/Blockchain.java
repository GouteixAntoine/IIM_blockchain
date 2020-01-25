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
        Block firstBlock = blocks.get(0);

        if (firstBlock.getIndex() != 0){
            return false;
        }

        if (firstBlock.getPreviousHash() != null) {
            return false;
        }

        if (firstBlock.getHash() == null || !Block.calculateHash(firstBlock).equals(firstBlock.getHash())){
            return false;
        }
        return true;
    }

    // Méthode pour vérifier que le nouveau block est valide par rapport au block précédent.
    // Pour cela on va vérifier que l'index est égal à sa valeur -1, cohérence du previsousHash et du hash du block courant
    public boolean isValidNewBlock(Block newBlock, Block previousBlock) {
        if (newBlock != null && previousBlock != null) {
            if (previousBlock.getIndex() + 1 != newBlock.getIndex()) {
                return false;
            }

            if (newBlock.getPreviousHash() == null || !newBlock.getPreviousHash().equals(previousBlock.getHash())) {
                return false;
            }

            if (newBlock.getHash() == null || !Block.calculateHash(newBlock).equals(newBlock.getHash())) {
                return false;
            }

            return true;
        }
        return false;
    }

    // Méthode pour valider que notre blockchain est valide. Pour garder l'intégrité de notre blockchain
    // Pour cela tous les noeuds doivent être valide. On va itérer sur le contenu de chaque block de la blockchain et vérifier que le premier block est bien valide

    public boolean isBlockChainValid() {
        if (!isFirstBlockValid()) {
            return false;
        }

        for (int i = 1; i < blocks.size(); i++) {
            Block currentBlock = blocks.get(i);
            Block previousBlock = blocks.get(i - 1);

            if (!isValidNewBlock(currentBlock, previousBlock)) {
                return false;
            }
        }

        return true;
    }

    // On va surcharger la méthode toString de l'objet Blockchain pour pouvoir retourner sous forme de String le contenu de notre Blockchain
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (Block block : blocks) {
            builder.append(block).append("\n");
        }
        return builder.toString();
    }

}
