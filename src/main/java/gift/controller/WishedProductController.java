package gift.controller;

import gift.argumentresolver.LoginMember;
import gift.dto.MemberDTO;
import gift.dto.WishedProductDTO;
import gift.service.WishedProductService;
import jakarta.validation.Valid;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wishes")
public class WishedProductController {

    private final WishedProductService wishedProductService;

    @Autowired
    public WishedProductController(WishedProductService wishedProductService) {
        this.wishedProductService = wishedProductService;
    }

    @GetMapping
    public ResponseEntity<Collection<WishedProductDTO>> getWishedProducts(@LoginMember MemberDTO memberDTO) {
        return ResponseEntity.ok().body(wishedProductService.findAll(memberDTO.email()));
    }

    @PostMapping
    public ResponseEntity<WishedProductDTO> addWishedProduct(@LoginMember MemberDTO memberDTO, @Valid @RequestBody WishedProductDTO wishedProductDTO) {
        return ResponseEntity.ok().body(wishedProductService.save(memberDTO.email(), wishedProductDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<WishedProductDTO> deleteWishedProduct(@PathVariable("id") long id, @LoginMember MemberDTO memberDTO) {
        return ResponseEntity.ok().body(wishedProductService.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WishedProductDTO> updateWishedProduct(@PathVariable("id") long id, @LoginMember MemberDTO memberDTO, @Valid @RequestBody WishedProductDTO wishedProductDTO) {
        return ResponseEntity.ok().body(wishedProductService.update(id, memberDTO.email(), wishedProductDTO));
    }
}
