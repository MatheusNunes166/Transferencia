package com.Bancosesi.Transferencia.Controller;

import com.Bancosesi.Transferencia.Service.S_Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class C_Usuario {
    @GetMapping("/cadastro")
    public String getCadastro() {
        return "Usuario/cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrarUsuario(@RequestParam("nome") String nome,
                                   @RequestParam("email") String email,
                                   @RequestParam("cpf") String cpf,
                                   @RequestParam("cep") String cep,
                                   @RequestParam("estado") String estado) {
        S_Usuario.cadastrarUsuario(nome, email, cpf, cep, estado);
        return "Usuario/cadastro";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "index";
    }

    @PostMapping("/login")
    @ResponseBody
    public boolean postLogin(@RequestParam("cpf") String cpf,
                             @RequestParam("senha") String senha,
                             HttpSession session) {
        session.setAttribute("usuario", S_Usuario.verificaLogin(cpf, senha));
        if (session.getAttribute("usuario") == null) {
            return false;
        } else {
            return true;
        }
    }
}

