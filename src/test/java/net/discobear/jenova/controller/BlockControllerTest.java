package net.discobear.jenova.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlockControllerTest {

  private BlockController controller;

  @Autowired
  BlockControllerTest(BlockController controller) {
    this.controller = controller;
  }

  @Test
  public void contextLoads() {
    assertThat(controller).isNotNull();
  }

}