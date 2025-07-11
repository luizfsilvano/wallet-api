package com.luizfsilvano.wallet.web.controller;

import com.luizfsilvano.wallet.domain.model.User;
import com.luizfsilvano.wallet.domain.model.Wallet;
import com.luizfsilvano.wallet.domain.repository.WalletRepository;
import com.luizfsilvano.wallet.domain.service.UserService;
import com.luizfsilvano.wallet.web.dto.CreateWalletDTO;
import com.luizfsilvano.wallet.web.dto.WalletDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {

    private final WalletRepository walletRepo;
    private final UserService userService;

    public WalletController(WalletRepository walletRepo, UserService userService) {
        this.walletRepo = walletRepo;
        this.userService = userService;
    }

    @PostMapping
    public WalletDTO createWallet(@Validated @RequestBody CreateWalletDTO dto, @AuthenticationPrincipal UserDetails ud) {
        User user = userService.findByUsername(ud.getUsername());

        Wallet w = new Wallet();
        w.setBalance(dto.getBalance());
        w.setUser(user);

        Wallet saved = walletRepo.save(w);
        return new WalletDTO(saved.getId(), saved.getBalance());
    }

    @GetMapping
    public List<WalletDTO> listWallets(@AuthenticationPrincipal UserDetails ud) {
        User user = userService.findByUsername(ud.getUsername());

        List<Wallet> wallets = walletRepo.findByUserId(user.getId());
        return wallets.stream()
                      .map(w -> new WalletDTO(w.getId(), w.getBalance()))
                      .toList();
    }
}