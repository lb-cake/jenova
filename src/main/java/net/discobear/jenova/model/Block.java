package net.discobear.jenova.model;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import net.discobear.jenova.util.StringUtil;

@Entity
public class Block implements Serializable {

  private final static long serialVersionUID = 2L;

  @Id
  private long id;
  private long timestamp;
  private String previousHash;
  private String hashOfTheBlock;
  private long generationTime;
  private int noonce;

  static Block generate(Block prevBlock) {
    if (prevBlock == null) {
      return new Block(1, "0");
    }
    return new Block(prevBlock.id + 1, prevBlock.hashOfTheBlock);
  }

  public Block(long id, String previousHash) {
    this.id = id;
    this.timestamp = new Date().getTime();
    this.previousHash = previousHash;

    String hash = "";
    String hashBegin = "0".repeat(Blockchain.getZerosQty());
    LocalDateTime start = LocalDateTime.now();

    this.hashOfTheBlock = hash;
    this.generationTime = Duration.between(start, LocalDateTime.now()).toSeconds();
  }

  public boolean validate(String previousHash) {
    if (!this.previousHash.equals(previousHash)) return false;
    if (!generateHash().equals(this.hashOfTheBlock)) return false;
    return true;
  }

  public void mineBlock(int difficulty) {
    String target = new String(new char[difficulty]).replace('\0', '0'); // Create a string with difficulty * "0"
    while (!hashOfTheBlock.substring(0, difficulty).equals(target)) {
      noonce++;
      hashOfTheBlock = generateHash();
    }
  }

  public long getId() {
    return id;
  }

  public String getPreviousHash() {
    return previousHash;
  }

  public String getHashOfTheBlock() {
    return hashOfTheBlock;
  }
  
  private String generateHash() {
    return StringUtil.applySha256(id + " " + timestamp + " " + previousHash);
  }
}
