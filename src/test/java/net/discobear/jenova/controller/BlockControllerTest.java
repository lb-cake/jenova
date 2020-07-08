package net.discobear.jenova.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("BlockController should")
class BlockControllerTest {

  private BlockController controller;

  @Autowired
  BlockControllerTest(BlockController controller) {
    this.controller = controller;
  }

  @Test
  @DisplayName("not be null")
  public void contextLoads() {
    assertThat(controller).isNotNull();
  }

}