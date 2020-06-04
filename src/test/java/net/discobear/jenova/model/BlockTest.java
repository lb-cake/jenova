package net.discobear.jenova.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlockTest {

  @Mock
  Block block;

  @Test
  @DisplayName("Genesis Block Test")
  public void generateNewGenesisBlockTest() {
    Block b = block.generate(null);
    assertEquals(b.getPreviousHash(), "0");
  }

  @Test
  @DisplayName("Generate New Block")
  public void generateNewBlockTest() {
    Block b1 = block.generate(null);
    Block b2 = block.generate(b1);
    assertEquals(b2.getId(), 2);
  }

  @Test
  @DisplayName("Validate Block")
  public void validateBlockTest() {
    Block genesis = block.generate(null);
    Block b1 = block.generate(genesis);
    assertTrue(genesis.validate("0"));
    assertFalse(genesis.validate("1"));
  }
}