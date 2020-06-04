package net.discobear.jenova.controller;

import javax.websocket.server.PathParam;
import net.discobear.jenova.model.Block;
import net.discobear.jenova.repository.BlockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BlockController {

  private Logger LOG = LoggerFactory.getLogger(BlockController.class);
  private BlockRepository blockRepository;

  @Autowired
  public BlockController(BlockRepository blockRepository) {
    this.blockRepository = blockRepository;
  }

  @GetMapping(value = "/api/blocks/{id}")
  public @ResponseBody Block getBlock(@PathVariable Long id) {
    LOG.info("Block Id: {}", id);
    return blockRepository.getOne(id);
  }

}
