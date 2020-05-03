package net.discobear.jenova.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
public class Blockchain implements Serializable {

  private final static Logger LOG = LoggerFactory.getLogger(Blockchain.class);

  @Id
  private Long id;

  final static String FILENAME = "blockchain.dat";
  private static int zerosQty;
  final static long serialVersionUID = 1L;

  private ArrayList<Block> blocks;

  public Blockchain() {}

  public static Blockchain get(int zerosQty) {
    Blockchain.zerosQty = zerosQty;

    try {
      var bc = Blockchain.loadFromDisk();

      if (!bc.validate()) throw new RuntimeException("Can not validate blockchain");
      return bc;
    } catch (Exception e) {
      return new Blockchain(new ArrayList<>());
    }
  }

  public static Blockchain loadFromDisk() throws Exception {
    File file = new File(FILENAME);
    try (FileInputStream fis = new FileInputStream(file)) {
      ObjectInputStream ois = new ObjectInputStream(fis);
      return (Blockchain) ois.readObject();
    }
  }

  public Block generateNewBlock() {
    Block newBlock = Block.generate(getLastBlock());
    blocks.add(newBlock);
    saveBlockchain();
    return newBlock;
  }

  public boolean validate() {
    String prevHash = "0";
    for (var block : blocks) {
      if (!block.validate(prevHash)) return false;
      prevHash = block.getHashOfTheBlock();
    }
    return true;
  }

  public static int getZerosQty() {
    return zerosQty;
  }

  private void saveBlockchain() {
    File file = new File(FILENAME);
    try {
      try (var fos = new FileOutputStream(file, false)) {
          var oos = new ObjectOutputStream(fos);
          oos.writeObject(this);
      }
    } catch (Exception e) {
      LOG.error("Can not save file " + FILENAME, e);
      e.printStackTrace();
    }
  }

  private Block getLastBlock() {
    return blocks.size() == 0 ? null : blocks.get(blocks.size() - 1);
  }

  private Blockchain(ArrayList<Block> blocks) {
    this.blocks = blocks;
  }


}
