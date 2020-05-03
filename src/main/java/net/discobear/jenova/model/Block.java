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
  private long magicNumber;

  public Block() {

  }

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

    for (magicNumber = 0; magicNumber < Long.MAX_VALUE; magicNumber++) {
      hash = generateHash();
      if (hash.startsWith(hashBegin)) break;
    }
    this.hashOfTheBlock = hash;
    this.generationTime = Duration.between(start, LocalDateTime.now()).toSeconds();
  }

  public boolean validate(String previousHash) {
    if (!this.previousHash.equals(previousHash)) return false;
    if (!generateHash().equals(this.hashOfTheBlock)) return false;
    return true;
  }

  public long getId() {
    return id;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public String getPreviousHash() {
    return previousHash;
  }

  public String getHashOfTheBlock() {
    return hashOfTheBlock;
  }

  @Override
  public String toString() {
    return "Block{" +
        "id=" + id +
        ", timestamp=" + timestamp +
        ", previousHash='" + previousHash + '\'' +
        ", hashOfTheBlock='" + hashOfTheBlock + '\'' +
        ", generationTime=" + generationTime +
        ", magicNumber=" + magicNumber +
        '}';
  }

  private String generateHash() {
    return StringUtil.applySha256(id + " " + timestamp + " " + previousHash + " " + magicNumber);
  }
}
