package net.discobear.jenova.controller;

import javax.websocket.server.PathParam;
import net.discobear.jenova.model.Block;
import net.discobear.jenova.repository.BlockRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BlockController {

  private BlockRepository blockRepository;

  public BlockController(BlockRepository blockRepository) {
    this.blockRepository = blockRepository;
  }

  @GetMapping(value = "/api/blocks/{id}")
  public @ResponseBody Block getBlock(@PathVariable Long id) {
    return blockRepository.getOne(id);
  }

}
