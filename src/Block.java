import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class Block {

    private int index;
    private long timestamp; // Stocker une information sur la date de création
    private String hash; // Assurer l'intégrité des données du bloc en cours
    private String previousHash; // Permettant de lié le block courant et préceent dans la chaine de bloc
    private String data; // Des données
    private int nonce; // Servira durant la phase de minage

    // Constructeur
    public Block(int index, long timestamp, String previousHash, String data) {
        this.index = index;
        this.timestamp = timestamp;
        this.previousHash = previousHash;
        this.data = data;
        nonce = 0;
        hash = Block.calculateHash(this); // Calculer le premier hash du bloc
    }

    // Getter accessible en lecture pas en écriture blockchain non modifiable
    public int getIndex() {
        return index;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public String getData() {
        return data;
    }

    // Methode pour récupérer les informations de type String sauf le hash qui est en cours de calcul
    public String str() {
        return index + timestamp + previousHash + data + nonce;
    }

    // Pour visualiser plus facilement le contenu d'un bloc visuel en mode texte du bloc
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Block #").append(index).append(" [previousHash : ").append(previousHash).append(", ").
                append("timestamp : ").append(new Date(timestamp)).append(", ").append("data : ").append(data).append(", ").
                append("hash : ").append(hash).append("]");
        return builder.toString();
    }

    // Methode pour calculer le hash avec SHA 256
    public static String calculateHash(Block block) {
        if (block != null) {
            MessageDigest digest = null;
            try {
               digest = MessageDigest.getInstance("SHA-256"); // Implémentation Algo SHA-256
            } catch (NoSuchAlgorithmException e) {
                return null;
            }

            String txt = block.str(); // Récupération des informations du bloc sous forme de chaine de caractère
            final byte bytes[] = digest.digest(txt.getBytes()); // Récupération du hash sous forme de byte
            final StringBuilder builder = new StringBuilder();

            for (final byte b : bytes) {
                String hex = Integer.toHexString(0xff & b);

                if (hex.length() == 1) {
                    builder.append('0');
                }

                builder.append(hex);
            }

            return builder.toString();
        }

        return null;
    }

    // Methode qui nous permettre de miner le block en résolvant la preuve de travail, nonce stock le nombre d'éssai avant de réussir à valider le block courant
    // La difficulté déterminera le nombre de zéro que comportera le block courant
    // Tant que le hash calculé est différent du hash attendu, on continue de calculer
    // Quand la preuve de travail est résolu et la valeur hash correctement mise sur le block courant

    public void mineBlock(int difficulty) {
        nonce = 0;

        while (!getHash().substring(0, difficulty).equals(Utils.zeros(difficulty))) {
            nonce ++;
            hash = Block.calculateHash(this);
        }

    }

}

