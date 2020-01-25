public class Main {

    public static void main(String[] args) {
        // Création denotre blockchain avec une difficulté de 4 "Proof of Work"
        Blockchain blockchain = new Blockchain(4);

        // On ajoute des blocks avec des données que l'on veut sécuriser
        blockchain.addBlock(blockchain.newBlock("Identifiant : antoinegouteix@orange.fr"));
        blockchain.addBlock(blockchain.newBlock("Mot de passe : 5Tgdhs!khd579Jdjdjf"));
        blockchain.addBlock(blockchain.newBlock("Nom et prénom : Gouteix Antoine"));

        //System.out.println(blockchain);

        // Ajout d'un block pour corrompre notre blockchain
        //blockchain.addBlock(new Block(15, System.currentTimeMillis(), "Block corrompu", "Block invalide"));

        // Affichage pour savoir si blockchain est valide ou pas ?
        System.out.println("La blockchain est valide ou pas ? " + blockchain.isBlockChainValid());

        // Visualiser la blockchain via sa représentation textuelle
        System.out.println(blockchain);
    }
}
